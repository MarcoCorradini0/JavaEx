package StampaFigura.Forme;
import java.lang.Math;

public class Rettangolo extends Forma {
    private double base;
    private double altezza;

    public Rettangolo(double base, double altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public double area() {
        return base*altezza;
    }

    @Override
    public double perimetro() {
        return (base+altezza)*2.0;
    }

    @Override
    public void disegna() {
        int baseIntera = (int) Math.round(base);
        int altezzaIntera = (int) Math.round(altezza);
        for (int i = 0; i < altezzaIntera; i++) {
            for (int j = 0; j < baseIntera; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public double getBase() {
        return base;
    }
    public double getAltezza() {
        return altezza;
    }
}