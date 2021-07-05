
import java.util.Random;
public class DE {
    int iteration;
    int pointNum;
    int dimension;
    double range;
    double cp;//crossover probability
    Point[] points;
    Fitness f_name;
    DE(int iteration, int pointNum, int dimension, double range, double cp, String f_name){
        this.iteration = iteration;
        this.pointNum = pointNum;
        this.dimension = dimension;
        this.range = range;
        this.cp = cp;
        this.f_name =new Fitness(f_name);
    }
    double fitness(Point temp){
        double fitness_value = f_name.fitness(temp.point);
        return fitness_value;
    }
    Point mutation(Point cur){
        Random ran = new Random();
        Point[] selectPoints = new Point[3];
        Point v = new Point(this.dimension);
        for(int i=0;i<3;i++){
            selectPoints[i] = this.points[ran.nextInt(this.points.length)];
        }
        
        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            v.point[i] = selectPoints[0].point[i]+F*(selectPoints[1].point[i]-selectPoints[2].point[i]);
        }
        return v;
    }
    void checkRange(Point v){
        int length = v.point.length;
        for(int i = 0; i<length;i++){
            double num = v.point[i];
            if(num>this.range){
                v.point[i] = range;
            }else if(num<-this.range){
                v.point[i] = -range;
            }
        }
    }
    Point crossover(Point v, Point current){// v is mutation temp
        Point temp = new Point(this.dimension);
        Random ran = new Random();
        for(int i=0;i<this.dimension;i++){
            if(ran.nextDouble()>this.cp){
                temp.point[i] = current.point[i];
            }else{
                temp.point[i] = v.point[i];
            }
        }
        return temp;
    }
    boolean selection(Point temp,Point current){
        double tempFit = fitness(temp);
        double currentFit = fitness(current);
        if(tempFit < currentFit)
            return true;
        else
            return false;
    }
    void init(){
        points = new Point[this.pointNum];
        for(int i=0;i<this.pointNum;i++){
            points[i] = new Point(this.dimension,this.range);
        }
    }
    double DE_start(){
        init();
        for(int iter = 0;iter<this.iteration;iter++){
            Point[] tempArr = new Point[this.pointNum];
            for(int i = 0; i < points.length;i++){
                Point v = mutation(points[i]);
                checkRange(v);
                Point temp = crossover(v,points[i]);
                if(selection(temp,points[i])){
                    tempArr[i] = temp;
                }else{
                    tempArr[i] = points[i];
                }
            }
            points = tempArr;
        }
        //get the best solution
        Point best=points[0];
        double bestFitness=this.fitness(best);
        for(int i=1;i<this.pointNum;i++){
            double fit = this.fitness(this.points[i]);
            if(fit<bestFitness){
                best = this.points[i];
                bestFitness = fit;
            }
        }
        //System.out.println("bestFitness is "+bestFitness);
        for(int i = 0;i<best.point.length;i++){
            System.out.print(best.point[i]+"\t");
        }
        System.out.println("");
        return bestFitness;
    }
    
}
class Point{
    double[] point;
    Point(int dimension){
        point = new double[dimension];
    }
    Point(int dimension, double range){
        point = new double[dimension];
        random(range);
    }
    //clone use
    Point(Point p){
        point = new double[p.point.length];
        double[] temp = p.point;
        for(int i = 0; i < temp.length; i++){
            point[i] = temp[i];
        }
    }
    void random(double range){
        Random ran = new Random();
        for(int i = 0; i < point.length; i++){
            point[i] = 2*ran.nextDouble()*range - range;
        }
    }
}
