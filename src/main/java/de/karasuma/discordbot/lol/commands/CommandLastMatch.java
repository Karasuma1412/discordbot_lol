package de.karasuma.discordbot.lol.commands;

import de.karasuma.discordbot.lol.commandhandling.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandLastMatch implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }
}
