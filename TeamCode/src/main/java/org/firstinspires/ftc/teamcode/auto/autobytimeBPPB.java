package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotHardware;


@Autonomous(name = "Robot: Auto By time BPPB", group = "Robot")

public class autobytimeBPPB extends LinearOpMode{

    RobotHardware robot = new RobotHardware(this);
    public ElapsedTime runtime = new ElapsedTime();

    static final double FORWARD_SPEED = 0.5;
    static final double STRAFE_SPEED = 0.5;
    static final double TURN_SPEED = 0.4;

    double drive = 0;
    double strafe  = 0;
    double turn = 0;

    @Override
    public void runOpMode(){

            robot.init();
    waitForStart();

    //drive to net zone
        robot.driveRobotCentric(-0.5, 0, 0);
        robot.setHorizontalPosition(0.5);
        robot.setIntakePosition(0.05);
    sleep(1300);

        robot.driveRobotCentric(0,0,0);
    sleep(100);
    //aim to basket
        robot.driveRobotCentric(0, -0.5, -0.6);
    sleep(200);

        robot.driveRobotCentric(0, 0, 0);
        robot.setSlidePower(1);
    sleep(800);
        robot.setVerticalPower(-1);
    sleep(2000);
        robot.setSlidePower(-1);
        robot.setVerticalPower(1);
    sleep(850);
        robot.setSlidePower(0);

    robot.driveRobotCentric(0.75, 0.5, -1);
    sleep(300);
    robot.driveRobotCentric(0, 0.75, -1);
    sleep(100);
    robot.driveRobotCentric(0.75, 0, 0);
    sleep(1100);
    robot.driveRobotCentric(0.0,-0.75, 0);
    sleep(500);
    robot.driveRobotCentric(-0.75, 0, 0);
    sleep(1200);
    robot.driveRobotCentric(0, 0, 0);
    sleep(300);
    robot.driveRobotCentric(0.75, 0.75, 0);
    sleep(100);
    robot.driveRobotCentric(0.75, 0, 0);
    sleep(1100);
    robot.driveRobotCentric(0.0,-0.75, 0);
    sleep(200);
    robot.driveRobotCentric(-0.75, 0, 0);
    sleep(1200);
    robot.driveRobotCentric(0, 0, 0);
    sleep(100);
    robot.driveRobotCentric(0.5, 0,0);
    sleep(200);
    robot.driveRobotCentric(0, 0, 0);
    sleep(100);
    robot.driveRobotCentric(0, 0.5, -0.4);
    sleep(200);




}

}

