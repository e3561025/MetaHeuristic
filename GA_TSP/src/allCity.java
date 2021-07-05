

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
    
    
    //double[][] pheromone;
    List<City> citys;
    double[][] cityLength;
    //double[][] pheromoneCityLength;
    //we need to read the city data
    allCity(String datapath){
        citys = new ArrayList<City>();
        BufferedReader reader = null;
        try {
            File file = new File(datapath);
            reader = new BufferedReader(new FileReader(file));
            
            String strLine;
            while((strLine=reader.readLine())!=null){
                String[] temp = strLine.split(" ");
                City city = new City(Integer.parseInt(temp[0])-1,Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
                citys.add(city);
            }
            int cityNum = citys.size();
            cityLength = new double[cityNum][cityNum];
            //init total City Length
            this.computeCityLength();
            
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
    void computePathLength(CityPath gene){
        double length =0;
        for(int i=0;i<gene.cityPath.length-1;i++){
            
            length = length+this.cityLength[gene.cityPath[i]][gene.cityPath[i+1]];
        }
        length = length+this.cityLength[gene.cityPath[0]][gene.cityPath[gene.cityPath.length-1]];
        //System.out.println(length);
        gene.totalLength=length;
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
}
//every city of its num and coordinate
class City{
    int num;
    double x;
    double y;
    City(int num,double x,double y){
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