package de.karasuma.discordbot.lol;

import de.karasuma.discordbot.lol.consolecommands.ConsoleCommandListenerRunnable;

import java.util.HashMap;

public class Main {
	
	private FileReadAndWriter fileReadAndWriter;
	private HashMap<String, DiscordBot> bots = new HashMap<>();
	private boolean running;

	public static void main(String[] args) {
		Main main = new Main();
		main.createBotAndFileReadAndWriter();
	}

	private void createBotAndFileReadAndWriter() {				
		bots.put("wiki", new LoLBot(this, "PokeWiki"));
		
		fileReadAndWriter = new FileReadAndWriter(this);
		
		for (DiscordBot bot : bots.values()) {
			bot.init();
		}		
		
		running = true;
		Thread thread = new Thread(new ConsoleCommandListenerRunnable(this));
		thread.setDaemon(false);
		thread.start();
		
		System.out.println("Bots loaded successful. You can now start operating.");
	}

	public FileReadAndWriter getFileReaderAndWriter() {
		return fileReadAndWriter;
	}

	public HashMap<String, DiscordBot> getBots() {
		return bots;
	}

	public void setRunning(boolean value) {
		running = value;		
	}

	public boolean getRunning() {
		return running;
	}

}
