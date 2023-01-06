package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Slides Test2")
public class SlidesTest2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        DcMotorEx frontLeftMotor = null;

        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();

        while (opModeIsActive()) {
            int position = frontLeftMotor.getCurrentPosition();
            telemetry.addData("Encoder", frontLeftMotor.getCurrentPosition());
            telemetry.update();

            if (gamepad1.left_stick_y != 0) {


            while (gamepad1.left_stick_y != 0) {

                frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                frontLeftMotor.setPower(gamepad1.left_stick_y);
            }
            else if (gamepad1.a) {
                frontLeftMotor.setTargetPosition(-4000);
                frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontLeftMotor.setVelocity(1000);
            }
            else if (gamepad1.b) {
                frontLeftMotor.setTargetPosition(-2000);
                frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontLeftMotor.setVelocity(1000);
            }
            else if (gamepad1.x) {
                frontLeftMotor.setTargetPosition(0);
                frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontLeftMotor.setVelocity(1000);
            }
            else{
                frontLeftMotor.setPower(0);
            }


        }

    }
}