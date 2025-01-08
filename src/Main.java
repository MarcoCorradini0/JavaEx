//Chiedere all'utente quale figura vuole stampare, chiedere i parametri per stamparla, stampare la figura, il perimetro e l'area.
import java.util.Scanner;
import StampaFigura.Forme.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Scegli la figura da stampare: ");
        System.out.println("1) Cerchio");
        System.out.println("2) Ellisse");
        System.out.println("3) Quadrato");
        System.out.println("4) Rettangolo");
        System.out.println("5) Triangolo Equilatero \n");
        System.out.print("Scelta: ");
        int scelta = scanner.nextInt();
        Forma figura = null;

        switch (scelta) {
            case 1:
                System.out.println("Inserisci il raggio del cerchio: ");
                double raggio = scanner.nextDouble();
                figura = new Cerchio(raggio);
                break;
            case 2:
                System.out.println("Inserisci l'asse maggiore dell'ellisse: ");
                double asseMaggiore = scanner.nextDouble();
                System.out.println("Inserisci l'asse minore dell'ellisse: ");
                double asseMinore = scanner.nextDouble();
                figura = new Ellisse(asseMaggiore, asseMinore);
                break;
            case 3:
                System.out.println("Inserisci il lato del quadrato: ");
                double lato = scanner.nextDouble();
                figura = new Quadrato(lato);
                break;
            case 4:
                System.out.println("Inserisci la base del rettangolo: ");
                double base = scanner.nextDouble();
                System.out.println("Inserisci l'altezza del rettangolo: ");
                double altezza = scanner.nextDouble();
                figura = new Rettangolo(base, altezza);
                break;
            case 5:
                System.out.println("Inserisci la base del triangolo equilatero: ");
                double baseTriangolo = scanner.nextDouble();
                figura = new TriangoloEquilatero(baseTriangolo);
                break;
            default:
                System.out.println("Scelta non valida.");break;
        }
        if (figura != null) {
            System.out.println("Perimetro: " + figura.perimetro());
            System.out.println("Area: " + figura.area());
            System.out.println("Disegno: \n");
            figura.disegna();
        }
        scanner.close();
    }
}