package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp(name = "PIDF Test")
public class PIDF_Test extends OpMode {
    private PIDController controller;
    public static double p=0, i=0, d=0;
    public static double f=0;
    public static int target=0;
    private final double ticks_in_degree=537.7/180.0;
    private DcMotorEx slideRight, slideLeft;
    @Override
    public void init(){
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        slideRight = hardwareMap.get(DcMotorEx.class, "rightSlide");
        slideLeft = hardwareMap.get(DcMotorEx.class, "leftSlide");
    }
    @Override
    public void loop(){
        controller.setPID(p,i,d);
        int slidePos_right = slideRight.getCurrentPosition();
        int slidePos_left = slideLeft.getCurrentPosition();
        double pid_right = controller.calculate(slidePos_right, target);
        double pid_left = controller.calculate(slidePos_left, target);
        double ff = Math.cos(Math.toRadians(target/ticks_in_degree))*f;

        double power_right = pid_right+ff;
        double power_left = pid_left+ff;

        slideRight.setPower(power_right);
        slideLeft.setPower(power_left);

        telemetry.addData("right pos", slidePos_right);
        telemetry.addData("target", target);
        telemetry.update();
    }
}
