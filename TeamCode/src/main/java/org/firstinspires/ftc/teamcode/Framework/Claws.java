package org.firstinspires.ftc.teamcode.Framework;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
//open: 0.547399999999956
//closed: 0.6605999999999436
public class Claws {
    static Servo servo;
    public Claws(Servo servo){this.servo = servo;}
    static boolean open = false;

    public static void clawsOpen(){
        servo.setPosition(0.459399999999956);
    }
    public static void clawsClose() {servo.setPosition(0.6605999999999436);}


    public double getCurrentPosition() {
        return servo.getPosition();
    }
}
