# SOLID Android Client Kotlin Analytics Interface

![fancy-diagram](https://cdn-images-1.medium.com/max/2000/1*WS5jAiurPYSrY_RdGDK8pQ.png)

[Writing SOLID Analytics With Kotlin for Android](https://medium.com/@nadavfima/how-to-build-better-analytics-with-kotlin-60ab50ce25ac) - Medium Article

This library demonstrates how you could decouple analytics libraries from your business logic code, effectively allowing you to:
* Add & remove analytics services quickly
* Add & remove events quickly
* Change event names and parameters according to the required kit

## Quick Start Guide

### Add To Gradle
Add library to your gradle module

#### UPDATE: Both reporsitory and libraries were changed. Please advise the example below

```gradle
repositories {
    maven { url "http://dl.bintray.com/sofakingforever/libraries" }
}

dependencies {

    def analyticsVersion = '1.0.7'

    // add the basic analytics interfface library
    compile "com.sofakingforever.analytics:analytics:$analyticsVersion@aar"

    // then add the kits you need
    compile "com.sofakingforever.analytics:kit-flurry:$analyticsVersion@aar"
    compile "com.sofakingforever.analytics:kit-answers:$analyticsVersion@aar"
    compile "com.sofakingforever.analytics:kit-firebase:$analyticsVersion@aar"

    // or implement your own kit/dispatcher

}
```

### Kotlin Implementation Example
Initiate analytics and send events

```kotlin
// init analytics
analytics = Analytics(this, AnswersDispatcherImpl(init = true), FirebaseDispatcherImpl(init = true))

// send event
analytics.track(SimpleEvent())

// declare event - will be sent to both Answers and Firebase
class SimpleEvent : AnalyticsEvent {
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
- [ ] Add Documentation
- [ ] Additional Common Dispatchers?
- [ ] Anything else important..?

### Originally developed for [Wakey - Beautiful Alarm Clock](http://bit.ly/2Pmlwhg)
