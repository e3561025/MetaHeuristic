
package bitstring_homework;
import bitstring_homework.bitString;
import java.util.Random;
public class BitString_SimulatedAnneling {
    bitString bitstring;
    double temperature=1000;
    int num;
    void init(int num){
        bitstring = new bitString(num);
        //when start,it need random coordinate
        randomInit();
        this.num=num;
        //record the String of maxNum
        String string = new String();
        //record start's oneTime of bitstring
        int startNum=bitstring.oneTimes();
        //handle the temp bitString
        bitString temp = new bitString();
        //start to find local best answer
        while(true){
            temp.bit = add();
            int current = temp.oneTimes();
            int previous = bitstring.oneTimes();
            if(current>previous){
                bitstring.bit=temp.bit;
            }else{
                //need to check temperature
                if(check_temperature(current,previous)){
                    bitstring.bit = temp.bit;
                }
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
    private void randomInit(){
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
    private boolean[] add(){
        Random random = new Random();
        boolean temp[] = bitstring.bit.clone();
        int ranNum = random.nextInt(num);
        temp[ranNum]=!temp[ranNum];
        return temp;
    }
    //check that whether to change to bad range
    private boolean check_temperature(int c,int p){
        double Pa=this.SAtemperature_num(c, p);
        Random random = new Random();
        double random_pro = random.nextDouble();
        this.simulatedAnneling();
        if(Pa>random_pro){
            return true;
        }else{
            return false;
        }
    }
    //after compare probability, temperature need to decrease
    private void simulatedAnneling(){
        this.temperature = this.temperature*(0.9);
    }
    //Pa number
    private double SAtemperature_num(int c,int p){
        double Pa = Math.exp((c-p)/this.temperature);
        return Pa;
    }
}
