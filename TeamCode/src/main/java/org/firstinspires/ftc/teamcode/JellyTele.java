package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;

@TeleOp(name = "Power Play JellyTele")

public class JellyTele extends BaseOpMode {
    public void runOpMode() throws InterruptedException{

        initHardware();
        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Status", "Running");
            telemetry.update();


            int position = slideLeft.getCurrentPosition();
            telemetry.addData("Encoder", slideLeft.getCurrentPosition());
            telemetry.update();
            if(gamepad1.left_stick_y!=0){
                slideLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                slideLeft.setPower(gamepad1.left_stick_y);
            }

            if(gamepad1.a){
                slideLeft.setTargetPosition(-4000);
                slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideLeft.setVelocity(1000);
            }

            if(gamepad1.b){
                slideLeft.setTargetPosition(-2000);
                slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideLeft.setVelocity(1000);
            }

            if(gamepad1.x){
                slideLeft.setTargetPosition(0);
                slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideLeft.setVelocity(1000);
            }
        }
    }
}