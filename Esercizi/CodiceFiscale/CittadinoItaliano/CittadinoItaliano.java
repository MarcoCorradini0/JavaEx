package classes;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Month;

public class CittadinoItaliano {
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String sesso;

    public CittadinoItaliano(String nome, String cognome, LocalDate dataNascita, String sesso) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.sesso = sesso;
    }

    // TESTED, FUNZIONA
    public String getCodiceFiscale() {
        String codiceFiscale = "";
        codiceFiscale += findLettereCognome();
        codiceFiscale += findLettereNome();
        System.out.println(findLettereNome());
        codiceFiscale += findCifreAnnoNascita();
        codiceFiscale += findCarattereMese();
        codiceFiscale += findCifreGiornoNascita();
        // HARDCODED, CORRISPONDE A MILANO (F205)
        codiceFiscale += "L682";
        codiceFiscale += findValoriControllo(codiceFiscale);
        return codiceFiscale;
    }

    private Character findVocale(String parola, int index) {
        String upperedParola = parola.toUpperCase();
        int vocaleIndex = 0;
        for (int i = 0; i < parola.length(); i++) {
            char lettera = upperedParola.charAt(i);
            if (lettera == 'A' || lettera == 'E' || lettera == 'I' || lettera == 'O' || lettera == 'U') {
                if (vocaleIndex == index)
                    return lettera;
                vocaleIndex++;
            }
        }
        return null;
    }

    private Character findConsonante(String parola, int index) {
        String upperedParola = parola.toUpperCase();
        int consonanteIndex = 0;
        for (int i = 0; i < parola.length(); i++) {
            char lettera = upperedParola.charAt(i);
            if (lettera != 'A' && lettera != 'E' && lettera != 'I' && lettera != 'O' && lettera != 'U') {
                if (consonanteIndex == index)
                    return lettera;
                consonanteIndex++;
            }
        }
        return null;
    }

    // TESTED, FUNZIONA
    public String findLettereCognome() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            if (findConsonante(this.cognome, i) != null)
                result += findConsonante(this.cognome, i);
            else
                break;
        }
        for (int i = result.length(); i < 3; i++) {
            if (findVocale(this.cognome, i) != null)
                result += findVocale(this.cognome, i - result.length());
            else
                break;
        }
        for (int i = result.length(); i < 3; i++) {
            result += 'X';
        }
        return result;
    }

    // TESTED, FUNZIONA
    public String findLettereNome() {
        String result = "";
        String lett1 = "";
        String lett2 = "";
        String lett3 = "";
        String lett4 = "";

        /* Le consonanti da prendere sono la prima, la terza e la quarta/seconda,
           gli indici sono uguali alla lettera da prendere - 1
           perchè findConsonante() si aspetta
           che gli arrivino gli index da un for con i inizializzato a 0 */
        if (findConsonante(this.nome, 0) != null)
            lett1 += findConsonante(this.nome, 0);
        if (findConsonante(this.nome, 2) != null)
            lett3 += findConsonante(this.nome, 2);
        if (findConsonante(this.nome, 3) != null)
            lett4 += findConsonante(this.nome, 3);
        else if (findConsonante(this.nome, 1) != null)
            lett2 += findConsonante(this.nome, 1);

        result += lett1 + lett2 + lett3 + lett4;

        for (int i = result.length(); i < 3; i++) {
            if (findVocale(this.nome, i) != null)
                result += findVocale(this.nome, i - result.length());
            else
                break;
        }

        for (int i = result.length(); i < 3; i++) {
            result += 'X';
        }

        return result;
    }

    // TESTED, FUNZIONA
    public String findCifreAnnoNascita() {
        // Si prende la penultima (lenght - 2) e l'ultima (lenght - 1) !
        // NON lenght e basta, altrimenti vai out of bound
        String castedYear = String.valueOf(dataNascita.getYear());
        return String.valueOf(castedYear.charAt(castedYear.length() - 2)) +
                String.valueOf(castedYear.charAt(castedYear.length() - 1));
    }

    // TESTED, FUNZIONA
    public String findCarattereMese() {
        /* TABELLA DI CORRISPONDENZA
           Gennaio        A
           Febbraio      B
           Marzo         C
           Aprile        D
           Maggio        E
           Giugno        H
           Luglio        L
           Agosto        M
           Settembre     P
           Ottobre       R
           Novembre      S
           Dicembre      T */

        String result = "";

        if (dataNascita.getMonth().equals(Month.JANUARY)) result = "A";
        if (dataNascita.getMonth().equals(Month.FEBRUARY)) result = "B";
        if (dataNascita.getMonth().equals(Month.MARCH)) result = "C";
        if (dataNascita.getMonth().equals(Month.APRIL)) result = "D";
        if (dataNascita.getMonth().equals(Month.MAY)) result = "E";
        if (dataNascita.getMonth().equals(Month.JUNE)) result = "H";
        if (dataNascita.getMonth().equals(Month.JULY)) result = "L";
        if (dataNascita.getMonth().equals(Month.AUGUST)) result = "M";
        if (dataNascita.getMonth().equals(Month.SEPTEMBER)) result = "P";
        if (dataNascita.getMonth().equals(Month.OCTOBER)) result = "R";
        if (dataNascita.getMonth().equals(Month.NOVEMBER)) result = "S";
        if (dataNascita.getMonth().equals(Month.DECEMBER)) result = "T";

        return result;
    }

    // TESTED, FUNZIONA
    public String findCifreGiornoNascita() {
        return sesso.equals("Maschio") ?
                String.valueOf(dataNascita.getDayOfMonth()) :
                String.valueOf(dataNascita.getDayOfMonth() + 40);
    }

    public String findValoriControllo(String cFiscale) {
        String result = "";
        int sum = 0;
        for (int i = 0; i < cFiscale.length(); i++) {
            if (cFiscale.charAt(i) == 'A' || cFiscale.charAt(i) == '0')
                sum += i % 2 == 0 ? 1 : 0;
            else if (cFiscale.charAt(i) == 'B' || cFiscale.charAt(i) == '1')
                sum += i % 2 == 0 ? 0 : 1;
            else if (cFiscale.charAt(i) == 'C' || cFiscale.charAt(i) == '2')
                sum += i % 2 == 0 ? 5 : 2;
            else if (cFiscale.charAt(i) == 'D' || cFiscale.charAt(i) == '3')
                sum += i % 2 == 0 ? 7 : 3;
            else if (cFiscale.charAt(i) == 'E' || cFiscale.charAt(i) == '4')
                sum += i % 2 == 0 ? 9 : 4;
            else if (cFiscale.charAt(i) == 'F' || cFiscale.charAt(i) == '5')
                sum += i % 2 == 0 ? 13 : 5;
            else if (cFiscale.charAt(i) == 'G' || cFiscale.charAt(i) == '6')
                sum += i % 2 == 0 ? 15 : 6;
            else if (cFiscale.charAt(i) == 'H' || cFiscale.charAt(i) == '7')
                sum += i % 2 == 0 ? 17 : 7;
            else if (cFiscale.charAt(i) == 'I' || cFiscale.charAt(i) == '8')
                sum += i % 2 == 0 ? 19 : 8;
            else if (cFiscale.charAt(i) == 'J' || cFiscale.charAt(i) == '9')
                sum += i % 2 == 0 ? 21 : 9;
            else if (cFiscale.charAt(i) == 'K')
                sum += i % 2 == 0 ? 2 : 10;
            else if (cFiscale.charAt(i) == 'L')
                sum += i % 2 == 0 ? 4 : 11;
            else if (cFiscale.charAt(i) == 'M')
                sum += i % 2 == 0 ? 18 : 12;
            else if (cFiscale.charAt(i) == 'N')
                sum += i % 2 == 0 ? 20 : 13;
            else if (cFiscale.charAt(i) == 'O')
                sum += i % 2 == 0 ? 11 : 14;
            else if (cFiscale.charAt(i) == 'P')
                sum += i % 2 == 0 ? 3 : 15;
            else if (cFiscale.charAt(i) == 'Q')
                sum += i % 2 == 0 ? 6 : 16;
            else if (cFiscale.charAt(i) == 'R')
                sum += i % 2 == 0 ? 8 : 17;
            else if (cFiscale.charAt(i) == 'S')
                sum += i % 2 == 0 ? 12 : 18;
            else if (cFiscale.charAt(i) == 'T')
                sum += i % 2 == 0 ? 14 : 19;
            else if (cFiscale.charAt(i) == 'U')
                sum += i % 2 == 0 ? 16 : 20;
            else if (cFiscale.charAt(i) == 'V')
                sum += i % 2 == 0 ? 10 : 21;
            else if (cFiscale.charAt(i) == 'W')
                sum += i % 2 == 0 ? 22 : 22;
            else if (cFiscale.charAt(i) == 'X')
                sum += i % 2 == 0 ? 25 : 23;
            else if (cFiscale.charAt(i) == 'Y')
                sum += i % 2 == 0 ? 24 : 24;
            else if (cFiscale.charAt(i) == 'Z')
                sum += i % 2 == 0 ? 23 : 25;
        }

        int resto = sum % 26;
        return String.valueOf((char) ('A' + resto));
    }
}