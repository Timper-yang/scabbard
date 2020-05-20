package com.github.scabbard.gradle

import com.android.build.api.artifact.ArtifactType
import com.android.build.api.artifact.BuildArtifactType
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.api.artifact.SourceArtifactType
import com.android.build.gradle.internal.scope.AnchorOutputType
import com.android.build.gradle.internal.scope.InternalArtifactType
import com.android.build.gradle.internal.scope.VariantScope
import com.android.sdklib.BuildToolInfo
import java.io.File

/**
 * User: tangpeng.yang
 * Date: 2020/5/14
 * Description:
 * FIXME
 */
object VariantScopeV32 {

    fun getExtension(scope: VariantScope): BaseExtension =
        scope.globalScope.extension as BaseExtension

    fun getMergedManifests(scope: VariantScope): Collection<File> = getFinalArtifactFiles(
        scope,
        InternalArtifactType.MERGED_MANIFESTS
    )

    fun getMergedRes(scope: VariantScope): Collection<File> =
        getFinalArtifactFiles(scope, InternalArtifactType.MERGED_RES)

    fun getMergedAssets(scope: VariantScope): Collection<File> =
        getFinalArtifactFiles(scope, InternalArtifactType.MERGED_ASSETS)

    fun getProcessedRes(scope: VariantScope): Collection<File> =
        getFinalArtifactFiles(scope, InternalArtifactType.PROCESSED_RES)

    fun getAllClasses(scope: VariantScope): Collection<File> =
        getFinalArtifactFiles(scope, AnchorOutputType.ALL_CLASSES)

    fun getSymbolList(scope: VariantScope): Collection<File> =
        getFinalArtifactFiles(scope, InternalArtifactType.SYMBOL_LIST)

    fun getSymbolListWithPackageName(scope: VariantScope): Collection<File> =
        getFinalArtifactFiles(scope, InternalArtifactType.SYMBOL_LIST_WITH_PACKAGE_NAME)

    fun getAar(scope: VariantScope): Collection<File> =
        getFinalArtifactFiles(scope, InternalArtifactType.AAR)

    fun getApk(scope: VariantScope): Collection<File> =
        getFinalArtifactFiles(scope, InternalArtifactType.APK)

    fun getJavac(scope: VariantScope): Collection<File> =
        getFinalArtifactFiles(scope, InternalArtifactType.JAVAC)

    fun getAllArtifacts(scope: VariantScope): Map<String, Collection<File>> {
        val types: Array<ArtifactType> = emptyArray()
        types.plus(AnchorOutputType.values())
        types.plus(BuildArtifactType.values())
        types.plus(SourceArtifactType.values())
        types.plus(InternalArtifactType.values())
        return types.associateBy({ it.name() }, { getFinalArtifactFiles(scope, it) })
    }

    fun getFinalArtifactFiles(scope: VariantScope, type: ArtifactType): Collection<File> =
        scope.artifacts.getFinalArtifactFiles(type).files

    fun getBuildTools(scope: VariantScope): BuildToolInfo =
        scope.globalScope.androidBuilder.buildToolInfo

    fun getDataBindingDependencyArtifacts(scope: VariantScope): File =
        scope.artifacts.getFinalArtifactFiles(InternalArtifactType.DATA_BINDING_DEPENDENCY_ARTIFACTS).get().singleFile
}