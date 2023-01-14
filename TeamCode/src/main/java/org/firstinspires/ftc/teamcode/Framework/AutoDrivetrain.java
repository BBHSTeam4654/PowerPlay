package org.firstinspires.ftc.teamcode.Framework;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;

public class AutoDrivetrain {

         DcMotorEx leftEncoder;
         DcMotorEx rightEncoder;
         DcMotorEx frontEncoder;
         double leftTarget;
         double rightTarget;
         double lPower;
         double rPower;
         double Kp = 0.01;
        DcMotor[]motors;

    public AutoDrivetrain(DcMotorEx leftEncoder, DcMotorEx rightEncoder, DcMotorEx frontEncoder, DcMotor[]motors){
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        this.frontEncoder = frontEncoder;

        leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
        public void foward(int x){
                //Set x to ticks for 1 mat
                leftTarget = this.leftEncoder.getCurrentPosition()+x;
                rightTarget = this.rightEncoder.getCurrentPosition()+x;

        }
        public void clockwise(int x){
                //Set x to # ticks for rotation
                leftTarget = this.leftEncoder.getCurrentPosition()+x;
                rightTarget = this.rightEncoder.getCurrentPosition()-x;
        }
        public void counterclockwise(int x){
                //Set x to # ticks for rotation
                leftTarget = this.leftEncoder.getCurrentPosition()-x;
                rightTarget = this.rightEncoder.getCurrentPosition()+x;
        }
        public void tLoop(){

                double leftEncoderPosition = leftEncoder.getCurrentPosition();
                double rightEncoderPosition = rightEncoder.getCurrentPosition();

                double left_current_error = leftTarget-leftEncoderPosition;
                double right_current_error = rightTarget-rightEncoderPosition;

                lPower = Kp*left_current_error;
                rPower = Kp*right_current_error;
                motors[0].setPower(rPower);
                motors[1].setPower(rPower);
                motors[2].setPower(lPower);
                motors[3].setPower(lPower);
        }
    }

