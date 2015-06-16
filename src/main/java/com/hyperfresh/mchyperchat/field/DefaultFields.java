package com.hyperfresh.mchyperchat.field;

import com.hyperfresh.mchyperchat.User;

public class DefaultFields
{
	public static class Name extends Field
	{
		@Override
		public String[] getFieldNames()
		{
			return new String[]{"name"};
		}

		@Override
		public String getFieldValue(User sender, String... args)
		{
			return sender.getName();
		}
	}

	public static class Message extends Field
	{
		@Override
		public String[] getFieldNames()
		{
			return new String[]{"message", "msg"};
		}

		@Override
		public String getFieldValue(User sender, String... args)
		{
			return sender.getLastMessage();
		}
	}
}
