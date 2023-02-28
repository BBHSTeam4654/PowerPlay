package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Framework.Slides;

@TeleOp(name = "NewSlidesTest")
public class NewSlidesTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx slideLeft;
        DcMotorEx slideRight;
        Slides slides;

        slideLeft = (DcMotorEx) hardwareMap.dcMotor.get("leftSlide");


        slideRight = (DcMotorEx) hardwareMap.dcMotor.get("rightSlide");
        slides = new Slides(slideLeft, slideRight);

        waitForStart();



        while (opModeIsActive()) {

            slides.manual(gamepad2.left_stick_y);

            if (gamepad2.a) {
                slides.high();
                //do auto precision mode
            }

            if (gamepad2.b && gamepad2.right_trigger==0) {
                slides.mid();
            }

            if (gamepad2.y) {
                slides.low();
            }
            if (gamepad2.x) {
                slides.reset();
            }

            if (gamepad2.dpad_down) {
                slides.fiveCups();
            }
            if (gamepad2.dpad_right) {
                slides.fourCups();
            }
            if (gamepad2.dpad_up) {
                slides.threeCups();
            }
            if (gamepad2.dpad_left) {
                slides.twoCups();
            }
            if(gamepad2.left_stick_button){
                slides.override();
            }
            if(gamepad2.right_stick_button){
                slides.overrideReset();
            }

            slides.pLoop();
        }

    }
}
