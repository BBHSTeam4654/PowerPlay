package org.firstinspires.ftc.teamcode.Framework;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;


public abstract class BaseOpMode extends LinearOpMode {
        protected DcMotorEx slideLeft;
        protected DcMotorEx slideRight;
        protected Slides slides;



        //protected Claws claw;
        //protected DcMotor[] motors;

        protected void initHardware() {
        /*
        motors = new DcMotor[]{

                hardwareMap.dcMotor.get("motor fr"),
                hardwareMap.dcMotor.get("motor br"),
                hardwareMap.dcMotor.get("motor fl"),
                hardwareMap.dcMotor.get("motor bl")
        };

        motors[Motors.FR].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[Motors.FL].setDirection(DcMotorSimple.Direction.FORWARD);
        motors[Motors.BL].setDirection(DcMotorSimple.Direction.FORWARD);
        motors[Motors.BR].setDirection(DcMotorSimple.Direction.REVERSE);
        */

        
        slideLeft = (DcMotorEx) hardwareMap.dcMotor.get("leftSlide");


        slideRight = (DcMotorEx) hardwareMap.dcMotor.get("rightSlide");
        slides = new Slides(slideLeft, slideRight);


        //claw = new Claws(hardwareMap.servo.get("servo"));
        //claw.clawsOpen();



    }



}
