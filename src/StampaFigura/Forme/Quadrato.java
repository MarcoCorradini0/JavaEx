package StampaFigura.Forme;
import java.lang.Math;

public class Quadrato extends Rettangolo {
    public Quadrato(double base) {
        super(base, base);                          //super richiama un'altra classe
    }
    public double getBase() {
        return super.getBase();
    }

    @Override
    public void disegna() {
        int latoIntero = (int) Math.round(getBase());
        for (int i = 0; i < latoIntero; i++) {
            for (int j = 0; j < latoIntero; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}