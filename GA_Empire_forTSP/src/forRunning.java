public class forRunning {
    public static void main(String args[]){
        int iter = 10000;
        int geneNum=20;
        int smallCity=10;
        int bigCity=2;
        GA_Roulette ga = new GA_Roulette();
        ga.init(iter, geneNum,smallCity,bigCity);
        
    }
}
