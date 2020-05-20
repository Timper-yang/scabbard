package com.github.scabbard.gradle

import com.android.SdkConstants
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.publishing.AndroidArtifacts
import com.android.build.gradle.internal.scope.VariantScope
import com.android.build.gradle.internal.variant.BaseVariantData
import com.android.build.gradle.tasks.ProcessAndroidResources
import com.github.scabbard.kotlinx.search
import org.gradle.api.Project
import org.gradle.api.Task
import java.io.File

/**
 * User: tangpeng.yang
 * Date: 2020/5/14
 * Description:
 * FIXME
 */
val BaseVariant.variantData: BaseVariantData
    get() = javaClass.getDeclaredMethod("getVariantData").invoke(this) as BaseVariantData

val BaseVariant.scope: VariantScope
    get() = variantData.scope

val BaseVariant.extension: BaseExtension
    get() = scope.extension

val BaseVariant.project: Project
    get() = scope.globalScope.project

val BaseVariant.platform: File
    get() = extension.run {
        sdkDirectory.resolve("platforms").resolve(compileSdkVersion)
    }

val BaseVariant.dependencies: ResolvedArtifactResults
    get() = ResolvedArtifactResults(this)


@Suppress("DEPRECATION")
val BaseVariant.javaCompilerTask: Task
    get() = if (GTE_V3_3) {
        this.javaCompileProvider.get()
    } else {
        this.javaCompiler
    }

@Suppress("DEPRECATION")
val BaseVariant.preBuildTask: Task
    get() = if (GTE_V3_3) {
        this.preBuildProvider.get()
    } else {
        this.preBuild
    }

@Suppress("DEPRECATION")
val BaseVariant.mergeAssetsTask: Task
    get() = if (GTE_V3_3) {
        this.mergeAssetsProvider.get()
    } else {
        this.mergeAssets
    }

@Suppress("DEPRECATION")
val BaseVariant.assembleTask: Task
    get() = if (GTE_V3_3) {
        this.assembleProvider.get()
    } else {
        this.assemble
    }

@Suppress("DEPRECATION")
val BaseVariant.mergeResourcesTask: Task
    get() = if (GTE_V3_3) {
        this.mergeResourcesProvider.get()
    } else {
        this.mergeResources
    }

val BaseVariant.processResTask: ProcessAndroidResources
    get() = project.tasks.withType(ProcessAndroidResources::class.java).findByName("process${name.capitalize()}Resources")!!