package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class MeepMeepTesting {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        MeepMeep meepMeep = new MeepMeep(600);

        Pose2d startPose1 = new Pose2d(-36, 62, Math.toRadians(270));
        Pose2d startPose2 = new Pose2d(-36, -62, Math.toRadians(90));
        Pose2d startPose3 = new Pose2d(36, -62, Math.toRadians(90));
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueLight())
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 13.54331)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startPose1)
                        .forward(50)
                        .strafeLeft(24)
                        // start
                        .build()

                );
        RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedLight())
                .setConstraints(30, 10, Math.toRadians(180), Math.toRadians(180), 13.54331)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startPose2)
                        .addTemporalMarker(() -> {
//                            Claws.clawsClose();
                        })
                        .waitSeconds(1)
                        .addTemporalMarker(() -> {
//                            Slides.high();
                        })
                        .lineToConstantHeading(new Vector2d(-36, -12))
                        .addTemporalMarker(() -> {
//                            Arm.armNorm();
                        })
                        .lineToConstantHeading(new Vector2d(-25.5, -12))
                        .waitSeconds(0.1)
                        .addTemporalMarker(() -> {
//                            Claws.clawsOpen();
                        })
                        .back(2)
                        .addTemporalMarker(() -> {
//                            Arm.armLeft();
//                            Slides.fiveCups();
                        })
                        .lineToConstantHeading(new Vector2d(-62, -14))
                        .addTemporalMarker(() -> {
//                            Claws.clawsClose();
                        })
                        .waitSeconds(1.25)
                        .addTemporalMarker(() -> {
//                            Slides.high();
                        })
                        .lineToConstantHeading(new Vector2d(-25.5, -13.5))
                        .addTemporalMarker(() -> {
//                            Arm.armNorm();
                        })
                        .forward(1.5)
                        .waitSeconds(0.1)
                        .addTemporalMarker(() -> {
//                            Claws.clawsOpen();
                        })
                        .lineToConstantHeading(new Vector2d(-58, -14))
                        .addTemporalMarker(() -> {
//                            Arm.armLeft();
//                            Slides.reset();
                        })
                        .build()
                );
        RoadRunnerBotEntity myBot3 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueLight())
                .setConstraints(30, 10, Math.toRadians(180), Math.toRadians(180), 13.54331)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startPose3)
                        .addTemporalMarker(() -> {
//                            Claws.clawsClose();
                        })
                        .waitSeconds(1)
                        .addTemporalMarker(() -> {
//                            Slides.high();
                        })
                        .lineToConstantHeading(new Vector2d(36, -12))
                        .addTemporalMarker(() -> {
//                            Arm.armNorm();
                        })
                        .lineToConstantHeading(new Vector2d(25.5, -12))
                        .waitSeconds(0.1)
                        .addTemporalMarker(() -> {
//                            Claws.clawsOpen();
                        })
                        .back(2)
                        .addTemporalMarker(() -> {
//                            Arm.armRight();
//                            Slides.fiveCups();
                        })
                        .lineToConstantHeading(new Vector2d(62, -14))
                        .addTemporalMarker(() -> {
//                            Claws.clawsClose();
                        })
                        .waitSeconds(1.25)
                        .addTemporalMarker(() -> {
//                            Slides.high();
                        })
                        .lineToConstantHeading(new Vector2d(25.5, -13.5))
                        .addTemporalMarker(() -> {
//                            Arm.armNorm();
                        })
                        .forward(1.5)
                        .waitSeconds(0.1)
                        .addTemporalMarker(() -> {
//                            Claws.clawsOpen();
                        })
                        .lineToConstantHeading(new Vector2d(58, -14))
                        .addTemporalMarker(() -> {
//                            Arm.armLeft();
//                            Slides.reset();
                        })
                        .build()


                );
        System.setProperty("sun.java2d.opengl", "true");
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                //.addEntity(myBot)
                .addEntity(myBot2)
                .addEntity(myBot3)
                .start();
    }
}

//Save for later
/*

.lineToConstantHeading(new Vector2d(-36, -22))
                        .splineToSplineHeading(new Pose2d(-20, -10, Math.toRadians(180)), Math.toRadians(0))
                        .waitSeconds(0.5)
                        .splineToConstantHeading(new Vector2d(-48, -12), Math.toRadians(180))
                        .lineToConstantHeading(new Vector2d(-65, -12))
                        .waitSeconds(0.5)
                        .lineToConstantHeading(new Vector2d(-48, -12))
                        .splineToConstantHeading(new Vector2d(-20, -10), Math.toRadians(0))

 */