package org.firstinspires.ftc.teamcode.Framework;

import org.firstinspires.ftc.teamcode.Framework.BaseOpMode;

public class AutoDrivetrain {

        static DcMotorEx leftEncoder;
        static DcMotorEx rightEncoder;
        static DcMotorEx frontEncoder;
        static double leftTarget;
        static double rightTarget;
        static double lpower;
        static double rpower;
        static double Kp = 0.01;
        DcMotr[]motors}

    public Auto(DcMotorEx leftEncoder, DcMotorEx rightEncoder, DcMotorEx frontEncoder,DcMotor[]motors){
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        this.frontEncoder = frontEncoder;

        leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int leftEncoderPosition = leftEncoder.getCurrentPosition();
        int rightEncoderPosition = rightEncoder.getCurrentPosition();
        int frontEncoderPosition = frontEncoder.getCurrentPosition();
        telemetry.addData("LeftEncoder", leftencoder.getCurrentPosition());
        telemetry.addData("RightEncoder", rightEncoder.getCurrentPosition());
        telemetry.addData("FrontEncoder", frontEncoder.getCurrentPosition());
        telemetry.update();
    }
        public static void foward(int x){
                //Set x to ticks for 1 mat
                leftTarget = this.leftEncoder.getCurrentPosition()+x;
                rightTarget = this.rightEncoder.getCurrentPosition()+xx;

        }
        public static void clockwise(int x){
                //Set x to # ticks for rotation
                leftTarget = this.leftEncoder.getCurrentPosition()+x;
                rightTarget = this.rightEncoder.getCurrentPosition()-x;
        }
        public static void counterclockwise(int x){
                //Set x to # ticks for rotation
                leftTarget = this.leftEncoder.getCurrentPosition()-x;
                rightTarget = this.rightEncoder.getCurrentPosition()+x;
        }
        public void tLoop(){

                double leftEncoderPosition = leftEncoder.getCurrentPosition();
                double rightEncoderPosition = rightEncoder.getCurrentPosition();

                double left_current_error = Math.abs(leftTarget-leftEncoderPosition);
                double right_current_error = Math.abs(rightTarget-rightEncoderPosition);

                lPower = Kp*left_current_error;
                rPower = Kp*right_current_error;
                motors[0] = .setPower(rPower);
                motors[1] = .setPower(rPower);
                motors[2] = .setPower(lPower)
                motors[3] = .setPower(lPower)
        }
    }
}
