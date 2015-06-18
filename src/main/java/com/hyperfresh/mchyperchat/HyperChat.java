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
	 * The current instance of HyperChat
	 */
	private static final HyperChat instance = new HyperChat();

	private HyperChatPlugin plugin = null;

	private final FieldManager fieldManager = new FieldManager();
	private final ThemeManager themeManager = new ThemeManager();

	/**
	 * This will run when this plugin is enabled.
	 */
	public void onEnable(HyperChatPlugin plugin)
	{
		this.plugin = plugin;
		//READ "field" FOLDER HERE
	}

	/**
	 * This will run when this plugin is disabled.
	 */
	public void onDisable()
	{
		this.plugin = null;
		this.fieldManager.clear();
	}

	/**
	 * Gets the current console.
	 *
	 * @return the current console
	 */
	public User getConsole()
	{
		return this.plugin.getConsole();
	}

	public Collection<Player> getPlayers()
	{
		return this.plugin.getPlayers();
	}

	public ThemeManager getThemeManager()
	{
		return this.themeManager;
	}

	/**
	 * Get the main list of field.
	 *
	 * @return the main list of field
	 */
	public FieldManager getFieldManager()
	{
		return this.fieldManager;
	}

	/**
	 * Replaces the existing FieldList with a new one,
	 * effectively deleting all existing field.
	 */
	public void resetFields()
	{
		this.fieldManager.clear();
	}

	/**
	 * Replaces all field in a string with their values.
	 * Fields are detected by the pattern <code>"${<field name>}"</code>
	 *
	 * @param str
	 * @param user
	 * @return
	 */
	public String processDynamicFields(String str, User user)
	{
		for (Map.Entry<String, Field> e : this.fieldManager.getDynamicFields().entrySet())
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
	public String processStaticFields(String str)
	{
		for (Map.Entry<String, Field> e : this.fieldManager.getStaticFields().entrySet())
		{
			str = str.replaceAll("(?i)\\$\\{" + e.getKey() + "\\}(?-i)", e.getValue().getFieldValue(null));
		}

		return str;
	}
}