package com.github.scabbard.gradle

import com.android.build.gradle.internal.variant.BaseVariantData
import com.google.wireless.android.sdk.stats.GradleBuildVariant

object BaseVariantDataV30 {

    fun isForTesting(variantData: BaseVariantData) = variantData.type.isForTesting

    fun isAar(variantData: BaseVariantData) =
        variantData.type.analyticsVariantType == GradleBuildVariant.VariantType.LIBRARY

    fun isApk(variantData: BaseVariantData) = variantData.type.analyticsVariantType == GradleBuildVariant.VariantType.APPLICATION

    fun isBaseModule(variantData: BaseVariantData) = false

    fun isDynamicFeature(variantData: BaseVariantData) = false

    fun isHybrid(variantData: BaseVariantData) = false

    fun getAnalyticsVariantType(variantData: BaseVariantData) = variantData.type.analyticsVariantType

}
