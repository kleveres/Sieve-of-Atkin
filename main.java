import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class main {
    public static void main(String[] args) {
        String filename = "results.csv";
        
        try (PrintWriter csv = new PrintWriter(new FileWriter(filename))) {
            csv.println("N,switchCount,sqrCount,allCount,avgTime");

            for (int N = 0; N <= 1000000; N += 25000) {
                int K = 100;
                long totalTime = 0;
                
                notpie.totalStats lastRes = null;
                // Запускаем алгоритм R раз для усреднения времени
                for (int r = 0; r < K; r++) {
                    lastRes = notpie.compute(N);
                    totalTime += lastRes.timeDelta;
                }
                
                double avgTimeSec = ( ((double) totalTime / K) / 1000000000);
                long totalOps = lastRes.allOper();
                
                // Записываем строку в CSV файл
                csv.printf(Locale.US, "%d,%d,%d,%d,%.9f\n", 
                        N, lastRes.switchCount, lastRes.sqrCount, totalOps, avgTimeSec);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}