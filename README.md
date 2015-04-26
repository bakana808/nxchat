HyperChat (the alpha) [![Build Status](https://travis-ci.org/hyperfresh/mc-hyperchat.svg?style=flat-square)](https://travis-ci.org/hyperfresh/mc-hyperchat) [![Downloads](https://img.shields.io/github/downloads/atom/atom/latest/total.svg?style=flat-square)](https://github.com/hyperfresh/mc-hyperchat/releases/latest)
===
###### or nxChat 2.0 for the insiders

HyperChat is a chat plugin for Minecraft.
*Everything is still work-in-progress and could be changed.*

If you want to contribute to this project, feel free to fork and create pull requests.
Contact us in IRC by joining the `#hyperfresh` channel in `irc.esper.net`.

Fields
------
Fields are variables that are used to include information inside your theme.
Fields are used in a theme by typing `${<id>}`, where `<id>` is the field key. 
This will be replaced with the field's value in-game.

Creating Fields
------
HyperChat includes its own pre-defined fields and allows you to define your own.
All user-defined fields are contained in the `fields` folder.

There are two ways to create fields.

1) **create a JSON file in the `fields` folder.**

This JSON file is an array of "field arrays", which contain the following keys:
 - `keys` - an array of strings to use as the field keys
 - `value` - the value of the field.

For example, part of `fields/color.json` contains:
```json
[
	{
		"keys": ["c", "red"],
		"value": "\u00A7c"
	},
	{
		"keys": ["d", "pink"],
		"value": "\u00A7d"
	}
]
```

2) **define fields using the API**

In this case, there can define two types of fields: dynamic and static.
For optimization purposes, if a field is "static", then field keys in themes will be replaced once when it is loaded. If it's "dynamic", then it will be replaced when any message using the theme is printed.

```java
ChatFieldList fields = HyperChat.getFields();

//static field
fields.addField("c", "\u00A7c");

//dynamic field
fields.addField("name", new ChatField()
{
	public boolean isDynamic() { return true; }

	public String getValue(ChatSender sender, String... args)
	{
		return sender.getName();
	}
}
```

Themes
------
Themes change how player and (supported) system messages look.
Other than simply changing the player message format, you can also define other elements, such as a header and footer, allowing for different chat formats.

Creating Themes
------
All user-defined themes are located in the `themes` folder.

There are two ways to create themes.

1) **create a JSON file in the `themes` folder.**

This JSON file is a "theme array", which contains the following keys:
 - `name` - The name of the theme
 - `player-header` - (optional) This will be printed before a player's first chat message in a chain of chat messages.
 - `player-body` - This will be printed every time a player chats.
 - `player-footer` - (optional) This will be printed first if the next chat message is from a different player.

For example, this is an included theme, `themes/classic.json`
```json
{
	"name": "classic",
	"player-body": "<${name}> ${message}"
}
```

2) **define themes using the API**

Further documentation will be made later.
