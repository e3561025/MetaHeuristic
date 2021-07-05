
package bitstring_homework;
import bitstring_homework.bitString;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
public class BitString_TabuSearch {
    bitString bitstring;
    Queue<bitString> queue = new LinkedList<bitString>();
    int queue_length = 7;
    int num;
    int updateMaxTime;
    void init(int num){
        bitstring = new bitString(num);
        //when start,it need random coordinate
        randomInit();
        this.num=num;
        updateMaxTime = num*100;
        //record start's oneTime of bitstring
        int startNum=bitstring.oneTimes();
        queue.offer(bitstring);
        //in Tabu,we need to record the Maximum answer
        bitString currentMax = new bitString(num);
        
        
        //start to find local best answer
        int listUpdateTime=0;
        while(true){
            //if exceed updateTime,we stop the search
            if(listUpdateTime>=updateMaxTime){
                break;
            }
            bitString temp = new bitString(num);
            temp.bit = add();
            int flag=0;
            for(bitString bs:queue){
                if(Arrays.equals(bs.bit,temp.bit)){
                    flag=1;
                    break;
                }
            }
            //flag=0 is queue hasn't duplicate
            //we need offer temp to queue and check queue_length wheather exceed,then change bitstring
            if(flag==0){
                //if temp best than current bitstring,change current bitstring
                if(temp.oneTimes()>bitstring.oneTimes()){
                    queue.offer(temp);
                    if(queue.size()>queue_length){
                        queue.poll();
                    }
                    //currentMax.bit = temp.bit;
                    if(currentMax.oneTimes()<temp.oneTimes()){
                        currentMax.bit =  temp.bit;
                    }
                    bitstring = temp;
                    listUpdateTime++;
                }
                //if temp bad than current,check currentMax whether in queue,if yes,change current to bad temp
                else{
                    //check currentMax whether in queue
                    int MaxInQueue=0;
                    for(bitString bs:queue){
                        if(Arrays.equals(currentMax.bit,bs.bit)){
                            MaxInQueue=1;
                            break;
                        }
                    }
                    if(MaxInQueue==1){
                        bitstring = temp;
                        queue.offer(temp);
                        if(queue.size()>queue_length){
                            queue.poll();
                        }
                        listUpdateTime++;
                    }
                }
            }
         
            //if flag=1,queue has temp,so we don't get that temp_bitstring
        }
        System.out.println("startNum : "+startNum);
        System.out.println("currentMaxString : "+currentMax.getString());
        System.out.println("currentMaxNum : "+currentMax.oneTimes());
        System.out.println("queue_update : "+listUpdateTime);
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

}
