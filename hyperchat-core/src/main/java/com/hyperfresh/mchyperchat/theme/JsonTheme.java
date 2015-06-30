package com.hyperfresh.mchyperchat.theme;

import com.hyperfresh.mchyperchat.HyperChat;

import java.util.EnumMap;
import java.util.Map;

public class JsonTheme implements Theme
{
	public static final String NAME_KEY = "name";
	public static final String HEADER_KEY = "header";
	public static final String MESSAGE_KEY = "message";
	public static final String FOOTER_KEY = "footer";

	Map<ThemeElement, String> elements = new EnumMap<>(ThemeElement.class);
	String name;

	public JsonTheme(HyperChat hyperChat, String name, String header, String message, String footer)
	{
		this.name = hyperChat.preprocessThemeFormat(name);

		elements.put(ThemeElement.CHAT_HEADER_FORMAT, 	hyperChat.preprocessThemeFormat(header));
		elements.put(ThemeElement.CHAT_MESSAGE_FORMAT, 	hyperChat.preprocessThemeFormat(message));
		elements.put(ThemeElement.CHAT_FOOTER_FORMAT, 	hyperChat.preprocessThemeFormat(footer));
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getAuthor()
	{
		return null;
	}

	@Override
	public String getElement(ThemeElement element)
	{
		return elements.get(element);
	}
}
