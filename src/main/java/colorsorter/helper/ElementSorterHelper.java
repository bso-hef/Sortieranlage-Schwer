package colorsorter.helper;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ElementSorterHelper {

    private static final int SENSOR_RETRY_TIMEOUT = 200;
    private static final int PORTAL_INITIAL_POSITION = 0;
    public static final int GRABBER_TO_ELEMENT_PORTAL_ANGLE = 320;
    private static final int BOX1_PORTAL_ANGLE = -180;
    private static final int BOX2_PORTAL_ANGLE = -400;
    private static final int BOX3_PORTAL_ANGLE = -950;
    private static final int GRABBER_OPEN_ANGLE = 0;
    private static final int GRABBER_CLOSE_ANGLE = -820;

    private EV3LargeRegulatedMotor motorConveyorBelt;
    private EV3MediumRegulatedMotor motorRelease;
    private EV3LargeRegulatedMotor motorPortal;
    private EV3LargeRegulatedMotor motorGrapper;

    /**
     * Creates an ElementSorterHelper to use the Motors in an easy way.
     *
     * @param motorConveyorBelt
     * @param motorRelease
     * @param motorPortal
     * @param motorGrapper
     */
    public ElementSorterHelper(EV3LargeRegulatedMotor motorConveyorBelt, EV3MediumRegulatedMotor motorRelease, EV3LargeRegulatedMotor motorPortal, EV3LargeRegulatedMotor motorGrapper) {
        this.motorConveyorBelt = motorConveyorBelt;
        this.motorRelease = motorRelease;
        this.motorPortal = motorPortal;
        this.motorGrapper = motorGrapper;
    }

    /**
     * Helper method to wait until the given button is pressed.<br/>
     * Code example:<br/>
     * <code>ElementSorterHelper.waitUntilButtonPressed(buttonS1);</code>
     *
     * @param button, an object of EV3TouchSensor that is connected to any sensor port
     */
    public static void waitUntilButtonPressed(EV3TouchSensor button) {
        final SampleProvider sampleProvider = button.getTouchMode();
        int touchValue = 0;

        //Control loop
        while (touchValue == 0) {

            float[] sampleValue = new float[sampleProvider.sampleSize()];
            sampleProvider.fetchSample(sampleValue, 0);
            touchValue = (int) sampleValue[0];

            Delay.msDelay(SENSOR_RETRY_TIMEOUT);
        }
    }

    /**
     * Returns the distance in meters.<br/>
     * <br/>
     * Code Example:<br/>
     * <code>//Creates an Objekt of the class EV3UltrasonicSensor that is connected to<br/>
     * //an EV3UltrasonicSensor at Port 3<br/>
     * EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S3);<br/>
     * //ließt den Abstand des Sensors aus.<br/>
     * float distance = ElementSorterHelper.getDistance(ultrasonicSensor);</code>
     *
     * @return the distance measured by EV3UltrasonicSensor
     * @param ultrasonicSensor, an EV3UltrasonicSensor object that is connected to any Sensor Port
     */
    public static float getDistance(EV3UltrasonicSensor ultrasonicSensor) {
        SampleProvider ultrasonicSampleProvider = ultrasonicSensor.getDistanceMode();
        float[] sampleValue = new float[ultrasonicSampleProvider.sampleSize()];
        ultrasonicSampleProvider.fetchSample(sampleValue, 0);
        return sampleValue[0];
    }

    /**
     * Moves the sensor to the element on the conveyorBelt.
     */
    public void moveSensorToElement() {
        motorPortal.rotateTo(PORTAL_INITIAL_POSITION);
    }

    /**
     * Opens the grapper to release an element.
     */
    public void openGrabber() {
        motorGrapper.rotateTo(GRABBER_OPEN_ANGLE);
    }

    /**
     * Moves the Portal to box 1.
     */
    public void grabElement() {
        motorGrapper.rotateTo(GRABBER_CLOSE_ANGLE);
    }

    /**
     * Moves the portal to a given angle;
     * @param angle
     */
    private void movePortal(int angle) {
        motorPortal.rotateTo(angle);
    }

    /**
     * Moves the Portal to box 1.
     */
    public void moveToBox1() {
        movePortal(BOX1_PORTAL_ANGLE);
    }

    /**
     * Moves the Portal to box 2.
     */
    public void moveToBox2() {
        movePortal(BOX2_PORTAL_ANGLE);
    }

    /**
     * Moves the Portal to box 3.
     */
    public void moveToBox3() {
        movePortal(BOX3_PORTAL_ANGLE);
    }

    /**
     * Moves the grabber on the portal to the Element on the conveyor belt.
     */
    public void moveGrabberToElement() {
        motorPortal.setSpeed(200);
        motorPortal.rotateTo(GRABBER_TO_ELEMENT_PORTAL_ANGLE);
    }

    /**
     * Moves the element from the release to the sensor.
     */
    public void moveElementToSensor() {
        motorConveyorBelt.setSpeed(150);
        motorConveyorBelt.rotate(500);
    }

    /**
     * Releases an element from the magazine.
     */
    public void releaseElement() {
        motorRelease.rotate(360);
    }
}
