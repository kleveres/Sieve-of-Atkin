public class notpie {

    // класс для хранения результатов работы алгоритма
    public static class totalStats {
        public boolean[] atkArr; // массив с флагами простых чисел
        public long switchCount;  // число переключений
        public long sqrCount;  // число вычеркиваний квадратов
        public long timeDelta; // время работы

        public long allOper() {
            return switchCount + sqrCount;
        }
    }

    public static totalStats compute(int N) {
        totalStats result = new totalStats();
        long t0 = System.nanoTime();

        boolean[] atk = new boolean[N + 1];
        result.atkArr = atk;
        if (N >= 2) {atk[2] = true;}
        if (N >= 3) {atk[3] = true;}

        // основной алгоритм
        int sqrtN = (int) Math.sqrt(N);
        long switchCount = 0;

        for (int x = 1; x <= sqrtN; x++) {
            int x2 = x * x;
            for (int y = 1; y <= sqrtN; y++) {
                int y2 = y * y;

                int n = 4 * x2 + y2; //проверка первой формулы
                if (n <= N && (n % 12 == 1 || n % 12 == 5)) {
                    atk[n] = !atk[n]; //меняем флаг
                    switchCount++;
                }

                n = 3 * x2 + y2;
                if (n <= N && n % 12 == 7) {
                    atk[n] = !atk[n];
                    switchCount++;
                }

                n = 3 * x2 - y2;
                if (x > y && n <= N && n % 12 == 11) {
                    atk[n] = !atk[n];
                    switchCount++;
                }
            }
        }
        result.switchCount = switchCount;

        // вычеркиваем числа, кратные квадратам простых
        long sqrCount = 0;
        for (int i = 5; i <= sqrtN; i++) {
            if (atk[i]) {
                int i2 = i * i;
                for (int j = i2; j <= N; j += i2, sqrCount++) {
                    atk[j] = false; // составное
                }
            }
        }
        result.sqrCount = sqrCount;

        result.timeDelta = System.nanoTime() - t0;
        return result;
    }

    public static void main(String[] args) {
        int N = 1000000;
        totalStats res = compute(N);
        
        System.out.println("Список простых чисел до " + N + ":");
        for (int i = 2; i <= N; i++) {
            if (res.atkArr[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println("\n\n\nСтатистика:");
        System.out.println("Счетчик switchCount: " + res.switchCount);
        System.out.println("Счетчик sqrCount: " + res.sqrCount);
        System.out.println("Всего операций: " + res.allOper());
        System.out.println("Время: " + res.timeDelta / 1000000); // в секундах
    }
}