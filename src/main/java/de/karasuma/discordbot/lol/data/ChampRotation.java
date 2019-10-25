package de.karasuma.discordbot.lol.data;

import java.util.HashMap;

public class ChampRotation {
    private final HashMap<String, String> freeChampions;
    private final HashMap<String, String> freeChampionsForNewPlayers;
    private final long maxNewPlayerLevel;

    public ChampRotation(HashMap<String, String> freeChampionIds, HashMap<String, String> freeChampionIdsForNewPlayers, long maxNewPlayerLevel) {
        this.freeChampions = freeChampionIds;
        this.freeChampionsForNewPlayers = freeChampionIdsForNewPlayers;
        this.maxNewPlayerLevel = maxNewPlayerLevel;
    }

    public long getMaxNewPlayerLevel() {
        return maxNewPlayerLevel;
    }

    public HashMap<String, String> getFreeChampions() {
        return freeChampions;
    }

    public HashMap<String, String> getFreeChampionsForNewPlayers() {
        return freeChampionsForNewPlayers;
    }
}
