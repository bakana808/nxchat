package com.hyperfresh.mchyperchat;

import java.util.Map;

public class HyperChat
{
	private HyperChat() { }

	public static HyperChat INSTANCE = new HyperChat();

	public void onEnable()
	{
		ChatFieldList fieldList = ChatFieldList.getInstance();
		//READ "fields" FOLDER HERE
	}

	public void onDisable()
	{
		ChatFieldList.getInstance().clear();
	}

	public String processString(String str) { return processString(str, false); }
	public String processString(String str, boolean staticOnly)
	{
		for(Map.Entry<String, ChatField> e: ChatFieldList.getInstance().entrySet())
		{
			str = str.replaceAll("(?i)\\$\\{" + e.getKey() + "\\}(?-i)", e.getValue().getValue(null));
		}

		return str;
	}
}