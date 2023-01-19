package org.firstinspires.ftc.teamcode.Framework;
import com.acmerobotics.roadrunner.geometry.Pose2d;

public class PoseStorage {
    public static Pose2d right_startPose = new Pose2d(-35, 62, Math.toRadians(270));
    public static Pose2d left_startPose = new Pose2d(-35, -62, Math.toRadians(90));
    public static Pose2d currentPose = new Pose2d();
}
