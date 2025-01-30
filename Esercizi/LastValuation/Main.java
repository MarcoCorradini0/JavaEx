package LastValuation;

import java.io.*;
import java.util.*;

public class Main {
     public static class Startup { // Classe che rappresenta una startup
          private String nome;
          private double valutazione;
          private String paese;

          public Startup(String nome, double valutazione, String paese) {
               this.nome = nome;
               this.valutazione = valutazione;
               this.paese = paese;
          }

          public String getNome() {
               return nome;
          }

          public double getValutazione() {
               return valutazione;
          }

          public String getPaese() {
               return paese;
          }

          @Override
          public String toString() {
               return "Startup{" +
                         "nome='" + nome + '\'' +
                         ", valutazione=" + valutazione +
                         ", paese='" + paese + '\'' +
                         '}';
          }
     }

     private static boolean eUnPaeseEuropeo(String paese) { // Funzione per controllare se un paese è in Europa
          Set<String> paesiEuropei = new HashSet<>(Arrays.asList( // Set di codici ISO dei paesi europei
                    "AL", "AD", "AM", "AT", "AZ", "BY", "BE", "BA", "BG", "HR", "CY", "CZ", "DK", "EE", "FI", "FR",
                    "GE", "DE",
                    "GR", "HU", "IS", "IT", "KZ", "KW", "KG", "LV", "LT", "LU", "MK", "MT", "MD", "MC", "ME", "NL",
                    "NO", "PL",
                    "PT", "RO", "RU", "RS", "SK", "SI", "ES", "SE", "CH", "TR", "UA", "GB", "VA"));
          Map<String, String> mappaCodici = new HashMap<>(); // Mappa con i nomi dei paesi e i rispettivi codici ISO
          mappaCodici.put("Albania", "AL");
          mappaCodici.put("Andorra", "AD");
          mappaCodici.put("Armenia", "AM");
          mappaCodici.put("Austria", "AT");
          mappaCodici.put("Azerbaijan", "AZ");
          mappaCodici.put("Belarus", "BY");
          mappaCodici.put("Belgium", "BE");
          mappaCodici.put("Bosnia and Herzegovina", "BA");
          mappaCodici.put("Bulgaria", "BG");
          mappaCodici.put("Croatia", "HR");
          mappaCodici.put("Cyprus", "CY");
          mappaCodici.put("Czech Republic", "CZ");
          mappaCodici.put("Denmark", "DK");
          mappaCodici.put("Estonia", "EE");
          mappaCodici.put("Finland", "FI");
          mappaCodici.put("France", "FR");
          mappaCodici.put("Germany", "DE");
          mappaCodici.put("Greece", "GR");
          mappaCodici.put("Hungary", "HU");
          mappaCodici.put("Iceland", "IS");
          mappaCodici.put("Italy", "IT");
          mappaCodici.put("Kazakhstan", "KZ");
          mappaCodici.put("Kosovo", "XK");
          mappaCodici.put("Kuwait", "KW");
          mappaCodici.put("Latvia", "LV");
          mappaCodici.put("Lithuania", "LT");
          mappaCodici.put("Luxembourg", "LU");
          mappaCodici.put("Malta", "MT");
          mappaCodici.put("Moldova", "MD");
          mappaCodici.put("Monaco", "MC");
          mappaCodici.put("Montenegro", "ME");
          mappaCodici.put("Netherlands", "NL");
          mappaCodici.put("Norway", "NO");
          mappaCodici.put("Poland", "PL");
          mappaCodici.put("Portugal", "PT");
          mappaCodici.put("Romania", "RO");
          mappaCodici.put("Russia", "RU");
          mappaCodici.put("Serbia", "RS");
          mappaCodici.put("Slovakia", "SK");
          mappaCodici.put("Slovenia", "SI");
          mappaCodici.put("Spain", "ES");
          mappaCodici.put("Sweden", "SE");
          mappaCodici.put("Switzerland", "CH");
          mappaCodici.put("Turkey", "TR");
          mappaCodici.put("Ukraine", "UA");
          mappaCodici.put("United Kingdom", "GB");
          mappaCodici.put("Vatican City", "VA");
          String codicePaese = mappaCodici.getOrDefault(paese, null); // Ottieni il codice del paese
          return codicePaese != null && paesiEuropei.contains(codicePaese); // Controlla se il codice del paese è
                                                                            // presente tra quelli europei
     }

     private static Startup elaboraRiga(String riga) { // Funzione che elabora ogni riga del CSV e restituisce una
                                                       // Startup
          String[] valori = riga.split(",");
          if (valori.length < 8) {
               System.out.println("Riga ignorata (troppo corta): " + riga);
               return null;
          }

          String nomeStartup = valori[2]; // Nome della startup
          String paese = valori[8]; // Paese della startup
          double valutazione; // Valutazione della startup

          try { // Cerca di ottenere la valutazione e gestisce gli errori
               valutazione = Double.parseDouble(valori[4]); // Valutazione
          } catch (NumberFormatException e) {
               System.out.println("Valutazione non valida per la startup " + nomeStartup + ": " + valori[3]);
               return null;
          }

          if (eUnPaeseEuropeo(paese)) { // Se il paese è europeo, crea una nuova Startup
               return new Startup(nomeStartup, valutazione, paese);
          } else {
               // System.out.println("Startup non europea ignorata: " + nomeStartup + " (" +
               // paese + ")");
               return null;
          }
     }

     // Funzione principale per leggere il file e stampare i risultati
     public static void main(String[] args) {
          String percorsoFile = "LastValuation/dati.csv"; // Percorso del file CSV
          List<Startup> startupEuropee = new ArrayList<>(); // Lista di startup europee

          try (BufferedReader br = new BufferedReader(new FileReader(percorsoFile))) {
               String riga;
               br.readLine(); // Ignora la prima riga (header)

               // Elabora ogni riga del file
               while ((riga = br.readLine()) != null) {
                    Startup startup = elaboraRiga(riga);
                    if (startup != null) {
                         startupEuropee.add(startup); // Aggiungi la startup se è europea
                         // System.out.println("Aggiunta startup: " + startup.getNome() + " (" +
                         // startup.getPaese() + ")");
                    }
               }

               // Se ci sono startup europee, stampale ordinate per valutazione
               if (startupEuropee.isEmpty()) {
                    // System.out.println("Nessuna startup europea trovata.");
               } else {
                    startupEuropee.sort((s1, s2) -> Double.compare(s2.getValutazione(), s1.getValutazione()));
                    System.out.println("\nTop 10 startup europee per valutazione:\n");
                    for (int i = 0; i < Math.min(10, startupEuropee.size()); i++) {
                         Startup startup = startupEuropee.get(i);
                         System.out.println((i + 1) + ". " + startup.getNome() + " - " + startup.getValutazione()
                                   + " Billion $\n");
                    }
               }
          } catch (IOException e) {
               e.printStackTrace();
          }
     }
}
