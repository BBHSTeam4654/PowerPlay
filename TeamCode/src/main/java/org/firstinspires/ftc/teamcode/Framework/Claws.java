package org.firstinspires.ftc.teamcode.Framework;
import com.qualcomm.robotcore.hardware.Servo;

public class Claws {
   static Servo servo;
    public Claws(Servo servo){
        this.servo = servo;
    }
    public static void ClawsOpen(){
        servo.setPosition(0.35);
    }
    public static void ClawsClose(){
        servo.setPosition(0);
    }
}
