
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class GA_Roulette {
    CityPath[] genes;
    double mutationP=0.1;
    double crossoverP=0.6;
    void init(int iter,int genesNum){
        //get all cityDistance
        allCity citys = new allCity("eil51.txt");
        //create total genes
        genes = new CityPath[genesNum];
        //set genes initialize
        for(int i=0;i<genes.length;i++){
            genes[i] = new CityPath(citys.citys.size());
            citys.computePathLength(genes[i]);
            //System.out.println(genes[i].totalLength);
        }
        
        //totalLength sort --- small to big
        /* why i need to sort
        Arrays.sort(genes, new Comparator<CityPath>(){
                public int compare(CityPath a1,CityPath a2){
                    if(a1.totalLength<a2.totalLength){
                        return -1;
                    }else{
                        return 1;
                    }
                }
        });
        */
        
        
        for(int ttt=0;ttt<iter;ttt++){
            select();
            //System.out.println("asdasdadads");
            crossover();
            mutation();
            for(int i=0;i<genes.length;i++){
                //genes[i] = new CityPath(genesNum);
                citys.computePathLength(genes[i]);
            }
            
            List<CityPath> temp = (Arrays.asList(genes));
            Collections.shuffle(temp);
            genes = temp.toArray(genes);
            
            
        }
        
        
        /*
        for(int i=0;i<genes.length;i++){
            for(int j=0;j<genes[i].cityPath.length;j++){
                System.out.print(genes[i].cityPath[j]+" ");
            }
            System.out.println("");
        }*/
        for(int i=0;i<genes[0].cityPath.length;i++){
            //System.out.println(genes[i].totalLength);
            System.out.print(genes[0].cityPath[i]+" ");
        }
        System.out.println("");
        System.out.println(genes[0].totalLength);
        
    }
    
    void select(){
        double total = 0;
        double min=10000;
        double[] rouletteData = new double[genes.length];
        for(int i=0;i<genes.length;i++){
            if(genes[i].totalLength<min){
                min = genes[i].totalLength;
            }
        }
        for(int i=0;i<genes.length;i++){
            double temp = 1/(genes[i].totalLength-min+3);
            rouletteData[i] = temp;
            total = total+temp;
        }
        CityPath[] newgene = new CityPath[genes.length];
        for(int i=0;i<genes.length;i++){
            Random ran = new Random();
            double p = ran.nextDouble()*total;
            for(int j=0;j<genes.length;j++){
                p = p-rouletteData[j];
                if(p<=0){
                    newgene[i] = genes[j];
                    break;
                }
            }
        }
        this.genes = newgene;
        
    }
    void crossover(){
        System.out.println("aaaaa");
    }
    void mutation(){
        Random ran = new Random();
        for(int i=0;i<genes.length;i++){
            double p = ran.nextDouble();
            if(p<this.mutationP){
                CityPath c = genes[i];
                int index1 = ran.nextInt(c.cityPath.length);
                int index2 = ran.nextInt(c.cityPath.length);
                int temp = c.cityPath[index1];
                c.cityPath[index1] = c.cityPath[index2];
                c.cityPath[index2] = temp;
            }
        }
    }
}
