package ELO;

import java.util.Map;

public class EloManager {
    //Funzione per ottenere l'"ELO" del giocatore
    public static int getElo(String giocatore, Map<String, Integer> elogiocatore) {
        return elogiocatore.getOrDefault(giocatore, 400);
    }
}