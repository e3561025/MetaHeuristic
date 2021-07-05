
import java.util.Random;
public class Partially_mapped extends GA_Roulette{
    @Override
    void crossover(){
        Random ran = new Random();
        double p = ran.nextDouble();
        if(p<this.crossoverP){
            for(int num=0;num<this.genes.length/2;num++){
                //random the change length
                int range = ran.nextInt(this.genes[0].cityPath.length+1);
                //random the change start
                int start = ran.nextInt(this.genes[0].cityPath.length-range+1);
                //get crossover 
                CityPath first = genes[num*2];
                CityPath second = genes[num*2+1];
                int[] firstTemp = new int[range];
                int[] secondTemp = new int[range];
                for(int i=0;i<range;i++){
                    //record the change num of current
                    int current = start+i;
                    //record the change data
                    firstTemp[i] = first.cityPath[current];
                    secondTemp[i] = second.cityPath[current];
                    //swap two element
                    int temp = first.cityPath[current];
                    first.cityPath[current] = second.cityPath[current];
                    second.cityPath[current] = temp;
                }
                //revise the other element--before
                for(int k=0;k<start;k++){
                    int tempFirst = first.cityPath[k];
                    for(int i=0;i<secondTemp.length;i++){
                        if(tempFirst==secondTemp[i]){
                            tempFirst=firstTemp[i];
                            i=-1;
                        }
                    }
                    first.cityPath[k]=tempFirst;
                    int tempSecond = second.cityPath[k];
                    for (int i = 0; i < firstTemp.length; i++) {
                        if(tempSecond==firstTemp[i]){
                            tempSecond=secondTemp[i];
                            i=-1;
                        }
                    }
                    second.cityPath[k]=tempSecond;
                }
                //revise the other element --behind
                for(int k=start+range;k<first.cityPath.length;k++){
                    int tempFirst = first.cityPath[k];
                    for(int i=0;i<secondTemp.length;i++){
                        if(tempFirst==secondTemp[i]){
                            tempFirst=firstTemp[i];
                            i=-1;
                        }
                    }
                    first.cityPath[k]=tempFirst;
                    int tempSecond = second.cityPath[k];
                    for (int i = 0; i < firstTemp.length; i++) {
                        if(tempSecond==firstTemp[i]){
                            tempSecond=secondTemp[i];
                            i=-1;
                        }
                    }
                    second.cityPath[k]=tempSecond;
                }
            }
        }
    }
}
