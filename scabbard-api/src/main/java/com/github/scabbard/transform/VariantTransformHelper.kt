package com.github.scabbard.transform

import com.android.SdkConstants
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.publishing.AndroidArtifacts
import com.github.scabbard.gradle.*
import com.github.scabbard.kotlinx.search
import com.github.scabbard.transform.util.TransformHelper
import java.io.File

/**
 * User: tangpeng.yang
 * Date: 2020/5/18
 * Description:
 * FIXME
 */
class VariantTransformHelper(variant: BaseVariant, input: File) :
    TransformHelper(input, variant.platform, variant.artifacts, variant.applicationId, variant.name)

val BaseVariant.artifacts: ArtifactManager
    get() = object : ArtifactManager {

        override fun get(type: String): Collection<File> = when (type) {
            ArtifactManager.AAR -> scope.getArtifactCollection(
                AndroidArtifacts.ConsumedConfigType.RUNTIME_CLASSPATH,
                AndroidArtifacts.ArtifactScope.ALL,
                AndroidArtifacts.ArtifactType.AAR
            ).artifactFiles.files
            ArtifactManager.ALL_CLASSES -> scope.allClasses
            ArtifactManager.APK -> scope.apk
            ArtifactManager.JAR -> scope.getArtifactCollection(
                AndroidArtifacts.ConsumedConfigType.RUNTIME_CLASSPATH,
                AndroidArtifacts.ArtifactScope.ALL,
                AndroidArtifacts.ArtifactType.JAR
            ).artifactFiles.files
            ArtifactManager.JAVAC -> scope.javac
            ArtifactManager.MERGED_ASSETS -> scope.mergedAssets
            ArtifactManager.MERGED_RES -> scope.mergedRes
            ArtifactManager.MERGED_MANIFESTS -> scope.mergedManifests.search { SdkConstants.FN_ANDROID_MANIFEST_XML == it.name }
            ArtifactManager.PROCESSED_RES -> scope.processedRes.search {
                it.name.startsWith(
                    SdkConstants.FN_RES_BASE
                ) && it.name.endsWith(SdkConstants.EXT_RES)
            }
            ArtifactManager.SYMBOL_LIST -> scope.symbolList
            ArtifactManager.SYMBOL_LIST_WITH_PACKAGE_NAME -> scope.symbolListWithPackageName
            ArtifactManager.DATA_BINDING_DEPENDENCY_ARTIFACTS -> scope.dataBindingDependencyArtifacts.listFiles()?.toList()
                ?: kotlin.collections.emptyList()
            else -> kotlin.TODO("Unexpected type: $type")
        }

    }
