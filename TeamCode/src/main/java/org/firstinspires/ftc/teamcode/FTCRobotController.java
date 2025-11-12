package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.configuration.annotations.ServoType;

@TeleOp (name = "Drive")
public class FTCRobotController extends OpMode
{
    DcMotor Front_Left;
    DcMotor Front_Right;
    DcMotor Back_Left;
    DcMotor Back_Right;
    //Servo Drive_Const;
    //Servo Drive_Pos;


    int time;

    @Override
    public void init()
    {
        Front_Left = hardwareMap.dcMotor.get("Front Left");
        Front_Right = hardwareMap.dcMotor.get("Front Right");
        Back_Left = hardwareMap.dcMotor.get("Back Left");
        Back_Right = hardwareMap.dcMotor.get("Back Right");
        //Drive_Const = hardwareMap.servo.get("Const");
        //Drive_Pos = hardwareMap.servo.get("Pos");

        telemetry.addData("Status", "Ready to run!");
        telemetry.update();

    }

    @Override
    public void loop()
    {
        Front_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Front_Left.setDirection(DcMotorSimple.Direction.FORWARD);
        Back_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Back_Left.setDirection(DcMotorSimple.Direction.FORWARD);

        Front_Right.setPower(gamepad1.right_stick_y);
        Front_Left.setPower(gamepad1.left_stick_y);
        Back_Right.setPower(gamepad1.right_stick_y);
        Back_Left.setPower(gamepad1.left_stick_y);

        if (gamepad1.right_trigger > 0)
        {
            Front_Right.setPower(0.5);
            Front_Left.setPower(-0.5);
            Back_Right.setPower(-0.5);
            Back_Left.setPower(0.5);
        }
        else if (gamepad1.left_trigger > 0)
        {
            Front_Right.setPower(-0.5);
            Front_Left.setPower(0.5);
            Back_Right.setPower(0.5);
            Back_Left.setPower(-0.5);
        }

        telemetry.addData("[Front Left Odometer]",
                Front_Right.getCurrentPosition());
        telemetry.addData("[Front Right Odometer]",
                Back_Left.getCurrentPosition());
        telemetry.addData("[Back Odometer]",
                Front_Left.getCurrentPosition());

    }
}
