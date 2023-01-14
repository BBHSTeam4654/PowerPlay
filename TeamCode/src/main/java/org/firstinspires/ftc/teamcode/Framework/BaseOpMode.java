package org.firstinspires.ftc.teamcode.Framework;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

public abstract class BaseOpMode extends LinearOpMode {
        protected DcMotorEx slideLeft;
        protected DcMotorEx slideRight;
        protected Slides slides;
        protected RevBlinkinLedDriver blinker;


        protected Claws claw;
        protected DcMotor[] motors;
        protected DcMotor leftEncoder, rightEncoder, frontEncoder;

        protected void initHardware() {

        motors = new DcMotor[]{

                hardwareMap.dcMotor.get("motor fr"),
                hardwareMap.dcMotor.get("motor br"),
                hardwareMap.dcMotor.get("motor fl"),
                hardwareMap.dcMotor.get("motor bl")
        };

        motors[0].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[1].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[2].setDirection(DcMotorSimple.Direction.FORWARD);
        motors[3].setDirection(DcMotorSimple.Direction.FORWARD);


        
        slideLeft = (DcMotorEx) hardwareMap.dcMotor.get("leftSlide");


        slideRight = (DcMotorEx) hardwareMap.dcMotor.get("rightSlide");
        slides = new Slides(slideLeft, slideRight);


        claw = new Claws(hardwareMap.servo.get("servo"));
        claw.clawsOpen();

        leftEncoder = hardwareMap.dcMotor.get("motor fl");
        rightEncoder = hardwareMap.dcMotor.get("motor bl");
        frontEncoder = hardwareMap.dcMotor.get("motor fr");


        //blinker = hardwareMap.get(RevBlinkinLedDriver.class, "blinker");
        //blinker.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_OCEAN_PALETTE);

    }



}
