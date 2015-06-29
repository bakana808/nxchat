package com.hyperfresh.mchyperchat.command;

import com.hyperfresh.mchyperchat.User;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Octopod - octopodsquad@gmail.com
 */
public class CommandMethod
{
	Command command;
	Method method;
	Object instance;
	Parameter[] parameters;

	public CommandMethod(Method method, Object instance)
	{
		this.command = method.getAnnotation(Command.class);
		this.method = method;
		this.instance = instance;
		this.parameters = method.getParameters();
	}

	public void dispatch(User source, String... args)
	{
		if(!source.hasPermission(command.permission()))
		{
			source.sendMessage("&cSorry, you don't have permission to use this command.");
			return;
		}

		Object[] parameters = convertArguments(source, args);

		if(parameters != null)
		{
			try
			{
				method.invoke(instance, parameters);
			}
			catch (Exception e)
			{
				source.sendMessage("&cAn error has occured while executing this commands.");
				e.printStackTrace();
			}
		}
	}

	public String getUsage()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("/").append(method.getName());

		// skip first index since that's always User
		for(int i = 1; i < parameters.length; i++)
		{
			Parameter parameter = parameters[i];
			Optional def = parameter.getAnnotation(Optional.class);
			if(def == null)
			{
				sb.append(" <").append(parameter.getName()).append(">");
			}
			else
			{
				sb.append(" [").append(parameter.getName()).append("]");
			}
		}

		return sb.toString();
	}

	/**
	 * Converts string arguments into Java objects.
	 *
	 * @param user
	 * @param stringArgs
	 * @return
	 */
	public Object[] convertArguments(User user, String[] stringArgs)
	{
		Object[] arguments = new Object[parameters.length];
		arguments[0] = user;
		for(int i = 1; i < parameters.length; i++)
		{
			Parameter parameter = parameters[i];
			Optional def = parameter.getAnnotation(Optional.class);
			try
			{
				if(i > (stringArgs.length))
				{
					if(def != null)
					{
						String value = def.value().length == 0 ? null : def.value()[0];
						arguments[i] = convertArgument(value, parameter.getType());
					}
					else
					{
						user.sendMessage("[" + getUsage() + "] " + command.description());
						return null;
					}
				}
				else
				{
					arguments[i] = convertArgument(stringArgs[i - 1], parameter.getType());
				}
			}
			catch (IllegalArgumentException e)
			{
				user.sendMessage("Command error: " + e.getMessage());
				return null;
			}
		}
		return arguments;
	}

	private static <T> T convertArgument(String arg, Class<T> type)
	{
		if(type == String.class)
		{
			return (T)arg;
		}
		else if(type.isEnum())
		{
			return (T)Enum.valueOf((Class<? extends Enum>) type, arg.toUpperCase());
		}
		else if(type == int.class || type == Integer.class)
		{
			try
			{
				return (T)(Integer)Integer.parseInt(arg);
			}
			catch (NumberFormatException e)
			{
				throw new IllegalArgumentException("Expected an integer, but recieved " + arg);
			}
		}
		throw new IllegalArgumentException("Argument type not supported: " + type.getName());
	}
}
