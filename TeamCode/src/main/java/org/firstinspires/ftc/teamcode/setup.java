package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.framework.*;
public class setup {
    void initHardware(){

        motors = new DcMotor[]{
            hardwareMap.dcMotor.get("fr")
            hardwareMap.dcMotor.get("bl")
            hardwareMap.dcMotor.get("fl")
            hardwareMap.dcMotor.get("bl")
        }

        DcMotor leftSlide = new DcMotor()
        DcMotor rightSlide = new DcMotor()
        leftSlide = hardwareMap.dcMotor.get()
        rightSlide = hardwareMap.dcMotor.get()

    }

    protected void setMotorSpeeds(double mult, double[] powers) {
        for (int i = 0; i < 4; i++) {
            powers[i] = powers[i] * mult;
        }

        double max = Math.max(Math.max(Math.abs(powers[0]), Math.abs(powers[1])), Math.max(Math.abs(powers[2]), Math.abs(powers[3])));
        double scale = Math.abs(1 / max);

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

