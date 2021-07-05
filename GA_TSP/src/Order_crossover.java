
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Order_crossover extends GA_Roulette{
    @Override
    void crossover(){
        Random ran = new Random();
        double p = ran.nextDouble();
        if(p<this.crossoverP){
            for(int num=0;num<this.genes.length/2;num++){
                CityPath first = genes[num*2];
                CityPath second = genes[num*2+1];
                List<Integer> firstList = new ArrayList<Integer>();
                List<Integer> secondList = new ArrayList<Integer>();
                for(int i=0;i<first.cityPath.length;i++){
                    firstList.add(first.cityPath[i]);
                    secondList.add(second.cityPath[i]);
                }
                //random the change length
                int range = ran.nextInt(this.genes[0].cityPath.length+1);
                //random the change start
                int start = ran.nextInt(this.genes[0].cityPath.length-range+1);
                for(int i=0;i<range;i++){
                    //firstList will use in second
                    //secondList will use in first
                    int data1 = second.cityPath[start+i];
                    int data2 = first.cityPath[start+i];
                    for(int j=0;j<firstList.size();j++){
                        if(firstList.get(j)==data1){
                            firstList.remove(j);
                            break;
                        }
                    }
                    for(int j=0;j<secondList.size();j++){
                        if(secondList.get(j)==data2){
                            secondList.remove(j);
                            break;
                        }
                    }
                }
                int length = first.cityPath.length;
                for(int i=0;i<length-range;i++){
                    int current = (start+range+i)%length;
                    first.cityPath[current] = secondList.get(0);
                    second.cityPath[current] = firstList.get(0);
                    firstList.remove(0);
                    secondList.remove(0);
                }
            }
        }
    }
}
