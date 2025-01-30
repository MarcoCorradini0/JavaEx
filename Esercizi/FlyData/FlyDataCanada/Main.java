package FlyData.FlyDataCanada;

import java.io.IOException;
import java.nio.files.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> airports = Files.readCsvFile(Path.of("airports.csv"));
        List<String> canaLines = getAirportsByCountry("Canada", airports);
        List
    }
}
//extractColumns
public static 
public static List<String> getAirportsByCountry(String country, List<String> airports){
    return airports.stream()Stream<String>
            .map((String line) -> extractColumns(line)) Stream<String[]>
            .filter((String[] columns) -> isAirportOfCountry2(country, columns))
            .map((String[] columns) -> extractNameOfAirport2(columns)) Stream<String>
            .sorted()
            .collect(Collectors.toList());
}
public static List<String> getAirportsByCountryForEach(String country, List<String> airports){
    List<String> result = new ArrayList<>();
    for (String line : airports) {
        String[] fields = line.split(",");
        if (isAirportOfCountry(country, line)) {
            String name = extractNameOfAirport(line);
            if (name != null) {
                result.add(name);
            }
        }
    }
    return result;
}
public static boolean isAirportOfCountry(String country, fields) {
    String[] fields = line.split(",");
    if()
}
