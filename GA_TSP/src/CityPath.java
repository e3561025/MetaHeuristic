
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CityPath {
    double totalLength=0;
    int[] cityPath;
    CityPath(int num){
        cityPath = new int[num];
        List<Integer> remainCity = new ArrayList<Integer>();
        for(int i=0;i<num;i++){
            remainCity.add(i);
        }
        Random random = new Random();
        for(int i=0;i<num;i++){
            int index = random.nextInt(remainCity.size());
            cityPath[i]=remainCity.get(index);
            remainCity.remove(index);
        }
    }
}
