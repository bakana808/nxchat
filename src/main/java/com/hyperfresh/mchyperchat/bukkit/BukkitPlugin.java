package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.HyperChatPlugin;
import com.hyperfresh.mchyperchat.Player;
import com.hyperfresh.mchyperchat.User;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The entry class for the Bukkit plugin.
 */
public class BukkitPlugin extends JavaPlugin implements HyperChatPlugin
{
	/**
	 * A cache of Users that wrap Players.
	 */
	private Map<org.bukkit.entity.Player, Player> userCache = new HashMap<>();

	/**
	 * Bukkit's console object.
	 */
	private User console = new BukkitConsole(Bukkit.getConsoleSender(), null);

	@Override
	public void onEnable()
	{
		HyperChat.onEnable(this);
	}

	@Override
	public void onDisable()
	{
		HyperChat.onDisable();
	}

	@Override
	public Player getPlayer(UUID id)
	{
		org.bukkit.entity.Player player = Bukkit.getPlayer(id);

		//return null if the player is null
		return player == null ? null : getPlayer(player);
	}

	public Player getPlayer(org.bukkit.entity.Player player)
	{
		return userCache.computeIfAbsent(player, p -> new BukkitUser(p, null));
	}

	@Override
	public User getConsole()
	{
		return console;
	}
}
