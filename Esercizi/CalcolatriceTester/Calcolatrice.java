package CalcolatriceTester;

public class Calcolatrice {
    // Operazione calcolatrice +
    public double somma(int a, int b) {
        return a + b;
    }
    // Operazione calcolatrice -
    public double sottrazione(int a, int b) {
        return a - b;
    }
    // Operazione calcolatrice *
    public double moltiplicazione(int a, int b) {
        return a * b;
    }
    // Operazione calcolatrice /
    public double divisione(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return (double) a / b;
    }
}
