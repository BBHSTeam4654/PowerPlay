package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "MeasureSlides")
public class MeasureSlides extends LinearOpMode {

    public void runOpMode() throws InterruptedException {

        //simple just moving slides up and down and seeing direction

        waitForStart();

        DcMotorEx frontLeftMotor = null;
        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (opModeIsActive()) {
            frontLeftMotor.setPower(gamepad1.left_stick_y);
            telemetry.addData("Encoder", frontLeftMotor.getCurrentPosition());
            telemetry.update();
        }
    }
}
