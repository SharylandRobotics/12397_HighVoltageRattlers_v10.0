package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotHardware;

@Autonomous(name =  "Auto By Enconder 100pts", group = "Robot")

public class AutoByEncoder extends LinearOpMode{
    RobotHardware robot = new RobotHardware(this);
    ElapsedTime runttime = new ElapsedTime();

    @Override
    public void runOpMode(){
        robot.init();

        waitForStart();
        runttime.reset();

        robot.driveEncoder(0.5, 1,1 ,1, 1, 1);
    }
}
