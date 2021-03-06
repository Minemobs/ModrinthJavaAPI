# ModrinthJavaAPI

<img alt="GitHub Top Language" src="https://img.shields.io/github/languages/top/minemobs/ModrinthJavaAPI"/> <img alt="Github License" src="https://img.shields.io/github/license/minemobs/ModrinthJavaAPI"/>

[Their API documentation](https://github.com/modrinth/labrinth/wiki/API-Documentation)

[My Documentation](https://minemobs.github.io/ModrinthJavaAPI/) (literally the same as the official documentation)

Thanks to [Emrio](https://github.com/TheEmrio) for helping me to make post request

* ### How do I add the API to my build.gradle ?

You just need to add jitpack as a repository

Like this:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

And add in your dependencies this :

```groovy
implementation 'com.github.Minemobs:ModrinthJavaAPI:main-SNAPSHOT'
```