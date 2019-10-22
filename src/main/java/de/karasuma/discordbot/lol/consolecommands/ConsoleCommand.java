package de.karasuma.discordbot.lol.consolecommands;

public interface ConsoleCommand {

	String execute(String input);

	String getDescription();

}
