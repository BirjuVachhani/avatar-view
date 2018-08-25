---
layout: page
title: Customizations
permalink: /customizations
---

<br/>
When it comes to customization,**Avatar-View** is highly customizable and provides built in support for most of the UI related customization.

Below is the list for almost all the UI elements information.
<br/>
<br/>
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

<!------------------------------------- Modes ------------------------------------->
<div class="modes"></div>
<br/>
<br/>

## Modes

Mode lets the library know on which type you are going to work to avoid rendering other things.
This should be the first thing to set when working on avatars. Although default mode is **color** mode.

```xml
<!-- xml -->
<com.birjuvachhani.avatarview.AvatarView
    android:id="@+id/avatarView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:mode="COLOR" />
```

```java
//java
avatarView.setMode(AvatarView.Mode.COLOR);
```

**Avatar-View** supports following modes:
* Color Mode ( Default )
* Gradient Mode
* Image Mode

Modes are mutually execusive to each other. So, properties defined for other than the current mode will be ignored and will not affect the look of the avatar. 

<!------------------------------------- Dimentions ------------------------------------->
<div id="dimensions"></div>
<br/>
<br/>

## Dimentions

All the dimentions like **radius, width, text size** are given in **dp** by default. **XML** supports all the kind of dimens where as java code supports **dp**. No need to pass calculated pixels, just give dimens in dp and library will calculate the rest.

#### Example
```java
avatarView.setCircleRadius(56);
avatarView.setBorderWidth(2);
```

> Note that **Circle radius** controls the height and width of the view. **Width** and **height** will always be ignored in case of the **circle radius**. So, it doesn't really matter what height and width are given to the avatar, it will always aquire the **space** it needs to draw the circle of given **radius**.

#### Example
```xml
<!-- layout_width and layout_height will be ignored -->
<com.birjuvachhani.avatarview.AvatarView
    android:id="@+id/avatarView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:circleRadius="56dp"
    app:mode="COLOR" />
```

<!------------------------------------- Colors ------------------------------------->
<div id="colors"></div>
<br/>
<br/>

## Colors

By default, **Avatar-View** is set to color mode and default background color is **light gray**.
Any type of color resource can be given to any color attributes. 

To use colors for avatars, set mode to **COLOR** and set any background color as shown below.

#### Example
```xml
<com.birjuvachhani.avatarview.AvatarView
    android:id="@+id/avatarView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:circleRadius="56dp"
    app:backgroundColor="#FF0000"
    app:mode="COLOR" />
```

**Avatar View** support all the **material primary colors** by **default**. Colors can be used as shown in the following example.
Use **material_** prefix followed by color name for using colors in xml and use **MaterialColors** class for using colors in java. 

For available colors information, have a look at [Available Colors]({{ site.data.links.material_colors }}).

#### Example
##### XML
```xml
<com.birjuvachhani.avatarview.AvatarView
    android:id="@+id/avatarView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:backgroundColor="@color/material_blue"
    app:mode="COLOR" />
```

```xml
<com.birjuvachhani.avatarview.AvatarView
    android:id="@+id/avatarView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:backgroundColor="@color/material_amber"
    app:mode="COLOR" />
```

##### Java
```java
avatarView.setBackgroundColor(MaterialColors.BLUE);
```
```java
avatarView.setBackgroundColor(MaterialColors.DEEP_ORANGE);
```

<div id="borders"></div>
<br/>
<br/>

## Borders

