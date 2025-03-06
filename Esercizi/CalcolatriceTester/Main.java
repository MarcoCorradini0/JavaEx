package CalcolatriceTester;
/*
Esercizio: test di una calcolatrice da riga di comando
La calcolatrice accetta solo in ingresso due numeri interi ed un operatore (+,-,*,/)
L'output è sempre un numero decimale
Il codice deve essere testabile (NO codice mono classe / mono funzione 
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try ( // Menù scelta operazione
            Scanner sc = new Scanner(System.in)) {
            System.out.println("\nInserisci il primo numero: \n");
            int num1 = sc.nextInt();
            System.out.println("\nInserisci il secondo numero: \n");
            int num2 = sc.nextInt();
            System.out.println("\nInserisci l'operazione: \n");
            char op = sc.next().charAt(0);
            System.out.println("\nIl risultato della tua operazione e': \n" + calculate(num1, num2, op));
            try {
                double result = calculate(num1, num2, op);
                System.out.println("\nIl risultato della tua operazione e': \n" + result);
            } catch (IllegalArgumentException e) {
                System.out.println("\nErrore: " + e.getMessage());
            }
        }
    }

    public static double calculate(int a, int b, char operator) {
        Calcolatrice calcolatrice = new Calcolatrice();
        switch (operator) {
            case '+' -> {
                return calcolatrice.somma(a, b);
            }
            case '-' -> {
                return calcolatrice.sottrazione(a, b);
            }
            case '*' -> {
                return calcolatrice.moltiplicazione(a, b);
            }
            case '/' -> {
                return calcolatrice.divisione(a, b);
            }
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
