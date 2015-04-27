package com.hyperfresh.mchyperchat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FieldList
{
    /**
     * The map where all the key-field pairs are stored.
     */
    private HashMap<String, Field> map = new HashMap<>();

    /**
     * Adds a static key-value field to this list.
     * Please use this if your field's value is a constant.
     * @param keys an array of keys to use for this field
     * @param value the field value
     */
    public void addField(String[] keys, String value)
    {
        addField(keys, new StaticField(value, keys));
    }

    public void addField(String key, String value)
    {
        addField(key, new StaticField(value, key));
    }

    /**
     * Adds a field to this list.
     * If your field returns a constant value, please use <code>addField(keys, value)</code> instead.
     * @param keys an array of keys to use for this field
     * @param field the field
     */
    public void addField(String[] keys, Field field)
    {
        for(String key: keys)
        {
            map.put(key, field);
        }
    }

    public void addField(String key, Field field)
    {
        map.put(key, field);
    }

    /**
     * Gets a field from the list by a key.
     * It will return null if the key does not exist.
     * @param key the key of the field
     * @return the field, or null
     */
    public Field getField(String key)
    {
        return map.get(key);
    }

    /**
     * Gets if the key exists in the list.
     * @param key the key of the field
     * @return true if the key exists
     */
    public boolean fieldExists(String key)
    {
        return map.containsKey(key);
    }

    /**
     * Gets an <code>EntrySet</code> where each entry is a string key and a field value.
     * @return the entrySet of this list.
     */
    public Set<Map.Entry<String, Field>> entrySet()
    {
        return map.entrySet();
    }

    /**
     * Clears the entire list of fields.
     */
    public void clear()
    {
        map = new HashMap<>();
    }

    /**
     * Represents a static key-value field.
     */
    private static class StaticField extends Field
    {
        private String value;
        private String[] names;

        public StaticField(String value, String... names)
        {
            this.value = value;
            this.names = names;
        }

        @Override
        public String[] getFieldNames()
        {
            return names;
        }

        @Override
        public String getFieldValue(User sender, String... args)
        {
            return value;
        }
    }
}
