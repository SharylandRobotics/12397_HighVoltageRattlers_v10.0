package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class RobotHardware {

    /* Declare OpMode members. */
    private LinearOpMode myOpMode = null;

    public ElapsedTime runtime = new ElapsedTime();

    public DcMotor leftFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor rightBackDrive = null;

    public DcMotor slideMotor = null;

    private Servo vertical = null;
    private Servo horizontal1 = null;
    private Servo horizontal2 = null;
    private Servo intake = null;
    private Servo lextend = null;
    private Servo rextend = null;

    // public static final double MID_SERVO = 0.8 ;
    public static final double SERVO_UP = 0.90;
    public static final double SERVO_DOWN = -0.90;
    public static final double SLIDE_UP_POWER = 1.0;
    public static final double SLIDE_DOWN_POWER = -0.50;

//    public double SLIDE_TICKS_PER_DEGREE = 28.0 * 60.0 / 360.0;
//
//    public double SLIDE_START = 0.0 * SLIDE_TICKS_PER_DEGREE;
//    public double SLIDE_LOW_BASKET = 360.0 * SLIDE_TICKS_PER_DEGREE;
//    public double SLIDE_HIGH_BASKET = 720.0 * SLIDE_TICKS_PER_DEGREE;
//
//    public double slidePosition = (int)SLIDE_START;


    public RobotHardware(LinearOpMode OpMode) {myOpMode = OpMode;}

    /**
     * Initialize all the robot's hardware.
     * This method must be called ONCE when the OpMode is initialized.
     * <p>
     * All of the hardware devices are accessed via the hardware map, and initialized.
     */

    public void init() {
        // Define and Initialize Motors (note: need to use reference to actual OpMode).
        leftFrontDrive = myOpMode.hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = myOpMode.hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = myOpMode.hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = myOpMode.hardwareMap.get(DcMotor.class, "right_back_drive");
        slideMotor = myOpMode.hardwareMap.get(DcMotor.class, "slide");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point on opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels. Gear Reduction or 90 Deg drives mat require direction flips.
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



//        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Define and initialize ALL installed servos.
        vertical = myOpMode.hardwareMap.get(Servo.class, "vertical");
        horizontal1 = myOpMode.hardwareMap.get(Servo.class, "horizontal1");
        horizontal2 = myOpMode.hardwareMap.get(Servo.class, "horizontal2");
        intake = myOpMode.hardwareMap.get(Servo.class, "intake");
        lextend = myOpMode.hardwareMap.get(Servo.class, "lextend");
        rextend = myOpMode.hardwareMap.get(Servo.class, "rextend");


        myOpMode.telemetry.addData(">", "Hardware Initialized");
        myOpMode.telemetry.update();
    }

    /**
     * Calculates the left/right motor powers required to achieve the requested to achieve the requested
     * robot motions: Drive (Axial motion) and Turn (Yaw motion).
     * Then sends these power levels to the motors.
     *
     * @param Drive     Fwd/Rev driving power(-1.0 to 1.0) +ve is forward
     * @param Turn      Right/Left turning power(-1.0 to 1.0) +ve is CW
     * @param Strafe
     */
    public void driveRobotCentric(double Drive,double Strafe, double Turn){
        //Combine drive and turn for blended motion.
        double leftFront = Drive + Strafe + Turn;
        double leftBack = Drive - Strafe + Turn;
        double rightFront = Drive - Strafe - Turn;
        double rightBack = Drive + Strafe -  Turn;

        //Scale the values so neither exceed +/-1.0
        double max = Math.max(Math.abs(leftFront), Math.abs(rightFront));
        max = Math.max(max, Math.abs(leftBack));
        max = Math.max(max, Math.abs(rightBack));

        if (max > 1.0)
        {
            leftFront /= max;
            leftBack /= max;
            rightFront /= max;
            rightBack /= max;
        }

        //Use existing function to drive both wheels.
        setDrivePower(leftFront, leftBack, rightFront, rightBack);
    }

    public void driveFieldCentric(double Drive,double Turn, double Strafe){
        //Combine drive and turn for blended motion.
        double leftFront = Drive + Strafe + Turn;
        double leftBack = Drive - Strafe + Turn;
        double rightFront = Drive - Strafe - Turn;
        double rightBack = Drive + Strafe -  Turn;

        //Scale the values so neither exceed +/-1.0
        double max = Math.max(Math.abs(leftFront), Math.abs(rightFront));
        max = Math.max(max, Math.abs(leftBack));
        max = Math.max(max, Math.abs(rightBack));

        if (max > 1.0)
        {
            leftFront /= max;
            leftBack /= max;
            rightFront /= max;
            rightBack /= max;
        }

        //Use existing function to drive both wheels.
        setDrivePower(leftFront, leftBack, rightFront, rightBack);
    }

    /**
     * Pass the requested wheel motor powers to the appropriate hardware drive motors.
     *
     * @param leftFrontWheel     Fwd/Rev driving power (-1.0 to 1.0) +ve is forward
     * @param leftBackWheel     Fwd/Rev driving power (-1.0 to 1.0) +ve is forward
     * @param rightFrontWheel    Fwd/Rev driving power (-1.0 to 1.0) +ve is forward
     * @param rightBackWheel    Fwd/Rev driving power (-1.0 to 1.0) +ve is forward
     */
    public void setDrivePower(double leftFrontWheel, double leftBackWheel, double rightFrontWheel, double rightBackWheel) {
        //Output the values to the motor drives.
        leftFrontDrive.setPower(leftFrontWheel);
        leftBackDrive.setPower(leftBackWheel);
        rightFrontDrive.setPower(rightFrontWheel);
        rightBackDrive.setPower(rightBackWheel);
    }

    /**
     *  Pass the requested slide power to the appropriate hardware drive motor
     *
     * @param power driving power (-1.0 to 1.0)
     */
    public void setSlidePower(double power) {slideMotor.setPower(power); }

    /**
     * Send the two hand-servos to opposing (mirrored) positions, based on the passed offset.
     * the extends are what extend the slides
     * @param offset
     */

    public void setIntakePosition(double offset){
        if (offset == 1) {
            lextend.setPosition(0.10);
            // max 0.25
            rextend.setPosition(1);
            //0.9
        } else if (offset == 0) {
            lextend.setPosition(1);
            rextend.setPosition(0);
        }
    }

    /**
     * Send the two hand-servos to opposing (mirrored) positions, based on the passed offset.
     * the horizontals are what rotates the intake
     * @param offset
     */
    public void setHorizontalPosition(double offset) {
        //offset = Range.clip(offset, -0.5, 0.5);
        if (offset == 1) {
            horizontal1.setPosition(0.35);
            // max 0.25
            horizontal2.setPosition(0.65);
            //0.9
        } else if (offset == 0) {
            horizontal1.setPosition(0.85);
            horizontal2.setPosition(0.15);
        }
    }
    public void setVerticalPower(double power) {vertical.setPosition(power);}

    public void setIntakePower(double power) {

        intake.setPosition(power);
    }
}


