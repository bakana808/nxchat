package com.hyperfresh.mchyperchat.field;

import java.util.HashMap;
import java.util.Map;

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

	public Map<String, Field> getStaticFields()
	{
		Map<String, Field> staticFields = new HashMap<>();

		fields.entrySet().forEach
		(
			e ->
			{
				if(!e.getValue().isDynamic())
				{
					staticFields.put(e.getKey(), e.getValue());
				}
			}
		);

		return staticFields;
	}

	public Map<String, Field> getDynamicFields()
	{
		Map<String, Field> dynamicFields = new HashMap<>();

		fields.entrySet().forEach
			(
				e ->
				{
					if(e.getValue().isDynamic())
					{
						dynamicFields.put(e.getKey(), e.getValue());
					}
				}
			);

		return dynamicFields;
	}

	public Map<String, Field> getInlinableFields()
	{
		Map<String, Field> dynamicFields = new HashMap<>();

		fields.entrySet().forEach
			(
				e ->
				{
					if(e.getValue().canInline())
					{
						dynamicFields.put(e.getKey(), e.getValue());
					}
				}
			);

		return dynamicFields;
	}

	/**
	 * Clears the entire list of field.
	 */
	public void clear()
	{
		fields = new HashMap<>();
	}

}
