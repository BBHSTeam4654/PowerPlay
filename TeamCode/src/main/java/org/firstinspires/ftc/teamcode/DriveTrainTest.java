package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;


@TeleOp(name = "DriveTrainTest")

    public class DriveTrainTest extends LinearOpMode {

        DcMotor moveleftFrontMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        DcMotor moveleftBackMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        DcMotor moverightFrontMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        DcMotor moverightBackMotor = hardwareMap.get(DcMotor.class, "backRightMotor");

        protected static enum DriveMode {
            TANK,
            TURN,
            MECANUM,
        }

    protected DriveMode driveMode = DriveMode.MECANUM;


    @Override
        public void runOpMode() {

            telemetry.addData("Status", "Initialized");
            telemetry.update();
            // Wait for the game to start (driver presses PLAY)

            waitForStart();

            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {

                if (gamepad1.dpad_left) {
                    driveMode = DriveMode.TANK;
                } else if (gamepad1.dpad_up) {
                    driveMode = DriveMode.MECANUM;
                } else if (gamepad1.dpad_right) {
                    driveMode = DriveMode.TURN;
                }
                //percision
                double mult = gamepad1.left_bumper ? 0.35 : gamepad1.right_bumper ? 0.7 : 1.0;

                switch (driveMode){
                    case TANK:{
                        //front back
                        moveleftFrontMotor.setPower(gamepad1.left_stick_y);
                        moveleftBackMotor.setPower(-gamepad1.left_stick_y);
                        moverightFrontMotor.setPower(-gamepad1.left_stick_y);
                        moverightBackMotor.setPower(-gamepad1.left_stick_y);

                    }
                    case TURN:{
                        //turning
                        moveleftFrontMotor.setPower(gamepad1.right_stick_y);
                        moveleftBackMotor.setPower(-gamepad1.right_stick_y);
                        moverightFrontMotor.setPower(gamepad1.right_stick_y);
                        moverightBackMotor.setPower(gamepad1.right_stick_y);
                    }
                    case MECANUM:{
                        //strafing
                        moveleftFrontMotor.setPower(-gamepad1.left_stick_x);
                        moveleftBackMotor.setPower(-gamepad1.left_stick_x);
                        moverightFrontMotor.setPower(-gamepad1.left_stick_x);
                        moverightBackMotor.setPower(gamepad1.left_stick_x);

                    }
                    telemetry.addData("Status", "Running");
                    telemetry.update();

                }


            }
        }
    }
