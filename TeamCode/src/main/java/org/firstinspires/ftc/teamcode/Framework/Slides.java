package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Slides{

    DcMotor leftSlide, rightSlide;

    public Slides(DcMotor leftSlide, DcMotor rightSlide){
        this.leftSlide = leftSlide;
        this.rightSlide = rightSlide;
        leftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    // Reset the encoder during initialization
    public void setBaseline() {
        for (DcMotor motor: motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  
        }
    }

    public void moveSlidesUp(){
        leftSlide.setPower(gamepad2.right_trigger);
        rightSlide.setPower(gamepad2.right_trigger);
    }

    public void moveSlidesDown(){
        leftSlide.setPower(gamepad2.left_trigger);
        rightSlide.setPower(gamepad2.left_trigger);
    }

}