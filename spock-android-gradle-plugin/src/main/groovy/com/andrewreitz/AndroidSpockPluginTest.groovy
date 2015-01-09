package com.andrewreitz

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidSpockPluginTest implements Plugin<Project> {
    void apply(Project project) {
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
    }
}
