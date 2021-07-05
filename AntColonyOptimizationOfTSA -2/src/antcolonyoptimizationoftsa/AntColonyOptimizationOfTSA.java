package antcolonyoptimizationoftsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntColonyOptimizationOfTSA {
    int iterator = 1000;
    int antNumber=50;
    double alpha=1;
    double beta=2;
    double rho=0.1;
    double q=0.9;
    List<Integer> currentBestPath;
    List<AntAndRunningCity> ants;
    public double init(){
        Random ran = new Random();
        currentBestPath = new ArrayList<Integer>();
        double currentBestLength=0;
        //input the city data
        allCity antCity = new allCity("eil51.txt");
        //init total ants
        ants = new ArrayList<AntAndRunningCity>();
        for(int i=0;i<antNumber;i++){
            ants.add(new AntAndRunningCity(new Ant()));
        }
        
        List<Integer> changeIter = new ArrayList<Integer>();
        List<Double> changeMin = new ArrayList<Double>();
        
        //start to iterator
        for(int iter=0;iter<this.iterator;iter++){
            //every ant will random to a city 
            for(int i=0;i<antNumber;i++){
                int firstCity = ran.nextInt(antCity.citys.size());
                this.ants.get(i).ant.path.add(firstCity);
                for(int j=0;j<antCity.citys.size();j++){
                    if(j==firstCity){
                        continue;
                    }
                    this.ants.get(i).antRunningCity.add(j);
                }
            }
            //while let ant start,we need compute the pheromone of it distance
            antCity.pheromoneCityLengthUpdate(alpha, beta);
            //start to let ant go to next city
            for(int runcity=0;runcity<antCity.citys.size()-1;runcity++){
                for(int i=0;i<this.antNumber;i++){
                    if(ran.nextDouble()<q ){
                        //直接走最短
                        AntAndRunningCity ant = ants.get(i);
                        int index=0;
                        double max = antCity.pheromoneCityLength[ant.antRunningCity.get(0)][ant.ant.getCurrentCity()];
                        for(int j=0;j<ant.antRunningCity.size();j++){
                            if(max<antCity.pheromoneCityLength[ant.ant.getCurrentCity()][ant.antRunningCity.get(j)]){
                                index =j;
                                max = antCity.pheromoneCityLength[ant.ant.getCurrentCity()][ant.antRunningCity.get(j)];
                            }
                        }
                        // we need add totalLength,antPath and remove runningPath
                        int nextCity = ant.antRunningCity.get(index);
                        ant.ant.goNextCity(nextCity, antCity.cityLength[nextCity][ant.ant.getCurrentCity()]);
                        ant.antRunningCity.remove(index);
                    }else{
                        //輪盤
                        AntAndRunningCity ant = ants.get(i);
                        int index=0;
                        double totalLength=0;
                        for(int j=0;j<ant.antRunningCity.size();j++){
                            totalLength+=antCity.pheromoneCityLength[ant.ant.getCurrentCity()][ant.antRunningCity.get(j)];
                        }
                        
                        double ranNum = ran.nextDouble()*totalLength;
                        for(int j=0;j<ant.antRunningCity.size();j++){
                            ranNum=ranNum-antCity.pheromoneCityLength[ant.ant.getCurrentCity()][ant.antRunningCity.get(j)];
                            if(ranNum<=0){
                                index = j;
                                break;
                            }
                        }
                        //Roulette end, let ant go next city
                        int nextCity = ant.antRunningCity.get(index);
                        ant.ant.goNextCity(nextCity, antCity.cityLength[ant.ant.getCurrentCity()][nextCity]);
                        ant.antRunningCity.remove(index);
                    }
                }
            }
            // remember to compute last city to start city
            for(int i=0;i<this.antNumber;i++){
                AntAndRunningCity ant = ants.get(i);
                ant.ant.goNextCity(ant.ant.getStartCity(),antCity.cityLength[ant.ant.getStartCity()][ant.ant.getCurrentCity()]);
            }
            
            //compute the minimum path
            if(currentBestLength==0){
                currentBestLength=ants.get(0).ant.totalPath;
                currentBestPath = new ArrayList(ants.get(0).ant.path);
            }
            int index=-1;
            for(int i=0;i<this.antNumber;i++){
                if(ants.get(i).ant.totalPath<currentBestLength){
                    index=i;
                    currentBestLength = ants.get(i).ant.totalPath;
                    changeMin.add(ants.get(i).ant.totalPath);
                    changeIter.add(iter);
                }
            }
            if(index!=-1){
                currentBestLength = ants.get(index).ant.totalPath;
                this.currentBestPath = new ArrayList(ants.get(index).ant.path);
            }
            
            //local ant pheromone update
            for(int i=0;i<antNumber;i++){
                antCity.pheromoneUpdate(ants.get(i).ant.path, ants.get(i).ant.totalPath, rho);
            }
            //get minpath, update Pheromone table
            antCity.pheromoneUpdate(this.currentBestPath, currentBestLength, rho);
            //init total ant of its path and totalLength
            for(int i=0;i<this.antNumber;i++){
                ants.get(i).ant.initAnt();
            }
            /*if(iter==1||iter==10||iter==20||iter==30||iter==50||iter==100||iter==200||iter==400||iter==800||iter==1500||iter==3000||iter==5000||iter==7000||iter==10000||iter==20000||iter==50000||iter==100000||iter==200000||iter==500000||iter==800000||iter==900000||iter==999999){
                System.out.println("iteration : "+iter);
                System.out.println("currentBestLength : "+currentBestLength);
            }*/
        }
        
        for(int i=0;i<changeMin.size();i++){
            System.out.println("iteration : "+changeIter.get(i)+"  pathLength : "+changeMin.get(i));
        }
        System.out.println("===========================");
        System.out.println("currentBestLength : "+currentBestLength);
        /*
        for(int i=0;i<antCity.pheromoneCityLength.length;i++){
            for(int j=0;j<antCity.pheromoneCityLength.length;j++){
                System.out.print(antCity.pheromoneCityLength[i][j]+"\t");
            }
            System.out.println("");
        }*/
        //System.out.println(currentBestPath.size());
        for(int i=0;i<this.currentBestPath.size();i++){
            System.out.print(currentBestPath.get(i)+"\t");
        }
        
        return currentBestLength;
        /*
        System.out.println("");
        System.out.println("check Pheromone");
        int pheromoneCheck=0;
        for(int i=0;i<antCity.pheromone.length;i++){
            for(int j=0;j<antCity.pheromone.length;j++){
                if(antCity.pheromone[i][j]!=antCity.pheromone[j][i]){
                    pheromoneCheck=1;
                }
            }
        }
        
        if(pheromoneCheck==0){
            System.out.println("PheromoneCheck is OK");
        }else{
            System.out.println("PheromoneCheck not the same");
        }
        */
        /*
        System.out.println("=====================");
        double length=0;
        for(int i=0;i<currentBestPath.size()-1;i++){
            int first = currentBestPath.get(i);
            int second = currentBestPath.get(i+1);
            length+=antCity.cityLength[first][second];
        }
        length+=antCity.cityLength[currentBestPath.get(0)][currentBestPath.get(currentBestPath.size()-1)];
        System.out.println("restart currentLength : "+length);
        */
    }
}


class AntAndRunningCity{
    Ant ant;
    List<Integer> antRunningCity;
    AntAndRunningCity(Ant ant){
        this.ant = ant;
        antRunningCity = new ArrayList<Integer>();
    }
}
