package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
/* 
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.configuration.annotations.ServoType;
import com.qualcomm.robotcore.hardware.OdometryPod; - Doesn't exist
OdometryPod is not a standard built-in class name in the FTC SDK for 
standard motor encoders; odometry pods are typically read using 
regular DcMotor or encoder objects (like Encoder or OverflowEncoder) 
unless you are using a specific custom library.
*/

@TeleOp(name = "Odometry_DT")
public class Odometry_DT extends LinearOpMode {
    /*
    Extending OpMode vs. LinearOpMode: OpMode requires you to handle 
    everything in an event-driven loop() cycle that constantly repeats. 
    Most FTC teams use LinearOpMode, which lets you use a while 
    (opModeIsActive()) loop and is much easier to read and debug.
    */

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private DcMotor thrower, lift;
    private Servo servoDoor;

    @Override
    public void runOpMode() {
        // Initialize Hardware
        frontLeft  = hardwareMap.get(DcMotor.class, "Front Left");
        frontRight = hardwareMap.get(DcMotor.class, "Front Right");
        backLeft   = hardwareMap.get(DcMotor.class, "Back Left");
        backRight  = hardwareMap.get(DcMotor.class, "Back Right");
        thrower    = hardwareMap.get(DcMotor.class, "Thrower");
        lift       = hardwareMap.get(DcMotor.class, "Lift");
        servoDoor  = hardwareMap.get(Servo.class, "Servo_Door");

        // Set Motor Directions (Do this once during init)
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
        thrower.setDirection(DcMotorSimple.Direction.FORWARD);

        // Reset Lift Encoder
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Ready to run!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Basic Tank / Mecanum Drive Math
            double forward = -gamepad1.left_stick_y; // Inverted Y-axis
            double turn    = gamepad1.right_stick_x;
            double strafe  = gamepad1.left_x;

            // Simple power distribution
            double flPower = forward + turn + strafe;
            double blPower = forward + turn - strafe;
            double frPower = forward - turn - strafe;
            double brPower = forward - turn + strafe;

            frontLeft.setPower(flPower);
            backLeft.setPower(blPower);
            frontRight.setPower(frPower);
            backRight.setPower(brPower);

            // Bumper Strafe Overrides
            if (gamepad1.right_bumper) {
                frontLeft.setPower(-1);
                backLeft.setPower(1);
                frontRight.setPower(-1);
                backRight.setPower(1);
            } else if (gamepad1.left_bumper) {
                frontLeft.setPower(1);
                backLeft.setPower(-1);
                frontRight.setPower(1);
                backRight.setPower(-1);
            }

            // Thrower Control
            if (gamepad2.x) {
                thrower.setPower(0.80);
            } else {
                thrower.setPower(0);
            }

            // Servo Door Control
            if (gamepad2.right_trigger > 0) {
                servoDoor.setPosition(1);
            } else if (gamepad2.left_trigger > 0) {
                servoDoor.setPosition(0);
            }

            // Telemetry
            telemetry.addData("Lift Position", lift.getCurrentPosition());
            telemetry.update();
        }
    }
}
