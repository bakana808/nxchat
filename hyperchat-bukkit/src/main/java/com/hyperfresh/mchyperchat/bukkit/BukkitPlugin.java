package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * The entry class for the Bukkit plugin.
 */
public class BukkitPlugin extends JavaPlugin implements Plugin
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
				copyDefaultThemes(getFile(), themeFolder);
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
				Map<String, Theme> themes = readThemes(hyperChat, themeFolder);
				hyperChat.getThemeManager().addAll(themes);
				this.getLogger().log(Level.INFO, themes.size() + " themes added: " + StringUtils.join(themes.keySet(), ", "));
			}
			catch (IOException | ParseException e)
			{
				e.printStackTrace();
			}
		}
	}

	private static void copyDefaultThemes(File jar, File folder) throws IOException
	{
		JarFile file = new JarFile(jar);
		folder.mkdirs();
		file.stream().forEach
		(
		entry ->
		{
			if(entry.getName().startsWith("themes/") && entry.getName().endsWith(".json"))
			{
				File outputFile = new File(folder, FilenameUtils.getName(entry.getName()));
				try
				{
					IOUtils.copy(file.getInputStream(entry), new FileOutputStream(outputFile));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		);
	}

	private static Map<String, Theme> readThemes(HyperChat hyperChat, File folder) throws IOException, ParseException
	{
		Map<String, Theme> themes = new HashMap<>();

		if(folder.exists())
		{
			JSONParser parser = new JSONParser();
			for(File file: folder.listFiles())
			{
				String filename = file.getName();

				if(FilenameUtils.getExtension(filename).equals("json"))
				{
					String themeId = FilenameUtils.getBaseName(filename);

					Object obj = parser.parse(FileUtils.readFileToString(file));

					if(obj instanceof JSONObject)
					{
						final JSONObject json = (JSONObject)obj;

						String name = 		(String) json.get(JsonTheme.NAME_KEY);
						String header = 	(String) json.get(JsonTheme.HEADER_KEY);
						String message = 	(String) json.get(JsonTheme.MESSAGE_KEY);
						String footer = 	(String) json.get(JsonTheme.FOOTER_KEY);

						themes.put(themeId, new JsonTheme(hyperChat, name, header, message, footer));
					}
				}
			}
		}
		else
		{
			throw new FileNotFoundException("Cannot find the themes folder.");
		}

		return themes;
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
