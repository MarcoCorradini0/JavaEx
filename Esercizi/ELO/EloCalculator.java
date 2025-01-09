package ELO;

import java.util.Random;

public class EloCalculator {
    //Funzione per calcolare il guadagno di "ELO"
    public static int calculateWin(int elogiocatore1, int elogiocatore2) {
        Random rand = new Random();
        if (elogiocatore1 > elogiocatore2) {
            //se il giocatore 1 ha un "ELO" superiore a quello del giocatore 2, il giocatore 1 guadagna meno punti "ELO"
            return elogiocatore1 + rand.nextInt(100) - 50;
        } else if (elogiocatore1 < elogiocatore2) {
            //se il giocatore 1 ha un "ELO" inferiore a quello del giocatore 2, il giocatore 1 guadagna piu' punti "ELO"
            return elogiocatore1 + rand.nextInt(100) + 50;
        } else {
            //se il giocatore 1 ha lo stesso "ELO" del giocatore 2, il giocatore 1 guadagna punti "ELO" fissi
            return elogiocatore1 + 100;
        }
    }
}