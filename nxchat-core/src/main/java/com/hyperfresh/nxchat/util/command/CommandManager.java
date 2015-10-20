package com.hyperfresh.mchyperchat.util.command;

import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.command.ThemeCommands;
import com.hyperfresh.mchyperchat.User;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Octopod - octopodsquad@gmail.com
 */
public final class CommandManager
{
	private Map<String, CommandMethod> commands = new HashMap<>();

	public CommandManager(HyperChat hyperChat)
	{
		registerCommands(new ThemeCommands(hyperChat));
	}

	public void registerCommands(Object object)
	{
		for(Method method: object.getClass().getMethods())
		{
			if(!method.isAnnotationPresent(Command.class)) continue;

			if(method.getParameterTypes().length == 0 || method.getParameterTypes()[0] != User.class) continue;

			CommandMethod command = new CommandMethod(method, object);

			commands.put(method.getName().toLowerCase(), command);
			for(String alias: method.getAnnotation(Command.class).aliases())
			{
				commands.put(alias, command);
			}
		}
	}

	public boolean commandExists(String alias)
	{
		return commands.containsKey(alias);
	}

	public boolean dispatchCommand(User user, String[] args)
	{
		if(args.length == 0)
		{
			throw new IllegalArgumentException("Cannot dispatch a command with no arguments");
		}
		else if(args.length == 1) // command with no arguments
		{
			return dispatchCommand(user, args[0], new String[0]);
		}
		else // at least one argument
		{
			return dispatchCommand(user, args[0], Arrays.copyOfRange(args, 1, args.length));
		}
	}

	public boolean dispatchCommand(User source, String alias, String[] args)
	{
		if(!commands.containsKey(alias)) return false;
		CommandMethod command = commands.get(alias);
		command.dispatch(source, args);
		return true;
	}

}
