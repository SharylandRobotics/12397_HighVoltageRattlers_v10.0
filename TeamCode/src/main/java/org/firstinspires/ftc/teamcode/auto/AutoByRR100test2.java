package org.firstinspires.ftc.teamcode.auto;


// RR-specific imports

import com.acmerobotics.roadrunner.*;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.lang.Math;


@Autonomous(name =  "Auto By RoadRunner 100pts test2", group = "Robot")
public class AutoByRR100test2 extends LinearOpMode{
    RobotHardware robot = new RobotHardware(this);

    @Override
    public void runOpMode(){
        robot.init();
        Pose2d initialPose = new Pose2d(0, 0, -Math.PI/2);
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);


        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .afterTime(0, () -> {
                    robot.setIntakePosition(1);
                    robot.setOutClawPosition(1);
                    robot.setVerticalPower(0);
                })
                .lineToY(12)
                .afterTime(1, () -> {
                    robot.setVerticalPower(0);
                })
                .stopAndAdd(() ->{
                    robot.slidePosition = robot.SLIDE_HIGH_BASKET;
                    robot.setSlidePosition();
                })
                .waitSeconds(3)
                .stopAndAdd(() -> {
                    robot.setOutClawPosition(0);
                    robot.setVerticalPower(1);
                })

                .setTangent(0)
                .lineToX(10);


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