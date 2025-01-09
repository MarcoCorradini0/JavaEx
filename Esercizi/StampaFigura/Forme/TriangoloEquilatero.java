package StampaFigura.Forme;
import java.lang.Math;

public class TriangoloEquilatero extends Forma {
    private double base;
    public TriangoloEquilatero(double base) {
        this.base = base;
    }
    public double getBase() {
        return base;
    }

    @Override
    public double area() {
        return (base*base*Math.sqrt(3.0))/4.0;
    }

    @Override
    public double perimetro() {
        return base*3.0;
    }

    @Override
    public void disegna() {
        int latoIntero = (int) Math.round(getBase());
        for (int i = 0; i < latoIntero; i++) {
            // Stampa spazi iniziali
            for (int j = 0; j < latoIntero - i - 1; j++) {
                System.out.print(" ");
            }
            // Stampa asterischi
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("°");
            }
            System.out.println();
        }
    }
}