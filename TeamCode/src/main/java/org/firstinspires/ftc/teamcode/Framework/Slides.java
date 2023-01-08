package org.firstinspires.ftc.teamcode.Framework;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Slides{

    static DcMotorEx leftSlide;
    static DcMotorEx rightSlide;
    static double target;

    public Slides(DcMotorEx leftSlide, DcMotorEx rightSlide){
        this.leftSlide = leftSlide;
        this.rightSlide = rightSlide;
        leftSlide.setZeroPowerBehavior(BRAKE);
        rightSlide.setZeroPowerBehavior(BRAKE);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int left_position = leftSlide.getCurrentPosition();
        int right_position = rightSlide.getCurrentPosition();
        telemetry.addData("EncoderLeft", leftSlide.getCurrentPosition());
        this.target=0;
        telemetry.addData("EncoderRight", rightSlide.getCurrentPosition());
        telemetry.addData("Target", target);
        telemetry.update();
    }

    public static void high(){
        target = -4000;
    }
    public static void mid(){
        target = -2000;
    }
    public static void low(){
        target = -1000;
    }
    public static void reset(){
        target = 0;
    }
    //For slide positions in auto like cups
    public static void slidesPosition(int x){
        leftSlide.setTargetPosition(x);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setVelocity(1000);
        rightSlide.setTargetPosition(x);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setVelocity(1000);
    }
    public static void manual(){
        target += gamepad2.left_stick_y*1.5;
    }

    public static void pLoop(){

        double leftPosition = (double)(leftSlide.getCurrentPosition());

        double left_current_error = target-leftPosition;
        double lkp = 0.016;
        double lp = lkp * left_current_error;

        double rightPosition = (double)(leftSlide.getCurrentPosition());

        double right_current_error = target-rightPosition;
        double rkp = 0.016;
        double rp = rkp * right_current_error;
        leftSlide.setPower(lp);
        rightSlide.setPower(rp);
        if(target>0){
            target = 0;
        }
    }


    // Reset the encoder during initialization

}