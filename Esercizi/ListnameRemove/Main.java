//Rimuovere i nomi di una lista che contengono la lettera 'a'
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> nomi = new ArrayList<>();
        nomi.add("Mario");
        nomi.add("Luigi");
        nomi.add("Peach");
        nomi.add("Yoshi");
        nomi.add("Donkey Kong");
        nomi.add("Bowser");
        nomi.add("Toad");
        nomi.add("Koopa");
        nomi.add("Waluigi");
        nomi.add("Wario");
        nomi.add("Rosalina"); 
        nomi.add("Axel");  

        Iterator<String> iterator = nomi.iterator();
        while (iterator.hasNext()) {
            String nome = iterator.next();
            if (nome.contains("a")||nome.contains("A")) {
                iterator.remove();
            }
        }
        System.out.println(nomi);
    }
}