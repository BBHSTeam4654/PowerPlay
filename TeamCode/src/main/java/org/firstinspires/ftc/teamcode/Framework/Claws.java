package org.firstinspires.ftc.teamcode.Framework;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

public class Claws {
   static Servo servo;
    public Claws(Servo servo){
        this.servo = servo;
    }
    public static void clawsOpen(){
        servo.setPosition(0.5);
    }
    public static void clawsClose(){
        servo.setPosition(0);
    }
}
