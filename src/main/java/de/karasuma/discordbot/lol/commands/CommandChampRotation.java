package de.karasuma.discordbot.lol.commands;

import de.karasuma.discordbot.lol.data.ChampRotation;
import de.karasuma.discordbot.lol.RiotAPIHandler;
import de.karasuma.discordbot.lol.commandhandling.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class CommandChampRotation implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    private String buildChampRotationMessage(ChampRotation champRotation) {
        StringBuilder builder = new StringBuilder();

        builder.append("Free Champions Rotation:");
        for (String name : champRotation.getFreeChampions().values()) {
            builder.append("\n")
                    .append(name);
        }
        builder.append("\n");

        builder.append("\nRotation for players below level ")
                .append(champRotation.getMaxNewPlayerLevel())
                .append(":");
        for (String name : champRotation.getFreeChampionsForNewPlayers().values()) {
            builder.append("\n")
                    .append(name);
        }

        return builder.toString();
    }

    private ChampRotation parseChampRotation(JSONObject champRotationData, HashMap<String, String> champMap) {
        HashMap<String, String> freeChampionsMap = new HashMap<>();
        HashMap<String, String> freeChampionsForNewPlayerMap = new HashMap<>();

        JSONArray freeChampionIds = (JSONArray) champRotationData.get("freeChampionIds");
        JSONArray freeChampionIdsForNewPlayers = (JSONArray) champRotationData.get("freeChampionIdsForNewPlayers");
        long maxNewPlayerLevel = (long) champRotationData.get("maxNewPlayerLevel");

        for (int i = 0; i < freeChampionIds.size(); i++) {
            long key = (long) freeChampionIds.get(i);
            String name = champMap.get(String.valueOf(key));
            freeChampionsMap.put(String.valueOf(key), name);
            System.out.println(name);
        }

        for (int i = 0; i < freeChampionIdsForNewPlayers.size(); i++) {
            long key = (long) freeChampionIdsForNewPlayers.get(i);
            String name = champMap.get(String.valueOf(key));
            freeChampionsForNewPlayerMap.put(String.valueOf(key), name);
            System.out.println(name);
        }

        return new ChampRotation(freeChampionsMap
                , freeChampionsForNewPlayerMap, maxNewPlayerLevel);
    }

    private HashMap<String, String> parseChampData(JSONObject champData) {
        JSONObject data = (JSONObject) champData.get("data");
        HashMap<String, String> champMap = new HashMap<>();

        for (Object o : data.values()) {
            JSONObject jsonObject = (JSONObject) o;
            String key = (String) jsonObject.get("key");
            String name = (String) jsonObject.get("name");
            champMap.put(key, name);
        }
        return champMap;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        JSONObject champRotationData = RiotAPIHandler.getChampRotation();
        if (champRotationData == null) {
            event.getTextChannel().sendMessage("Error: Could not retrieve Champ Rotation data").queue();
        }

        JSONObject champData = RiotAPIHandler.getChampData();
        HashMap<String, String> champMap = parseChampData(champData);

        ChampRotation champRotation = parseChampRotation(champRotationData, champMap);

        String message = buildChampRotationMessage(champRotation);
        event.getTextChannel().sendMessage(message).queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
