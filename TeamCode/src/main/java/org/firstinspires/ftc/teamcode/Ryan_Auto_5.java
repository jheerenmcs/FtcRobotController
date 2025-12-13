package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//This program is an autonomous that is a copy of "Ryan Auto." All that's different is there is a five second delay before starting moving.
@Autonomous(name = "Auto 5s Delay")
public class Ryan_Auto_5 extends LinearOpMode
{
    DcMotor Front_Left;
    DcMotor Front_Right;
    DcMotor Back_Left;
    DcMotor Back_Right;
    DcMotor Thrower;
    //Servo Servo_Door;

    private ElapsedTime runtime = new ElapsedTime();

    int saveposition;

    @Override
    public void runOpMode () throws InterruptedException
    {
        Front_Left = hardwareMap.dcMotor.get("Front Left");
        Front_Right = hardwareMap.dcMotor.get("Front Right");
        Back_Left = hardwareMap.dcMotor.get("Back Left");
        Back_Right = hardwareMap.dcMotor.get("Back Right");
        Thrower = hardwareMap.dcMotor.get("Thrower");
        // Servo_Door = hardwareMap.servo.get("Servo_Door");

        //Set motor directions
        Front_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Front_Left.setDirection(DcMotorSimple.Direction.FORWARD);
        Back_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Back_Left.setDirection(DcMotorSimple.Direction.FORWARD);
        Thrower.setDirection(DcMotorSimple.Direction.FORWARD);

        //Telemetry start data
        telemetry.addData("[Front Right Motor Power]",
                Front_Right.getPower());
        telemetry.addData("[Front Left Motor Power]",
                Front_Left.getPower());
        telemetry.addData("[Back Left Motor Power]",
                Back_Left.getPower());
        telemetry.addData("[Back Right Motor Power]",
                Back_Right.getPower());
        telemetry.addData("[Dead Wheel Left]",
                Front_Right.getCurrentPosition());
        telemetry.addData("[Dead Wheel Right]",
                Front_Left.getCurrentPosition());
        telemetry.addData( "[Dead Wheel Middle]",
                Back_Left.getCurrentPosition());
        telemetry.update();

        waitForStart();
        sleep(5000);


        Front_Right.setPower(-0.5);
        Front_Left.setPower(-0.5);
        Back_Right.setPower(-0.5);
        Back_Left.setPower(-0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5))
        {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(1000);
    }
}
