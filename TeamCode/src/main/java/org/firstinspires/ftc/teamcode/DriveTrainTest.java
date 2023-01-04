package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


    @TeleOp(name = "DriveTrain Test", group="Linear Opmode")

    public class DriveTrainTest extends LinearOpMode {

        DcMotor moveleftFrontMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        DcMotor moveleftBackMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        DcMotor moverightFrontMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        DcMotor moverightBackMotor = hardwareMap.get(DcMotor.class, "backRightMotor");

        @Override
        public void runOpMode() {

            telemetry.addData("Status", "Initialized");
            telemetry.update();
            // Wait for the game to start (driver presses PLAY)

            waitForStart();

            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                //front back
                moveleftFrontMotor.setPower(gamepad1.left_stick_y);
                moveleftBackMotor.setPower((gamepad1.left_stick_y) * -1);
                moverightFrontMotor.setPower((gamepad1.left_stick_y) * -1);
                moverightBackMotor.setPower((gamepad1.left_stick_y) * -1);
                //turning
                moveleftFrontMotor.setPower(gamepad1.right_stick_y);
                moveleftBackMotor.setPower((gamepad1.right_stick_y) * -1);
                moverightFrontMotor.setPower(gamepad1.right_stick_y);
                moverightBackMotor.setPower(gamepad1.right_stick_y);
                //strafing
                moveleftFrontMotor.setPower((gamepad1.left_stick_x) * -1);
                moveleftBackMotor.setPower((gamepad1.left_stick_x) * -1);
                moverightFrontMotor.setPower((gamepad1.left_stick_x) * -1);
                moverightBackMotor.setPower(gamepad1.left_stick_x);
                telemetry.addData("Status", "Running");
                telemetry.update();


            }
        }
    }
