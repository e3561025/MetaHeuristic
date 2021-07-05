public class Fitness {
    String f_name;
    Fitness(String f_name){
        this.f_name = f_name;
    }
    double fitness(double[] temp){
        double value = 0;
        if(this.f_name.equals("Ackley")){
            double temp1 = 0;
            double temp2 = 0;
            for(int i = 0;i< temp.length;i++){
                temp1  = temp1+Math.pow(temp[i],2);
                temp2 = temp2+Math.cos(2*Math.PI*temp[i]);
            }
            temp1 = Math.sqrt(temp1/temp.length);
            temp2 = temp2/temp.length;
            value = -20*Math.pow(Math.E,-0.2*temp1) - Math.pow(Math.E,temp2) + 20 + Math.E;
        }else if(this.f_name.equals("Rastrigin")){
            double temp1 = 0;
            for(int i=0;i<temp.length;i++){
                temp1 = temp1+Math.pow(temp[i], 2) - 10*Math.cos(2*Math.PI*temp[i]) + 10;
            }
            value = temp1;
        }else if(this.f_name.equals("Sphere")){
            double temp1=0;
            for(int i=0;i<temp.length;i++){
                temp1 = temp1+ temp[i]*temp[i];
            }
            value = temp1;
        }else if(this.f_name.equals("Rosenbrock")){
            double temp1 = 0;
            for(int i = 0;i<temp.length/2;i++){
                double a = temp[2*i];
                double b = temp[2*i+1];
                temp1 = temp1+100*Math.pow(b-a*a,2)+Math.pow(1-a, 2);
            }
            value = temp1;
        }else if(this.f_name.equals("Michalewicz")){
            double temp1 = 0;
            for(int i = 0;i<temp.length;i++){
                double a = temp[i];
                temp1 = temp1 + Math.sin(a)*Math.pow(Math.sin((i+1)*a*a/Math.PI),20);
            }
            value = temp1;
        }
        return value;
    }
}
