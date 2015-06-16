package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.HyperChatEventPoster;
import com.hyperfresh.mchyperchat.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class BukkitListener
{
	private BukkitPlugin plugin;

	public BukkitListener(BukkitPlugin plugin)
	{
		this.plugin = plugin;
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

		HyperChatEventPoster.onPlayerChat(player, event.getMessage());
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
				HyperChatEventPoster.onConsoleChat(event.getCommand().substring(4));
			}
		}
	}
}
