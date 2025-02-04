package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class MeepMeepTesting {
    public static void main(String args[]){
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(9.5, -61.25, Math.toRadians(270)))
                        .lineTo(new Vector2d(9.5, -32.5))
                        .waitSeconds(3)
                        .strafeLeft(28)
                        .setTangent(-Math.PI / 2)
                        .splineToConstantHeading(new Vector2d(0, -32.5), 0)
                        .forward(40)
                        .setTangent(Math.PI / 2)
                        .splineToConstantHeading(new Vector2d(56, -11), 0)
                        .forward(40)
                        .setTangent(Math.PI / 2)
                        .splineToConstantHeading(new Vector2d(61, -13), 0)
                        .forward(40)
                        .setTangent(-Math.PI/2)
                        .splineToConstantHeading(new Vector2d(8, -31),0)
                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }
}