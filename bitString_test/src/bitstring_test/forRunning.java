
package bitstring_test;

import bitstring_test.BitString_test;
import java.util.Date;

public class forRunning {
    public static void main(String[] args) {
            //init();
        for(int i=25;i<26;i++){
            BitString_test bitstring = new BitString_test();
            
            Date date = new Date();
            long first = date.getTime();
            bitstring.init(i);
            date = new Date();
            long second = date.getTime();
            long time = (second-first);
            System.out.println("i : "+i+"\ntime : "+time);
            if(time>=9600000){
                break;
            }
        }
    }
}
