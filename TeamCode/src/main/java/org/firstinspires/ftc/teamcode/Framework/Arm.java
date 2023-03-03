package org.firstinspires.ftc.teamcode.Framework;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    static Servo servo;
    public Arm(Servo servo){this.servo = servo;}

    public static void armLeft(){servo.setPosition(0.97054);}
    public static void armRight() {servo.setPosition(0.4249399999);}
    public static void armNorm() {servo.setPosition(0.691);}
}
