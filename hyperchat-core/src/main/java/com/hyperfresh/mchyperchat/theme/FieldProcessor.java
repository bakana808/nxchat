package com.hyperfresh.mchyperchat.theme;

import com.hyperfresh.mchyperchat.Color;
import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.User;
import com.hyperfresh.mchyperchat.field.Field;
import com.hyperfresh.mchyperchat.field.FieldFlag;

import java.util.EnumSet;
import java.util.Map;

public class FieldProcessor
{
	private static final String FIELD_REGEX_L = "(?i)\\$\\{", FIELD_REGEX_R = "\\}";

	private HyperChat hyperChat;

	public FieldProcessor(HyperChat hyperChat)
	{
		this.hyperChat = hyperChat;
	}

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
		return processFields(hyperChat.getFieldManager().getFields(EnumSet.of(FieldFlag.CONSTANT), true), str, user);
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
		return processFields(hyperChat.getFieldManager().getFields(EnumSet.of(FieldFlag.CONSTANT)), str, null);
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
		return processFields(hyperChat.getFieldManager().getFields(EnumSet.of(FieldFlag.INLINEABLE)), str, user);
	}


}
