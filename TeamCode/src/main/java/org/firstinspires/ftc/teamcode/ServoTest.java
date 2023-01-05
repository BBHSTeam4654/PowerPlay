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


        while(opModeIsActive()){
<<<<<<< HEAD

=======
            if (gamepad1.left_bumper){
                clawServo.setPosition(0.35);
            } else if (gamepad1.right_bumper){
                clawServo.setPosition(0);
            }
>>>>>>> a36b75b01b2527d6d60097671a13c7d3102c971e
        }
    }
}
