package org.firstinspires.ftc.teamcode.Framework;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class AutoDrivetrain {

         static double leftTarget;
         static double rightTarget;
         double lPower;
         double rPower;
         double Kp = .00005;
         double allowableDistanceError;
        static DcMotor[] motors;

    public AutoDrivetrain(DcMotor[]motors){
        this.motors = motors;

        motors[2].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors[3].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors[0].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motors[2].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors[3].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors[0].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motors[0].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[1].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[2].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motors[3].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //generally 1 mat is -2000 ticks
        public static void moveForward(int x){
                //Set x to ticks for 1 mat
                leftTarget = motors[2].getCurrentPosition()+x;
                rightTarget = motors[3].getCurrentPosition()+x;
    }
        public static void moveClockwise(int x){
        // generally 9000ish? seems like left goes by less for some reason
                //Set x to # ticks for rotation
                leftTarget = motors[2].getCurrentPosition()+x;
                rightTarget = motors[3].getCurrentPosition()-x;
        }
        public static void moveCounterclockwise(int x){
        //-9000
                //Set x to # ticks for rotation
                leftTarget = motors[2].getCurrentPosition()-x;
                rightTarget = motors[3].getCurrentPosition()+x;
        }
        public void tLoop(){
                //returns false when done

                double leftEncoderPosition = motors[2].getCurrentPosition();
                double rightEncoderPosition = motors[3].getCurrentPosition();

                double left_current_error = leftTarget-leftEncoderPosition;
                double right_current_error = rightTarget-rightEncoderPosition;

                lPower = Kp*left_current_error;
                rPower = Kp*right_current_error;

                if(lPower>0.3){
                    lPower=0.3;
                }
                if(rPower>0.3){
                    rPower=0.3;
                }


                motors[0].setPower(rPower);
                motors[1].setPower(rPower);
                motors[2].setPower(lPower);
                motors[3].setPower(lPower);
        }

    }

