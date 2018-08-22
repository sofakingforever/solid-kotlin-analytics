[ ![Bintray](https://api.bintray.com/packages/sofakingforever/analytics/kotlin-analytics/images/download.svg) ](https://bintray.com/sofakingforever/analytics/kotlin-analytics/_latestVersion)


# SOLID Android Client Kotlin Analytics Interface

![fancy-diagram](https://cdn-images-1.medium.com/max/2000/1*WS5jAiurPYSrY_RdGDK8pQ.png)

[Writing SOLID Analytics With Kotlin for Android](https://medium.com/@nadavfima/how-to-build-better-analytics-with-kotlin-60ab50ce25ac) - Medium Article

This library demonstrates how you could decouple analytics libraries from your business logic code, effectively allowing you to:
* Add & remove analytics services quickly
* Add & remove events quickly
* Change event names and parameters according to the required kit

## Quick Start Guide

#### UPDATE: Both repo and libraries were changed. Please update your implementation!
### Add To Gradle
Add library to your gradle module

```gradle
repositories {
    maven { url "http://dl.bintray.com/sofakingforever/analytics" }
}

dependencies {

    def version = '1.0.10'

    // add the basic analytics interface library
    compile "com.sofakingforever.analytics:analytics:version@aar"

    // then add the kits you need
    compile "com.sofakingforever.analytics:kit-answers:version@aar"
    compile "com.sofakingforever.analytics:kit-firebase:version@aar"
    compile "com.sofakingforever.analytics:kit-flurry:version@aar"
    compile "com.sofakingforever.analytics:kit-mixpanel:version@aar"

    // or implement your own kit/dispatcher

}
```

### Kotlin Implementation Example
Initiate analytics and send events

```kotlin
// init analytics
analytics = Analytics(this, AnswersDispatcherImpl(), FirebaseDispatcherImpl())

// send event
analytics.track(SimpleEvent())

// declare event - will be sent to both Answers and Firebase
class SimpleEvent : CustomEvent {
    override fun getEventName(kit: AnalyticsKit): String = "Simple Event"

}
```

### See more integration stuff in the [example code](https://github.com/sofakingforever/kotlin-analytics/tree/master/app/src/main/java/com/sofakingforever/example) attached

## Dispatchers Currently Available
* Fabric Answers
* Google Firebase
* Yahoo Flurry
* Logger (prints to logcat)
* Build your own Dispatcher :)

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
- [x] Add Documentation
- [ ] Who knows... :o

### Originally developed for [Wakey - Beautiful Alarm Clock](http://bit.ly/2Pmlwhg)
