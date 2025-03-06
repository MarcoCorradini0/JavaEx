package org.acme;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class WeatherClient {

    @Inject
    @ConfigProperty(name = "openweather.api.key")
    String apiKey;


    public Forecast getWeather(String city) {
        OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(apiKey);
        return openWeatherClient
                .forecast5Day3HourStep()
                .byCityName(city)
                .language(Language.ITALIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();
    }
}
