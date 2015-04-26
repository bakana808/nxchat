package com.hyperfresh.mchyperchat;

import java.util.Map;

public class HyperChat
{
	private HyperChat() { }

	/**
	 * The current instance of ChatFieldList.
	 */
	private static ChatFieldList FIELD_LIST = new ChatFieldList();

	/**
	 * This will run when this plugin is enabled.
	 */
	public static void onEnable()
	{
		//READ "fields" FOLDER HERE
	}

	/**
	 * This will run when this plugin is disabled.
	 */
	public static void onDisable()
	{
		FIELD_LIST.clear();
	}

	/**
	 * Get the main list of fields.
	 * @return the main list of fields
	 */
	public static ChatFieldList getFields() { return FIELD_LIST; }

	/**
	 * Replaces the existing ChatFieldList with a new one,
	 * effectively deleting all existing fields.
	 */
	public static void resetFields() { FIELD_LIST = new ChatFieldList(); }

	/**
	 * Replaces all fields in a string with their values.
	 * Fields are detected by the pattern <code>"${<field name>}"</code>
	 * @param str
	 * @return
	 */
	public static String processString(String str) { return processString(str, false); }
	public static String processString(String str, boolean staticOnly)
	{
		for(Map.Entry<String, ChatField> e: FIELD_LIST.entrySet())
		{
			str = str.replaceAll("(?i)\\$\\{" + e.getKey() + "\\}(?-i)", e.getValue().getFieldValue(null));
		}

		return str;
	}
}