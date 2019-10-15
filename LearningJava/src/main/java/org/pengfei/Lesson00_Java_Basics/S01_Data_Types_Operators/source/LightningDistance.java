package org.pengfei.Lesson00_Java_Basics.S01_Data_Types_Operators.source;

public class LightningDistance {
    private static final int soundSpeedFeetPerSec=1100;

    public static double getLightningDistance(double soundTime){
        return soundTime*soundSpeedFeetPerSec;
    }
}
