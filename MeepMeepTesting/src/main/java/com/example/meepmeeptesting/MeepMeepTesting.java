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
        MeepMeep meepMeep = new MeepMeep(800);

        Pose2d startPose1 = new Pose2d(-35, 62, Math.toRadians(270));
        Pose2d startPose2 = new Pose2d(-35, -62, Math.toRadians(90));
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueLight())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startPose1)
                        .forward(36)
                        .lineToLinearHeading(new Pose2d(-31, 8, Math.toRadians(315)))
                        .addTemporalMarker(() -> {
                            // slides go up
                        })
                        .addDisplacementMarker(() -> {
                            // open claw
                        })
                        .addTemporalMarker(() -> {
                            // slides go down
                        })
                        // 1+1
                        .lineToLinearHeading(new Pose2d(-35, 12, Math.toRadians(180)))
                        .forward(22)
                        .addDisplacementMarker(() -> {
                            // claw grabs cup
                        })
                        .addTemporalMarker(() -> {
                            // slides go up
                        })
                        .back(22)
                        .lineToLinearHeading(new Pose2d(-31, 8, Math.toRadians(315)))
                        .addDisplacementMarker(() -> {
                            // claw open
                        })
                        .addTemporalMarker(() -> {
                            // slides go down
                        })
                        // start
                        .build()

                );
        RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedLight())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startPose2)
                        .forward(36)
                        .lineToLinearHeading(new Pose2d(-31, -8, Math.toRadians(45)))

                        .addDisplacementMarker(() -> {
                            // open claw + slides go down
                        })
                        .lineToLinearHeading(new Pose2d(-35, -12, Math.toRadians(180)))
                        .forward(22)
                        .addDisplacementMarker(() -> {
                            // claw grabs cup -> slides go up
                        })
                        .back(22)
                        .lineToLinearHeading(new Pose2d(-31, -8, Math.toRadians(45)))

                        .addDisplacementMarker(() -> {
                            // open claw + slides go down
                        })
                        .lineToLinearHeading(new Pose2d(-35, -12, Math.toRadians(180)))
                        .forward(22)
                        .addDisplacementMarker(() -> {
                            // claw grabs cup -> slides go up
                        })
                        .back(22)
                        .lineToLinearHeading(new Pose2d(-31, -8, Math.toRadians(45)))

                        .addDisplacementMarker(() -> {
                            // open claw + slides go down
                        })
                        .lineToLinearHeading(new Pose2d(-35, -12, Math.toRadians(180)))
                        .forward(22)
                        .addDisplacementMarker(() -> {
                            // claw grabs cup -> slides go up
                        })
                        .back(22)
                        .lineToLinearHeading(new Pose2d(-31, -8, Math.toRadians(45)))

                        .addDisplacementMarker(() -> {
                            // open claw + slides go down
                        })
                        .lineToLinearHeading(new Pose2d(-35, -12, Math.toRadians(180)))
                        .forward(22)
                        .addDisplacementMarker(() -> {
                            // claw grabs cup -> slides go up
                        })
                        .back(22)
                        .lineToLinearHeading(new Pose2d(-31, -8, Math.toRadians(45)))
                        .addDisplacementMarker(() -> {
                            // open claw + slides go down
                        })
                        .lineToLinearHeading(new Pose2d(-35, -12, Math.toRadians(180)))
                        .forward(22)
                        .addDisplacementMarker(() -> {
                            // claw grabs cup -> slides go up
                        })
                        .back(22)
                        .lineToLinearHeading(new Pose2d(-31, -8, Math.toRadians(45)))
                        .addDisplacementMarker(() -> {
                            // open claw + slides go down
                        })
                        // start
                        .build()

                );
        System.setProperty("sun.java2d.opengl", "true");
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .addEntity(myBot2)
                .start();
    }
}