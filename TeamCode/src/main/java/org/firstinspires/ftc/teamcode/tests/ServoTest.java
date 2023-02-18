package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo Test")
public class ServoTest extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{
        Servo clawServo  = null;
        clawServo = hardwareMap.get(Servo.class, "servo");
        waitForStart();
        double servoPosition = 0.5;


        while(opModeIsActive()){
            telemetry.addData("Servo", clawServo.getPosition());
            telemetry.update();
            clawServo.setPosition(servoPosition);
            if(gamepad1.a){
                servoPosition+=0.00002;
            }
            if(gamepad1.b){
                servoPosition-=0.00002;
            }
            /*
            if (gamepad1.a){
                clawServo.setPosition(1);
            } else if (gamepad1.b){
                clawServo.setPosition(0);
            }
            */
        }
    }
}
