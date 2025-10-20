package org.acme;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;

import io.quarkus.qute.Template;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/weather")
public class MainForecastResource {

    @Inject
    Template forecast;
    @Inject
    RedisDataSource redis;
    @Inject
    WeatherClient weatherClient;

    @GET
    @Path("")
    @Produces(MediaType.TEXT_HTML)
    public Response getWeather(@QueryParam("city") String city,
            @QueryParam("days") @DefaultValue("3") int days) {
        if (city == null || city.isBlank()) {
            // Se la città non è fornita, ritorna una pagina vuota con il form
            return Response.ok(forecast.data("forecastData", null)).build();
        }

        // Chiamata API OpenWeatherMap
        try {
            Forecast apiResponse = weatherClient.getWeather(city);

            // Filtra le previsioni solo per i giorni successivi
            LocalDate today = LocalDate.now();
            List<Map<String, String>> forecastData = apiResponse.getWeatherForecasts().stream()
                    // Prendi solo i dati che iniziano dal giorno successivo
                    .filter(x -> x.getForecastTime().toLocalDate().isAfter(today))
                    // Raggruppa per data, prendendo solo una previsione per giorno
                    .collect(Collectors.groupingBy(
                            x -> x.getForecastTime().toLocalDate(),
                            Collectors.toList()))
                    .entrySet().stream()
                    .limit(days) // Limita al numero di giorni richiesti
                    .map(entry -> extractInfo(entry.getValue().get(0))) // Usa la prima previsione del giorno
                    .collect(Collectors.toList());
            ValueCommands<String, Long> commands = redis.value(Long.class);
            long visits = commands.incr("counter");

            return Response.ok(forecast
                    .data("forecastData", forecastData, "visits", visits)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Problema nel caricamento dei dati meteo: " + e.getMessage())
                    .build();
        }
    }

    public Map<String, String> extractInfo(WeatherForecast item) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = item.getForecastTime().toLocalDate().format(formatter);

        return Map.of(
                "Data", formattedDate,
                "Temperatura", item.getTemperature() + "°C",
                "Descrizione", item.getWeatherState().getDescription());
    }
}
