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
	private final HyperChatEventPoster eventPoster = new HyperChatEventPoster(this);

	/**
	 * Gets the current instance of HyperChat
	 *
	 * @return the current instance of HyperChat.
	 */
	public static HyperChat getInstance()
	{
		return instance;
	}

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

	public HyperChatEventPoster getEventPoster()
	{
		return this.eventPoster;
	}

	/**
	 * Replaces the existing FieldList with a new one,
	 * effectively deleting all existing field.
	 */
	public void resetFields()
	{
		this.fieldManager.clear();
	}

	private static final String FIELD_REGEX_L = "(?i)\\$\\{", FIELD_REGEX_R = "\\}";

	/**
	 * Replaces all dynamic fields in a string with their values.
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
			str = str.replaceAll(FIELD_REGEX_L + e.getKey() + FIELD_REGEX_R, e.getValue().getFieldValue(user));
		}

		return str;
	}

	/**
	 * Replaces all static fields in a string with their values.
	 * Fields are detected by the pattern <code>"${<field name>}"</code>
	 *
	 * @param str
	 * @return
	 */
	public String processStaticFields(String str)
	{
		for (Map.Entry<String, Field> e : this.fieldManager.getStaticFields().entrySet())
		{
			str = str.replaceAll(FIELD_REGEX_L + e.getKey() + FIELD_REGEX_R, e.getValue().getFieldValue(null));
		}

		return str;
	}

	/**
	 * Replaces all inlinable fields in a string with their values.
	 * Fields are detected by the pattern <code>"${<field name>}"</code>
	 *
	 * @param str
	 * @return
	 */
	public String processInlineFields(String str, User user)
	{
		for (Map.Entry<String, Field> e : this.fieldManager.getInlinableFields().entrySet())
		{
			str = str.replaceAll(FIELD_REGEX_L + e.getKey() + FIELD_REGEX_R, e.getValue().getFieldValue(user));
		}

		return str;
	}
}