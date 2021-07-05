
import java.util.Random;

//DE-best-2pair
public class DEB2P extends DE{
    
    public DEB2P(int iteration, int pointNum, int dimension, double range, double cp, String f_name) {
        super(iteration, pointNum, dimension, range, cp, f_name);
    }
    Point mutation(Point cur){
        Random ran = new Random();
        Point[] selectPoints = new Point[5];
        Point v = new Point(this.dimension);
        for(int i=0;i<5;i++){
            selectPoints[i] = this.points[ran.nextInt(this.points.length)];
        }
        
        //get bestPoint
        double bestfit=this.fitness(selectPoints[0]);
        int index=0;
        for(int i = 1;i<5;i++){
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
            double F1 = ran.nextDouble();
            double F2 = ran.nextDouble();
            v.point[i] = selectPoints[0].point[i]+F1*(selectPoints[1].point[i]-selectPoints[2].point[i])
                    +F2*(selectPoints[3].point[i]-selectPoints[4].point[i]);
        }
        return v;
    }
}
