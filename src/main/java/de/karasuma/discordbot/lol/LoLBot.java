package de.karasuma.discordbot.lol;

import de.karasuma.discordbot.lol.commandhandling.CommandHandler;
import de.karasuma.discordbot.lol.commandhandling.CommandListener;
import de.karasuma.discordbot.lol.commands.CommandChampRotation;
import de.karasuma.discordbot.lol.commands.CommandLastMatch;
import de.karasuma.discordbot.lol.commands.CommandOPUserLink;
import de.karasuma.discordbot.lol.consolecommands.ConsoleCommandStart;
import de.karasuma.discordbot.lol.consolecommands.ConsoleCommandStop;
import de.karasuma.discordbot.lol.consolecommands.ConsoleCommandUpdateActivityName;
import de.karasuma.discordbot.lol.consolecommands.ConsoleCommandUpdateActivityType;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.util.HashMap;

public class LoLBot extends DiscordBot{

	private final String ACTIVITY_NAME = "LoL Wiki";

	public LoLBot(Main main, String name) {
		super(main, name);
	}
	
	@Override
	public void init() {
		setBuilder(new JDABuilder(AccountType.BOT));
		getBuilder().setToken(Token.TOKEN_TEST);
		getBuilder().setAutoReconnect(true);
		getBuilder().addEventListeners(new CommandListener());

		getBuilder().setActivity(Activity.of(DEFAULT_ACTIVITY_TYPE, ACTIVITY_NAME));

		addCommands();
		setupConsoleCommands();
		startBot();
	}
	
	private void addCommands() {
		CommandHandler.commands.put("op", new CommandOPUserLink(this));
		CommandHandler.commands.put("OP", new CommandOPUserLink(this));
		CommandHandler.commands.put("Op", new CommandOPUserLink(this));
		CommandHandler.commands.put("rotation", new CommandChampRotation());
		CommandHandler.commands.put("Rotation", new CommandChampRotation());
		CommandHandler.commands.put("rota", new CommandChampRotation());
		CommandHandler.commands.put("Rota", new CommandChampRotation());
		CommandHandler.commands.put("lg", new CommandLastMatch());
	}
	
	private void setupConsoleCommands() {
		setConsoleCommands(new HashMap<>());
		getConsoleCommands().put("stop", new ConsoleCommandStop(this));
		getConsoleCommands().put("start", new ConsoleCommandStart(this));
		getConsoleCommands().put("update_gamename", new ConsoleCommandUpdateActivityName(this));
		getConsoleCommands().put("update_gametype", new ConsoleCommandUpdateActivityType(this));
	}

}
