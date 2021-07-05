
import java.util.Random;
//DE-current-best
public class DECB extends DE{
    public DECB(int iteration, int pointNum, int dimension, double range, double cp, String f_name) {
        super(iteration, pointNum, dimension, range, cp, f_name);
    }
    Point mutation(Point cur){
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
}
