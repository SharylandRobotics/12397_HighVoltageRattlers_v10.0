package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.RobotHardware;

@Autonomous(name =  "Auto By Encoder 100pts", group = "Robot")
public class AutoByEncoder100 extends LinearOpMode{
    RobotHardware robot = new RobotHardware(this);
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode(){
        robot.init();

        waitForStart();
        runtime.reset();


        //strafing is always 2 inches less than the inches stated in code

        //driving is always 1 inch more than said in code.

        //keeps slides retracted until they are used
        robot.setIntakePosition(1);
        robot.setOutClawPosition(1);
        robot.setVerticalPower(0);

        robot.driveEncoder(.75,-28.25,-28.25,-28.25,-28.25);
        robot.slidePosition = robot.SLIDE_HIGH_RUNG;
        robot.setSlidePosition();
        robot.setHorizontalPosition(0);
        sleep(1000);
        robot.slidePosition = robot.SLIDE_START;
        robot.setSlidePosition();
        robot.setOutClawPosition(0);
        robot.setVerticalPower(1);
        sleep(950);
        ///// score preload specimen ^^
        robot.driveEncoder(.4, 17.5,17.5,17.5,17.5);
        robot.driveEncoder(.8, 46.5,46.5,-46.5,-46.5);
        // intake facing submersivle^^
        robot.driveEncoder(.5,44.097,-44.097,-44.097,44.097);
        //robot is current facing leftmost sample
        robot.setHorizontalPosition(1);
        sleep(200);
        robot.setIntakePosition(0);
        sleep(750);
        robot.setInClawPosition(1);
        sleep(300);
        robot.setHorizontalPosition(0);
        robot.setIntakePosition(1);
        sleep(750);
        robot.setOutClawPosition(1);
        sleep(100);
        robot.setInClawPosition(0);
        sleep(500);
        robot.setVerticalPower(2);
        sleep(950);
        robot.setOutClawPosition(0);
        sleep(200);
        robot.setVerticalPower(1);
        robot.driveEncoder(.6,11.25,-11.25,-11.25,11.25);
        robot.setHorizontalPosition(1);
        sleep(200);
        robot.setIntakePosition(0);
        sleep(700);
        robot.setInClawPosition(1);
        sleep(300);
        robot.setIntakePosition(2);
        robot.driveEncoder(.8, -46.5,-46.5,46.5,46.5);
        robot.setInClawPosition(0);
        sleep(200);
    }
}
