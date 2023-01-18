package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;

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

            slides.manual(gamepad2.left_stick_y);

            if (gamepad2.a) {
                slides.high();
            }

            if (gamepad2.b) {
                slides.mid();
            }

            if (gamepad2.y) {
                slides.low();
            }
            if (gamepad2.x) {
                slides.reset();
            }
            if(gamepad2.left_bumper){
                slides.drop();
            }
            slides.pLoop();

            // CLAW

            if (gamepad2.right_bumper) {
                claw.clawsToggle();
            }

            // DRIVETRAIN
            if (gamepad1.dpad_left) {
                driveMode = DriveMode.TANK;
                gamepad1.rumbleBlips(1);
            } else if (gamepad1.dpad_up) {
                driveMode = DriveMode.MECANUM;
                gamepad1.rumbleBlips(2);
            } else if (gamepad1.dpad_right) {
                driveMode = DriveMode.DRIVE;
                gamepad1.rumbleBlips(3);
            }
            // precision
            double mult = gamepad1.left_bumper ? 0.35 : gamepad1.right_bumper ? 0.7 : 1.0;
            if (gamepad1.left_bumper){
                gamepad1.rumble( 0.5, 0.5, 10);
            }
            else if (gamepad1.right_bumper){
                gamepad1.rumble(0.8,0.8,10);
            }



            telemetry.addData("        Gamepad2 Controls ", "as follows:");
            telemetry.addData("+-------------------------", "--------------------------------+");
            telemetry.addData("| Gamepad2 Button: A/X Button    ", "High Junction            |");
            telemetry.addData("| Gamepad2 Button: B/O          ", " Medium Junction          |");
            telemetry.addData("| Gamepad2 Button: Y/△          ", "Low Junction             |");
            telemetry.addData("| Gamepad2 Button: Y/△           ", "Low Junction             |");
            telemetry.addData("| Gamepad2 Button: X/□          ", " Reset Slides             |");
            telemetry.addData("| Gamepad2 Button: Left Bumper  ", " Open Claw                |");
            telemetry.addData("| Gamepad2 Button: Right Bumper ", " Close Claw               |");
            telemetry.addData("+-------------------------", "--------------------------------+");
            telemetry.addData("        Data", ":");
            telemetry.addData("drive mode", driveMode);
            telemetry.addData("mX", gamepad2.left_stick_x);
            telemetry.addData("mY", gamepad2.left_stick_y);
            telemetry.addData("precision mode", mult);
            telemetry.addData("LeftSlide", slideLeft.getCurrentPosition());
            telemetry.addData("RightSlide", slideRight.getCurrentPosition());
            telemetry.addData("Front Encoder:", frontEncoder.getCurrentPosition());
            telemetry.addData("Left Encoder:", leftEncoder.getCurrentPosition());
            telemetry.addData("Right Encoder:", rightEncoder.getCurrentPosition());
            telemetry.update();
            switch (driveMode) {
                case TANK: {
                    // front back
                    double l = -gamepad1.left_stick_y;
                    double r = -gamepad1.right_stick_y;
                    setMotorSpeeds(mult, new double[] {
                            (Math.signum(r) * (Math.pow(2
                                    , Math.abs(r)) - 1)),
                            (Math.signum(r) * (Math.pow(2
                                    , Math.abs(r)) - 1)),
                            (Math.signum(l) * (Math.pow(2
                                    , Math.abs(l)) - 1)),
                            (Math.signum(l) * (Math.pow(2
                                    , Math.abs(l)) - 1)) });
                    break;
                }
                case DRIVE: {
                    // turning
                    double pivot = gamepad1.left_stick_x;
                    double y = -gamepad1.left_stick_y;
                    setMotorSpeeds(mult, new double[] {
                            (Math.signum(y) * (Math.pow(2
                                    , Math.abs(y)) - 1)) - pivot,
                            (Math.signum(y) * (Math.pow(2
                                    , Math.abs(y)) - 1)) - pivot,
                            (Math.signum(y) * (Math.pow(2
                                    , Math.abs(y)) - 1)) + pivot,
                            (Math.signum(y) * (Math.pow(2
                                    , Math.abs(y)) - 1)) + pivot });
                    break;
                }
                case MECANUM: {
                    // left right
                    double pivot = gamepad1.right_stick_x;
                    double mX = gamepad1.left_stick_x;
                    double mY = -gamepad1.left_stick_y;
                    setMotorSpeeds(mult, new double[] {
                            (Math.signum(mY) * (Math.pow(2, Math.abs(mY)) - 1))
                                    - (Math.signum(mX) * (Math.pow(2
                                    , Math.abs(mX)) - 1)) - pivot,
                            (Math.signum(mY) * (Math.pow(2
                                    , Math.abs(mY)) - 1))
                                    + (Math.signum(mX) * (Math.pow(2
                                    , Math.abs(mX)) - 1)) - pivot,
                            (Math.signum(mY) * (Math.pow(2
                                    , Math.abs(mY)) - 1))
                                    + (Math.signum(mX) * (Math.pow(2
                                    , Math.abs(mX)) - 1)) + pivot,
                            (Math.signum(mY) * (Math.pow(2
                                    , Math.abs(mY)) - 1))
                                    - (Math.signum(mX) * (Math.pow(2
                                    , Math.abs(mX)) - 1)) + pivot });
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