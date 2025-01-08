import classes.CittadinoItaliano;
import org.w3c.dom.CDATASection;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        CittadinoItaliano c = new CittadinoItaliano("Marco", "Corradini", LocalDate.of(1999, 9, 26), "Maschio");
        System.out.println(c.getCodiceFiscale());
        System.out.println(c.findValoriControllo("CRRMRC99P26L682"));
    }
}