import java.io.FileWriter;
import java.io.PrintWriter;

public class mainHist {
    //программа чисто для гистограммы в подразделе 6.6
    public static void main(String[] args) throws Exception {
        int N = 500000;
        PrintWriter out = new PrintWriter(new FileWriter("hist.csv"));
        out.println("delta_t");
        
        // 100 запусков
        for (int i = 0; i < 100; i++) {
            notpie.totalStats res = notpie.compute(N);
            out.println((double) res.timeDelta / 1000000);
        }
        out.close();
        System.out.println("Saved to hist.csv");
    }
}