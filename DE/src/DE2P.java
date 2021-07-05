
import java.util.Random;
//DE-2pair
public class DE2P extends DE{
    
    public DE2P(int iteration, int pointNum, int dimension, double range, double cp, String f_name) {
        super(iteration, pointNum, dimension, range, cp, f_name);
    }
    @Override
    Point mutation(Point cur){
        Random ran = new Random();
        Point[] selectPoints = new Point[5];
        Point v = new Point(this.dimension);
        for(int i=0;i<5;i++){
            selectPoints[i] = this.points[ran.nextInt(this.points.length)];
        }
        
        for(int i=0;i<this.dimension;i++){
            double F1 = ran.nextDouble();
            double F2 = ran.nextDouble();
            v.point[i] = selectPoints[0].point[i]+F1*(selectPoints[1].point[i]-selectPoints[2].point[i])
                    +F2*(selectPoints[3].point[i]-selectPoints[4].point[i]);
        }
        return v;
    }
    
}
