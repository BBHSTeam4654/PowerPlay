package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Slides Test2")
public class SlidesTest2 extends LinearOpMode {
    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        DcMotorEx frontLeftMotor = null;



        frontLeftMotor = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double target = 0;
        double integralSum = 0;
        double Kp = 0.016;
        double Ki = 0.01;
        double Kd = 0.01;
        waitForStart();


        while (opModeIsActive()) {
            if(target>0) {
                target = 0;
            }

            double leftPosition = (double) (frontLeftMotor.getCurrentPosition());

            double left_error = target - leftPosition;
            integralSum+=left_error*timer.seconds();
            double derivative = (left_error-lastError)/timer.seconds();
            lastError = left_error;
            timer.reset();
            double output = (left_error*Kp) + (integralSum*Ki)+(derivative*Kd);


            frontLeftMotor.setPower(output);
            telemetry.addData("Target", target);
            telemetry.update();
            if (gamepad1.left_stick_y != 0) {
                //frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                target += gamepad1.left_stick_y*5;
            } else if (gamepad1.a) {
                //frontLeftMotor.setTargetPosition(-4000);
                target = -3350;
                //frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //frontLeftMotor.setVelocity(1000);
            } else if (gamepad1.b) {
                target = -2000;
                //frontLeftMotor.setTargetPosition(-2000);
                //frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //frontLeftMotor.setVelocity(1000);
            } else if (gamepad1.x) {
                target = 0;
                //frontLeftMotor.setTargetPosition(0);
                //frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //frontLeftMotor.setVelocity(1000);
            } else if (gamepad1.y){
                target = -1000;
            }

        }

    }
}
