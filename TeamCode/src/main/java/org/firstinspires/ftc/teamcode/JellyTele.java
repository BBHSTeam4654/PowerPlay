package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
import org.firstinspires.ftc.teamcode.Framework.Slides;
import org.firstinspires.ftc.teamcode.Framework.Claws;
import org.firstinspires.ftc.teamcode.misc.*;

@TeleOp(name = "Power Play JellyTele")

public class JellyTele extends BaseOpMode {
    protected static enum DriveMode {
        TANK,
        DRIVE,
        MECANUM,
    }

    protected DriveMode driveMode = DriveMode.MECANUM;

    public void runOpMode() throws InterruptedException {

        initHardware();
        waitForStart();

        while (opModeIsActive()) {

            // Slides
            /*
             * if (gamepad2.left_stick_y != 0) {
             * slide.manual();
             * }
             *
             * if (gamepad2.a) {
             * slide.high();
             * }
             *
             * if (gamepad2.b) {
             * slide.mid();
             * }
             *
             * if (gamepad2.y){
             * slide.low();
             * }
             * if (gamepad2.x) {
             * slide.reset();
             * }
             * slide.pLoop();
             */
            // CLAW
            if (gamepad2.left_bumper) {
                claw.clawsOpen();
            }
            if (gamepad2.right_bumper) {
                claw.clawsClose();
            }

            // DRIVETRAIN
            if (gamepad1.dpad_left) {
                driveMode = DriveMode.TANK;
            } else if (gamepad1.dpad_up) {
                driveMode = DriveMode.MECANUM;
            } else if (gamepad1.dpad_right) {
                driveMode = DriveMode.DRIVE;
            }
            // precision
            double mult = gamepad1.left_bumper ? 0.35 : gamepad1.right_bumper ? 0.7 : 1.0;

            telemetry.addData("drive mode", driveMode);
            telemetry.addData("precision mode", mult);
            telemetry.update();
            switch (driveMode) {
                case TANK: {
                    // front back
                    double l = -gamepad1.left_stick_y;
                    double r = -gamepad1.right_stick_y;
                    setMotorSpeeds(mult, new double[]{
                            (Math.signum(r) * (Math.pow(2, Math.abs(r)) - 1)),
                            (Math.signum(r) * (Math.pow(2, Math.abs(r)) - 1)),
                            (Math.signum(l) * (Math.pow(2, Math.abs(l)) - 1)),
                            (Math.signum(l) * (Math.pow(2, Math.abs(l)) - 1))});
                    break;
                }
                case DRIVE: {
                    // turning
                    double pivot = gamepad1.left_stick_x;
                    double y = -gamepad1.left_stick_y;
                    setMotorSpeeds(mult, new double[]{
                            (Math.signum(y) * (Math.pow(2, Math.abs(y)) - 1)) - pivot,
                            (Math.signum(y) * (Math.pow(2, Math.abs(y)) - 1)) - pivot,
                            (Math.signum(y) * (Math.pow(2, Math.abs(y)) - 1)) + pivot,
                            (Math.signum(y) * (Math.pow(2, Math.abs(y)) - 1)) + pivot});
                    break;
                }
                case MECANUM: {
                    // left right
                    double pivot = gamepad1.right_stick_x;
                    double mX = gamepad1.left_stick_x;
                    double mY = -gamepad1.left_stick_y;
                    setMotorSpeeds(mult, new double[]{
                            (Math.signum(mY) * (Math.pow(2, Math.abs(mY)) - 1))
                                    - (Math.signum(mX) * (Math.pow(2, Math.abs(mX)) - 1)) - pivot,
                            (Math.signum(mY) * (Math.pow(2, Math.abs(mY)) - 1))
                                    + (Math.signum(mX) * (Math.pow(2, Math.abs(mX)) - 1)) - pivot,
                            (Math.signum(mY) * (Math.pow(2, Math.abs(mY)) - 1))
                                    + (Math.signum(mX) * (Math.pow(2, Math.abs(mX)) - 1)) + pivot,
                            (Math.signum(mY) * (Math.pow(2, Math.abs(mY)) - 1))
                                    - (Math.signum(mX) * (Math.pow(2, Math.abs(mX)) - 1)) + pivot});
                    break;
                }

            }

        }
    }

    // precision method
    protected void setMotorSpeeds(double mult, double[] powers) {
        for (int i = 0; i < 4; i++) {
            powers[i] = powers[i] * mult;
        }

        double max = Math.max(Math.max(Math.abs(powers[0]), Math.abs(powers[1])),
                Math.max(Math.abs(powers[2]), Math.abs(powers[3])));
        double scale = Math.abs(1 / max);
        // don't increase power, only decrease
        if (scale > 1) {
            scale = 1;
        }

        for (int i = 0; i < 4; i++) {
            powers[i] *= scale;
        }

        for (int i = 0; i < 4; i++) {
            motors[i].setPower(powers[i]);
        }
    }
}