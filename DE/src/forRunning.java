public class forRunning {
    public static void main(String[] args){
        int iteration1=200;
        int iteration2=400;
        int iteration3=600;
        int iteration4=800;
        int iteration5=1000;
        int iteration6=1500;
        int pointNum=30;
        int dimension=2;
        double range = 30;
        double cp=0.6;
        String f_name = "Ackley";
        //Ackley  Rastrigin  Sphere  Rosenbrock  Michalewicz
        //  30       5.12       100       10          π
        double best1 = 10000;
        double best2 = 10000;
        double best3 = 10000;
        double best4 = 10000;
        double best5 = 10000;
        double best6 = 10000;
        
        //for(int i = 0;i<10;i++){    
            DE de = new DECB2P(10000,pointNum,dimension,range,cp,f_name);
            double temp = de.DE_start();
            if(temp<best1){
                best1 = temp;
            }
        //}
        System.out.println(best1);
        /*
        for(int i = 0;i<10;i++){    
            DE de = new DECB(iteration2,pointNum,dimension,range,cp,f_name);
            double temp = de.DE_start();
            if(temp<best2){
                best2 = temp;
            }
        }
        System.out.println(best2);
        for(int i = 0;i<10;i++){    
            DE de = new DECB(iteration3,pointNum,dimension,range,cp,f_name);
            double temp = de.DE_start();
            if(temp<best3){
                best3 = temp;
            }
        }
        System.out.println(best3);
        for(int i = 0;i<10;i++){    
            DE de = new DECB(iteration4,pointNum,dimension,range,cp,f_name);
            double temp = de.DE_start();
            if(temp<best4){
                best4 = temp;
            }
        }
        System.out.println(best4);
        for(int i = 0;i<10;i++){    
            DE de = new DECB(iteration5,pointNum,dimension,range,cp,f_name);
            double temp = de.DE_start();
            if(temp<best5){
                best5 = temp;
            }
        }
        System.out.println(best5);
        for(int i = 0;i<10;i++){    
            DE de = new DECB(iteration6,pointNum,dimension,range,cp,f_name);
            double temp = de.DE_start();
            if(temp<best6){
                best6 = temp;
            }
        }
        System.out.println(best6);
*/
    }
}
