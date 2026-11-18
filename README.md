# Sieve of Atkin — Empirical Complexity Analysis

Java implementation and empirical performance analysis of the **Sieve of Atkin**, a modern algorithm for finding all prime numbers up to a specified integer $N$.

## Quick Overview (EN)

Proposed by A. O. L. Atkin and D. J. Bernstein in 2004, the Sieve of Atkin uses binary quadratic forms ($4x^2 + y^2$, $3x^2 + y^2$, and $3x^2 - y^2$) modulo 12 instead of iterative multiple crossing-off.

* **Theoretical Complexity:** $O(N / \log \log N)$ time, $O(N^{1/2 + o(1)})$ space.
* **Key Finding:** Within $N \in [25\,000, 1\,000\,000]$, the baseline implementation demonstrates strictly linear $O(N)$ scaling ($T(2N)/T(N) \approx 2.000$[cite: 4], $T(N)/N \approx 0.404$[cite: 3]) due to array allocation and JVM warm-up overhead masking the slow-growing $1 / \log \log N$ factor.
* **Quick Start:** `javac notpie.java main.java && java main` (exports metrics to `results.csv`).

---

## Подробное описание (RU)

Программная реализация на языке Java и апостериорный анализ трудоёмкости алгоритма **Решето Аткина**, предназначенного для поиска всех простых чисел, не превышающих заданного натурального числа $N$.

### Суть алгоритма
В отличие от классического решета Эратосфена с оценкой $O(N \log \log N)$, решето Аткина опирается на свойства бинарных квадратичных форм ($4x^2+y^2$, $3x^2+y^2$, $3x^2-y^2$) по модулю 12. Простота свободного от квадратов числа определяется нечётностью количества его представлений соответствующей формой.

* **Теоретическая сложность по времени:** $O(N / \log \log N)$
* **Теоретическая сложность по памяти:** $O(N^{1/2+o(1)})$

### Структура проекта
* `notpie.java` — реализация самого алгоритма (метод `compute`) со встроенными аппаратно-независимыми счётчиками базовых операций (`switchCount` для переключений чётности и `sqrCount` для вычеркивания квадратов) и замером времени через `System.nanoTime()`.
* `main.java` — генератор входных данных и запускатор эксперимента. Выполняет прогон для $N$ от $25\,000$ до $1\,000\,000$ с шагом $25\,000$ (40 точек), усредняет время по 100 запускам для сглаживания фонового шума ОС и сохраняет результаты в `results.csv`.

### Основные выводы эксперимента
1. **Линейный рост на практике:** На исследованном диапазоне ($N \le 10^6$) базовая реализация ведёт себя как строго линейный алгоритм $O(N)$ — график зависимости числа операций от $N$ представляет собой прямую линию[cite: 1]
