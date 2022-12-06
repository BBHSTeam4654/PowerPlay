package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Power Play JellyTele")

public class JellyTele extends BaseOpMode{
    public void runOpMode() throws InterruptedException{
        
        protected static enum DriveMode{
            TANK,
            DRIVE,
            MECANUM,
        }
        protected DriveMode driveMode = DriveMode.MECANUM;
        
        initHardware();
        waitForStart();
        while(opModeIsActive()){
            if (gamepad1.dpad_left) {
                driveMode = DriveMode.TANK;
            } else if (gamepad1.dpad_up) {
                driveMode = DriveMode.MECANUM;
            } else if (gamepad1.dpad_right) {
                driveMode = DriveMode.DRIVE;
            }

            telemetry.addData("drive mode", driveMode);
            telemetry.addData("precision mode", mult);
            telemetry.update();

            switch(driveMode){
                case TANK:{
                    setMotorSpeeds(mult, new double[] {-gamepad1.right_stick_y, -gamepad1.right_stick_y, -gamepad1.left_stick_y, -gamepad1.left_stick_y});
                    break;
                }
                case DRIVE:{
                    double x = gamepad1.left_stick_x
                    double y = -gamepad1.left_stick_y;

                    setMotorSpeeds(mult,new double[]{y-pivot,y-pivot,y+pivot,y+pivot})
                    break;
                }
                case MECANUM:{
                    double pivot = gamepad1.right_stick_x;
                    double mX = gamepad1.left_stick_x;
                    double mY = gamepad1.left_stick_y;
                    setMotorSpeeds(mult,new double[]){mY - mX - pivot, mY + mX - pivot, mY + mX + pivot, mY - mX + pivot}
                    }
                    break;
                }
            }
            

        }
    }
}