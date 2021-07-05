/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolonyoptimizationoftsa;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author rshow
 */
public class test {
    public static void main(String args[]){
        /*Scanner scn = new Scanner(System.in);
        allCity city = new allCity("eil51.txt");
        Ant ant = new Ant();
        int first = scn.nextInt()-1;
        for(int i=0;i<51;i++){
            int second = scn.nextInt()-1;
            ant.goNextCity(second, city.cityLength[first][second]);
            first = second;
        }
        System.out.println("total : "+ant.totalPath);*/
        Random ran  =new Random();
        System.out.println(ran.nextInt(2));
    }
}
