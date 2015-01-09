package com.andrewreitz

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidSpockPlugin implements Plugin<Project> {
  void apply(Project project) {
    project.buildscript {
      repositories {
        jcenter()
      }

      dependencies {
        classpath 'me.champeau.gradle:gradle-groovy-android-plugin:0.3.4'
      }
    }

    project.apply {
      plugin: 'me.champeau.gradle.groovy-android'
    }

    project.android {
      defaultConfig {
        testInstrumentationRunner "com.andrewreitz.spock.AndroidSpockRunner"
      }

      packagingOptions {
        exclude 'META-INF/services/org.codehaus.groovy.transform.ASTTransformation'
        exclude 'LICENSE.txt'
        exclude 'META-INF/LICENSE.txt'
        exclude 'META-INF/groovy-release-info.properties'
      }
    }

    project.dependencies {
      androidTestCompile 'com.andrewreitz:spock-android:1.0.0-SNAPSHOT'
      androidTestCompile 'org.codehaus.groovy:groovy:2.4.0-rc-2:grooid'

      androidTestCompile 'junit:junit-dep:4.11'

      androidTestCompile('org.spockframework:spock-core:0.7-groovy-2.0') {
        exclude group: 'org.codehaus.groovy'
      }
    }
  }
}
