package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Framework.Arm;
import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;

@TeleOp(name = "Servo Test meow3")
public class ServoTest extends BaseOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        Servo clawServo  = null;
        clawServo = hardwareMap.get(Servo.class, "servo");
        waitForStart();

        double position = 0.5;


        while(opModeIsActive()){
            telemetry.addData("Servo", position);
            telemetry.update();

            clawServo.setPosition(position);

            if(gamepad1.dpad_left){
                position-=0.0001;
            }
            if(gamepad1.dpad_right){
                position+=0.0001;
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
