[ ![Bintray](https://api.bintray.com/packages/sofakingforever/analytics/kotlin-analytics/images/download.svg) ](https://bintray.com/sofakingforever/analytics/kotlin-analytics/_latestVersion)


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

```gradle
repositories {
    maven { url "http://dl.bintray.com/sofakingforever/analytics" }
}

dependencies {

    def version = '1.0.19'


    // This is the generic java/kotlin interface
    compile "com.sofakingforever.analytics:analytics:version"

    // This is an android-dependant extension interface
    compile "com.sofakingforever.analytics:android:version@aar"
    
    // then add the kits you need, or implement your own kit/dispatcher
    compile "com.sofakingforever.analytics:kit-answers:version@aar"
    compile "com.sofakingforever.analytics:kit-firebase:version@aar"
    compile "com.sofakingforever.analytics:kit-flurry:version@aar"
    compile "com.sofakingforever.analytics:kit-mixpanel:version@aar"
    
    
    // Don't forget to add kotlin dependency, if you're in a pure-java project
    implementation 'org.jetbrains.kotlin:kotlin-stdlib:1.2.71


}
```

### Kotlin Implementation Example
Initiate analytics and send events

```kotlin
// init analytics
analytics = Analytics(settings,
                CustomDispatcher(init = true),
                LoggerDispatcherImpl(init = true, context = this),
                FirebaseDispatcherImpl(init = true, context = this),
                MixPanelDispatcherImpl(init = true, projectToken = "TOKEN", context = this),
                AnswersDispatcherImpl(init = true, context = this)


//              if you're using crashlytics, or any other fabric kit in addition to Answers
//              AnswersDispatcherImpl(init = true, Answers(), Crashlytics())
        )

// send event
analytics.track(SimpleEvent())
```

Don't forget to declare the event
```kotlin
class SimpleEvent : CustomEvent {
    // declare name - will be sent to all dispatchers
    override fun getEventName(kit: AnalyticsKit): String = "Simple Event"

}
```
### Android Highlights

If you're targeting Android, you need the android package, and you should generally extend
`AndroidAnalyticsDispatcher` and `AndroidAnalyticsSettings` instead of the kotlin ones.

#### See more integration examples in the [source code](https://github.com/sofakingforever/kotlin-analytics/tree/master/app/src/main/java/com/sofakingforever/example)

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
- [x] Add Unit Tests
- [ ] Who knows... :o


### Originally developed for [Wakey - Beautiful Alarm Clock](https://wakey.app/?ref=github)

Wakey is a simple & beautiful animated alarm clock, featuring a spectacular design and an immersive experience - guaranteed to wake you up with a smile everyday!

With our smiling sunrise, and grumpy lunar animations, this is the most unique alarm clock in our solar system.

![Wakey Alarm Clock](https://cdn-images-1.medium.com/max/2000/1*DhcklS1xNZwHogX0wDQEyw.png)

License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
