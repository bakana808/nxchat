package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.HyperChatPlugin;
import com.hyperfresh.mchyperchat.Player;
import com.hyperfresh.mchyperchat.User;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The entry class for the Bukkit plugin.
 */
public class BukkitPlugin extends JavaPlugin implements HyperChatPlugin
{
	/**
	 * A cache of Users that wrap Players.
	 */
	private Map<UUID, Player> userCache = new HashMap<>();

	/**
	 * Bukkit's console object.
	 */
	private User console;

	private BukkitListener listener;

	private HyperChat hyperChat;

	@Override
	public void onEnable()
	{
		hyperChat = HyperChat.getInstance();
		listener = new BukkitListener(hyperChat, this);

		hyperChat.onEnable(this);

		this.console = new BukkitConsole(hyperChat, Bukkit.getConsoleSender());

		Bukkit.getOnlinePlayers().stream().forEach(
			player -> userCache.put(player.getUniqueId(), new BukkitPlayer(hyperChat, player))
		);

		Bukkit.getPluginManager().registerEvents(listener, this);
	}

	@Override
	public void onDisable()
	{
		hyperChat.onDisable();

		userCache.clear();
	}

	@Override
	public Player getPlayer(UUID id)
	{
		org.bukkit.entity.Player player = Bukkit.getPlayer(id);

		//return null if the player is null
		return player == null ? null : getPlayer(player);
	}

	@Override
	public Collection<User> getUsers()
	{
		Collection<User> users = userCache.values().stream()
			.map(player -> (User)player)
			.collect(Collectors.toList());

		users.add(console);

		return users;
	}

	public Player getPlayer(org.bukkit.entity.Player player)
	{
		return userCache.computeIfAbsent(player.getUniqueId(), id -> new BukkitPlayer(hyperChat, player));
	}

	public void addPlayer(org.bukkit.entity.Player player)
	{
		userCache.put(player.getUniqueId(), new BukkitPlayer(hyperChat, player));
	}

	public void removePlayer(org.bukkit.entity.Player player)
	{
		userCache.remove(player.getUniqueId());
	}
}
