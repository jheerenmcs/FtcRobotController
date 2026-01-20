package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous(name = "Auto Ball Shooter Blue")
public class Auto_Shooter_Blue extends LinearOpMode
{
    DcMotor Front_Left;
    DcMotor Front_Right;
    DcMotor Back_Left;
    DcMotor Back_Right;
    DcMotor Thrower;
    Servo Servo_Door;

    private ElapsedTime     runtime = new ElapsedTime();

    //int saveposition;

    @Override
    public void runOpMode () throws InterruptedException
    {
        Front_Left = hardwareMap.dcMotor.get("Front Left");
        Front_Right = hardwareMap.dcMotor.get("Front Right");
        Back_Left = hardwareMap.dcMotor.get("Back Left");
        Back_Right = hardwareMap.dcMotor.get("Back Right");
        Thrower = hardwareMap.dcMotor.get("Thrower");
        Servo_Door = hardwareMap.servo.get("Servo_Door");

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

        //First Step
        Front_Right.setPower(-0.5);
        Front_Left.setPower(-0.5);
        Back_Right.setPower(-0.5);
        Back_Left.setPower(-0.5);
        sleep(100);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5 ))
        {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        //Second Step
        Front_Right.setPower(-0.5);
        Front_Left.setPower(0);
        Back_Right.setPower(-0.5);
        Back_Left.setPower(0);
        sleep(250);
        /*Thrower.setTargetPosition(-48000);
        Thrower.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Thrower.setPower(0.75);
        */
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.25))
        {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }



        //Third Step
        Front_Right.setPower(0.5);
        Front_Left.setPower(0.5);
        Back_Right.setPower(0.5);
        Back_Left.setPower(0.5);
        sleep(1000);

        telemetry.addData("Path", "Complete");
        telemetry.update();

        //stop
        Front_Right.setPower(0);
        Front_Left.setPower(0);
        Back_Right.setPower(0);
        Back_Left.setPower(0);
        sleep(1000);
        Thrower.setPower(0.77);
        sleep(1000);

        //FIRE!!!!!
        runtime.reset();
        while (runtime.seconds() < 1) {
           /* if (runtime.equals(1)) {
                Servo_Door.setPosition(1);
            }*/
            Servo_Door.setPosition(1);
        }

        runtime.reset();

        while (runtime.seconds() < 1) {
           /* if (runtime.equals(1)) {
                Servo_Door.setPosition(0);
            }*/
            Servo_Door.setPosition(0);
        }


        Front_Right.setPower(0);
        Front_Left.setPower(0);
        Back_Right.setPower(0);
        Back_Left.setPower(0);
        sleep(1000);

        //Back up
        Front_Right.setPower(0.5);
        Front_Left.setPower(0.5);
        Back_Right.setPower(0.5);
        Back_Left.setPower(0.5);
        sleep(250);





    }
}