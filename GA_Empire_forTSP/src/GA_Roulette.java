
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class GA_Roulette {
    //CityPath[] genes;
    // one for nation,one for empire or village ,one for inside of person
    CityPath[][][] genesCity;
    double mutationP=0.01;
    double crossoverP=0.6;
    int empirePersonNum=100;
    int villagePersonNum=20;
    allCity citys;
    void init(int iter,int genesNum,int villageNum,int empireNum){
        //get all cityDistance
        citys = new allCity("eil51.txt");
        System.out.println(citys.citys.size());
        //create total genes
        genesCity = new CityPath[empireNum][][];
        // create one for empire and smallCityNum for village
        for(int i=0;i<empireNum;i++){
            genesCity[i] = new CityPath[villageNum+1][];
        }
        //start to create empire and village person
        for(int i=0;i<empireNum;i++){
            //first is empire person
            genesCity[i][0] = new CityPath[empirePersonNum];
            //other is person of village of empire
            for(int j=0;j<villageNum;j++){
                genesCity[i][j+1] = new CityPath[villagePersonNum];
            }
        }
        //create gene and compute every gene length
        for(int i=0;i<empireNum;i++){
            for(int j=0;j<genesCity[i].length;j++){
                for(int k=0;k<genesCity[i][j].length;k++){
                    genesCity[i][j][k] = new CityPath(citys.citys.size(),citys);
                    citys.computePathLength(genesCity[i][j][k]);
                    //System.out.println(genesCity[i][j][k].totalLength);
                }
            }
        }
        //start to iteration
        for(int ttt=0;ttt<iter;ttt++){
            if(ttt%50==0&&ttt!=0){
                //earthRange of Select and Crossover
                selectAndCrossover_earth(genesCity);
            }else if(ttt%20==0&&ttt!=0){
                //nationRange of Select and Crossover
                for(int i=0;i<empireNum;i++){
                    selectAndCrossover_nation(genesCity[i]);
                }
                
            }else{
                //smallRange of Select and Crossover
                for(int i=0;i<empireNum;i++){
                    for(int j=0;j<genesCity[i].length;j++){
                        //select and crossover
                        selectAndCrossover_each(genesCity[i][j]);
                    }
                }
            }
            //recompute Path and shuffle the array
            for(int i=0;i<empireNum;i++){
                for(int j=0;j<genesCity[i].length;j++){
                    //mutation
                    mutation(genesCity[i][j]);
                    for(int k=0;k<genesCity[i][j].length;k++){
                        citys.computePathLength(genesCity[i][j][k]);
                    }
                    List<CityPath> temp = (Arrays.asList(genesCity[i][j]));
                    Collections.shuffle(temp);
                    genesCity[i][j] = temp.toArray(genesCity[i][j]);
                }
            }
        }
        
        CityPath temp =genesCity[0][0][0];
        double totalLength=genesCity[0][0][0].totalLength;
        for(int i=0;i<genesCity.length;i++){
            for(int j=0;j<genesCity[i].length;j++){
                for(int k=0;k<genesCity[i][j].length;k++){
                    if(genesCity[i][j][k].totalLength<totalLength){
                        temp = genesCity[i][j][k];
                        totalLength = genesCity[i][j][k].totalLength;
                    }
                }
            }
        }
        for(int i=0;i<temp.cityPath.length;i++){
            System.out.print(temp.cityPath[i]+" ");
        }
        System.out.println("\ntotalMinLength is "+temp.totalLength);
    }
    
    void mutation(CityPath[] genes){
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
    //just village or empire use select
    void selectAndCrossover_each(CityPath[] genes){
        double max=0;
        int maxindex=0;
        double min=0;
        int minindex=0;
        //start to Select------ just not choose worst
        for(int i=0;i<genes.length;i++){
            if(genes[i].totalLength>max){
                max = genes[i].totalLength;
                maxindex = i;
            }
        }
        //start to Select best ---- hold the best
        CityPath[] newgene = new CityPath[genes.length];
        min = genes[minindex].totalLength;
        for(int i=1;i<genes.length;i++){
            if(min>genes[i].totalLength){
                min = genes[i].totalLength;
                minindex = i;
            }
        }
        //hold the best in first
        newgene[0] = new CityPath(genes[minindex]);
        
        int current=1;
        while(current<genes.length){
            Random ran = new Random();
            int p = ran.nextInt(genes.length);
            if(p==maxindex) continue;
            newgene[current] = new CityPath(genes[p]);
            current++;
        }
        genes = newgene;
        //use cycleCrossover
        Random ran = new Random();
        int useCrossover = ran.nextInt(3);
        if(useCrossover==0){
            crossover_Cycle(genes);
        }else if(useCrossover==1){
            crossover_Order(genes);
        }else{
            crossover_Partially(genes);
        }
    }
    void selectAndCrossover_nation(CityPath[][] genes_nation){
        int totalArrayLength=0;
        //create all gene of array
        for(int i=0;i<genes_nation.length;i++){
            totalArrayLength+=genes_nation[i].length;
        }
        CityPath[] temp = new CityPath[totalArrayLength];
        int index=0;
        //input total Path in temp
        for(int i=0;i<genes_nation.length;i++){
            for(int j=0;j<genes_nation[i].length;j++){
                temp[index] = genes_nation[i][j];
                index++;
            }
        }
        //we will cut half by select and add half by crossover
        Arrays.sort(temp, new Comparator<CityPath>(){
            public int compare(CityPath a1,CityPath a2){
                if(a1.totalLength<a2.totalLength){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        /*for(int i=0;i<temp.length;i++){
            System.out.println(temp[i].totalLength);
        }*/
        //front half * 2
        for(int i=0;i<temp.length/2;i++){
            temp[i+temp.length/2]=new CityPath(temp[i]);
        }
        //shuffle temp
        List<CityPath> newtemp = (Arrays.asList(temp));
        Collections.shuffle(newtemp);
        temp = newtemp.toArray(temp);
        //then we can do crossover
        Random ran = new Random();
        int useCrossover = ran.nextInt(3);
        if(useCrossover==0){
            crossover_Cycle(temp);
        }else if(useCrossover==1){
            crossover_Order(temp);
        }else{
            crossover_Partially(temp);
        }
        //distribution to everyone
        int size = temp.length;
        for(int i=0;i<genes_nation.length;i++){
            for(int j=0;j<genes_nation[i].length;j++){
                index = ran.nextInt(size);
                genes_nation[i][j] = new CityPath(temp[index]);
            }
        }
    }
    void selectAndCrossover_earth(CityPath[][][] genes_earth){
        int totalArrayLength=0;
        //create all gene of array
        for(int i=0;i<genes_earth.length;i++){
            for(int j=0;j<genes_earth[i].length;j++){
                totalArrayLength+=genes_earth[i][j].length;
            }
            
        }
        CityPath[] temp = new CityPath[totalArrayLength];
        int index=0;
        //input total Path in temp
        for(int i=0;i<genes_earth.length;i++){
            for(int j=0;j<genes_earth[i].length;j++){
                for (int k = 0; k < genes_earth[i][j].length; k++) {
                    temp[index] = genes_earth[i][j][k];
                    index++;
                }
            }
        }
        //we will cut half by select and add half by crossover
        Arrays.sort(temp, new Comparator<CityPath>(){
            public int compare(CityPath a1,CityPath a2){
                //System.out.println(a1.totalLength+"  :  "+a2.totalLength);
                if(a1.totalLength<a2.totalLength){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        //front half * 2
        for(int i=0;i<temp.length/2;i++){
            temp[i+temp.length/2]=new CityPath(temp[i]);
        }
        //shuffle temp
        List<CityPath> newtemp = (Arrays.asList(temp));
        Collections.shuffle(newtemp);
        temp = newtemp.toArray(temp);
        //then we can do crossover
        Random ran = new Random();
        int useCrossover = ran.nextInt(3);
        if(useCrossover==0){
            crossover_Cycle(temp);
        }else if(useCrossover==1){
            crossover_Order(temp);
        }else{
            crossover_Partially(temp);
        }
        //distribution to everyone
        int size = temp.length;
        for(int i=0;i<genes_earth.length;i++){
            for(int j=0;j<genes_earth[i].length;j++){
                for(int k=0;k<genes_earth[i][j].length;k++){
                    index = ran.nextInt(size);
                    genes_earth[i][j][k] = new CityPath(temp[index]);
                }
            }
        }
    }
    void crossover_Cycle(CityPath[] genes){
        Random ran = new Random();
        double p = ran.nextDouble();
        if(p<this.crossoverP){
            for(int num=0;num<genes.length/2;num++){
                CityPath first = genes[num*2];
                CityPath firstCopy = new CityPath(first);
                CityPath second = genes[num*2+1];
                CityPath secondCopy = new CityPath(second);
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
                citys.computePathLength(first);
                citys.computePathLength(second);
                if(first.totalLength>firstCopy.totalLength){
                    first.cityPath = firstCopy.cityPath;
                    first.totalLength = firstCopy.totalLength;
                }
                if(second.totalLength>secondCopy.totalLength){
                    second.cityPath = secondCopy.cityPath;
                    second.totalLength = secondCopy.totalLength;
                }
            }
        }
    }
    void crossover_Order(CityPath[] genes){
        Random ran = new Random();
        double p = ran.nextDouble();
        if(p<this.crossoverP){
            for(int num=0;num<genes.length/2;num++){
                CityPath first = genes[num*2];
                CityPath second = genes[num*2+1];
                CityPath firstCopy = new CityPath(first);
                CityPath secondCopy = new CityPath(second);
                List<Integer> firstList = new ArrayList<Integer>();
                List<Integer> secondList = new ArrayList<Integer>();
                for(int i=0;i<first.cityPath.length;i++){
                    firstList.add(first.cityPath[i]);
                    secondList.add(second.cityPath[i]);
                }
                //random the change length
                int range = ran.nextInt(genes[0].cityPath.length+1);
                //random the change start
                int start = ran.nextInt(genes[0].cityPath.length-range+1);
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
                citys.computePathLength(first);
                citys.computePathLength(second);
                if(first.totalLength>firstCopy.totalLength){
                    first.cityPath = firstCopy.cityPath;
                    first.totalLength = firstCopy.totalLength;
                }
                if(second.totalLength>secondCopy.totalLength){
                    second.totalLength = secondCopy.totalLength;
                    second.cityPath = secondCopy.cityPath;
                }
            }
        }
    }
    void crossover_Partially(CityPath[] genes){
        Random ran = new Random();
        double p = ran.nextDouble();
        if(p<this.crossoverP){
            for(int num=0;num<genes.length/2;num++){
                //random the change length
                int range = ran.nextInt(genes[0].cityPath.length+1);
                //random the change start
                int start = ran.nextInt(genes[0].cityPath.length-range+1);
                //get crossover 
                CityPath first = genes[num*2];
                CityPath second = genes[num*2+1];
                CityPath firstCopy = new CityPath(first);
                CityPath secondCopy = new CityPath(second);
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
                citys.computePathLength(first);
                citys.computePathLength(second);
                if(first.totalLength>firstCopy.totalLength){
                    first.cityPath = firstCopy.cityPath;
                    first.totalLength = firstCopy.totalLength;
                }
                if(second.totalLength>secondCopy.totalLength){
                    second.totalLength = secondCopy.totalLength;
                    second.cityPath = secondCopy.cityPath;
                }
                
            }
        }
    }
}
