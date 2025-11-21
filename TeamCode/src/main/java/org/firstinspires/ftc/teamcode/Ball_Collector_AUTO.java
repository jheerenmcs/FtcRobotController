package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@Autonomous(name = "Ball Collector")
public class Ball_Collector_AUTO extends LinearOpMode
{
    DcMotor Front_Left;
    DcMotor Front_Right;
    DcMotor Back_Left;
    DcMotor Back_Right;

    int saveposition;


    @Override
    public void runOpMode () throws InterruptedException
    {
        Front_Left = hardwareMap.dcMotor.get("Front Left");
        Front_Right = hardwareMap.dcMotor.get("Front Right");
        Back_Left = hardwareMap.dcMotor.get("Back Left");
        Back_Right = hardwareMap.dcMotor.get("Back Right");

        Front_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Front_Left.setDirection(DcMotorSimple.Direction.FORWARD);
        Back_Right.setDirection(DcMotorSimple.Direction.REVERSE);
        Back_Left.setDirection(DcMotorSimple.Direction.FORWARD);

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
        
    }
}
