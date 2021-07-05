
package bitstring_homework;

import java.util.Random;

public class GA_Tournament extends GA_Roulette{
    int roulette_time=5;
    private bitString[] selection(){
        Random random = new Random();
        bitString[] newbitString = new bitString[genomesNum];
        
        for(int i=0;i<newbitString.length;i++){
            int maxNum=0;
            int maxIndex=0;
            for(int j=0;j<roulette_time;j++){
                int index = random.nextInt(genomesNum);
                if(genomes_times[index]>maxNum){
                    maxNum = genomes_times[index];
                    maxIndex = index;
                }
            }
            newbitString[i] = new bitString(genomes[maxIndex].bit);
        }
        return newbitString;
        
    }
}
