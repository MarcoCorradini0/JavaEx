package ELO;

import java.util.*;

/*
Gioco randomico, chiedere il nome dei due giocatori, verificare se il giocatore è in classifica caricare il suo "ELO",altrimenti se non ha mai giocato assegnargli 800 punti "ELO".
Un solo turno dove randomicamente uno dei due giocatori vince e guadagna punti "ELO".
Se il giocatore che ha vinto ha un "ELO" superiore a quello del giocatore che ha perso, il giocatore guadagna meno punti "ELO".
Se il giocatore che ha vinto ha un "ELO" inferiore a quello del giocatore che ha perso, il giocatore guadagna piu' punti "ELO".
Se il giocatore che ha vinto ha lo stesso "ELO" del giocatore che ha perso, il giocatore guadagna punti "ELO" fissi.
Stampare i giocatori con il loro "ELO" e il vincitore del turno.
*/

public class EloGame {
    //Menu principale del gioco
    public static void main(String[] args) {
        Map<String, Integer> elogiocatore = Leaderboard.Classifica();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il nome del giocatore 1:");
        String giocatore1 = scanner.nextLine();
        System.out.println("Inserisci il nome del giocatore 2:");
        String giocatore2 = scanner.nextLine();
        int elogiocatore1 = EloManager.getElo(giocatore1, elogiocatore);
        int elogiocatore2 = EloManager.getElo(giocatore2, elogiocatore);
        elogiocatore.putIfAbsent(giocatore1, elogiocatore1);
        elogiocatore.putIfAbsent(giocatore2, elogiocatore2);
        Random rand = new Random();
        String vincitore = rand.nextBoolean() ? giocatore1 : giocatore2;
        String perdente = vincitore.equals(giocatore1) ? giocatore2 : giocatore1;
        if(vincitore.equals(giocatore1)){
            elogiocatore.put(giocatore1, EloCalculator.calculateWin(elogiocatore1, elogiocatore2));
        } else {
            elogiocatore.put(giocatore2, EloCalculator.calculateWin(elogiocatore2, elogiocatore1));
        }
        System.out.println("\n Classifica: ");
        System.out.println(giocatore1 + ": "+ elogiocatore.get(giocatore1) + "ELO");
        System.out.println(giocatore2 + ": " + elogiocatore.get(giocatore2) + "ELO");
        System.out.println("\n" + vincitore + " Ha vinto, invece " + perdente + " ha perso");
        Leaderboard.Classifica(elogiocatore);
        scanner.close();
    }
}