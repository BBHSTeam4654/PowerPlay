package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Framework.Slides.multPrecision;
import static org.firstinspires.ftc.teamcode.Framework.Slides.slideExtracted;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Framework.Arm;
import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
import org.firstinspires.ftc.teamcode.Framework.Claws;
import org.firstinspires.ftc.teamcode.Framework.Slides;
import org.firstinspires.ftc.teamcode.misc.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.misc.trajectorysequence.TrajectorySequence;

@TeleOp(name = "Power Play JellyTele")

public class JellyTele extends BaseOpMode {

    protected enum DriveMode {
        TANK,
        DRIVE,
        MECANUM,
        FIELDCENTRIC,
    }
    protected enum LiftState {
        RETRACTED, //Full usage of slides - no l+r to prevent army from hitting drivetrain
        EXTRACTED //Limited usage of slides
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
                RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
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
            if(gamepad2.left_stick_button){
               Slides.override();
            }
            if(gamepad2.right_stick_button){
                Slides.overrideReset();
            }
            if(slideExtracted){
                liftState=LiftState.EXTRACTED;
            }
            if(!slideExtracted){
                liftState=LiftState.RETRACTED;
            }
            slides.manual(gamepad2.left_stick_y);
            slides.pLoop();

            // CLAW

            if(gamepad2.right_bumper){
                Claws.clawsClose();
            }
            if(gamepad2.left_bumper){
                Claws.clawsOpen();
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
            double tMult = 1.0;
            if(gamepad1.x && gamepad1.back){
                tMult = 0.55;
            }
            if(gamepad1.b && gamepad1.back){
                tMult = 1.0;
            }
            // precision
            double mult = gamepad1.left_bumper ? 0.35 : gamepad1.right_bumper ? 0.7 : multPrecision ? tMult : 1.0;
            if (gamepad1.left_bumper){
                gamepad1.rumble( 1, 1, 10);
            }
            if (gamepad1.right_bumper){
                gamepad1.rumble(2.7,2.7,10);
            }
            // Degree turns
            if (gamepad1.x && !gamepad1.back) {
                // Turns clockwise 90
                drive.followTrajectorySequenceAsync(clockwise90);
            }
            if (gamepad1.y && !gamepad1.back && gamepad1.options){
                // Turns clockwise 180
                drive.followTrajectorySequenceAsync(clockwise180);
            }
            if (gamepad1.b && !gamepad1.back) {
                // Turns counter clockwise 90
                drive.followTrajectorySequenceAsync(counterClockwise90);
            }
            if (gamepad1.a && !gamepad1.back){
                // Turns counter clockwise 180
                drive.followTrajectorySequenceAsync(counterClockwise180);
            }
            if (gamepad1.y && gamepad1.back){
                imu.resetYaw();
                gamepad1.rumbleBlips(5);
            }
            drive.update();
            Pose2d myPose = drive.getPoseEstimate();

            telemetry.addData("drive mode", driveMode);
            telemetry.addData("liftState", liftState);
            telemetry.addData("mX", gamepad2.left_stick_x);
            telemetry.addData("mY", gamepad2.left_stick_y);
            telemetry.addData("precision mode", mult);
            telemetry.addData("LeftSlide", slideLeft.getCurrentPosition());
            telemetry.addData("RightSlide", slideRight.getCurrentPosition());
            telemetry.addData("Front Encoder:", frontEncoder.getCurrentPosition());
            telemetry.addData("Left Encoder:", leftEncoder.getCurrentPosition());
            telemetry.addData("Right Encoder:", rightEncoder.getCurrentPosition());
            telemetry.addData("Claw encoder", claw.getCurrentPosition());
            telemetry.addData("multPrecision", multPrecision);
            telemetry.update();
            switch (driveMode) {
                case TANK: {
                    // front back
                    double l = -gamepad1.left_stick_y;
                    double r = -gamepad1.right_stick_y;
                    double eR = (Math.signum(r) * (Math.pow(2, Math.abs(r)) - 1));
                    double eL = (Math.signum(l) * (Math.pow(2, Math.abs(l)) - 1));
                    setMotorSpeeds(mult, new double[] {
                            eR,
                            eR,
                            eL,
                            eL});
                    break;
                }
                case DRIVE: {
                    // turning
                    double pivot = gamepad1.left_stick_x;
                    double y = -gamepad1.left_stick_y;
                    double e = (Math.signum(y) * (Math.pow(2, Math.abs(y)) - 1));
                    setMotorSpeeds(mult, new double[] {
                            e - pivot,
                            e - pivot,
                            e + pivot,
                            e + pivot });
                    break;
                }
                case MECANUM: {
                    // left right
                    double pivot = gamepad1.right_stick_x;
                    double mX = gamepad1.left_stick_x;
                    double mY = -gamepad1.left_stick_y;
                    double eY = (Math.signum(mY) * (Math.pow(2, Math.abs(mY)) - 1));
                    double eX = (Math.signum(mX) * (Math.pow(2, Math.abs(mX)) - 1));
                    setMotorSpeeds(mult, new double[] {
                            eY - eX - pivot,
                            eY + eX - pivot,
                            eY + eX + pivot,
                            eY - eX + pivot });
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
            //Finite State Machine
            switch (liftState){
                case RETRACTED:{
                    if (gamepad2.a && !gamepad2.back) {
                        Slides.high();
                        liftState=LiftState.EXTRACTED;
                    }

                    if (gamepad2.b && !gamepad2.back) {
                        Slides.mid();
                        liftState=LiftState.EXTRACTED;
                    }

                    if (gamepad2.y && !gamepad2.back) {
                        Slides.low();
                        liftState=LiftState.EXTRACTED;
                    }
                    if (gamepad2.dpad_down) {
                        Slides.fiveCups();
                        liftState=LiftState.EXTRACTED;
                    }
                    if (gamepad2.dpad_right) {
                        Slides.fourCups();
                        liftState=LiftState.EXTRACTED;
                    }
                    if (gamepad2.dpad_up) {
                        Slides.threeCups();
                    }
                    if (gamepad2.dpad_left) {
                        Slides.twoCups();
                    }
                    if (gamepad2.x && !gamepad2.back) {
                        Slides.reset();
                    }

                    if(gamepad2.right_bumper){
                        Claws.clawsClose();

                    }
                    if(gamepad2.left_bumper){
                        Claws.clawsOpen();
                    }
                    break;
                }
                case EXTRACTED:{
                    if (gamepad2.a && !gamepad2.back) {
                        Slides.high();
                    }
                    if (gamepad2.b && !gamepad2.back) {
                        Slides.mid();
                    }
                    if (gamepad2.y && !gamepad2.back) {
                        Slides.low();
                    }
                    if (gamepad2.dpad_down) {
                        Slides.fiveCups();
                    }
                    if (gamepad2.dpad_right) {
                        Slides.fourCups();
                    }
                    if (gamepad2.dpad_up) {
                        Slides.threeCups();
                        liftState=LiftState.RETRACTED;
                    }
                    if (gamepad2.dpad_left) {
                        Slides.twoCups();
                        liftState=LiftState.RETRACTED;
                    }
                    if (gamepad2.x && !gamepad2.back) {
                        Slides.reset();
                        liftState=LiftState.RETRACTED;
                    }

                    if(gamepad2.right_bumper){
                        Claws.clawsClose();
                    }
                    if(gamepad2.left_bumper){
                        Claws.clawsOpen();
                    }
                    if (gamepad2.x && gamepad2.back) {
                        Arm.armLeft();
                    }
                    if (gamepad2.b && gamepad2.back) {
                        Arm.armRight();
                    }
                    if (gamepad2.y && gamepad2.back) {
                        Arm.armNorm();
                    }
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