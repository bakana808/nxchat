![nxChat2](http://puu.sh/jk9RD/2bdce09bf5.png)

[ ![Build Status][build-badge] ][build] [ ![Downloads][dl-badge] ][dl] [ ![Join IRC][irc-badge] ][irc]
===
nxChat is a chat plugin for Minecraft.
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
nxChat includes its own pre-defined fields and allows you to define your own.
All user-defined fields are contained in the `fields` folder.

There are two ways to create fields.

1) **Create a JSON file in the `fields` folder.**

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

2) **Define fields using the API**

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

To add your own themes to nxChat, create a .yml file inside the `themes` folder.

The YAML should contain these specific keys:
 - __`name`__ - The name of the theme
 - __`author`__ - The author of the theme
 - `header` - This will be printed before a player's first chat message in a chain of chat messages.
 - __`body`__ - This will be printed every time a player chats.
 - `footer` - This will be printed first if the next chat message is from a different player.

For example:
```yaml
name: Example Theme
author: octopod
header: '[${name}]'
body: '    >${message}'
footer: '----------------'
```
[build-badge]: https://img.shields.io/travis/hyperfresh/mc-hyperchat.svg?style=flat-square

[build]: https://travis-ci.org/hyperfresh/mc-hyperchat

[dl-badge]: https://img.shields.io/github/downloads/hyperfresh/mc-hyperchat/latest/total.svg?style=flat-square

[dl]: https://github.com/hyperfresh/mc-hyperchat/releases/latest

[irc-badge]: https://img.shields.io/badge/irc-join%20chat-brightgreen.svg?style=flat-square

[irc]: https://webchat.esper.net/?channels=hyperfresh
