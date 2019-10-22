package de.karasuma.discordbot.lol;

import de.karasuma.discordbot.lol.commandhandling.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandOPUserLink implements Command {

    private String baseURL = "https://euw.op.gg/summoner/userName=";

    public CommandOPUserLink(LoLBot bot) {

    }

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        System.out.println("test");
        if (args.length <= 0) {
            System.out.println("no args found");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(args[0]);

        for (int i = 1; i < args.length; i++) {
            builder.append("_").append(args[i]);
        }

        event.getTextChannel().sendMessage(baseURL + builder.toString()).queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
