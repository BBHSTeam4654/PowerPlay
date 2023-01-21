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
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startPose2)
                        .UNSTABLE_addTemporalMarkerOffset(0.5, () -> {
                            //slides up to mid
                        })
                        .forward(44)

                        .splineToConstantHeading(new Vector2d(-24, -8), Math.toRadians(90))
                        //slides up high
                        .waitSeconds(2)
                        //go right over junction
                        .forward(3)
                        //drop
//                        .waitSeconds(2)
//                        .back(5)
//                        .setReversed(true)
//                        .lineToSplineHeading(new Pose2d(-40, -12, Math.toRadians(180)))
//                        .setReversed(false)
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