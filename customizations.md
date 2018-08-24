---
layout: page
title: Customizations
permalink: /customizations
---

<br/>
When it comes to customization,**Avatar-View** is highly customizable and provides built in support for most of the UI related customization.

Below is the list for almost all the UI elements information.

## Available Customizations

* [Modes](#modes)
* [Dimentions](#dimensions)
* [Colors](#colors)
* [Borders](#borders)
* [Texts](#texts)
* [Gradients](#gradients)
* [Custom Gradients](#custom_gradients)
* [Images](#images)

For more information, refer the [**javadocs**]().

<div class="modes"></div>
<br/>
<br/>
## Modes

Mode lets the library know on which type you are going to work to avoid rendering other things.
This should be the first thing to set when working on avatars. Although default mode is **color** mode.

```xml
<!-- xml -->
app:mode="COLOR"
```

```java
//java
avatarView.setMode(AvatarView.Mode.COLOR);
```

**Avatar-View** supports following modes:
* Color Mode ( Default )
* Gradient Mode
* Image Mode

Modes are mutually execusive to each other. So, properties defined for other than the current mode will be ignored any will not affect the look of avatar. 


<div id="colors"></div>
<br/>
<br/>
### Color Mode

By default, **Avatar-View** is set to color mode. Default background color is **light gray**.