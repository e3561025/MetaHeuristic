
import java.util.Random;
public class DE {
    int iteration;
    int pointNum;
    int dimension;
    double upperRange;
    double lowerRange;
    double cp;//crossover probability
    Point[] points;
    Fitness f_name;
    DE(int iteration, int pointNum, int dimension, double upperRange,double lowerRange, double cp, String f_name){
        this.iteration = iteration;
        this.pointNum = pointNum;
        this.dimension = dimension;
        this.upperRange = upperRange;
        this.lowerRange = lowerRange;
        this.cp = cp;
        this.f_name =new Fitness(f_name);
    }
    double fitness(Point temp){
        double fitness_value = f_name.fitness(temp.point);
        return fitness_value;
    }
    Point mutation(Point cur){
        return cur;
    }
    Point mutationDE(Point cur){
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
    Point mutationDEB(Point cur){
        Random ran = new Random();
        Point[] selectPoints = new Point[3];
        Point v = new Point(this.dimension);
        for(int i=0;i<3;i++){
            selectPoints[i] = this.points[ran.nextInt(this.points.length)];
        }
        //get bestPoint
        double bestfit=this.fitness(selectPoints[0]);
        int index=0;
        for(int i = 1;i<3;i++){
            double fit = this.fitness(selectPoints[i]);
            if(fit < bestfit){
                bestfit = fit;
                index = i;
            }
        }
        //change
        Point temp=selectPoints[0];
        selectPoints[0] = selectPoints[index];
        selectPoints[index] = temp;
        
        
        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            v.point[i] = selectPoints[0].point[i]+F*(selectPoints[1].point[i]-selectPoints[2].point[i]);
        }
        return v;
    }
    Point mutationDEC(Point cur){
        Random ran = new Random();
        Point[] selectPoints = new Point[3];
        Point v = new Point(this.dimension);
        for(int i=0;i<3;i++){
            selectPoints[i] = this.points[ran.nextInt(this.points.length)];
        }
        
        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            double K = ran.nextDouble();
            v.point[i] = cur.point[i] + K * (selectPoints[0].point[i]-cur.point[i]) +
                    F * (selectPoints[1].point[i]-selectPoints[2].point[i]);
        }
        return v;
    }
    Point mutationDECB(Point cur){
        Random ran = new Random();
        Point[] selectPoints = new Point[3];
        Point v = new Point(this.dimension);
        for(int i=0;i<3;i++){
            selectPoints[i] = this.points[ran.nextInt(this.points.length)];
        }
        
        //get bestPoint
        double bestfit=this.fitness(selectPoints[0]);
        int index=0;
        for(int i = 1;i<3;i++){
            double fit = this.fitness(selectPoints[i]);
            if(fit < bestfit){
                bestfit = fit;
                index = i;
            }
        }
        //change
        Point temp=selectPoints[0];
        selectPoints[0] = selectPoints[index];
        selectPoints[index] = temp;
        
        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            double K = ran.nextDouble();
            v.point[i] = cur.point[i] + K * (selectPoints[0].point[i]-cur.point[i]) +
                    F * (selectPoints[1].point[i]-selectPoints[2].point[i]);
        }
        return v;
    }
    Point mutationDES(Point cur){
        Random ran = new Random();
        Point[] selectPoints = new Point[3];
        Point v = new Point(this.dimension);
        Point vector = new Point(this.dimension);
        for(int i=0;i<3;i++){
            selectPoints[i] = this.points[ran.nextInt(this.points.length)];
        }
        //record vector
        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            vector.point[i] = F*(selectPoints[1].point[i]-selectPoints[2].point[i]);
        }
        //rotate
        for(int i = 0;i<this.dimension;i++){
            int radius = ran.nextInt(360);
            int rotate2_num = ran.nextInt(this.dimension);
            double a = vector.point[i];
            double b = vector.point[rotate2_num];
            vector.point[i] = a*Math.cos(Math.toRadians(radius)) - b*Math.sin(Math.toRadians(radius));
            vector.point[rotate2_num] = b*Math.cos(Math.toRadians(radius)) + a*Math.sin(Math.toRadians(radius));
        }

        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            v.point[i] = selectPoints[0].point[i]+vector.point[i];
        }
        return v;
    }
    Point mutationDEBS(Point cur){
        Random ran = new Random();
        Point[] selectPoints = new Point[3];
        Point v = new Point(this.dimension);
        Point vector = new Point(this.dimension);
        for(int i=0;i<3;i++){
            selectPoints[i] = this.points[ran.nextInt(this.points.length)];
        }
        //get bestPoint
        double bestfit=this.fitness(selectPoints[0]);
        int index=0;
        for(int i = 1;i<3;i++){
            double fit = this.fitness(selectPoints[i]);
            if(fit < bestfit){
                bestfit = fit;
                index = i;
            }
        }
        //change
        Point temp=selectPoints[0];
        selectPoints[0] = selectPoints[index];
        selectPoints[index] = temp;
        
        
        //record vector
        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            vector.point[i] = F*(selectPoints[1].point[i]-selectPoints[2].point[i]);
        }
        //rotate
        for(int i = 0;i<this.dimension;i++){
            int radius = ran.nextInt(360);
            int rotate2_num = ran.nextInt(this.dimension);
            double a = vector.point[i];
            double b = vector.point[rotate2_num];
            vector.point[i] = a*Math.cos(Math.toRadians(radius)) - b*Math.sin(Math.toRadians(radius));
            vector.point[rotate2_num] = b*Math.cos(Math.toRadians(radius)) + a*Math.sin(Math.toRadians(radius));
        }

        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            v.point[i] = selectPoints[0].point[i]+vector.point[i];
        }
        return v;
    }
    Point mutationDECS(Point cur){
        Random ran = new Random();
        Point[] selectPoints = new Point[3];
        Point v = new Point(this.dimension);
        Point vector = new Point(this.dimension);
        for(int i=0;i<3;i++){
            selectPoints[i] = this.points[ran.nextInt(this.points.length)];
        }
        //record vector
        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            vector.point[i] = F*(selectPoints[1].point[i]-selectPoints[2].point[i]);
        }
        //rotate
        for(int i = 0;i<this.dimension;i++){
            int radius = ran.nextInt(360);
            int rotate2_num = ran.nextInt(this.dimension);
            double a = vector.point[i];
            double b = vector.point[rotate2_num];
            vector.point[i] = a*Math.cos(Math.toRadians(radius)) - b*Math.sin(Math.toRadians(radius));
            vector.point[rotate2_num] = b*Math.cos(Math.toRadians(radius)) + a*Math.sin(Math.toRadians(radius));
        }

        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            double K = ran.nextDouble();
            v.point[i] = cur.point[i] + K * (selectPoints[0].point[i]-cur.point[i]) +
                    vector.point[i];
        }
        return v;
    }
    Point mutationDECBS(Point cur){
        Random ran = new Random();
        Point[] selectPoints = new Point[3];
        Point v = new Point(this.dimension);
        Point vector = new Point(this.dimension);
        for(int i=0;i<3;i++){
            selectPoints[i] = this.points[ran.nextInt(this.points.length)];
        }
        
        //get bestPoint
        double bestfit=this.fitness(selectPoints[0]);
        int index=0;
        for(int i = 1;i<3;i++){
            double fit = this.fitness(selectPoints[i]);
            if(fit < bestfit){
                bestfit = fit;
                index = i;
            }
        }
        //change
        Point temp=selectPoints[0];
        selectPoints[0] = selectPoints[index];
        selectPoints[index] = temp;
        
        //record vector
        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            vector.point[i] = F*(selectPoints[1].point[i]-selectPoints[2].point[i]);
        }
        //rotate
        for(int i = 0;i<this.dimension;i++){
            int radius = ran.nextInt(360);
            int rotate2_num = ran.nextInt(this.dimension);
            double a = vector.point[i];
            double b = vector.point[rotate2_num];
            vector.point[i] = a*Math.cos(Math.toRadians(radius)) - b*Math.sin(Math.toRadians(radius));
            vector.point[rotate2_num] = b*Math.cos(Math.toRadians(radius)) + a*Math.sin(Math.toRadians(radius));
        }
        
        for(int i=0;i<this.dimension;i++){
            double F = ran.nextDouble();
            double K = ran.nextDouble();
            v.point[i] = cur.point[i] + K * (selectPoints[0].point[i]-cur.point[i]) +
                    vector.point[i];
        }
        return v;
    }
    void checkRange(Point v){
        int length = v.point.length;
        for(int i = 0; i<length;i++){
            double num = v.point[i];
            if(num>this.upperRange){
                v.point[i] = this.upperRange;
            }else if(num<this.lowerRange){
                v.point[i] = this.lowerRange;
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
    // shuffle point
    void shuffle(){
        Random ran = new Random();
        for(int i = 0;i<this.pointNum;i++){
            int change_num = ran.nextInt(this.pointNum);
            Point a = points[i];
            points[i] = points[change_num];
            points[change_num] = a;
        }
    }
    //spread point by range/iteration length 
    void reset_point_rangeVersion(int iteration, double range){
        Random ran = new Random();
        //get the best
        Point best=points[0];
        double bestFitness=this.fitness(best);
        for(int i=1;i<this.pointNum;i++){
            double fit = this.fitness(this.points[i]);
            if(fit<bestFitness){
                best = this.points[i];
                bestFitness = fit;
            }
        }
        // copy the Point
        best = new Point(best);
        // reset the point
        points[0] = best;
        for(int i = 1;i<this.pointNum;i++){
            Point temp = points[i];
            for(int j = 1;j<this.dimension;j++){
                double resetNum = (ran.nextDouble() * 2 - 1) * range / iteration;
                temp.point[j]+=resetNum;
            }
        }
    }
    //spread from best point by standard*2 length
    void reset_point_MeanVersion(){
        Random ran = new Random();
        //get the best
        Point best=points[0];
        double bestFitness=this.fitness(best);
        for(int i=1;i<this.pointNum;i++){
            double fit = this.fitness(this.points[i]);
            if(fit<bestFitness){
                best = this.points[i];
                bestFitness = fit;
            }
        }
        // copy the Point, we need to keep the best point
        best = new Point(best);
        
        //get the mean and standard
        double[] mean = new double[this.dimension];
        for(int i = 0;i<this.dimension;i++){
            for(int j= 0;j<this.pointNum;j++){
                mean[i] = mean[i]+this.points[j].point[i];
            }
            mean[i] = mean[i]/this.pointNum;
        }
        //compute the standard, sqrt(sum((di-m)^2))
        double[] standard = new double[this.dimension];
        for(int i = 0;i<this.dimension;i++){
            for(int j = 0;j<this.pointNum;j++){
                double dif = this.points[j].point[i] - mean[i];
                standard[i] = standard[i] + dif*dif;
            }
            standard[i] = Math.sqrt(standard[i]);
        }
        // reset the point
        points[0] = best;
        for(int i = 1;i<this.pointNum;i++){
            Point temp = points[i];
            for(int j = 1;j<this.dimension;j++){
                // every point plus 2*standard
                double resetNum = (ran.nextDouble() * 2 - 1) * (standard[j]*2);
                temp.point[j]+=resetNum;
            }
        }
    }
    void init(){
        points = new Point[this.pointNum];
        for(int i=0;i<this.pointNum;i++){
            points[i] = new Point(this.dimension,this.upperRange,this.lowerRange);
        }
    }
    double getCurrentBestFitness(){
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
        return bestFitness;
    }
    double[] DE_start(){
        // every 100 iter, need shuffle onetimes
        // every 1500 iter, need reset_point onetimes
        //every iter, need choose one mutation from eight mutation functions
        init();
        Random ran = new Random();
        double[] CurrentBestFitness = new double[this.iteration/200];
        int recordTime = 0;
        for(int iter = 1;iter<=this.iteration;iter++){
            if(iter%100==0){
                shuffle();
            }
            // every 1500 iter, do reset
            if(iter%1500==0){
                
                int r_f = ran.nextInt(2);
                if(r_f==0){
                    reset_point_rangeVersion(this.iteration,this.upperRange-this.lowerRange);
                }else{
                    reset_point_MeanVersion();
                }
                for(int i = 0;i<this.pointNum;i++){
                    checkRange(this.points[i]);
                }
            }else{
                Point[] tempArr = new Point[this.pointNum];
                for(int i = 0; i < points.length;i++){
                    Point v =null;
                    int m_f = ran.nextInt(8);
                    if(m_f==0)
                        v = mutationDE(points[i]);
                    else  if(m_f==1)
                        v = mutationDEB(points[i]);
                    else  if(m_f==2)
                        v = mutationDEC(points[i]);
                    else  if(m_f==3)
                        v = mutationDECB(points[i]);
                    else  if(m_f==4)
                        v = mutationDES(points[i]);
                    else  if(m_f==5)
                        v = mutationDEBS(points[i]);
                    else  if(m_f==6)
                        v = mutationDECS(points[i]);
                    else  if(m_f==7)
                        v = mutationDECBS(points[i]);
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
            
            
            if(iter%200==0){
                CurrentBestFitness[recordTime] = this.getCurrentBestFitness();
                recordTime++;
            }
        }
        return CurrentBestFitness;
    }
    
}
class Point{
    double[] point;
    Point(int dimension){
        point = new double[dimension];
    }
    Point(int dimension, double upperRange, double lowerRange){
        point = new double[dimension];
        random(upperRange,lowerRange);
    }
    //clone use
    Point(Point p){
        point = new double[p.point.length];
        double[] temp = p.point;
        for(int i = 0; i < temp.length; i++){
            point[i] = temp[i];
        }
    }
    void random(double upperRange,double lowerRange){
        Random ran = new Random();
        double range = upperRange-lowerRange;
        for(int i = 0; i < point.length; i++){
            point[i] = ran.nextDouble()*range + lowerRange;
        }
    }
}
