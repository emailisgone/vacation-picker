package com.vu.vacationdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VacChoiceInfo extends UserVacChoice {
    private String capital;
    private String flagDescription;
    private String flagUrl;
    private Image flag;
    private String currencies;
    public VacChoiceInfo(){
        this.capital = null;
        this.flagDescription = null;
        this.flagUrl = null;
        this.currencies = null;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlagDescription() {
        return flagDescription;
    }

    public void setFlagDescription(String flagDescription) {
        this.flagDescription = flagDescription;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public void setFlag(Image flag) {
        this.flag = flag;
    }

    public Image getFlag() {
        return flag;
    }

    public void generateInfoData(UserVacChoice userData){
        try {
            URL url = new URL("https://restcountries.com/v3.1/name/" + userData.getCountry());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);

            setCapital(root.get(0).path("capital").get(0).asText());

            setFlagDescription(root.get(0).path("flags").path("alt").asText());

            setFlagUrl(root.get(0).path("flags").path("png").asText());

            StringBuilder currency = new StringBuilder();
            JsonNode currenciesNode = root.get(0).path("currencies");
            for (JsonNode currencyNode : currenciesNode) {
                String currencyCode = currencyNode.get("name").asText();
                String currencySymbol = currencyNode.get("symbol").asText();

                currency.append(currencyCode).append(" [").append(currencySymbol).append("]\n");
            }
            setCurrencies(currency.toString());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getFormattedDate(String unformattedDate) {
        String[] parts = unformattedDate.split(" ");

        String year = parts[parts.length - 1];
        String month = parts[1];
        String day = parts[2];

        return year + " " + month + " " + day;
    }
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