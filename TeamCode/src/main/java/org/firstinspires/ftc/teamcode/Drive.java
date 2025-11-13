package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp (name = "Drive")
public class Drive extends OpMode
{
    DcMotor Front_Left;
    DcMotor Front_Right;
    DcMotor Back_Left;
    DcMotor Back_Right;
    DcMotor Lift;
    DcMotor Wrist;
    DcMotor Hang;
    Servo Claw;


    //   Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    int time;
    @Override
    public void init()
    {
        Front_Left = hardwareMap.dcMotor.get("Front Left");
        Front_Right = hardwareMap.dcMotor.get("Front Right");
        Back_Left = hardwareMap.dcMotor.get("Back Left");
        Back_Right = hardwareMap.dcMotor.get("Back Right");
        Lift = hardwareMap.dcMotor.get("Lift");
        Wrist = hardwareMap.dcMotor.get("Wrist");
        Hang = hardwareMap.dcMotor.get("Hang");
        Claw = hardwareMap.servo.get("Claw");

        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        telemetry.addData("Status", "Ready to run");
        telemetry.update();


    }
    @Override
    public void loop()
    {
        Front_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Front_Left.setDirection(DcMotorSimple.Direction.FORWARD);
        Back_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Back_Left.setDirection(DcMotorSimple.Direction.FORWARD);

        Lift.setDirection(DcMotorSimple.Direction.REVERSE);
        Wrist.setDirection(DcMotorSimple.Direction.REVERSE);
        Hang.setDirection(DcMotorSimple.Direction.FORWARD);
        //Lift.setPower()


        Front_Right.setPower(gamepad1.right_stick_y);
        Front_Left.setPower(gamepad1.left_stick_y);
        Back_Right.setPower(gamepad1.right_stick_y);
        Back_Left.setPower(gamepad1.left_stick_y);

        //Lift.setPower(gamepad2.left_stick_y);


        //Lift Position High
        if (gamepad2.left_stick_y < 0)
        {
            Lift.setTargetPosition(-4800);
            Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lift.setPower(1);
        }
        if (gamepad2.left_stick_y > 0)
        {
            Lift.setTargetPosition(0);
            Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lift.setPower(1);
            // Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        if (gamepad2.y)
        {
            Lift.setTargetPosition(-2400);
            Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lift.setPower(1);
        }
        if (gamepad2.a)
        {
            Lift.setTargetPosition(-200);
            Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lift.setPower(1);
        }
        if (gamepad2.x)
        {
            Lift.setTargetPosition(-2100);
            Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lift.setPower(1);
        }


        Wrist.setPower(gamepad2.right_stick_y / 1.75);

        if (gamepad2.dpad_up)
        {
            Hang.setPower(1);
        }
        else if (gamepad2.dpad_down)
        {
            Hang.setPower(-1);
        }
        else
        {
            Hang.setPower(0);
        }

        /*if (gamepad2.right_stick_y>0.01)
        {
            Wrist.setPower(0.75);
        }
        else if (gamepad2.right_stick_y<-0.01)
        {
           Wrist.setPower(-0.75);
        }*/

        if (gamepad1.right_trigger>0)
        {
            Front_Right.setPower(0.5);
            Front_Left.setPower(-0.5);
            Back_Right.setPower(-0.5);
            Back_Left.setPower(0.5);
        }
        else if (gamepad1.left_trigger>0)
        {
            Front_Right.setPower(-0.5);
            Front_Left.setPower(0.5);
            Back_Right.setPower(0.5);
            Back_Left.setPower(-0.5);
        }
        if (gamepad1.b)
        {
            Front_Right.setPower(1);
            Front_Left.setPower(-1);
            Back_Right.setPower(-1);
            Back_Left.setPower(1);
        }
        else if (gamepad1.x)
        {
            Front_Right.setPower(-1);
            Front_Left.setPower(1);
            Back_Right.setPower(1);
            Back_Left.setPower(-1);
        }

        if (gamepad2.right_trigger>0)
        {
            Claw.setPosition(0.4);
            //Claw.setPower(1);
        }

        else if (gamepad2.left_trigger>0)
        {
            Claw.setPosition(1);
        }


        telemetry.addData("[Front Left Odometer]",
                Front_Right.getCurrentPosition());
        telemetry.addData("[Front Right Odometer]",
                Back_Left.getCurrentPosition());
        telemetry.addData( "[Back Odometer]",
                Front_Left.getCurrentPosition());
        telemetry.addData("[Claw Position]",
                Claw.getPosition());
        telemetry.addData("[Lift Encoder Position]",
                Lift.getCurrentPosition());
        telemetry.update();
    }

}