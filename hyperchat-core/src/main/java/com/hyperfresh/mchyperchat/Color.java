package com.hyperfresh.mchyperchat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author octopod
 */
public enum Color
{
	BLACK('0'),
	DARK_BLUE('1'),
	DARK_GREEN('2'),
	DARK_AQUA('3'),
	DARK_RED('4'),
	DARK_PURPLE('5'),
	GOLD('6'),
	GRAY('7'),
	DARK_GRAY('8'),
	BLUE('9'),
	GREEN('a'),
	AQUA('b'),
	RED('c'),
	LIGHT_PURPLE('d'),
	YELLOW('e'),
	WHITE('f');

	Character character = null;

	private static final char SECTION_SIGN = '\u00A7';
	private static final Map<Character, Color> map = new HashMap<>();

	static
	{
		for (Color c : values()) map.put(c.character, c);
	}

	Color(char c)
	{
		character = c;
	}

	public char getChar()
	{
		return character;
	}

	static public Color fromChar(char c)
	{
		return map.get(c);
	}

	static public String colorize(String string)
	{
		return string.replaceAll("(?i)&([0-9A-FK-OR])", "\u00A7$1");
	}

	@Override
	public String toString()
	{
		return SECTION_SIGN + "" + character;
	}
}
