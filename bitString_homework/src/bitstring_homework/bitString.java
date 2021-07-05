
package bitstring_homework;

public class bitString{
    boolean bit[]=null;
    bitString(){
        bit=new boolean[10];
    }
    bitString(int num){
        bit = new boolean[num];
    }
    //compute how many one in array
    int oneTimes(){
        int num=0;
        for(int i=0;i<bit.length;i++){
            if(bit[i]){
                num++;
            }
        }
        return num;
    }
    //get boolean array to 0/1 string
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
    // check the array of element all is  one
    boolean end(){
        for(int i=0;i<bit.length;i++){
            if(bit[i]==false){
               return false;
            }
        }
        return true;
    }
}