package com.hyperfresh.nxchat.util.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Octopod - octopodsquad@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command
{
	/**
	 * An array of commands aliases that will refer to this commands.
	 * The name of the annotated method is the first one.
	 */
	String[] aliases() default {};

	String permission();

	String description() default "";
}
