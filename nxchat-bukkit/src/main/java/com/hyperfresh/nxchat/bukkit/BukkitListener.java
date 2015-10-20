package com.hyperfresh.nxchat.bukkit;

import com.hyperfresh.nxchat.HyperChat;
import com.hyperfresh.nxchat.Player;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class BukkitListener implements Listener
{
	private BukkitPlugin plugin;
	private HyperChat hyperChat;

	public BukkitListener(HyperChat hyperChat, BukkitPlugin plugin)
	{
		this.plugin = plugin;
		this.hyperChat = hyperChat;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		plugin.addPlayer(event.getPlayer());
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		plugin.removePlayer(event.getPlayer());
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		event.setCancelled(true);

		Player player = plugin.getPlayer(event.getPlayer());

		hyperChat.getChatManager().chatAs(player, event.getMessage());
	}

	@EventHandler
	public void onConsoleCommand(ServerCommandEvent event)
	{
		if(event.getSender().equals(Bukkit.getConsoleSender()))
		{
			String[] args = event.getCommand().split(" ");
			if(args[0].equals("say"))
			{
				//remove the "say" part of the command
				String said = String.join(" ", (String[])ArrayUtils.remove(args, 0));

				event.setCommand("");

				hyperChat.getChatManager().chatAs(plugin.getConsole(), said);
			}
		}
	}

	@EventHandler
	public void onPreprocessCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = plugin.getPlayer(event.getPlayer());
		String[] split = event.getMessage().substring(1).split(" ");
		if(hyperChat.getCommandManager().dispatchCommand(player, split))
		{
			event.setCancelled(true);
		}
	}
}
