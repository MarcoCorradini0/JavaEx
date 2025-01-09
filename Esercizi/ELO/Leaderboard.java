package ELO;

import java.io.*;
import java.util.*;

public class Leaderboard {
    //Funzione per ordinare la classifica in base all'ELO
    public static Map<String, Integer> Classifica() {
        Map<String, Integer> elogiocatore = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("classifica.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(":")) {
                    String[] parts = line.split(": ");
                    String giocatore = parts[0];
                    int elo = Integer.parseInt(parts[1].split(" ")[0]);
                    elogiocatore.put(giocatore, elo);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
        return elogiocatore;
    }
    public static void Classifica(Map<String, Integer> elogiocatore) {
        //Ordina i giocatori in base al punteggio ELO (in ordine decrescente)
        List<Map.Entry<String, Integer>> classifica = new ArrayList<>(elogiocatore.entrySet());
        //Ordinamento decrescente
        classifica.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        //Salva la classifica ordinata in un file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("classifica.txt"))) {
            writer.write("Leaderboard - Classifica ELO\n");
            writer.write("==============================\n");
            for (Map.Entry<String, Integer> entry : classifica) {
                writer.write(entry.getKey() + ": " + entry.getValue() + " ELO\n");
            }
            System.out.println("Classifica salvata in 'classifica.txt'.");
            } catch (IOException e) {
            System.out.println("Errore nel salvataggio della classifica: " + e.getMessage());
        }
    }
}