# Spock for Android

[![Build Status](https://snap-ci.com/pieces029/android-spock/branch/master/build_image)](https://snap-ci.com/pieces029/android-spock/branch/master)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Spock%20for%20Android-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1345)

This library allows for the [Spock Framework](//spockframework.org) to be run on Android.

## Usage

### Setup Groovy For Android

```groovy
buildscript {
  repositories {
    jcenter()
  }

  dependencies {
    classpath 'com.android.tools.build:gradle:1.0.0'
    classpath 'org.codehaus.groovy:gradle-groovy-android-plugin:0.3.5'
  }
}

apply plugin: 'com.android.application'
apply plugin: 'groovyx.grooid.groovy-android'
```

See [groovy-android-gradle-plugin](//github.com/groovy/groovy-android-gradle-plugin) for more
details.

### Setup Dependencies

```groovy
dependencies {
  ...
  androidTestCompile 'org.codehaus.groovy:groovy:2.4.0:grooid'
  androidTestCompile "com.andrewreitz:spock-android:${androidSpockVersion}"
  androidTestCompile 'junit:junit-dep:4.11'
  androidTestCompile('org.spockframework:spock-core:0.7-groovy-2.0') {
    exclude group: 'org.codehaus.groovy'
    exclude group: 'junit'
  }
  androidTestCompile('com.android.support.test:testing-support-lib:0.1') {
    exclude group: 'junit'
  }

  // Optional, needed for mocking
  androidTestCompile "com.google.dexmaker:dexmaker:1.2"
  ...
}
```

### Setup Android Plugin

```groovy
android {
  ...

  defaultConfig {
    ...
    testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
  }

  packagingOptions {
    exclude 'META-INF/services/org.codehaus.groovy.transform.ASTTransformation'
    exclude 'LICENSE.txt'
  }
}
```

### Write your tests

Tests must be placed in the `./src/androidTest/groovy` directory.

Write your tests like you would regular spock tests. See the spock-android-sample project and
[Spock Framework](//spockframework.org) for more details.

### Mocking

Objenesis and cglib do not work with Android. But that's okay. Using dexmaker we can still create
mock objects in spock fashion. The only difference is instead of your test classes inheriting from
`Specification`, you just need to inherit from `AndroidSpecification`.

Note: You can not use mocked automatic getters and setters. Example `mocked.getString()` will work
where as `mocked.string` will not. This is due to limitations of Android not containing certain core
java classes.

### Annotations

`UseActivity` is an field annotation to get access to your Activity during tests. You can even
provide bundle arguments by supplying a BundleCreator.

Ex.
```groovy
@UseActivity(MyActivity) def myActivity
```

`UseApplication` is a field annotation that supplies your Application.

Ex.
```groovy
@UseApplication(MyApplication) def myApplication
```

`WithContext` is a field annotation that supplies you with a context. This is not an implementation of
your application.

Ex.
```groovy
@WithContext def context
```

All field annotations will be set during the setup fixture.

### Notes

If you get `INSTALL_FAILED_DEXOPT`, there is something wrong with your spock tests. This is
unfortunately very hard to debug as there is little to no log output. The easiest way to avoid this
is write one test at a time. I currently have has this issue mainly with RxJava Actions.

## License

    Copyright 2015 Andrew Reitz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
