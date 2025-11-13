package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.configuration.Utility;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import java.util.concurrent.locks.Lock;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@Disabled
@Autonomous(name = "Auto 2 if we need it but not main option so dont click unless someone says otherwise but until then dont use it.")

public class Blue_2 extends LinearOpMode {

    DcMotor Front_Left;
    DcMotor Front_Right;
    DcMotor Back_Left;
    DcMotor Back_Right;
    DcMotor Wrist;
    DcMotor Lift;
    Servo Claw;
    int saveposition;


    @Override
    public void runOpMode () throws InterruptedException {

        Front_Left = hardwareMap.dcMotor.get("Front Left");
        Front_Right = hardwareMap.dcMotor.get("Front Right");
        Back_Left = hardwareMap.dcMotor.get("Back Left");
        Back_Right = hardwareMap.dcMotor.get("Back Right");
        Wrist = hardwareMap.dcMotor.get("Wrist");
        Lift = hardwareMap.dcMotor.get("Lift");
        Claw = hardwareMap.servo.get("Claw");

        //Set motor directions
        Front_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Front_Left.setDirection(DcMotorSimple.Direction.FORWARD);
        Back_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Back_Left.setDirection(DcMotorSimple.Direction.FORWARD);
        Lift.setDirection(DcMotorSimple.Direction.REVERSE);
        Wrist.setDirection(DcMotorSimple.Direction.REVERSE);


        //Telemtry start data
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

        sleep(10000);

        Front_Left.setPower(.6);
        Front_Right.setPower(.6);
        Back_Left.setPower(.6);
        Back_Right.setPower(.6);

        sleep(2100);


    }

}
