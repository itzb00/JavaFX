package ITEC3150;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;

/**
 * represents a city with name, latitude, longitude
 */

public class City {
    //needs latitude, longitude after end of url
    public static final String DARKSKY_URL = "";
    private String name;
    private double latitude;
    private double longitude;

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getCurrentTemperature() {
        ObjectMapper mapper = new ObjectMapper();
        String cityURL = DARKSKY_URL + latitude + "," + longitude;
        String tempString = "";
        try {
            URL url = new URL(cityURL);
            String json = IOUtils.toString(url.openStream(),"UTF-8");
            JsonNode root = mapper.readTree(json);
            JsonNode temp = root.get("currently").get("temperature");
            tempString = temp.asText();

        } catch (IOException e) {
            throw new RuntimeException("error retrieving data");
        }
        return 0;
    }
}
