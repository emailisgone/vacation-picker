package com.vu.vacationdata;

import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VacChoiceInfo extends UserVacChoice {
    private String capital;

}


/* BIG WIP
*       https://restcountries.com/#rest-countries      -    HUUUUUGE API, VERY POG STUFF
*
* try {
            URL url = new URL("https://api.api-ninjas.com/v1/city?country=LV");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("X-Api-Key", "VHaeAz6bdIfR3FY+TqKtqg==bgXuqrPTgfgn3qAB");
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);
            if(root.isArray()){
                for (JsonNode city : root) {
                    String cityName = city.path("name").asText();
                    System.out.println("City Name: " + cityName);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*
*
*
* */