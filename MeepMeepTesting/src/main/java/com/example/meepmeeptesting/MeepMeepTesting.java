package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(900);

        Pose2d startPose = new Pose2d(-35, 62, Math.toRadians(270));
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose)
                            .forward(36)
                            .lineToLinearHeading(new Pose2d( -31, 8, Math.toRadians(315)))
                            .addTemporalMarker(()->{
                                //slides go up
                            })
                            .addDisplacementMarker(()->{
                                //open claw + slides go down
                            })
                                //1+1
                            .lineToLinearHeading(new Pose2d( -35, 12, Math.toRadians(180)))
                            .forward(22)
                            .addDisplacementMarker(()->{
                                //claw grabs cup -> slides go up
                            })
                                //1+2
                            .back(22)
                            .lineToLinearHeading(new Pose2d( -31, 8, Math.toRadians(315)))
                            .addDisplacementMarker(()->{
                                //claw open
                            })
                            .lineToLinearHeading(new Pose2d( -35, 12, Math.toRadians(180)))
                            .forward(22)
                            .addDisplacementMarker(()->{
                                //claw grabs cup -> slides go up
                            })
                                //1+3
                            .back(22)
                            .lineToLinearHeading(new Pose2d( -31, 8, Math.toRadians(315)))
                            .addDisplacementMarker(()->{
                                //claw open
                            })
                            .lineToLinearHeading(new Pose2d( -35, 12, Math.toRadians(180)))
                            .forward(22)
                            .addDisplacementMarker(()->{
                                //claw grabs cup -> slides go up
                            })
                                //1+4
                            .back(22)
                            .lineToLinearHeading(new Pose2d( -31, 8, Math.toRadians(315)))
                            .addDisplacementMarker(()->{
                                //claw open
                            })
                            .lineToLinearHeading(new Pose2d( -35, 12, Math.toRadians(180)))
                            .forward(22)
                            .addDisplacementMarker(()->{
                                //claw grabs cup -> slides go up
                            })
                                //1+5
                            .back(22)
                            .lineToLinearHeading(new Pose2d( -31, 8, Math.toRadians(315)))
                            .addDisplacementMarker(()->{
                                //claw open
                            })
                            //start
                            .build()

                );

        System.setProperty("sun.java2d.opengl", "true");
        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}