package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
/* 
@TeleOp(name = "Power Play MotorTest")

public class MotorTest {
    public void runOpMode() throws InterruptedException{
    protected static enum driveTest {
        frontL,
        frontR,
        backL,
        backR
    }

    protected driveTest mode = driveTest.frontL;
    public void runOpMode() throws InterruptedException{

            initHardware();
            waitForStart();

            while(opModeIsActive()) {
    if (gamepad1.x)
    {
        mode = MotorTest.driveTest.frontL;
    } else if(gamepad1.y)

    {
        mode = MotorTest.driveTest.frontR;
    } else if(gamepad1.b)

    {
        mode = MotorTest.driveTest.backL;
    } else if(gamepad1.a)

    {
        mode = MotorTest.driveTest.backR;
    }

    double mult = gamepad1.left_bumper ? 0.35 : gamepad1.right_bumper ? 0.7 : 1.0;

    switch(mode)

    {
        case frontL: {
            //front back
            double l = -gamepad1.left_stick_y;
            double r = -gamepad1.right_stick_y;
            setMotorSpeeds(mult, new double[] {
                    (Math.pow(2, r)-1),
                    (Math.pow(2, r)-1),
                    (Math.pow(2, l)-1),
                    (Math.pow(2, l)-1)});
            break;
        }
        case frontR: {
            double l = -gamepad1.left_stick_y;
            double r = -gamepad1.right_stick_y;
            setMotorSpeeds(mult, new double[] {
                    (Math.pow(2, r)-1),
                    (Math.pow(2, r)-1),
                    (Math.pow(2, l)-1),
                    (Math.pow(2, l)-1)});
            break;
        }
        case backL: {
            double l = -gamepad1.left_stick_y;
            double r = -gamepad1.right_stick_y;
            setMotorSpeeds(mult, new double[] {
                    (Math.pow(2, r)-1),
                    (Math.pow(2, r)-1),
                    (Math.pow(2, l)-1),
                    (Math.pow(2, l)-1)});
            break;
        }
        case backR: {
            double l = -gamepad1.left_stick_y;
            double r = -gamepad1.right_stick_y;
            setMotorSpeeds(mult, new double[] {
                    (Math.pow(2, r)-1),
                    (Math.pow(2, r)-1),
                    (Math.pow(2, l)-1),
                    (Math.pow(2, l)-1)});
            break;
        }
    }


        private void setMotorSpeeds(double mult, double[] powers){
            for (int i = 0; i < 4; i++) {
                powers[i] = powers[i] * mult;
            }

            double max = Math.max(Math.max(Math.abs(powers[0]), Math.abs(powers[1])), Math.max(Math.abs(powers[2]), Math.abs(powers[3])));
            double scale = 1 / max;

            if (scale > 1) {
                scale = 1;
            }
            for (int i = 0; i < 4; i++) {
                powers[i] *= scale;
            }
            for (int i = 0; i < 4; i++) {
                motors[i].setPower(powers[i]);
            }
        }
    }
}
    }


}

 */