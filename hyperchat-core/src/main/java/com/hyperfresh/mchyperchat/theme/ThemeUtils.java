package com.hyperfresh.mchyperchat.theme;

import com.hyperfresh.mchyperchat.HyperChat;
import com.hyperfresh.mchyperchat.JsonTheme;
import com.hyperfresh.mchyperchat.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;

public class ThemeUtils
{
	public static void copyDefaultThemes(File jar, File folder) throws IOException
	{
		JarFile file = new JarFile(jar);
		folder.mkdirs();
		file.stream().forEach
		(
			entry ->
			{
				if(entry.getName().startsWith("themes/") && entry.getName().endsWith(".json"))
				{
					File outputFile = new File(folder, FilenameUtils.getName(entry.getName()));
					try
					{
						IOUtils.copy(file.getInputStream(entry), new FileOutputStream(outputFile));
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		);
	}

	public static Map<String, Theme> readThemes(HyperChat hyperChat, File folder) throws IOException, ParseException
	{
		Map<String, Theme> themes = new HashMap<>();

		if(folder.exists())
		{
			JSONParser parser = new JSONParser();
			for(File file: folder.listFiles())
			{
				String filename = file.getName();

				if(FilenameUtils.getExtension(filename).equals("json"))
				{
					String themeId = FilenameUtils.getBaseName(filename);

					Object obj = parser.parse(FileUtils.readFileToString(file));

					if(obj instanceof JSONObject)
					{
						final JSONObject json = (JSONObject)obj;

						String name = 		(String) json.get(JsonTheme.NAME_KEY);
						String header = 	(String) json.get(JsonTheme.HEADER_KEY);
						String message = 	(String) json.get(JsonTheme.MESSAGE_KEY);
						String footer = 	(String) json.get(JsonTheme.FOOTER_KEY);

						themes.put(themeId, new JsonTheme(hyperChat, name, header, message, footer));
					}
				}
			}
		}
		else
		{
			throw new FileNotFoundException("Cannot find the themes folder.");
		}

		return themes;
	}
}
