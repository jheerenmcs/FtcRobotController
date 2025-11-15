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
@TeleOp (name = "Drive")
/*This is an operation mode. we uh don't really know how that works yet
We are naming this mode "Drive".
 */
public class FTCRobotController extends OpMode
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

    DcMotor Thrower;
    DcMotor Lift;
    Servo Servo_Door;
    //Ditto, but for our thrower and servo door.


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
        Thrower = hardwareMap.dcMotor.get("Thrower");
        Lift = hardwareMap.dcMotor.get("Lift");
        Servo_Door = hardwareMap.servo.get("Servo Door");

        /*
        This Hardware Mapping
         */
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Thrower.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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

        Lift.setDirection(DcMotorSimple.Direction.REVERSE);
        Thrower.setDirection(DcMotorSimple.Direction.REVERSE);

        Front_Right.setPower(gamepad1.right_stick_y);
        Front_Left.setPower(gamepad1.left_stick_y);
        Back_Right.setPower(gamepad1.right_stick_y);
        Back_Left.setPower(gamepad1.left_stick_y);
        //Front_Left.setPower(gamepad1.left_bumper);
        //Front_Right.setPower(gamepad1.right_bumper);

        if (gamepad1.right_bumper)
        {
            Front_Right.setPower(0.5);
            Front_Left.setPower(-0.5);
            Back_Right.setPower(-0.5);
            Back_Left.setPower(0.5);
        }
        else if (gamepad1.left_bumper)
        {
            Front_Right.setPower(-0.5);
            Front_Left.setPower(0.5);
            Back_Right.setPower(0.5);
            Back_Left.setPower(-0.5);
        }
        if (gamepad1.right_trigger > 0)
        {
            Front_Right.setPower(1);
            Front_Left.setPower(-1);
            Back_Right.setPower(-1);
            Back_Left.setPower(1);
        }
        else if (gamepad1.left_trigger > 0)
        {
            Front_Right.setPower(-1);
            Front_Left.setPower(1);
            Back_Right.setPower(1);
            Back_Left.setPower(-1);
        }

        //Servo_Door = hardwareMap.get(Servo_Door);

        //Lift Position High
        /*if (gamepad2.left_stick_y < 0)
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
        if (gamepad2.dpad_down)
        {
            Lift.setTargetPosition(-2400);
            Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Lift.setPower(1);
        }
        if (gamepad2.dpad_up)
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

         */

        if (gamepad2.right_trigger > 0)
        {
            Servo_Door.setPosition(0);
        }

        else if (gamepad2.left_trigger > 0)
        {
            Servo_Door.setPosition(1);
        }


        if (gamepad2.x)
        {
            Thrower.setTargetPosition(-4800);
            Thrower.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Thrower.setPower(1.0);
        }
        else
        {
            Thrower.setTargetPosition(0);
            Thrower.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Thrower.setPower(0);
        }


        if (gamepad1.right_trigger > 0)
        {
            Front_Right.setPower(1);
            Front_Left.setPower(-1);
            Back_Right.setPower(-1);
            Back_Left.setPower(1);
        }
        else if (gamepad1.left_trigger > 0)
        {
            Front_Right.setPower(-1);
            Front_Left.setPower(1);
            Back_Right.setPower(1);
            Back_Left.setPower(-1);
        }


        telemetry.addData("[Front Left Odometer]",
                Front_Right.getCurrentPosition());
        telemetry.addData("[Front Right Odometer]",
                Back_Left.getCurrentPosition());
        telemetry.addData("[Back Odometer]",
                Front_Left.getCurrentPosition());

    }
}
