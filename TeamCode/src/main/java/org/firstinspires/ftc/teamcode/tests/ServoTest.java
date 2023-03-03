package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;

@TeleOp(name = "Servo Test")
public class ServoTest extends BaseOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        Servo clawServo  = null;
        clawServo = hardwareMap.get(Servo.class, "servo1");
        waitForStart();
        double servoPosition = 0.5;


        while(opModeIsActive()){
            telemetry.addData("Servo", clawServo.getPosition());
            telemetry.update();
            clawServo.setPosition(servoPosition);

            if(gamepad1.a){
                servoPosition+=0.00001;
            }
            if(gamepad1.b){
                servoPosition-=0.00001;
            }

            /*

            if (gamepad1.a){
                servoPosition = 1.0;
            } else if (gamepad1.b){
                servoPosition = 0;
            }*/

        }
    }
}
