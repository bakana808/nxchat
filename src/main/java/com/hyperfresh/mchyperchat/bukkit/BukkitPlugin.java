package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.*;
import com.hyperfresh.mchyperchat.theme.ThemeUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
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
	private BukkitConsole console;

	private BukkitListener listener;

	private HyperChat hyperChat;

	@Override
	public void onEnable()
	{
		hyperChat = HyperChat.getInstance();
		listener = new BukkitListener(hyperChat, this);

		hyperChat.setPlugin(this);

		this.console = new BukkitConsole(hyperChat, Bukkit.getConsoleSender());

		Bukkit.getOnlinePlayers().stream().forEach(
			player -> userCache.put(player.getUniqueId(), new BukkitPlayer(hyperChat, player))
		);

		Bukkit.getPluginManager().registerEvents(listener, this);

		loadThemes();
	}

	private void loadThemes()
	{
		this.getLogger().log(Level.INFO, "Reading themes...");
		File themeFolder = new File(this.getDataFolder(), "themes"); //load themes from "theme" folder

		if(!themeFolder.exists())
		{
			try
			{
				ThemeUtils.copyDefaultThemes(getFile(), themeFolder);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				Map<String, Theme> themes = ThemeUtils.readThemes(hyperChat, themeFolder);
				hyperChat.getThemeManager().addAll(themes);
				this.getLogger().log(Level.INFO, themes.size() + " themes added: " + StringUtils.join(themes.keySet(), ", "));
			}
			catch (IOException | ParseException e)
			{
				e.printStackTrace();
			}
		}
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
			.map(player -> (User) player)
			.collect(Collectors.toList());

		users.add(console);

		return users;
	}

	public BukkitConsole getConsole()
	{
		return console;
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
