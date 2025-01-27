package org.firstinspires.ftc.teamcode.auto;


// RR-specific imports
import com.acmerobotics.roadrunner.*;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.lang.Math;



@Autonomous(name =  "Auto By RoadRunner 100pts", group = "Robot")
public class AutoByRR100 extends LinearOpMode{
    RobotHardware robot = new RobotHardware(this);

    @Override
    public void runOpMode(){
        robot.init();
        Pose2d initialPose = new Pose2d(9.5, -61.25, -Math.PI/2);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .afterTime(0, () -> {
                    robot.setIntakePosition(1);
                    robot.setOutClawPosition(1);
                    robot.setVerticalPower(0);
                })
                .lineToY(-34)
                .stopAndAdd(() ->{
                    robot.slidePosition = robot.SLIDE_HIGH_BASKET;
                    robot.setSlidePosition();
                })
                .waitSeconds(1)
                .stopAndAdd(() -> {
                    robot.setOutClawPosition(0);
                    robot.setVerticalPower(1);
                })
                .setTangent(0)
                .lineToX(36)
                .setTangent(Math.PI / 2)
                .splineToConstantHeading(new Vector2d(47, -9), 0)
                .setTangent(Math.PI /2)
                .lineToY(-50)
                .setTangent(Math.PI / 2)
                .splineToConstantHeading(new Vector2d(56, -11), 0)
                .setTangent(Math.PI / 2)
                .lineToY(-50)
                .setTangent(Math.PI / 2)
                .splineToConstantHeading(new Vector2d(64, -13), 0)
                .setTangent(Math.PI / 2)
                .lineToY(-50)
                .stopAndAdd(() -> {
                    robot.setIntakePosition(0);
                })
                .waitSeconds(.5)
                .stopAndAdd(() ->{
                    robot.setInClawPosition(1);
                    robot.setIntakePosition(1);
                });
//                .setTangent(Math.PI / 2);
                //.lineToConstantHeading(new Vector2d(0, -32.5));


        Action trajectoryActionCloseOut = tab1.endTrajectory().fresh()
                .build();

        waitForStart();

        if (isStopRequested()) return;

        Action trajectoryActionChosen;
        trajectoryActionChosen = tab1.build();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen,
                        trajectoryActionCloseOut
                )
        );
    }
}