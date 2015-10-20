package com.hyperfresh.nxchat.tests;

import com.hyperfresh.nxchat.HyperChat;
import com.hyperfresh.nxchat.Plugin;
import com.hyperfresh.nxchat.Player;
import com.hyperfresh.nxchat.User;
import com.hyperfresh.nxchat.field.FieldManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

public class FieldTests
{
	public class BlankPlugin implements Plugin
	{
		@Override
		public Player getPlayer(UUID id)
		{
			return null;
		}

		@Override
		public Collection<User> getUsers()
		{
			return null;
		}
	}

	HyperChat hyperChat;

	public FieldTests()
	{
		hyperChat = HyperChat.getInstance();

		hyperChat.setPlugin(new BlankPlugin());

		FieldManager fields = hyperChat.getFieldManager();

		fields.addField("c", "Red");
	}

	/**
	 * Test if static fields process correctly.
	 */
	@Test
	public void FieldProcessTest()
	{
		String str = hyperChat.processStaticFields("$c ${c} ${C}");

		Assert.assertEquals("$c Red Red", str);
	}
}
