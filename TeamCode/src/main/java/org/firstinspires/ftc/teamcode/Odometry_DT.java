package org.firstinspires.ftc.teamcode;
/*The package is like the main core.
It contains all of the files that you will call from.
 */
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.configuration.annotations.ServoType;
/*
All of the imports are what you are calling to be in your code.
Think of the package like a library, and all the imports are the books.
 */
@TeleOp (name = "Odometry_DT")
/*This is an operation mode. we uh don't really know how that works yet
We are naming this mode "Drive".
 */
public class Odometry_DT extends OpMode
    /*
    Inside the public class is where all your main code will go
     */
{
    DcMotor Front_Left;
    DcMotor Front_Right;
    DcMotor Back_Left;
    DcMotor Back_Right;

    /*
    These are calling all of the motors/what you are going to use.
    We are also giving them all names.
     */

    Servo   Drop_System;
    DcMotor Snail;
    //Ditto, but for our thrower and servo door.
    //Servo Servo_Holding;
    DcMotor Intake;


    /*Odometry
    DcMotor X_Axis;
    DcMotor Y_Axis;
     */

    int time;
    //This initializes an integer (number) named time.

    @Override
    /*
    This overrides what the original OpMode says (the template)
    this lets you make your own code.
    */
    public void init()
            /*
            This code initializes what everything is going to do
            The void means it wont return anything
            This code works when you press Init on the driver console
             */
    {
        Front_Left = hardwareMap.dcMotor.get("Front Left");
        Front_Right = hardwareMap.dcMotor.get("Front Right");
        Back_Left = hardwareMap.dcMotor.get("Back Left");
        Back_Right = hardwareMap.dcMotor.get("Back Right");

        //Servo_Holding = hardwareMap.servo.get("Servo_Holding");
        /*X_Axis = hardwareMap.dcMotor.get("X_Axis");
        Snail = hardwareMap.dcMotor.get("Snail");
        Intake = hardwareMap.dcMotor.get("Intake");

        Drop_System = hardwareMap.servo.get("Drop_System");

        /*
        This Hardware Mapping
         */
        Intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);


//  This Hardware Mapping
        telemetry.addData("Status", "Ready to run!");
        telemetry.update();
//Forwards the data from the controller to the Driver's Station
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
        //Front_Left.setPower(gamepad1.left_bumper);
        //Front_Right.setPower(gamepad1.right_bumper);

        if (gamepad1.right_bumper)
        {
            Front_Right.setPower(-1);
            Front_Left.setPower(-1);
            Back_Right.setPower(1);
            Back_Left.setPower(1);
        }
        else if (gamepad1.left_bumper)
        {
            Front_Right.setPower(1);
            Front_Left.setPower(1);
            Back_Right.setPower(-1);
            Back_Left.setPower(-1);
        }
        //This is fast strafe

        //This is slow strafe
        if (gamepad1.left_trigger > 0)
        {
            Front_Right.setPower(0.65);
            Front_Left.setPower(0.65);
            Back_Right.setPower(-0.65);
            Back_Left.setPower(-0.65);
        }
        else if (gamepad1.right_trigger > 0)
        {
            Front_Right.setPower(-0.65);
            Front_Left.setPower(-0.65);
            Back_Right.setPower(0.65);
            Back_Left.setPower(0.65);
        }
        //This is fast strafe

        //Catapult Controls.
        if (gamepad2.x)
        {
            Snail.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Snail.setTargetPosition(
                    Snail.getCurrentPosition() + 360);
            Snail.setPower(1);
        } else
        {
            Snail.setPower(0);
        }

        //Intake System
        if (gamepad2.y)
        {
            Intake.setPower(1);
        }
        else if (gamepad2.a)
        {
            Intake.setPower(-1);
        }

        //Drop System
        if (gamepad2.right_trigger > 0.1)
        {
            Drop_System.setPosition(22);
        }
        if (gamepad2.left_trigger > 0.1)
        {
            Drop_System.setPosition(0);
        }


        //Telemetry Data
        /*telemetry.addData("[Front Left Odometer]",
                Front_Right.getCurrentPosition());
        telemetry.addData("[Front Right Odometer]",
                Back_Left.getCurrentPosition());
        telemetry.addData("[Back Odometer]",
                Front_Left.getCurrentPosition());
        telemetry.addData("[X-Axis Odometer Position]",
                X_Axis.getCurrentPosition());
        telemetry.addData("[Y-Axis Odometer Position]",
                Y_Axis.getCurrentPosition());
        telemetry.update();
         */
    }
}
