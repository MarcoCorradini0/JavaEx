package StampaFigura.Forme;
import java.lang.Math;

public class Cerchio extends Ellisse {
    public Cerchio(double raggio) {
        super(2*raggio, 2*raggio);
    }
    public double getRaggio() {
        return (getAsseMaggiore()/2.0);
    }
    
    @Override
    public void disegna() {
        int raggioIntero = (int) Math.round(getRaggio());
        double fattoreScalaX = 2.0; 
        for (int y = -raggioIntero; y <= raggioIntero; y++) {
            for (int x = (int) (-raggioIntero * fattoreScalaX); x <= raggioIntero * fattoreScalaX; x++) {
                if (Math.sqrt((x / fattoreScalaX) * (x / fattoreScalaX) + y * y) <= raggioIntero) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}