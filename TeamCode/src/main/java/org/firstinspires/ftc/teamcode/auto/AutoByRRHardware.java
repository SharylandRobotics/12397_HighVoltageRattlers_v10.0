package org.firstinspires.ftc.teamcode.auto;


// RR-specific imports

import com.acmerobotics.roadrunner.*;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.lang.Math;



@Autonomous(name =  "Auto By RoadRunner implementation", group = "Robot")
public class AutoByRRHardware extends LinearOpMode{

    RobotHardware robot = new RobotHardware(this);
    @Override
    public void runOpMode(){

        Pose2d initialPose = new Pose2d(9.5, -61.25, -Math.PI/2);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        robot = new RobotHardware(this);
        robot.init();


        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .lineToY(-30);



        Action trajectoryActionCloseOut = tab1.endTrajectory().fresh()
                .build();

        waitForStart();

        if (isStopRequested()) return;

        Action trajectoryActionChosen;
        trajectoryActionChosen = tab1.build();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen,
                        robot.setSlidePosition(robot.SLIDE_HIGH_BASKET),
                        trajectoryActionCloseOut
                )
        );
    }
}