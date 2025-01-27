//package org.firstinspires.ftc.teamcode.auto;
//
//
//import androidx.annotation.NonNull;
//
//// RR-specific imports
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.SequentialAction;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.acmerobotics.roadrunner.*;
//import com.acmerobotics.roadrunner.ftc.Actions;
//
//// Non-RR imports
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import org.firstinspires.ftc.teamcode.MecanumDrive;
//import org.firstinspires.ftc.teamcode.RobotHardware;
//
//import java.lang.Math;
//
//
//@Autonomous(name =  "Auto By RoadRunner 100pts test", group = "Robot")
//@Disabled
//public class AutoByRR100test extends LinearOpMode{
//    public static class slides {
//        private DcMotor slideMotorL;
//        private DcMotor slideMotorR;
//
//        public slides(HardwareMap hardwareMap) {
//            slideMotorL = hardwareMap.get(DcMotorEx.class, "SlideMotorL");
//            slideMotorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            slideMotorL.setDirection(DcMotor.Direction.FORWARD);
//
//            slideMotorR = hardwareMap.get(DcMotorEx.class, "SlideMotorR");
//            slideMotorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            slideMotorR.setDirection(DcMotor.Direction.REVERSE);
//        }
//        public class runSlides implements Action {
//            private boolean initialized = false;
//
//            @Override
//            public boolean run(@NonNull TelemetryPacket packet){
//                if(!initialized){
//                    slides.setPower(0.8);
//                    initialized = true;
//                }
//
//                double pos = slides.getCurrentPosition();
//                packet.put("slidePos") , pos);
//                if (pos <725){
//                    return true;
//                } else {
//                    slides.setPower(0);
//                    return false;
//                }
//            }
//        }
//
//    }
//    @Override
//    public void runOpMode(){
//        Pose2d initialPose = new Pose2d(0, 0, -Math.PI/2);
//        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
//
//
//
//
//        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
//                .lineToY(12);
//
//
//
//
//
//
//
//        Action trajectoryActionCloseOut = tab1.endTrajectory().fresh()
//                .build();
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//        Action trajectoryActionChosen;
//        trajectoryActionChosen = tab1.build();
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        trajectoryActionChosen,
//                        trajectoryActionCloseOut
//                )
//        );
//    }
//}