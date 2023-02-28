package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Slides Test2 DO NOT USE")
public class SlidesTest2 extends LinearOpMode {
    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        DcMotorEx frontLeftMotor = null;
        DcMotorEx frontRightMotor = null;



        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "leftSlide");
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontRightMotor = hardwareMap.get(DcMotorEx.class, "rightSlide");
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double target = 0;
        double LKp = 0.016;
        double RKp = 0.016;
        waitForStart();


        while (opModeIsActive()) {
            if(target>0) {
                target = 0;
            }

            double leftPosition = (double) (frontLeftMotor.getCurrentPosition());
            double rightPosition = (double) (frontRightMotor.getCurrentPosition());

            double left_error = Math.abs(target - leftPosition);
            double right_error = Math.abs(target - rightPosition);
            //integralSum+=left_error*timer.seconds();
            //double derivative = (left_error-lastError)/timer.seconds();
            //lastError = left_error;
            //timer.reset();
            //double output = (left_error*Kp) + (integralSum*Ki)+(derivative*Kd);
            double leftOutput = left_error*LKp;
            double rightOutput = right_error*RKp;

            frontLeftMotor.setPower(leftOutput);
            frontRightMotor.setPower(rightOutput);

            telemetry.addData("Target", target);
            telemetry.addData("Left position ",frontLeftMotor.getCurrentPosition());
            telemetry.addData("Right position ",frontRightMotor.getCurrentPosition());
            telemetry.update();

            if (gamepad1.left_stick_y != 0) {
                //frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                target += gamepad1.left_stick_y*5;
            } else if (gamepad1.a) {
                //frontLeftMotor.setTargetPosition(-4000);
                target = 3350;
                //frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //frontLeftMotor.setVelocity(1000);
            } else if (gamepad1.b) {
                target = 2000;
                //frontLeftMotor.setTargetPosition(-2000);
                //frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //frontLeftMotor.setVelocity(1000);
            } else if (gamepad1.x) {
                target = 0;
                //frontLeftMotor.setTargetPosition(0);
                //frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //frontLeftMotor.setVelocity(1000);
            } else if (gamepad1.y){
                target = 1000;
            }

        }

    }
}
