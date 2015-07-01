package com.hyperfresh.mchyperchat.field;

import com.hyperfresh.mchyperchat.Player;
import com.hyperfresh.mchyperchat.User;

public class GeneralFields
{
	public static class Name implements Field
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

	public static class Message implements Field
	{
		@Override
		public String[] getFieldNames()
		{
			return new String[]{"message", "msg"};
		}

		@Override
		public String getFieldValue(User sender, String... args)
		{
			return sender.getLastSaid();
		}
	}

	public static class Uuid implements Field
	{
		@Override
		public FieldFlag[] getFlags()
		{
			return new FieldFlag[]{FieldFlag.INLINEABLE};
		}

		@Override
		public String[] getFieldNames()
		{
			return new String[]{"uuid", "msg"};
		}

		@Override
		public String getFieldValue(User user, String... args)
		{
			if(user instanceof Player)
			{
				return ((Player)user).getUUID().toString();
			}
			else
			{
				return "";
			}
		}
	}
}
