
package bitstring_homework;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
//輪盤寫法
public class GA_Roulette {
    double mutationProbability=0.01;
    double crossoverProbability=0.6;
    //how many genomes we have
    int genomesNum = 50;
    //genomes is all of genome
    bitString[] genomes;
    //need to record the genomes of length
    int[] genomes_times;
    //the genome algorithm stop times
    int stopNum=200;
    //every genome of length
    int num;
    //record currentBestBitString
    bitString currentMax;
    
    void init(int num){
        //we have many genome
        genomes = new bitString[genomesNum];
        genomes_times = new int[genomesNum];
        this.num = num;
        //every genome need init
        for(int i=0;i<genomes.length;i++){
            genomes[i] = new bitString(num);
        }
        //when start,it need random coordinate
        randomInit();
        currentMax=genomes[0];
        
        
        //start genomes algorithm
        int geneTimes=0;
        while(true){
            
            compute();
            genomes=selection();
            crossover();
            mutation();
            if(geneTimes>this.stopNum) break;
            geneTimes++;
        }
        
        System.out.println("currentMaxString is : "+currentMax.getString());
        System.out.println("currentMaxOneTimes is : "+currentMax.oneTimes());
    }
    //array init need to random, since it is genomes,so need two for-cycle
    private void randomInit(){
        Random ran = new Random();
        for(int i=0;i<genomes.length;i++){
            for(int j=0;j<num;j++){
                int temp = ran.nextInt(10);
                if(temp%2==0){
                    genomes[i].bit[j]=true;
                }else{
                    genomes[i].bit[j]=false;
                }
            }
            
        }
    }
    
    //Roulette selection
    private bitString[] selection(){
        Random random = new Random();
        //record the num of all probability
        int totalnum=0;
        for(int i=0;i<genomes_times.length;i++){
            totalnum+=genomes_times[i];
        }
        bitString[] newbitString = new bitString[genomesNum];
        //start to do Roulette
        for(int i=0;i<genomesNum;i++){
            int p = random.nextInt(totalnum);
            int temp=0;
            for(int j=0;j<genomes_times.length;j++){
                temp+=genomes_times[j];
                if(temp>p){
                    newbitString[i] = new bitString(genomes[j].bit);
                    break;
                }
            }
        }
        return newbitString;
    }
    // genomes could crossover to get new property
    private void crossover(){
        Random ran = new Random();
        for(int i=0;i<(genomes.length/2);i++){
            if(ran.nextDouble()<(crossoverProbability)){
                boolean[] left = new boolean[num];
                boolean[] right = new boolean[num];
                for(int j=0;j<num/2;j++){
                    left[j] = genomes[2*i].bit[j];
                    right[j] = genomes[2*i+1].bit[j];
                }
                for(int j=num/2;j<num;j++){
                    left[j] = genomes[2*i+1].bit[j];
                    right[j] = genomes[2*i].bit[j];
                }
            }
        }
    }
    private void mutation(){
        Random ran = new Random();
        for(int i=0;i<genomes.length;i++){
            for(int j=0;j<num;j++){
                double p = ran.nextDouble();
                if(p<=mutationProbability){
                    genomes[i].bit[j]=!genomes[i].bit[j];
                }
            }
        }
    }

    private void compute() {
        int maxNum=0;
        int index=0;
        for(int i=0;i<genomes.length;i++){
            genomes_times[i] = genomes[i].oneTimes();
            if(genomes_times[i]>maxNum){
                maxNum=genomes_times[i];
                index=i;
            }
        }
        if(maxNum>this.currentMax.oneTimes()){
            currentMax = genomes[index];
        }
    }
}
