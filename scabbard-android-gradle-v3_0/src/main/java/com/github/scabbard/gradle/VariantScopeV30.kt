package com.github.scabbard.gradle

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.scope.TaskOutputHolder
import com.android.build.gradle.internal.scope.VariantScope
import com.android.build.gradle.tasks.MergeResources
import com.android.ide.common.res2.ResourceSet
import com.android.sdklib.BuildToolInfo
import java.io.File
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream


/**
 * User: tangpeng.yang
 * Date: 2020/5/9
 * Description:
 * FIXME
 */
object VariantScopeV30 {

    fun getExtension(scope: VariantScope): BaseExtension =
        scope.globalScope.extension as BaseExtension

    fun getMergedManifests(scope: VariantScope): Collection<File> =
        scope.getOutput(TaskOutputHolder.TaskOutputType.MERGED_MANIFESTS).files

    fun getMergedRes(scope: VariantScope): Collection<File> =
        scope.getOutput(TaskOutputHolder.TaskOutputType.MERGED_RES).files

    fun getMergedAssets(scope: VariantScope): Collection<File> =
        scope.getOutput(TaskOutputHolder.TaskOutputType.MERGED_ASSETS).files

    fun getProcessedRes(scope: VariantScope): Collection<File> =
        scope.getOutput(TaskOutputHolder.TaskOutputType.PROCESSED_RES).files

    fun getAllClasses(scope: VariantScope): Collection<File> =
        scope.getOutput(TaskOutputHolder.AnchorOutputType.ALL_CLASSES).files

    fun getSymbolList(scope: VariantScope): Collection<File> =
        getOutput(scope, TaskOutputHolder.TaskOutputType.SYMBOL_LIST)

    fun getSymbolListWithPackageName(scope: VariantScope): Collection<File> =
        getOutput(scope, TaskOutputHolder.TaskOutputType.SYMBOL_LIST_WITH_PACKAGE_NAME)

    fun getAar(scope: VariantScope): Collection<File> =
        getOutput(scope, TaskOutputHolder.TaskOutputType.AAR)

    fun getApk(scope: VariantScope): Collection<File> =
        getOutput(scope, TaskOutputHolder.TaskOutputType.APK)

    fun getJavac(scope: VariantScope): Collection<File> =
        getOutput(scope, TaskOutputHolder.TaskOutputType.JAVAC)

    fun getAllArtifacts(scope: VariantScope): Map<String, Collection<File>> {
        val types: Array<TaskOutputHolder.OutputType> = emptyArray()
        return types.plus(TaskOutputHolder.TaskOutputType.values())
            .plus(TaskOutputHolder.AnchorOutputType.values())
            .associateBy({ it.name() }, { getOutput(scope, it) })
    }

    fun getOutput(scope: VariantScope, outputType: TaskOutputHolder.OutputType): Collection<File> =
        try {
            scope.getOutput(outputType).files
        } catch (e: RuntimeException) {
            Collections.emptySet()
        }

    fun getBuildTools(scope: VariantScope): BuildToolInfo =
        scope.globalScope.androidBuilder.buildToolInfo

    fun getRawAndroidResources(scope: VariantScope): Collection<File> = try {
        val computeResourceSetList =
            MergeResources::class.java.getDeclaredMethod("computeResourceSetList")
        computeResourceSetList.isAccessible = true
        val resources =
            computeResourceSetList.invoke(scope.variantData.mergeResourcesTask) as List<ResourceSet>
        resources.stream()
            .map { it.sourceFiles }
            .flatMap { obj: List<File> -> obj.stream() }
            .collect(Collectors.toSet())
    } catch (e: Throwable) {
        emptySet()
    }

    fun getDataBindingDependencyArtifacts(scope: VariantScope): File =
        File(scope.buildFolderForDataBindingCompiler, "dependent-lib-artifacts")
}
