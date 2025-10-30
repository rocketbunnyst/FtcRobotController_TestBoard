package org.firstinspires.ftc.teamcode.learnjavaforFTC;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class HelloWorld extends OpMode {

    @Override
    public void init() {
        telemetry.addData("Hello","World");
        telemetry.addData("Hi","World");
        telemetry.addData("Nice to","meet you");
        telemetry.addData("Your motherboard","looks nice today");
        telemetry.addData("Are those ","new circuits?");
    }

    @Override
    public void loop() {

    }
}
