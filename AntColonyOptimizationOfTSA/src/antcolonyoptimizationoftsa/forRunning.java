
package antcolonyoptimizationoftsa;

import java.util.Date;

public class forRunning {
    public static void main(String args[]){
        int runTimes=1;
        long dateTime=0;
        for(int i=0;i<runTimes;i++){
            AntColonyOptimizationOfTSA run = new AntColonyOptimizationOfTSA();
            Date date = new Date();
            long first = date.getTime();
            run.init();
            date = new Date();
            long second = date.getTime();
            long time = (second - first);
            System.out.println("ACOTime : " + time);
            dateTime+=time;
        }
        dateTime = dateTime/runTimes;
        System.out.println("===========================");
        System.out.println("average Time is "+dateTime );
    }
}
