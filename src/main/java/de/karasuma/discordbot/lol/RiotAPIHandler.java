package de.karasuma.discordbot.lol;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RiotAPIHandler {

    public static JSONObject getChampRotation() {
        JSONObject response = getRequestTo("https://euw1.api.riotgames.com/lol/platform/v3/champion-rotations");
        return response;
    }

    private static JSONObject getRequestTo(String url) {
        try {
            HttpResponse<String> response = Unirest.get(url)
                    .header("Origin", "null")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0")
                    .header("Accept-Language", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("X-Riot-Token", Token.API_KEY)
                    .asString();

            if (response.getStatus() != 200) {
                return null;
            }
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response.getBody());
            return jsonObject;
        } catch (UnirestException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getChampData() {
        JSONObject champData = getRequestTo("http://ddragon.leagueoflegends.com/cdn/9.3.1/data/en_US/champion.json");
        return champData;
    }
}
