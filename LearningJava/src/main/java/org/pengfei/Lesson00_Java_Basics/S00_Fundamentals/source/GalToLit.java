package org.pengfei.Lesson00_Java_Basics.S00_Fundamentals.source;

public class GalToLit {

    private static final double galLitRate=3.7854 ;

    public  static  double getGalFromLit(double liters){
        return liters/galLitRate;
    }

    public static double getLitFromGal(double gals){
        return gals*galLitRate;
    }

    public static void showTable(){
        int counter=0;
        for(double gal=0; gal<100; gal++){
            double lit=gal*galLitRate;
            System.out.println("Gal "+ gal +" has litre "+ lit);
            counter++;
            if (counter==10){
                System.out.println("******************************************************************");
                counter=0;
            }
        }
    }

}
