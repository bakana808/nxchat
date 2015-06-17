package com.hyperfresh.mchyperchat.tests;

import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.HyperChatPlugin;
import com.hyperfresh.mchyperchat.Player;
import com.hyperfresh.mchyperchat.User;
import com.hyperfresh.mchyperchat.field.FieldManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

public class StringProcessTest
{
	public class BlankPlugin implements HyperChatPlugin
	{
		@Override
		public Player getPlayer(UUID id)
		{
			return null;
		}

		@Override
		public Collection<Player> getPlayers()
		{
			return null;
		}

		@Override
		public User getConsole()
		{
			return null;
		}
	}

	@Test
	public void FieldProcessTest()
	{
		HyperChat.onEnable(new BlankPlugin());
		FieldManager fields = HyperChat.getFieldManager();

		fields.clear();
		fields.addField(new String[]{"c"}, "RED");

		String str;

		str = HyperChat.processStaticFields("${c}${C}");
		Assert.assertEquals("REDRED", str);
	}
}
