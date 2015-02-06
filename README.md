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
    classpath 'me.champeau.gradle:gradle-groovy-android-plugin:0.3.4'
  }
}

apply plugin: 'com.android.application'
apply plugin: 'me.champeau.gradle.groovy-android'
```

See [groovy-android-gradle-plugin](//github.com/melix/groovy-android-gradle-plugin/) for more
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

  // For mocking (Android does not support mocks used by Spock)
  androidTestCompile "org.mockito:mockito-core:1.10.19"
  androidTestCompile "com.google.dexmaker:dexmaker-mockito:1.2"
  ...
}
```

### Setup Android Plugin

```groovy
android {
  ...

  defaultConfig {
    ...
    testInstrumentationRunner "com.andrewreitz.spock.AndroidSpockRunner"
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

