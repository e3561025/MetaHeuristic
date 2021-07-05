package antcolonyoptimizationoftsa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class allCity {
    
    
    double[][] pheromone;
    List<City> citys;
    double[][] cityLength;
    double[][] pheromoneCityLength;
    //we need to read the city data
    allCity(String datapath){
        citys = new ArrayList<City>();
        BufferedReader reader = null;
        try {
            //System.out.println("asdasda");
            File file = new File(datapath);
            //System.out.println("asdasda");
            //System.out.println(file.exists());
            reader = new BufferedReader(new FileReader(file));
            
            String strLine;
            while((strLine=reader.readLine())!=null){
                String[] temp = strLine.split(" ");
                City city = new City(Integer.parseInt(temp[0])-1,Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
                citys.add(city);
            }
            int cityNum = citys.size();
            cityLength = new double[cityNum][cityNum];
            pheromone = new double[cityNum][cityNum];
            pheromoneCityLength = new double[cityNum][cityNum];
            //init total City Length
            this.computeCityLength();
            //double pher = maximumCityLength();
            //System.out.println(pher);
            //init total CityPath Pheromone
            this.initPheromone();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            Logger.getLogger(allCity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
            }
        }
        
    }
    
    void pheromoneUpdate(List<Integer> antPath,double totalLength,double rho){
        for(int i=0;i<antPath.size()-1;i++){
           int c1 = antPath.get(i);
           int c2 = antPath.get(i+1);
           this.pheromone[c1][c2] = rho*(1/totalLength)+(1-rho)*this.pheromone[c1][c2];
           this.pheromone[c2][c1] = rho*(1/totalLength)+(1-rho)*this.pheromone[c2][c1];
        }
        this.pheromone[antPath.get(0)][antPath.get(antPath.size()-1)]=rho*(1.0/totalLength)+(1-rho)*this.pheromone[antPath.get(0)][antPath.get(antPath.size()-1)];
        this.pheromone[antPath.get(antPath.size()-1)][antPath.get(0)]=rho*(1.0/totalLength)+(1-rho)*this.pheromone[antPath.get(antPath.size()-1)][antPath.get(0)];
    }
    void pheromoneCityLengthUpdate(double alpha,double beta){
        for(int i=0;i<pheromoneCityLength.length;i++){
            for(int j=0;j<pheromoneCityLength.length;j++){
                this.pheromoneCityLength[i][j]=Math.pow(this.pheromone[i][j],alpha)*Math.pow(1/this.cityLength[i][j], beta);
            }
        }
    }
    
    
    
    //=====================================================================
    // only one time function
    private double twoCityLength(City c1,City c2){
        return Math.sqrt((c1.x-c2.x)*(c1.x-c2.x)+(c1.y-c2.y)*(c1.y-c2.y));
    }
    private void computeCityLength(){
        int cityNum = citys.size();
        for(int i=0;i<cityNum;i++){
            for (int j = 0; j < cityNum; j++) {
                City c1 = citys.get(i);
                City c2 = citys.get(j);
                double length = twoCityLength(c1,c2);
                cityLength[i][j]=length;
            }
        }
    }
    //check length whether compute error
    void print(){
        for (int i = 0; i < citys.size(); i++) {
            for (int j = 0; j < citys.size(); j++) {
                System.out.print(this.cityLength[i][j]+"\t\t\t");
            }
            System.out.println("");
        }
    }
    private double maximumCityLength(){
        List<Integer> path = new ArrayList<Integer>();
        double totalLength=0;
        //get the first City ------- we suppose start in 0
        path.add(0);
        //record some city that we not yet run and their distance
        List<RunningCity> runningCity = new ArrayList<RunningCity>();
        for(int i=1;i<citys.size();i++){
            RunningCity temp = new RunningCity(i);
            double length = cityLength[0][i];
            runningCity.add(temp);
        }
        //start to choose path
        while(path.size()<citys.size()){
            double min = runningCity.get(0).length;
            int minIndex = 0;
            //we need get the minmum Length
            for (int i = 0; i < runningCity.size(); i++) {
                if(runningCity.get(i).length<min){
                    minIndex=i;
                    min=runningCity.get(i).length;
                }
            }
            //ant is running to this city
            path.add(runningCity.get(minIndex).num);
            totalLength+=runningCity.get(minIndex).length;
            //we need remove this city since we are runed
            runningCity.remove(minIndex);
            
            //recompute the distance with new city
            int currentCity = path.get(path.size()-1);
            for (int i = 0; i < runningCity.size(); i++) {
                runningCity.get(i).length = cityLength[currentCity][runningCity.get(i).num];
            }
        }
        int currentCity = path.get(path.size()-1);
        int finalCity = path.get(0);
        double finalLength = cityLength[currentCity][finalCity];
        totalLength+=finalLength;
        return totalLength;
    }
    private void initPheromone(){
        double initPher = 1/this.maximumCityLength();
        for(int i=0;i<this.pheromone.length;i++){
            for (int j = 0; j < this.pheromone.length; j++) {
                pheromone[i][j]=initPher;
            }
        }
    }
}
//every city of its num and coordinate
class City{
    int num;
    int x;
    int y;
    City(int num,int x,int y){
        this.num=num;
        this.x=x;
        this.y=y;
    }
}
//first we need record pheromone data
class RunningCity{
    int num;
    double length;
    RunningCity(int num){
        this.num=num;
    }
}