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
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.firstinspires.ftc.teamcode.Framework.Arm;
import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
import org.firstinspires.ftc.teamcode.Framework.Claws;
import org.firstinspires.ftc.teamcode.Framework.Slides;
import org.firstinspires.ftc.teamcode.misc.trajectorysequence.sequencesegment.*;
import org.firstinspires.ftc.teamcode.misc.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.misc.pipeline.AprilTagDetectionPipeline;
import org.firstinspires.ftc.teamcode.misc.trajectorysequence.*;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous(name = "Left Jellyauto")
public class left_Jellyauto extends BaseOpMode {
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

    AprilTagDetection tagOfInterest = null;


    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
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

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-36, -62, Math.toRadians(90));

        drive.setPoseEstimate(startPose);


        TrajectorySequence LPark = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(-36, -36))
                .lineToConstantHeading(new Vector2d(-60, -36))
                .build();
        TrajectorySequence MPark = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(-36, -36))
                .build();
        TrajectorySequence RPark = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(-36, -36))
                .lineToConstantHeading(new Vector2d(-12, -36))
                .build();

        TrajectorySequence RAuto = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {
                    Claws.clawsClose();
                })
                .waitSeconds(2)
                .addTemporalMarker(() -> {
                    Slides.high();
                })
                .lineToConstantHeading(new Vector2d(-36, -12))
                .addTemporalMarker(() -> {
                    Arm.armNorm();
                })
                .lineToConstantHeading(new Vector2d(-23, -12))
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Slides.highDown();
                })
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Claws.clawsOpen();
                })
                .back(2)
                .addTemporalMarker(() -> {
                    Arm.armLeft();
                    Slides.fiveCups();
                })
                .lineToConstantHeading(new Vector2d(-61.75, -14))
                .addTemporalMarker(() -> {
                    Claws.clawsClose();
                })
                .waitSeconds(2)
                .addTemporalMarker(() -> {
                    Slides.high();
                })
                .lineToConstantHeading(new Vector2d(-23, -13.5))
                .addTemporalMarker(() -> {
                    Arm.armNorm();
                })
                .forward(1.5)
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Slides.highDown();
                })
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Claws.clawsOpen();
                })
                .lineToConstantHeading(new Vector2d(-10, -14))
                .addTemporalMarker(() -> {
                    Arm.armLeft();
                    Slides.reset();
                })
                .build();

        TrajectorySequence MAuto = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {
                    Claws.clawsClose();
                })
                .waitSeconds(2)
                .addTemporalMarker(() -> {
                    Slides.high();
                })
                .lineToConstantHeading(new Vector2d(-36, -12))
                .addTemporalMarker(() -> {
                    Arm.armNorm();
                })
                .lineToConstantHeading(new Vector2d(-23, -12))
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Slides.highDown();
                })
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Claws.clawsOpen();
                })
                .back(2)
                .addTemporalMarker(() -> {
                    Arm.armLeft();
                    Slides.fiveCups();
                })
                .lineToConstantHeading(new Vector2d(-61.75, -14))
                .addTemporalMarker(() -> {
                    Claws.clawsClose();
                })
                .waitSeconds(2)
                .addTemporalMarker(() -> {
                    Slides.high();
                })
                .lineToConstantHeading(new Vector2d(-23, -13.5))
                .addTemporalMarker(() -> {
                    Arm.armNorm();
                })
                .forward(1.5)
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Slides.highDown();
                })
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Claws.clawsOpen();
                })
                .lineToConstantHeading(new Vector2d(-34, -14))
                .addTemporalMarker(() -> {
                    Arm.armLeft();
                    Slides.reset();
                })
                .build();

        TrajectorySequence LAuto = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {
                    Claws.clawsClose();
                })
                .waitSeconds(2)
                .addTemporalMarker(() -> {
                    Slides.high();
                })
                .lineToConstantHeading(new Vector2d(-36, -12))
                .addTemporalMarker(() -> {
                    Arm.armNorm();
                })
                .lineToConstantHeading(new Vector2d(-23, -12))
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Slides.highDown();
                })
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Claws.clawsOpen();
                })
                .back(2)
                .addTemporalMarker(() -> {
                    Arm.armLeft();
                    Slides.fiveCups();
                })
                .lineToConstantHeading(new Vector2d(-61.75, -14))
                .addTemporalMarker(() -> {
                    Claws.clawsClose();
                })
                .waitSeconds(2)
                .addTemporalMarker(() -> {
                    Slides.high();
                })
                .lineToConstantHeading(new Vector2d(-23, -13.5))
                .addTemporalMarker(() -> {
                    Arm.armNorm();
                })
                .forward(1.5)
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Slides.highDown();
                })
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    Claws.clawsOpen();
                })
                .lineToConstantHeading(new Vector2d(-58, -14))
                .addTemporalMarker(() -> {
                    Arm.armLeft();
                    Slides.reset();
                })
                .build();

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested()) {
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
            telemetry.addData("Front encoder", frontEncoder.getCurrentPosition());
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

        if (tagOfInterest == null || tagOfInterest.id == MIDDLE) {
            drive.followTrajectorySequenceAsync(MAuto);
        } else if (tagOfInterest.id == LEFT) {
            drive.followTrajectorySequenceAsync(LAuto);
        } else {
            drive.followTrajectorySequenceAsync(RAuto);
        }

        while (opModeIsActive() && !isStopRequested()) {
            drive.update();
            slides.pLoop();
        }
    }
    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }
}