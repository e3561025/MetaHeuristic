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
                temp1 = temp1 - Math.sin(a)*Math.pow(Math.sin((i+1)*a*a/Math.PI),20);
            }
            value = temp1;
        }else if(this.f_name.equals("Griewank")){
            double temp1 = 0;
            double temp2 = 1;
            for(int i = 0;i<temp.length;i++){
                temp1 = temp1 + (temp[i]-100)*(temp[i]-100);
            }
            temp1 = temp1/4000;
            for(int i = 0;i<temp.length;i++){
                temp2 = temp2 * (Math.cos((temp[i]-100)/(Math.sqrt(i+1))));
            }
            value = temp1 - temp2 +1;
        }else if(f_name.equals("Schaffer_N2")){
            double temp1 = Math.sin(temp[0]*temp[0]-temp[1]*temp[1]);
            double temp2 = 1+0.001*(temp[0]*temp[0]+temp[1]*temp[1]);
            double temp3 = 0.5 + (temp1*temp1-0.5)/(temp2*temp2);
            value = temp3;
        }else if(f_name.equals("Schwefel")){
            double temp1 = 418.9829*10;
            double temp2 = 0;
            for(int i= 0;i<temp.length;i++){
                double temp3 = Math.sin(Math.sqrt(Math.abs(temp[i])));
                temp2 = temp2 + temp3*temp[i];
            }
            value = temp1 - temp2;
        }else if(f_name.equals("Bohachevsky_N2")){
            double temp1 = 0.3*Math.cos(3*Math.PI*temp[0]);
            double temp2 = 0.4*Math.cos(4*Math.PI*temp[1]);
            value = temp[0]*temp[0] + 2*temp[1]*temp[1] - temp1 - temp2 + 0.7;
        }else if(f_name.equals("Sum_Squares")){
            double temp1 = 0;
            for(int i = 0;i<temp.length;i++){
                temp1 = temp1+(i+1)*temp[i]*temp[i];
            }
            value = temp1;
        }else if(f_name.equals("Booth_N2")){
            double temp1 = temp[0]+2*temp[1]-7;
            double temp2 = 2*temp[0]+temp[1]-5;
            value = temp1*temp1+temp2*temp2;
        }else if(f_name.equals("Zakharov")){
            double temp1 =0;
            double temp2 =0;
            for(int i = 0;i<temp.length;i++){
                temp1 = temp1+temp[i]*temp[i];
                temp2 = temp2+ 0.5*(i+1)*temp[i];
            }
            double temp3 = temp1 + Math.pow(temp2,2)+Math.pow(temp2,4);
            value = temp3;
        }else if(f_name.equals("Three-Hump_N2")){
            double temp1 = 2*temp[0]*temp[0] - 1.05*Math.pow(temp[0],4) + Math.pow(temp[0],6)/6 + temp[0]*temp[1] + temp[1]*temp[1];
            value = temp1;
        }else if(f_name.equals("De_Jong_N2")){
            double[] data = {-32,-16,0,16,32};
            double temp1 = 0;
            for(int i=0;i<25;i++){
                int a1 = i%5;
                int a2 = i/5;
                double temp2 = (i+1) + Math.pow(temp[0]-data[a1],6) + Math.pow(temp[1]-data[a2],6);
                temp1 = temp1+ 1/temp2;
            }
            temp1 = temp1+0.002;
            double temp3 = 1/temp1;
            value = temp3;
        }else if(f_name.equals("Beale_N2")){
            double temp1 = 1.5-temp[0]+temp[0]*temp[1];
            double temp2 = 2.25 - temp[0] + temp[0]*temp[1]*temp[1];
            double temp3 = 2.625 - temp[0] + temp[0]*temp[1]*temp[1]*temp[1];
            value = temp1*temp1+temp2*temp2+temp3*temp3;
        }else if(f_name.equals("Powell")){
            double temp1 = 0;
            int length = temp.length/4;
            for(int i = 0;i<length;i++){
                double x0 = temp[4*i]+10*temp[4*i+1];
                double x1 = temp[4*i+2]-temp[4*i+3];
                double x2 = temp[4*i+1]-2*temp[4*i+2];
                double x3 = temp[4*i]-temp[4*i+3];
                temp1 = temp1 + x0*x0+5*x1*x1+x2*x2*x2*x2+10*x3*x3*x3*x3;
            }
            value = temp1;
        }
        return value;
    }
}
