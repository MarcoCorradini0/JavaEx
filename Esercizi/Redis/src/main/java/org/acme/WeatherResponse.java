package org.acme;

import java.util.List;

public class WeatherResponse {
    public List<WeatherItem> list;

    public static class WeatherItem {
        public Main main;
        public List<Weather> weather;
        public String dtTxt;

        public static class Main {
            public String temp;
        }

        public static class Weather {
            public String description;
        }
    }
}
