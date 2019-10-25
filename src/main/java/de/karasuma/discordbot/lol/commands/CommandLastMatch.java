package de.karasuma.discordbot.lol.commands;

import de.karasuma.discordbot.lol.RiotAPIHandler;
import de.karasuma.discordbot.lol.commandhandling.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;

public class CommandLastMatch implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        JSONObject summonerData = RiotAPIHandler.getSummonerDataByName(args[0]);
        String accountId = parseSummonerAccountId(summonerData);
        JSONObject matchesData = RiotAPIHandler.getMAtchesDataByAccount(accountId);
    }

    private String parseSummonerAccountId(JSONObject summonerDate) {
        return (String) summonerDate.get("accountId");
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
