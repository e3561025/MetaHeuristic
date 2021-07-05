
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class forRunning {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int runTime = 30;
        int iteration=10000;
        int dataLength = iteration/200;
        int pointNum=30;
        double cp=0.6;
        StringUse totalData = new StringUse("");
        int dimension=30;
        double upperRange = 30;
        double lowerRange = -30;
        String f_name = "Ackley";
        //Ackley  Griewank Rastrigin  Schaffer_N2   Schwefel  Bohachevsky_N2 
        //  30       600     5.12         100           500         100
        //Sphere  Sum_Squares Booth_N2  Zakharov Three-Hump_N2  
        //  100       10         10      -5~10        5
        //Rosenbrock De_Jong_N2  Michalewicz Beale_N2   Powell 
        //    10       65.536        0~π       4.5      -4~5(dimension=4d)
        
        DEfitness(runTime,iteration,pointNum,dimension,30,-30,cp,"Ackley",totalData);
        DEfitness(runTime,iteration,pointNum,dimension,600,-600,cp,"Griewank",totalData);
        DEfitness(runTime,iteration,pointNum,dimension,5.12,-5.12,cp,"Rastrigin",totalData);
        DEfitness(runTime,iteration,pointNum,2,100,-100,cp,"Schaffer_N2",totalData);
        DEfitness(runTime,iteration,pointNum,dimension,500,-500,cp,"Schwefel",totalData);
        DEfitness(runTime,iteration,pointNum,2,100,-100,cp,"Bohachevsky_N2",totalData);
        DEfitness(runTime,iteration,pointNum,dimension,100,-100,cp,"Sphere",totalData);
        DEfitness(runTime,iteration,pointNum,dimension,10,-10,cp,"Sum_Squares",totalData);
        DEfitness(runTime,iteration,pointNum,2,10,-10,cp,"Booth_N2",totalData);
        DEfitness(runTime,iteration,pointNum,dimension,10,-5,cp,"Zakharov",totalData);
        DEfitness(runTime,iteration,pointNum,2,5,-5,cp,"Three-Hump_N2",totalData);
        DEfitness(runTime,iteration,pointNum,dimension,10,-10,cp,"Rosenbrock",totalData);
        DEfitness(runTime,iteration,pointNum,2,65.536,-65.536,cp,"De_Jong_N2",totalData);
        DEfitness(runTime,iteration,pointNum,dimension,Math.PI,0,cp,"Michalewicz",totalData);
        DEfitness(runTime,iteration,pointNum,2,4.5,-4.5,cp,"Beale_N2",totalData);
        DEfitness(runTime,iteration,pointNum,20,5,-4,cp,"Powell",totalData);

        //DEfitness(runTime,iteration,pointNum,dimension,Math.PI,0,cp,"Michalewicz",totalData);
        FileOutputStream f = new FileOutputStream("Total.txt");
        System.setOut(new PrintStream(f));
        System.out.println(totalData.s);
        f.close();
    }
    static void DEfitness(int runTime, int iteration,int pointNum,int dimension,double upperRange,double lowerRange,double cp,String f_name, StringUse totalData) throws FileNotFoundException, IOException{
        FileOutputStream f = new FileOutputStream(f_name+".txt");
        totalData.s = totalData.s+f_name+",";
        System.setOut(new PrintStream(f));
        int dataLength = iteration/200;
        double[][] bestFitness = new double[runTime][];
        for(int i = 0;i<runTime;i++){
            DE de = new DE(iteration,pointNum,dimension,upperRange,lowerRange,cp,f_name);
            //System.out.println("de start");
            bestFitness[i] = de.DE_start();
        }
        double[] meanFitness = new double[dataLength];
        for(int i = 0;i<runTime;i++){
            for(int j = 0;j< dataLength ;j++){
                meanFitness[j]+=(bestFitness[i][j]/runTime);
            }
        }
        
        for(int i = 0;i<dataLength;i++){
            System.out.print(meanFitness[i]+",");
            totalData.s = totalData.s + meanFitness[i]+",";
        }
        totalData.s = totalData.s+"\n";
        f.close();
    }
}
class StringUse{
    String s;
    StringUse(String s){
        this.s = s;
    }
}
