package org.firstinspires.ftc.teamcode.Framework;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Slides{

    static DcMotorEx leftSlide;
    static DcMotorEx rightSlide;
    static double leftTarget;
    static double rightTarget;
    static double lkp = 0.016;
    static double rkp = 0.016;

    public Slides(DcMotorEx leftSlide, DcMotorEx rightSlide){
        this.leftSlide = leftSlide;
        this.rightSlide = rightSlide;
        leftSlide.setZeroPowerBehavior(BRAKE);
        rightSlide.setZeroPowerBehavior(BRAKE);
        
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        int left_position = leftSlide.getCurrentPosition();
        int right_position = rightSlide.getCurrentPosition();

        this.leftTarget=0;
        this.rightTarget=0;
/*
        telemetry.addData("EncoderLeft", left_position);
        telemetry.addData("EncoderRight", right_position);
        telemetry.addData("leftTarget", leftTarget);
        telemetry.update();
        */
    }

    public static void high(){
        leftTarget = -4000;
        rightTarget = 4000;
    }
    public static void mid(){
        leftTarget = -2000;
        rightTarget = 2000;
    }
    public static void low(){
        leftTarget = -1000;
        rightTarget = 1000;
    }
    public static void reset(){
        leftTarget = 0;
        rightTarget = 0;
    }
    //For slide positions in auto like cups
    public static void slidesPosition(int x){
        //DO NOT USE
        leftSlide.setTargetPosition(x);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setVelocity(1000);
        rightSlide.setTargetPosition(x);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setVelocity(1000);
    }
    public void manual(float num){
        this.leftTarget -= (double)num*5;
        this.rightTarget += (double)num*5;
    }

    public void pLoop(){
//one of them is reversed... idk which one so use MeasureSlides to determine, 
// whichever one has encoders going backwards is left, whichever has positive is right
        double leftPosition = (double)(this.leftSlide.getCurrentPosition());

        //worried about this... check it out tomorrow
        double left_current_error = leftTarget-leftPosition;

        double lp = lkp * left_current_error;

        double rightPosition = (double)(this.rightSlide.getCurrentPosition());

        double right_current_error = rightTarget-rightPosition;

        double rp = rkp * right_current_error;
        
        this.leftSlide.setPower(lp);
        this.rightSlide.setPower(rp);
        
        if(leftTarget>0){
            leftTarget = 0;
        }
        if(rightTarget<0){
            rightTarget = 0;
        }
    }
    
}