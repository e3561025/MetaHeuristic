
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Cycle_crossover extends GA_Roulette{
    @Override
    void crossover(){
        Random ran = new Random();
        double p = ran.nextDouble();
        if(p<this.crossoverP){
            for(int num=0;num<this.genes.length/2;num++){
                CityPath first = genes[num*2];
                CityPath second = genes[num*2+1];
                List<Integer> index = new ArrayList<Integer>();
                int firstNum=first.cityPath[0];
                int current=0;
                while(true){
                    int temp = first.cityPath[current];
                    index.add(current);
                    temp = second.cityPath[current];
                    for(int i=0;i<first.cityPath.length;i++){
                        if(temp==first.cityPath[i]){
                            current=i;
                            break;
                        }
                    }
                    if(temp==firstNum){
                        break;
                    }
                }
                for(int i=0;i<first.cityPath.length;i++){
                    int flag=0;
                    for(int j=0;j<index.size();j++){
                        if(index.get(j)==i){
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0){
                        int temp = first.cityPath[i];
                        first.cityPath[i]=second.cityPath[i];
                        second.cityPath[i]=temp;
                    }   
                }
            }
        }
    }
}
