package com.hyperfresh.mchyperchat;

import com.hyperfresh.mchyperchat.field.Field;
import com.hyperfresh.mchyperchat.field.FieldManager;

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
	private static FieldManager fieldManager = new FieldManager();

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
		//READ "field" FOLDER HERE
	}

	/**
	 * This will run when this plugin is disabled.
	 */
	public static void onDisable()
	{
		HyperChat.plugin = null;
		fieldManager.clear();
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
	 * Get the main list of field.
	 *
	 * @return the main list of field
	 */
	public static FieldManager getFieldManager()
	{
		return fieldManager;
	}

	/**
	 * Replaces the existing FieldList with a new one,
	 * effectively deleting all existing field.
	 */
	public static void resetFields()
	{
		fieldManager = new FieldManager();
	}

	/**
	 * Replaces all field in a string with their values.
	 * Fields are detected by the pattern <code>"${<field name>}"</code>
	 *
	 * @param str
	 * @param user
	 * @return
	 */
	public static String processDynamicFields(String str, User user)
	{
		for (Map.Entry<String, Field> e : fieldManager.getDynamicFields().entrySet())
		{
			str = str.replaceAll("(?i)\\$\\{" + e.getKey() + "\\}(?-i)", e.getValue().getFieldValue(user));
		}

		return str;
	}

	/**
	 * Replaces all field in a string with their values.
	 * Fields are detected by the pattern <code>"${<field name>}"</code>
	 * If {@code onlyConstants} is true, then only field marked as static
	 * will be replaced.
	 *
	 * @param str
	 * @return
	 */
	public static String processStaticFields(String str)
	{
		for (Map.Entry<String, Field> e : fieldManager.getStaticFields().entrySet())
		{
			str = str.replaceAll("(?i)\\$\\{" + e.getKey() + "\\}(?-i)", e.getValue().getFieldValue(null));
		}

		return str;
	}
}