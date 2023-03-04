package org.firstinspires.ftc.teamcode.Framework;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    static Servo servo;
    public Arm(Servo servo){Arm.servo = servo;}

    public static void armLeft(){Arm.servo.setPosition(0.7254);}
    public static void armRight() {Arm.servo.setPosition(0.1757);}
    public static void armNorm() {Arm.servo.setPosition(0.4457);}
}
