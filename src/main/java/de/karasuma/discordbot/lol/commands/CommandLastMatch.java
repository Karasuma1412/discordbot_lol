package de.karasuma.discordbot.lol.commands;

import de.karasuma.discordbot.lol.RiotAPIHandler;
import de.karasuma.discordbot.lol.commandhandling.Command;
import de.karasuma.discordbot.lol.data.Summoner;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CommandLastMatch implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        JSONObject summonerData = RiotAPIHandler.getSummonerDataByName(args[0]);
        Summoner summoner = parseSummonerData(summonerData);
        JSONObject matchesData = RiotAPIHandler.getMatchesDataByAccount(summoner.getAccountId());
        long gameId = parseLastMatchId(matchesData);
        JSONObject lastMatchData = RiotAPIHandler.getMatchData(gameId);
        parseLastMatchData(lastMatchData, summoner.getName());
    }

    private void parseLastMatchData(JSONObject lastMatchData, String name) {
        JSONArray participantIdentities = (JSONArray) lastMatchData.get("participantIdentities");
        Integer participantId = null;
        for (int i = 0; i < participantIdentities.size(); i++) {
            JSONObject participant = (JSONObject) participantIdentities.get(i);
            JSONObject player = (JSONObject) participant.get("player");


        }
    }

    private long parseLastMatchId(JSONObject matchesData) {
        JSONArray matches = (JSONArray) matchesData.get("matches");
        JSONObject match = (JSONObject) matches.get(0);
        return (long) match.get("gameId");
    }

    private Summoner parseSummonerData(JSONObject summonerData) {
        String accountId = (String) summonerData.get("accountId");
        String name = (String) summonerData.get("name");

        return Summoner.builder()
                .accountId(accountId)
                .name(name)
                .build();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
