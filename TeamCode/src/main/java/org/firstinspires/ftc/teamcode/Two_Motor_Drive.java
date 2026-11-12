package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Two_Motor_Drive")
public class Two_Motor_Drive extends OpMode

{
    DcMotor Right_Side;
    DcMotor Left_Side;


    int time;

    @Override

    public void init()
    {
        Right_Side = hardwareMap.dcMotor.get("Right_Side");
        Left_Side = hardwareMap.dcMotor.get("Left_Side");

        telemetry.addData("Status", "Ready!");
        telemetry.update();
    }

    @Override

    public void loop()
    {
        Right_Side.setDirection(DcMotorSimple.Direction.REVERSE);
        Left_Side.setDirection(DcMotorSimple.Direction.FORWARD);

        Right_Side.setPower(gamepad1.right_stick_y);
        Left_Side.setPower(gamepad1.left_stick_y);

        telemetry.addData("[Right Motor Speed]",
                Right_Side.getPower());
        telemetry.addData("[Left Motor Speed]",
                Left_Side.getPower());
        telemetry.update();
    }
}
