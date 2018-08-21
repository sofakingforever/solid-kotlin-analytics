# Kotlin Analytics - sofakingforever


## Quick Start Guide

### Gradle
Add library to your gradle module

```gradle

repositories {

    maven { url "http://dl.bintray.com/sofakingforever/libraries" }

}

dependencies {

    compile 'com.sofakingforever.libraries:analytics:1.0.0@aar'

}
```

### Kotlin Implementation
Initiate analytics and send events

```kotlin

// init analytics
analytics = Analytics(this, AnswersDispatcherImpl(true), FirebaseDispatcherImpl(true))

// send event
analytics.track(SimpleEvent())

// declare event - will be sent to both Answers and Firebase
class SimpleEvent : AnalyticsEvent {
    override fun getEventName(kit: AnalyticsKit): String = "Simple Event"

}

```

See more integration stuff in the [example code](https://github.com/sofakingforever/kotlin-analytics/tree/master/app/src/main/java/com/sofakingforever/example) attached


## Todo List

- [x] Setup repository
- [x] Add Code
- [x] Upload First Build
- [x] Add Gradle Example
- [x] Add Fabric Answers Default Implementation
- [x] Add Firebase Default Implementation
- [x] Add Flurry Default Implementation
- [x] Add Example Code to App Module
- [x] Add Example Code to README
- [ ] Add Documentation
- [ ] Additional Common Dispatchers?
- [ ] Anything else important..?
