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
                        .addTemporalMarker(0.01, () -> {
                            //claw.clawsClose();
                        })
                        .lineToConstantHeading(new Vector2d(-36, -36))

                        .lineToConstantHeading(new Vector2d(-24, -36))
                        .addTemporalMarker(5.42, () -> {
                            //slides.mid();
                            //slides.wLoop();
                        })
                        .waitSeconds(1)
                        .forward(7.5)
                        .addTemporalMarker(8.09, () -> {
                            //claw.clawsOpen();
                        })
                        .waitSeconds(0.25)
                        .back(7.5)
                        .addTemporalMarker(10.01, () -> {
                            //slides.reset();
                            //slides.wLoop();
                        })
                        .waitSeconds(1)
                        .lineToConstantHeading(new Vector2d(-36, -36))
                        .build()
                );
        RoadRunnerBotEntity myBot3 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueLight())
                .setConstraints(45, 15, Math.toRadians(180), Math.toRadians(180), 13.54331)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startPose3)
                        //offset 3 seconds - Lift -> low
                        .lineToSplineHeading(new Pose2d(36, -24, Math.toRadians(0)))

                        // start
                        .build()

                );
        System.setProperty("sun.java2d.opengl", "true");
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                //.addEntity(myBot)
                .addEntity(myBot2)
                //.addEntity(myBot3)
                .start();
    }
}

//Save for later
/*

.lineToSplineHeading(new Pose2d(-24, -62, Math.toRadians(90)))
                        .splineToSplineHeading(new Pose2d(-12, -46, Math.toRadians(180)), Math.toRadians(90))
                        .lineToSplineHeading(new Pose2d(-12, -30, Math.toRadians(180)))
                        .splineToConstantHeading(new Vector2d(-14, -24), Math.toRadians(180))
                        //Lift Marker
                        //slows down
                        .forward(5)
                        //Drop Marker
                        .waitSeconds(1.5)
                        .back(2)
                        .splineToConstantHeading(new Vector2d(-20, -12), Math.toRadians(180))
                        //Lift offset to being by the end
                        .lineToSplineHeading(new Pose2d(-60, -12, Math.toRadians(180)))
                        //Slow down
                        .forward(3)
                        //close and lift to low
                        .waitSeconds(0.75)
                        .lineToSplineHeading(new Pose2d(-20, -12, Math.toRadians(180)))
                        .splineToConstantHeading(new Vector2d(-17, -24), Math.toRadians(180))
                        //Lift Marker
                        //slows down
                        .forward(2)

 */