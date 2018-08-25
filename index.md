---
layout: page
---

<div id="introduction"></div>
<br/>

# Introducing Avatar View
Avatar-View is an android library



<div id="download"></div>
<br/>
<br/>
## Download
Current Version: {{ site.version }}
<br/>
<div class="btn-download">
    <span><a href="{{ site.github_url }}/archive/{{ site.version }}.zip">Source code (zip)</a></span>
    <span><a href="{{ site.github_url }}/archive/{{ site.version }}.tar.gz">Source code (tar.gz)</a></span>

</div>
<br/>
### Gradle Dependency

* Add the JitPack repository to your project's build.gradle file

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

* Add the dependency in your app's build.gradle file

```gradle
dependencies {
    implementation 'com.github.BirjuVachhani:avatar-view:1.0.0'
}
```

<div id="usage"></div>
<br/>
<br/>
## Usage

#### XML

```xml
<com.birjuvachhani.avatarview.AvatarView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Birju Vachhani"
        android:textSize="24sp"
        app:backgroundColor="@color/material_blue"
        app:border_color="@android:color/white"
        app:border_width="2dp"
        app:circleRadius="56dp"
        app:mode="COLOR" />
```

#### Java
```java
//radius, width and size are in dp
AvatarView avatarView = findViewById(R.id.avImage);
avatarView.setMode(AvatarView.Mode.COLOR);
avatarView.setCircleRadius(56);
avatarView.setBackgroundColor(MaterialColors.BLUE);
avatarView.setBorderColor(Color.WHITE);
avatarView.setBorderWidth(2);
avatarView.setTextColor(Color.WHITE);
avatarView.setTextSize(24);
avatarView.setInitials("Birju Vachhani");
```

#### Using Style Builder
```java
//radius, width and size are in dp
AvatarView avatarView = findViewById(R.id.avImage);
AvatarStyle avatarStyle = new AvatarStyle.Builder(this)
        .setMode(AvatarView.Mode.COLOR)
        .setCircleRadius(56)
        .setBackgroundColor(MaterialColors.AMBER)
        .setBorderWidth(2)
        .setBorderColor(Color.WHITE)
        .setTextColor(Color.WHITE)
        .setTextSize(24)
        .setInitials("Birju Vachhani")
        .build();
avatarView.setAvatarStyle(avatarStyle);
```

<div id="licence"></div>
<br/>
<br/>
## Licence

&copy; 2018 [Birju Vachhani](https://github.com/BirjuVachhani)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
   ```html
   http://www.apache.org/licenses/LICENSE-2.0
   ```
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

