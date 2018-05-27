# Kotlin Patterns #

This library contains an implementation of some design patterns.

Patterns:

[Specification](#specification)  
[Visitor](/src/main/kotlin/kpatt/Visitor.kt)


## Dependency ##

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    compile 'com.github.ivan-osipov:kpatt:-SNAPSHOT'
}
```


## How to use ##

[Specification](/src/main/kotlin/kpatt/Specification.kt)
--------

```kotlin
val data = "string"
val satisfied = (StringContains("ring") and StringHasLength(4)) or
                (!StringContains("ping") and StringHasEnd("ng")) isSatisfiedBy data
assertTrue(satisfied)
```
