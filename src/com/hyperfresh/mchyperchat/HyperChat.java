package com.hyperfresh.mchyperchat;

import java.util.Map;

public class HyperChat
{
	private HyperChat() { }

	private final static ChatFieldList FIELD_LIST = new ChatFieldList();

	public static void onEnable()
	{
		//READ "fields" FOLDER HERE
	}

	public static void onDisable()
	{
		FIELD_LIST.clear();
	}

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