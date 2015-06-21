package com.hyperfresh.mchyperchat.field;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FieldManager
{
	/**
	 * The map where all the key-field pairs are stored.
	 */
	private HashMap<String, Field> fields = new HashMap<>();

	public FieldManager()
	{
		addFieldContainer(DefaultFields.class); //register default fields
	}

	/**
	 * Adds a static key-value field to this list.
	 * Please use this if your field's value is a constant.
	 *
	 * @param keys  an array of keys to use for this field
	 * @param value the field value
	 */
	public void addField(String[] keys, String value)
	{
		addField(new ConstantField(value, keys));
	}

	public void addField(String key, String value)
	{
		addField(new ConstantField(value, key));
	}

	/**
	 * Adds a field to this list.
	 * If your field returns a constant value, please use <code>addField(keys, value)</code> instead.
	 *
	 * @param field the field
	 */
	public void addField(Field field)
	{
		for(String key: field.getFieldNames())
		{
			fields.put(key, field);
		}
	}

	/**
	 * Attempts to add inner classes that extend {@code Field}.
	 *
	 * It will try to instantiate each class without arguments and will
	 * ignore the class if it throws an exception.
	 *
	 * @param clazz
	 * @return
	 */
	public int addFieldContainer(Class<?> clazz)
	{
		int fieldCount = 0;

		for(Class<?> c: clazz.getClasses())
		{
			if(Field.class.isAssignableFrom(c))
			{
				Class<? extends Field> fieldClass = (Class<? extends Field>)c;

				try
				{
					Field field = fieldClass.newInstance();
					addField(field);
					fieldCount++;
				} catch (InstantiationException | IllegalAccessException e)
				{
					//ignore this field
				}
			}
		}

		return fieldCount;
	}

	/**
	 * Gets a field from the list by a key.
	 * It will return null if the key does not exist.
	 *
	 * @param key the key of the field
	 * @return the field, or null
	 */
	public Field getField(String key)
	{
		return fields.get(key);
	}

	/**
	 * Gets if the key exists in the list.
	 *
	 * @param key the key of the field
	 * @return true if the key exists
	 */
	public boolean fieldExists(String key)
	{
		return fields.containsKey(key);
	}

	public Map<String, Field> getFields()
	{
		return new HashMap<>(fields);
	}

	public Map<String, Field> getFieldsByWhitelist(Set<FieldFlag> whitelist)
	{
		Map<String, Field> filteredFields = new HashMap<>();

		fields.entrySet().forEach
		(
			e ->
			{
				String ID = e.getKey();
				Field field = e.getValue();
				if(field.getFlags().containsAll(whitelist))
				{
					filteredFields.put(ID, field);
				}
			}
		);

		return filteredFields;
	}

	public Map<String, Field> getFieldsByBlacklist(Set<FieldFlag> blacklist)
	{
		Map<String, Field> filteredFields = new HashMap<>();

		fields.entrySet().forEach
			(
				e ->
				{
					String ID = e.getKey();
					Field field = e.getValue();
					if(!Collections.disjoint(field.getFlags(), blacklist))
					{
						filteredFields.put(ID, field);
					}
				}
			);

		return filteredFields;
	}

	/**
	 * Clears the entire list of field.
	 */
	public void clear()
	{
		fields = new HashMap<>();
	}

}
