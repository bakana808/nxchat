package com.hyperfresh.mchyperchat;

import com.hyperfresh.mchyperchat.util.command.CommandManager;
import com.hyperfresh.mchyperchat.field.Field;
import com.hyperfresh.mchyperchat.field.FieldFlag;
import com.hyperfresh.mchyperchat.field.FieldManager;
import com.hyperfresh.mchyperchat.theme.ThemeManager;

import java.util.EnumSet;
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

	private Plugin plugin = null;

	private final FieldManager fieldManager = new FieldManager();
	private final ThemeManager themeManager = new ThemeManager(this);
	private final CommandManager commandManager = new CommandManager(this);

	private final ChatManager chatManager = new ChatManager(this);

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
	public void setPlugin(Plugin plugin)
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
	public Plugin getPlugin()
	{
		return this.plugin;
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

	public CommandManager getCommandManager()
	{
		return this.commandManager;
	}

	public ChatManager getChatManager()
	{
		return this.chatManager;
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
	 * Preprocesses formats read from themes, which is a combination of {@code colorize()}
	 * and {@code processStaticFields()}.
	 *
	 * @param string
	 * @return
	 */
	public String preprocessThemeFormat(String string)
	{
		if(string == null) return null;
		string = Color.colorize(string);
		string = processStaticFields(string);
		return string;
	}

	private static String processFields(Map<String, Field> fields, String str, User user)
	{
		for (Map.Entry<String, Field> e : fields.entrySet())
		{
			str = str.replaceAll(FIELD_REGEX_L + e.getKey() + FIELD_REGEX_R, e.getValue().getFieldValue(user));
		}

		return str;
	}

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
		return processFields(fieldManager.getFields(EnumSet.of(FieldFlag.CONSTANT), true), str, user);
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
		return processFields(fieldManager.getFields(EnumSet.of(FieldFlag.CONSTANT)), str, null);
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
		return processFields(fieldManager.getFields(EnumSet.of(FieldFlag.INLINEABLE)), str, user);
	}


}