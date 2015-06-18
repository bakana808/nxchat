package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
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

		hyperChat.getEventPoster().onPlayerChat(player, event.getMessage());
	}

	@EventHandler
	public void onConsoleCommand(ServerCommandEvent event)
	{
		if(event.getSender().equals(Bukkit.getConsoleSender()))
		{
			String[] args = event.getCommand().split(" ");
			if(args[0].equals("say"))
			{
				event.setCommand("");

				//remove the "say " part of the command
				hyperChat.getEventPoster().onConsoleChat(event.getCommand().substring(4));
			}
		}
	}
}
