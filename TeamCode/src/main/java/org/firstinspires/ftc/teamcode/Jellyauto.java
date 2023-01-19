/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

//import org.firstinspires.ftc.teamcode.Framework.AutoDrivetrain;
import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
import org.firstinspires.ftc.teamcode.Framework.Slides;
import org.firstinspires.ftc.teamcode.misc.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.misc.pipeline.AprilTagDetectionPipeline;
import org.firstinspires.ftc.teamcode.misc.trajectorysequence.TrajectorySequence;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous(name = "Jellyauto")
public class Jellyauto extends BaseOpMode {
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    // Note - tuning is not necessary
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // Tag IDs
    int LEFT = 1;
    int MIDDLE = 2;
    int RIGHT = 3;

    // Side
    protected static enum Side {
        CUPS_LEFT, CUPS_RIGHT, STOP
    }

    protected Side side = Side.CUPS_LEFT;

    AprilTagDetection tagOfInterest = null;



    @Override
    public void runOpMode() {
        initHardware();

        //AutoDrivetrain AU = new AutoDrivetrain((DcMotorEx) leftEncoder, (DcMotorEx) rightEncoder, (DcMotorEx) frontEncoder,motors);
        claw.clawsClose();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id",
                hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"),
                cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested()) {
            if (gamepad1.x)
                side = Side.CUPS_LEFT;
            if (gamepad1.b)
                side = Side.CUPS_RIGHT;
            telemetry.addData("Side", side);

            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if (currentDetections.size() != 0) {
                boolean tagFound = false;

                for (AprilTagDetection tag : currentDetections) {
                    if (tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if (tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                } else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if (tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    } else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    }
                }
            } else {
                telemetry.addLine("Don't see tag of interest :(");

                if (tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)");
                } else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                }

            }
            telemetry.addData("Left encoder", leftEncoder.getCurrentPosition());
            telemetry.addData("Right encoder", rightEncoder.getCurrentPosition());
            telemetry.addData( "Front encoder", frontEncoder.getCurrentPosition());
            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */
        /* Update the telemetry */
        if (tagOfInterest != null) {
            telemetry.addLine("Tag snapshot:\n");
            telemetry.update();
        } else {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d right_startPose = new Pose2d(-35, 62, Math.toRadians(270));
        Pose2d left_startPose = new Pose2d(-35, 62, Math.toRadians(270));

        drive.setPoseEstimate(right_startPose);
        drive.setPoseEstimate(left_startPose);

        TrajectorySequence rightTrajSeq = drive.trajectorySequenceBuilder(right_startPose)
                .forward(36)
                .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                    slides.high();
                })


                .lineToLinearHeading(new Pose2d(-31, 8, Math.toRadians(315)))
                .addTemporalMarker(() -> {
                    claw.clawsToggle();
                })
                .build();


        while (opModeIsActive()) {
            if (side == Side.CUPS_LEFT) {
                if (tagOfInterest == null || tagOfInterest.id == MIDDLE) {
                    drive.followTrajectorySequence(rightTrajSeq);
                }
                /*else if(tagOfInterest.id == LEFT){
                    //left trajectory
                }else{
                    //right trajectory
                }
                */
            }
            /*
            else{
                if (tagOfInterest == null || tagOfInterest.id == MIDDLE) {
                    //middle trajectory
                }else if(tagOfInterest.id == LEFT){
                    //left trajectory
                }else{
                    //right trajectory
                }
            }
       */
        }
    }
}