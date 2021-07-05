
import java.util.Random;

//DE-current
public class DEC extends DE{
    
    public DEC(int iteration, int pointNum, int dimension, double range, double cp, String f_name) {
        super(iteration, pointNum, dimension, range, cp, f_name);
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
            double K = ran.nextDouble();
            v.point[i] = cur.point[i] + K * (selectPoints[0].point[i]-cur.point[i]) +
                    F * (selectPoints[1].point[i]-selectPoints[2].point[i]);
        }
        return v;
    }
}
