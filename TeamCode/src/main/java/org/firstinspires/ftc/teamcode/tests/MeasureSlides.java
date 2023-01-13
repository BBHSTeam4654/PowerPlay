package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "MeasureSlides")
public class MeasureSlides extends LinearOpMode {

    public void runOpMode() throws InterruptedException {

        //moves slides up and down using just a P loop

        DcMotorEx frontLeftMotor = null;
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double target = 0;
        double position = (double) (frontLeftMotor.getCurrentPosition());
        double current_error = target-position;
        double kP = 0.0016;
        double output = current_error*kP;

        waitForStart();

        while (opModeIsActive()) {
            target += gamepad1.left_stick_y*1.5;


            position = (double) (frontLeftMotor.getCurrentPosition());
            current_error = target-position;
            output = current_error*kP;
            frontLeftMotor.setPower(output);

            telemetry.addData("Encoder", frontLeftMotor.getCurrentPosition());
            telemetry.addData("Target", target);
            telemetry.update();
        }
    }
}
