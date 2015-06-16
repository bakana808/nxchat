package com.hyperfresh.mchyperchat;

import java.util.HashMap;
import java.util.Map;

public class ThemeManager
{
	private final Map<String, Theme> themes = new HashMap<>();

	private final Map<Class<? extends Theme>, Theme> themesByClass = new HashMap<>();

	public void add(Theme theme)
	{
		themes.put(theme.getID(), theme);
		themesByClass.put(theme.getClass(), theme);
	}

	public Theme get(String id)
	{
		return themes.get(id);
	}

	public <E extends Theme> E get(Class<E> themeClass)
	{
		return (E)themesByClass.get(themeClass);
	}
}
