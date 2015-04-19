mc-HyperChat
======
or nxChat 2.0 if you were there for that
---

mc-HyperChat is a chat plugin for Minecraft leaded by Octopod.

Chat Fields
------
Fields are variables that you can define in your chat themes for user convenience.

In a chat theme, including `${<id>}`, where <id> is the field key will replace it with the value it is registered under.

mc-HyperChat includes its own fields and allows you to define your own.
One way to do this is by creating .json files in the "fields" folder.

For example, fields/color.json contains:
```json
[
	# ...
	# a field that will replace ${c} with "\u00A7c" in a chat theme
	{
		"keys": ["c", "red"] 	//the keys to define this field under
		"value": "\u00A7c" 	//the value of the field
	}
	# ...
]
```

Another way is to do it externally:
```java
ChatFieldList.addField("c", "\u00A7c");

ChatFieldList.addField("name", new ChatField()
{
	public boolean isDynamic() { return true; }

	public String getValue(ChatMessager sender, String... args)
	{
		return sender.getName();
	}
}
```

Chat Themes
------
Chat Themes change how your messages look.
Other than simply changing the player message format, you can also define a header and footer, allowing for different chat formats.

They are also defined in a .json file located in the "themes" folder.

For example, themes/classic.json contains:
```json
{
	"name": "classic",
	"player-body": "<${name}> ${message}"
}
```

themes/classic-twoline.json:
```json
{
	"name": "classic-twoline",
	"player-header": "<${name}>",
	"player-body": "    ${message}"
}
```

Further documentation will be made later.
