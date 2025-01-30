package FlyData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    public static void main(String[] args) {
        List<String> righe = leggiFile("FlyData/airlines.csv");
        Map<String, Integer> lineeattive = calcolaLineeAttive(righe);
        stampa(lineeattive);
    }
*/
public class Main {
    public static void main(String[] args) {
        String txt = "FlyData/airlines.csv";
        Map<String, Integer> lineeattive = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(txt));
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] campi = line.split(",");
                if (campi.length > 5) {
                    String stato = campi[5].trim();
                    String paese = campi[4].trim();
                    /*
                     * int vecchioValore = lineeattive.getOrDefault(paese, 0);
                     * //Integer vecchioValore = lineeattive.get(paese);
                     * //boolean attiva = isAttiva(stato);
                     * if (vecchioValore == null) {
                     * vecchioValore = 0;
                     * }
                     * int nuovoValore = vecchioValore + 1;
                     * lineeattive.put(paese, nuovoValore);
                     */
                    if ("Y".equals(stato) && !stato.isEmpty()) {
                        lineeattive.put(paese, lineeattive.getOrDefault(paese, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nNumero di compagnie aeree attive per paese: \n");
        for (Map.Entry<String, Integer> entry : lineeattive.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
