package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.Framework.Slides;

@TeleOp(name = "Slides Test3")
public class SlidesTest3 extends LinearOpMode {
    DcMotorEx slideLeft = hardwareMap.get(DcMotorEx.class, "leftSlide");

    DcMotorEx slideRight = hardwareMap.get(DcMotorEx.class, "rightSlide");
    Slides slide2 = new Slides(slideLeft, slideRight);
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        while(opModeIsActive()){
            if (gamepad1.left_stick_y != 0) {
                slide2.manual();
            }

            if (gamepad1.a) {
                slide2.high();
            }

            if (gamepad1.b) {
                slide2.mid();
            }

            if (gamepad1.y) {
                slide2.low();
            }
            if (gamepad1.x) {
                slide2.reset();
            }
            slide2.pLoop();




        }

    }
}

