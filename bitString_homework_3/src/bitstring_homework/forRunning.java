package bitstring_homework;

import bitstring_homework.BitString_test;
import java.util.Date;

public class forRunning {

    public static void main(String[] args) {
        int arrayLength=100;
        int runTimes = 5;
        long dateTime=0;
        for (int z = 0; z < 5; z++) {
            //init();
            BitString_SimulatedAnneling bitstring = new BitString_SimulatedAnneling();

            Date date = new Date();
            long first = date.getTime();
            bitstring.init(arrayLength);
            date = new Date();
            long second = date.getTime();
            long time = (second - first);
            System.out.println("arrayLength : " + arrayLength + "\ntime : " + time);
            dateTime+=time;
            
        }
        dateTime = dateTime/runTimes;
        System.out.println("===========================");
        System.out.println("average Time is "+dateTime );
    }
}
