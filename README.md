# ModrinthJavaAPI

![GitHub Top Language](https://img.shields.io/github/languages/top/minemobs/ModrinthJavaLib)
![Github License](https://img.shields.io/github/license/minemobs/ModrinthJavaLib)
[![](https://jitpack.io/v/Minemobs/ModrinthJavaAPI.svg)](https://jitpack.io/#Minemobs/ModrinthJavaLib)

[Their API documentation](https://github.com/modrinth/labrinth/wiki/API-Documentation)

[My Documentation](https://minemobs.github.io/ModrinthJavaLib/) (literally the same as the official documentation)

Thanks to [Emrio](https://github.com/TheEmrio) for helping me to make post request

* ### How do I add the API to my build.gradle ?

You just need to add jitpack as a repository

Like this:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

Add this in your dependencies :

```groovy
implementation 'com.github.Minemobs:ModrinthJavaAPI:main-SNAPSHOT'
```