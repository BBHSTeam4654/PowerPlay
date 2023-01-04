package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
import org.firstinspires.ftc.teamcode.Framework.Slides;

@TeleOp(name = "Power Play JellyTele")

public class JellyTele extends BaseOpMode {
    public void runOpMode() throws InterruptedException{

        initHardware();
        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Status", "Running");
            telemetry.update();



            if(gamepad1.left_stick_y!=0){
                slide.manual();
            }

            if(gamepad1.a){
                slide.top();
            }

            if(gamepad1.b){
                slide.mid();
            }

            if(gamepad1.x){
                slide.reset();
            }
        }
    }
}