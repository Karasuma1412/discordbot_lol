package de.karasuma.discordbot.lol.consolecommands;

import de.karasuma.discordbot.lol.DiscordBot;

public class ConsoleCommandStop implements ConsoleCommand {
	
	private DiscordBot bot;

	public ConsoleCommandStop(DiscordBot bot) {
		this.bot = bot;
	}

	@Override
	public String execute(String input) {
		return bot.toString() + ": " + bot.shutDownBot();
	}

	@Override
	public String getDescription() {
		return "Stop " + bot.toString();
	}

}
