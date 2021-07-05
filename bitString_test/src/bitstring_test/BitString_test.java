
package bitstring_test;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitString_test {
    bitString bitstring ;
    public void init(int num){
        bitstring = new bitString(num);
        //System.out.println(bitstring.getString());
        String string = new String();
        int maxNum=0;
        while(true){
            int temp = bitstring.oneTimes();
            if(temp>maxNum){
                maxNum=temp;
                string = bitstring.getString();
            }
            if(bitstring.end()){
                break;
            }
            add();
            
        }
        System.out.println("maxNum : "+maxNum);
        System.out.println("bitstring : "+string);
    }
    public void add(){
        if(!bitstring.bit[bitstring.bit.length-1]){
            bitstring.bit[bitstring.bit.length-1]=true;
        }else{
            bitstring.bit[bitstring.bit.length-1]=false;
            for(int i=bitstring.bit.length-2;i>=0;i--){
                if(bitstring.bit[i]){
                    bitstring.bit[i]=false;
                }else{
                    bitstring.bit[i]=true;
                    break;
                }
            }
        }
    }
}
//bitString class
class bitString{
    boolean bit[]=null;
    bitString(){
        bit=new boolean[10];
    }
    bitString(int num){
        bit = new boolean[num];
    }
    int oneTimes(){
        int num=0;
        for(int i=0;i<bit.length;i++){
            if(bit[i]){
                num++;
            }
        }
        return num;
    }
    String getString(){
        String temp ="";
        for(int i=0;i<bit.length;i++){
            if(bit[i]){
                temp+="1";
            }else{
                temp+="0";
            }
        }
        return temp;
    }
    boolean end(){
        for(int i=0;i<bit.length;i++){
            if(bit[i]==false){
               return false;
            }
        }
        return true;
        
    }
}
/*
maxNum : 25
bitstring : 1111111111111111111111111
i : 25
time : 686
maxNum : 26
bitstring : 11111111111111111111111111
i : 26
time : 1326
maxNum : 27
bitstring : 111111111111111111111111111
i : 27
time : 2824
maxNum : 28
bitstring : 1111111111111111111111111111
i : 28
time : 5959
maxNum : 29
bitstring : 11111111111111111111111111111
i : 29
time : 12106
maxNum : 30
bitstring : 111111111111111111111111111111
i : 30
time : 25100
maxNum : 31
bitstring : 1111111111111111111111111111111
i : 31
time : 53275
maxNum : 32
bitstring : 11111111111111111111111111111111
i : 32
time : 114699
maxNum : 33
bitstring : 111111111111111111111111111111111
i : 33
time : 221395
maxNum : 34
bitstring : 1111111111111111111111111111111111
i : 34
time : 455568
maxNum : 35
bitstring : 11111111111111111111111111111111111
i : 35
time : 944816
maxNum : 36
bitstring : 111111111111111111111111111111111111
i : 36
time : 1954449
maxNum : 37
bitstring : 1111111111111111111111111111111111111
i : 37
time : 3988303
maxNum : 38
bitstring : 11111111111111111111111111111111111111
i : 38
time : 8214163
maxNum : 39
bitstring : 111111111111111111111111111111111111111
i : 39
time : 16931568
*/