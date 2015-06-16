package com.hyperfresh.mchyperchat;

import java.util.Collection;
import java.util.Map;

public class HyperChat
{
	private HyperChat()
	{
	}

	/**
	 * The current instance of FieldList.
	 */
	private static FieldList FIELD_LIST = new FieldList();

	/**
	 * The currently used plugin.
	 */
	private static HyperChatPlugin plugin = null;

	private static ThemeManager themeManager = new ThemeManager();

	/**
	 * This will run when this plugin is enabled.
	 */
	public static void onEnable(HyperChatPlugin plugin)
	{
		HyperChat.plugin = plugin;
		//READ "fields" FOLDER HERE
	}

	/**
	 * This will run when this plugin is disabled.
	 */
	public static void onDisable()
	{
		HyperChat.plugin = null;
		FIELD_LIST.clear();
	}

	/**
	 * Gets the current console.
	 *
	 * @return the current console
	 */
	public static User getConsole()
	{
		return HyperChat.plugin.getConsole();
	}

	public static Collection<Player> getPlayers()
	{
		return HyperChat.plugin.getPlayers();
	}

	public static ThemeManager getThemeManager()
	{
		return themeManager;
	}

	/**
	 * Get the main list of fields.
	 *
	 * @return the main list of fields
	 */
	public static FieldList getFields()
	{
		return FIELD_LIST;
	}

	/**
	 * Replaces the existing FieldList with a new one,
	 * effectively deleting all existing fields.
	 */
	public static void resetFields()
	{
		FIELD_LIST = new FieldList();
	}

	/**
	 * Replaces all fields in a string with their values.
	 * Fields are detected by the pattern <code>"${<field name>}"</code>
	 *
	 * @param str
	 * @param user
	 * @return
	 */
	public static String processDynamicFields(String str, User user)
	{
		for (Map.Entry<String, Field> e : FIELD_LIST.entrySet())
		{
			if(!e.getValue().isDynamic())
			{
				str = str.replaceAll("(?i)\\$\\{" + e.getKey() + "\\}(?-i)", e.getValue().getFieldValue(user));
			}
		}

		return str;
	}

	/**
	 * Replaces all fields in a string with their values.
	 * Fields are detected by the pattern <code>"${<field name>}"</code>
	 * If {@code onlyConstants} is true, then only fields marked as static
	 * will be replaced.
	 *
	 * @param str
	 * @return
	 */
	public static String processStaticFields(String str)
	{
		for (Map.Entry<String, Field> e : FIELD_LIST.entrySet())
		{
			if(!e.getValue().isDynamic())
			{
				str = str.replaceAll("(?i)\\$\\{" + e.getKey() + "\\}(?-i)", e.getValue().getFieldValue(null));
			}
		}

		return str;
	}
}