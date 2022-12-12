package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Slides{

    DcMotor leftSlide, rightSlide;

    public Slides(DcMotor leftSlide, DcMotor rightSlide){
        this.leftSlide = leftSlide;
        this.rightSlide = rightSlide;

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