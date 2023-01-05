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
            
        }
    }
}
