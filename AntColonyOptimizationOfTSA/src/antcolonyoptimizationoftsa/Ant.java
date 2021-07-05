
package antcolonyoptimizationoftsa;

import java.util.ArrayList;
import java.util.List;

public class Ant {
    List<Integer> path;
    double totalPath=0;
    Ant(){
        path = new ArrayList<Integer>();
    }
    Ant(int city){
        path = new ArrayList<Integer>();
        path.add(city);
    }
    //check the city whether the ant runned
    boolean checkInPath(int city){
        if(path.contains(city)){
            return false;
        }else{
            return true;
        }
    }
    //ant go to next city and the total length need increase
    void goNextCity(int nextCity,double nextCityPath){
        path.add(nextCity);
        this.totalPath+=nextCityPath;
    }
    int getStartCity(){
        return path.get(0);
    }
    
    int getCurrentCity(){
        return path.get(path.size()-1);
    }
    void initAnt(){
        this.path.clear();
        this.totalPath=0;
    }
}
