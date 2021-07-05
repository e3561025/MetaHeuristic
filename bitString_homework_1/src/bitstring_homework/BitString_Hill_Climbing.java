
package bitstring_homework;
import bitstring_homework.bitString;
import java.util.Random;
public class BitString_Hill_Climbing {
    bitString bitstring;
    int num;
    void init(int num){
        bitstring = new bitString(num);
        randomInit();
        this.num=num;
        
        String string = new String();
        int startNum=bitstring.oneTimes();
        bitString temp = new bitString();
        while(true){
            temp.bit = add();
            if(temp.oneTimes()>bitstring.oneTimes()){
                bitstring.bit=temp.bit;
            }
            if(bitstring.end()){
                string = bitstring.getString();
                break;
            }
        }
        System.out.println("startNum : "+startNum);
        System.out.println("bitstring : "+string);
    }
    //array init need to random
    void randomInit(){
        Random ran = new Random();
        for(int i=0;i<bitstring.bit.length;i++){
            int temp = ran.nextInt(10);
            if(temp%2==0){
                bitstring.bit[i]=true;
            }else{
                bitstring.bit[i]=false;
            }
        }
    }
    //create new array to save bitstring array
    //random a motion and change the motion of data
    boolean[] add(){
        Random random = new Random();
        boolean temp[] = bitstring.bit.clone();
        int ranNum = random.nextInt(num);
        temp[ranNum]=!temp[ranNum];
        return temp;
    }
}
