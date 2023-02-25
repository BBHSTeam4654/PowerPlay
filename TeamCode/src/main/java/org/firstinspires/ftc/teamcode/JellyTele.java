package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Framework.Slides.multPrecision;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
import org.firstinspires.ftc.teamcode.misc.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.misc.trajectorysequence.TrajectorySequence;

@TeleOp(name = "Power Play JellyTele")

public class JellyTele extends BaseOpMode {

    protected static enum DriveMode {
        TANK,
        DRIVE,
        MECANUM,
        FIELDCENTRIC,
    }
    protected static enum LiftState {
        RETRACTED,
        ARM_EXTRACTED,
        NORM_EXTRACTED
    }
    protected DriveMode driveMode = DriveMode.FIELDCENTRIC;
    protected LiftState liftState = LiftState.RETRACTED;

    public void runOpMode() throws InterruptedException {

        initHardware();
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        TrajectorySequence clockwise90 = drive.trajectorySequenceBuilder(new Pose2d())
                .turn(Math.toRadians(-90))
                .build();

        TrajectorySequence clockwise180 = drive.trajectorySequenceBuilder(new Pose2d())
                .turn(Math.toRadians(-180))
                .build();

        TrajectorySequence counterClockwise90 = drive.trajectorySequenceBuilder(new Pose2d())
                .turn(Math.toRadians(90))
                .build();

        TrajectorySequence counterClockwise180 = drive.trajectorySequenceBuilder(new Pose2d())
                .turn(Math.toRadians(180))
                .build();

        waitForStart();

        while (opModeIsActive()) {

            // Slides

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

            // CLAW

            if(gamepad2.right_bumper){
                claw.clawsClose();

            }
            if(gamepad2.left_bumper){
                claw.clawsOpen();
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
            else if (gamepad1.dpad_down) {
                driveMode = DriveMode.FIELDCENTRIC;
                gamepad1.rumbleBlips(4);
            }
            // precision
            double mult = gamepad1.left_bumper ? 0.35 : gamepad1.right_bumper ? 0.7 : multPrecision ? 0.8 : 1.0;
            if (gamepad1.left_bumper){
                gamepad1.rumble( 1, 1, 10);
            }
            if (gamepad1.right_bumper){
                gamepad1.rumble(2.7,2.7,10);
            }
            //Rotate 90 degrees

            if (gamepad1.x) {
                // Turns clockwise 90
                drive.followTrajectorySequenceAsync(clockwise90);
            }
            if (gamepad1.y){
                // Turns clockwise 180
                drive.followTrajectorySequenceAsync(clockwise180);
            }
            if (gamepad1.b) {
                // Turns counter clockwise 90
                drive.followTrajectorySequenceAsync(counterClockwise90);
            }
            if (gamepad1.a){
                // Turns counter clockwise 180
                drive.followTrajectorySequenceAsync(counterClockwise180);
            }

            drive.update();
            Pose2d myPose = drive.getPoseEstimate();

            telemetry.addData("        Gamepad2 Controls ", "as follows:");
            telemetry.addData("+-------------------------", "--------------------------------+");
            telemetry.addData("| Gamepad2 Button: A/X Button    ", "High Junction            |");
            telemetry.addData("| Gamepad2 Button: B/O           ", "Medium Junction          |");
            telemetry.addData("| Gamepad2 Button: Y/△           ", "Low Junction             |");
            telemetry.addData("| Gamepad2 Button: X/□           ", "Reset Slides/Bottom Cup  |");
            telemetry.addData("| Gamepad2 Button: Left Bumper   ", "Claw                     |");
            telemetry.addData("| Gamepad2 DPAD Bottom           ", "5 Cups                   |");
            telemetry.addData("| Gamepad2 DPAD Right            ", "4 Cups                   |");
            telemetry.addData("| Gamepad2 DPAD Up               ", "3 Cups                   |");
            telemetry.addData("| Gamepad2 DPAD Left             ", "2 Cups                   |");
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
            telemetry.addData("Claw encoder", claw.getCurrentPosition());
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
                case FIELDCENTRIC: {
                    double y = -gamepad1.left_stick_y; // Remember, this is reversed!
                    double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                    double rx = gamepad1.right_stick_x;

                    double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

                    // Rotate the movement direction counter to the bot's rotation
                    double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
                    double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
                    setMotorSpeeds(mult, new double[] {
                            rotY - rotX - rx,
                            rotY + rotX - rx,
                            rotY + rotX + rx,
                            rotY - rotX + rx});
                    break;
                }
            }
            switch (liftState){
                case RETRACTED:{

                }
                case ARM_EXTRACTED:{

                }
                case NORM_EXTRACTED:{

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