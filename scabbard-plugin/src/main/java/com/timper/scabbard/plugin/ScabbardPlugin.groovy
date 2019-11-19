package com.timper.scabbard.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

class ScabbardPlugin implements Plugin<Project> {
  @Override void apply(Project project) {
    def hasApp = project.plugins.withType(AppPlugin)
    def hasLib = project.plugins.withType(LibraryPlugin)
    if (!hasApp && !hasLib) {
      throw new IllegalStateException("'android' or 'android-library' plugin required.")
    }

    final def log = project.logger
    final def variants
    if (hasApp) {
      variants = project.android.applicationVariants
    } else {
      variants = project.android.libraryVariants
    }

    project.dependencies {
      debugCompile 'com.jakewharton.hugo:hugo-runtime:1.2.2-SNAPSHOT'
      debugCompile 'org.aspectj:aspectjrt:1.8.9'
      compile 'com.jakewharton.hugo:hugo-annotations:1.2.2-SNAPSHOT'
    }

    project.extensions.create('scabbard', ScabbardExtension)

    variants.all { variant ->
      if (!variant.buildType.isDebuggable()) {
        log.debug("Skipping non-debuggable build type '${variant.buildType.name}'.")
        return;
      } else if (!project.hugo.enabled) {
        log.debug("scabbard is not disabled.")
        return;
      }

      JavaCompile javaCompile = variant.javaCompile
      javaCompile.doLast {
        //下面的1.8是指我们兼容的jdk的版本
        String[] args = ["-showWeaveInfo",
                         "-1.8",
                         "-inpath", javaCompile.destinationDir.toString(),
                         "-aspectpath", javaCompile.classpath.asPath,
                         "-d", javaCompile.destinationDir.toString(),
                         "-classpath", javaCompile.classpath.asPath,
                         "-bootclasspath", android.bootClasspath.join(File.pathSeparator)]

        MessageHandler handler = new MessageHandler(true);
        new Main().run(args, handler)

        for (IMessage message : handler.getMessages(null, true)) {
          switch (message.getKind()) {
            case IMessage.ABORT:
            case IMessage.ERROR:
            case IMessage.FAIL:
              log.error message.message, message.thrown
              break;
            case IMessage.WARNING:
            case IMessage.INFO:
              log.info message.message, message.thrown
              break;
            case IMessage.DEBUG:
              log.debug message.message, message.thrown
              break;
          }
        }
      }
    }
  }
}
