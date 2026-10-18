package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
/* 
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.configuration.annotations.ServoType;
import com.qualcomm.robotcore.hardware.OdometryPod; <- Doesn't exist
OdometryPod is not a standard built-in class name in the FTC SDK for 
standard motor encoders; odometry pods are typically read using 
regular DcMotor or encoder objects (like Encoder or OverflowEncoder) 
unless you are using a specific custom library.
*/

@TeleOp (name = "Odometry_DT")
public class Odometry_DT extends OpMode 
{
    // Drive Motors
    DcMotor Front_Left;
    DcMotor Front_Right;
    DcMotor Back_Left;
    DcMotor Back_Right;

    // Mechanism Motors/Servos
    DcMotor Thrower;
    DcMotor Lift;
    Servo Servo_Door;

    /* 
     * NOTE: Changed from "OdometryPod" to "DcMotor". 
     * In the standard FTC SDK, odometry wheels are plugged into encoder ports 
     * on the Control/Expansion hubs and read as standard DcMotor objects.
     */
    DcMotor X_Axis;
    DcMotor Y_Axis;

    int time; // Initializes an integer named time

    @Override
    public void init() 
    {
        // Hardware Mapping
        Front_Left = hardwareMap.dcMotor.get("Front Left");
        Front_Right = hardwareMap.dcMotor.get("Front Right");
        Back_Left = hardwareMap.dcMotor.get("Back Left");
        Back_Right = hardwareMap.dcMotor.get("Back Right");
        Thrower = hardwareMap.dcMotor.get("Thrower");
        Lift = hardwareMap.dcMotor.get("Lift");
        Servo_Door = hardwareMap.servo.get("Servo_Door");
        
        // Maps encoder pods as standard motor encoder ports
        X_Axis = hardwareMap.dcMotor.get("X_Axis");
        Y_Axis = hardwareMap.dcMotor.get("Y_Axis");

        /* 
         * NOTE: Critical performance optimization.
         * Motor directions should ONLY be configured once inside init().
         * Putting them in loop() spams commands to the hub and causes massive lag.
         */
        Front_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Front_Left.setDirection(DcMotorSimple.Direction.FORWARD);
        Back_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Back_Left.setDirection(DcMotorSimple.Direction.FORWARD);
        Lift.setDirection(DcMotorSimple.Direction.REVERSE);
        Thrower.setDirection(DcMotorSimple.Direction.FORWARD);

        // Reset the encoder on initialization
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Status", "Ready to run!");
        telemetry.update();
    }

    @Override
    public void loop() 
    {
        /* 
         * NOTE: Replaced broken driver control hierarchy.
         * Previously, joysticks and bumpers fought for control every millisecond,
         * causing the motors to violently twitch. Now, if/else logic ensures
         * that button inputs override joystick inputs cleanly.
         */
        if (gamepad1.right_bumper) 
        {
            // Fast Strafe Right
            Front_Left.setPower(1.0);
            Front_Right.setPower(-1.0);
            Back_Left.setPower(-1.0);
            Back_Right.setPower(1.0);
        } 
        else if (gamepad1.left_bumper) 
        {
            // Fast Strafe Left
            Front_Left.setPower(-1.0);
            Front_Right.setPower(1.0);
            Back_Left.setPower(1.0);
            Back_Right.setPower(-1.0);
        } 
        else if (gamepad1.right_trigger > 0) 
        {
            // Slow Strafe Right
            Front_Left.setPower(0.65);
            Front_Right.setPower(-0.65);
            Back_Left.setPower(-0.65);
            Back_Right.setPower(0.65);
        } 
        else if (gamepad1.left_trigger > 0) 
        {
            // Slow Strafe Left
            Front_Left.setPower(-0.65);
            Front_Right.setPower(0.65);
            Back_Left.setPower(0.65);
            Back_Right.setPower(-0.65);
        } 
        else 
        {
            // Default Tank Drive (Active when no strafe buttons are pressed)
            Front_Left.setPower(gamepad1.left_stick_y);
            Back_Left.setPower(gamepad1.left_stick_y);
            Front_Right.setPower(gamepad1.right_stick_y);
            Back_Right.setPower(gamepad1.right_stick_y);
        }

        // Firing Servo Controls (Gamepad 2)
        if (gamepad2.right_trigger > 0) 
        {
            Servo_Door.setPosition(1);
        } 
        else if (gamepad2.left_trigger > 0) 
        {
            Servo_Door.setPosition(0);
        }

        // Thrower Power (Gamepad 2)
        if (gamepad2.x) 
        {
            Thrower.setPower(0.80);
        } 
        else 
        {
            Thrower.setPower(0);
        }

        // Telemetry Data (Sends live info to Driver Station)
        telemetry.addData("[Front Left Odometer]", Front_Right.getCurrentPosition());
        telemetry.addData("[Front Right Odometer]", Back_Left.getCurrentPosition());
        telemetry.addData("[Back Odometer]", Front_Left.getCurrentPosition());
        telemetry.addData("[X-Axis Odometer Position]", X_Axis.getCurrentPosition());
        telemetry.addData("[Y-Axis Odometer Position]", Y_Axis.getCurrentPosition());
        telemetry.update();        
    }
}
