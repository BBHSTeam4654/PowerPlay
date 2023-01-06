package org.firstinspires.ftc.teamcode.Framework;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Slides{

    static DcMotorEx leftSlide;
    static DcMotorEx rightSlide;

    public Slides(DcMotorEx leftSlide, DcMotorEx rightSlide){
        this.leftSlide = leftSlide;
        this.rightSlide = rightSlide;
        leftSlide.setZeroPowerBehavior(BRAKE);
        rightSlide.setZeroPowerBehavior(BRAKE);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int leftPosition = leftSlide.getCurrentPosition();
        int rightPosition = rightSlide.getCurrentPosition();
        telemetry.addData("EncoderLeft", leftSlide.getCurrentPosition());
        telemetry.addData("EncoderRight", rightSlide.getCurrentPosition());
        telemetry.update();
    }
    public static void top(){
        leftSlide.setTargetPosition(-4000);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setVelocity(1000);
        rightSlide.setTargetPosition(-4000);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setVelocity(1000);
    }
    public static void mid(){
        leftSlide.setTargetPosition(-2000);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setVelocity(1000);
        rightSlide.setTargetPosition(-2000);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setVelocity(1000);
    }
    public static void reset(){
        leftSlide.setTargetPosition(0);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setVelocity(1000);
        rightSlide.setTargetPosition(0);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setVelocity(1000);
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
        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftSlide.setPower(gamepad1.left_stick_y);
        rightSlide.setPower(gamepad1.left_stick_y);
    }


    // Reset the encoder during initialization
    

}