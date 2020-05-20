package com.github.scabbard.gradle

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.github.scabbard.annotations.Priority
import com.github.scabbard.task.spi.VariantProcessor
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

/**
 * User: tangpeng.yang
 * Date: 2020/5/15
 * Description:
 * FIXME
 */
class ScabbardPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.gradle.addListener(TransformTaskExecutionListener(project))

        when {
            project.plugins.hasPlugin("com.android.application") || project.plugins.hasPlugin("com.android.dynamic-feature") -> project.getAndroid<AppExtension>().let { android ->
                android.registerTransform(ScabbardTransform(project))
                project.afterEvaluate {
                    this.variantProcessors.let { processors ->
                        android.applicationVariants.forEach { variant ->
                            processors.forEach { processor ->
                                processor.process(variant)
                            }
                        }
                    }
                }
            }
            project.plugins.hasPlugin("com.android.library") -> project.getAndroid<LibraryExtension>().let { android ->
                android.registerTransform(ScabbardTransform(project))
                project.afterEvaluate {
                    this.variantProcessors.let { processors ->
                        android.libraryVariants.forEach { variant ->
                            processors.forEach { processor ->
                                processor.process(variant)
                            }
                        }
                    }
                }
            }
        }
    }

    private val variantProcessors: Collection<VariantProcessor>
        get() = ServiceLoader.load(VariantProcessor::class.java, javaClass.classLoader).sortedBy {
            it.javaClass.getAnnotation(Priority::class.java)?.value ?: 0
        }

}