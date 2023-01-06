package org.firstinspires.ftc.teamcode.Framework;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.framework.Motors;

public abstract class BaseOpMode extends LinearOpMode {
        protected DcMotorEx slideLeft;
        protected DcMotorEx slideRight;
        protected Slides slide;
        protected DcMotor[] motors;
        protected void initHardware() {
        DcMotor[] motors = new DcMotor[]{
                hardwareMap.dcMotor.get("motor fr"),
                hardwareMap.dcMotor.get("motor br"),
                hardwareMap.dcMotor.get("motor fl"),
                hardwareMap.dcMotor.get("motor bl")
        };
        slideLeft= (DcMotorEx) hardwareMap.dcMotor.get("leftSlide");


        slideRight= (DcMotorEx) hardwareMap.dcMotor.get("rightSlide");
        Servo clawServo = hardwareMap.servo.get("servo");

        Slides slide = new Slides(slideLeft, slideRight);


    }



}
