package com.hyperfresh.mchyperchat.field;

public enum FieldFlag
{
	/**
	 * Flags a field as constant, in which case HyperChat will process into themes
	 * once when they are initially loaded.
	 */
	CONSTANT,

	/**
	 * Flags a field as inlineable, in which players will be allowed to type that
	 * field into their messages.
	 */
	INLINEABLE
}
