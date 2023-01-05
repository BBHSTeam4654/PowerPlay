package org.firstinspires.ftc.teamcode;

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


        while(opModeIsActive){
            int position = clawServo.getPosition();
            if (gamepad1.left_bumper && position != 0.5){
                clawServo.setPosition(0.35);
            } else if (gamepad1.right_bumper && position != 0){
                clawServo.setPosition(0);
            }
        }
    }
}
