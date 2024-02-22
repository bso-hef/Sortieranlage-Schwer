package colorsorter;

import colorsorter.helper.EV3Color;
import colorsorter.helper.ElementSorterHelper;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ElementSorter {

    private static EV3LargeRegulatedMotor motorPortal = new EV3LargeRegulatedMotor(MotorPort.D);
    private static EV3LargeRegulatedMotor motorGrapper = new EV3LargeRegulatedMotor(MotorPort.C);
    private static EV3MediumRegulatedMotor motorRelease = new EV3MediumRegulatedMotor(MotorPort.A);
    private static EV3LargeRegulatedMotor motorConveyorBelt = new EV3LargeRegulatedMotor(MotorPort.B);
    private static EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S3);
    private static EV3ColorSensor farbSensor = new EV3ColorSensor(SensorPort.S2);

    private static EV3TouchSensor button = new EV3TouchSensor(SensorPort.S1);

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        farbSensor.setCurrentMode(1);

        ElementSorterHelper elementSorterHelper = new ElementSorterHelper(motorConveyorBelt, motorRelease, motorPortal, motorGrapper);
        LCD.clearDisplay();
        Sound.beep();
        LCD.drawString("Anlage bereit:", 0, 1);

        float distance = elementSorterHelper.getDistance(ultrasonicSensor);
        while (distance <= 0.048) {

            LCD.drawString("Zum Starten", 0, 3);
            LCD.drawString("gelben Knopf", 0, 4);
            LCD.drawString("druecken!", 0, 5);
            ElementSorterHelper.waitUntilButtonPressed(button);
            LCD.clearDisplay();

            LCD.drawString("Anlage arbeitet:", 0, 1);
            elementSorterHelper.releaseElement();
            Delay.msDelay(1000);
            elementSorterHelper.moveElementToSensor();

            EV3Color elementColor = EV3Color.byValue(farbSensor.getColorID());
            LCD.drawString("Farbe: " + elementColor.toString(), 0, 3);

            switch (elementColor) {
                case RED:
                case BLUE:
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    break;
                case BLACK:
                case WHITE:
                case GRAY:
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    break;
                case GREEN:
                default:
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    elementSorterHelper. ();
                    break;
            }

            distance = elementSorterHelper.getDistance(ultrasonicSensor);

        }

        LCD.drawString("Teile nachlegen", 0, 3);
        LCD.drawString("Programm neu starten", 0, 4);



    }

}






