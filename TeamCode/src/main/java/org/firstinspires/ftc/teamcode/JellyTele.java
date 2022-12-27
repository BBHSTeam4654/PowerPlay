package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Power Play JellyTele")

public class JellyTele extends LinearOpMode {
    public void runOpMode() throws InterruptedException{


        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}