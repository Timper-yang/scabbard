package com.github.scabbard.gradle

import com.android.build.gradle.internal.variant.BaseVariantData
import com.google.wireless.android.sdk.stats.GradleBuildVariant

/**
 * User: tangpeng.yang
 * Date: 2020/5/14
 * Description:
 * FIXME
 */
object BaseVariantDataV32 {

    fun isForTesting(variantData: BaseVariantData) = variantData.type.isForTesting

    fun isAar(variantData: BaseVariantData) = variantData.type.isAar

    fun isApk(variantData: BaseVariantData) = variantData.type.isApk

    fun isBaseModule(variantData: BaseVariantData) = variantData.type.isBaseModule

    fun isDynamicFeature(variantData: BaseVariantData) = variantData.type.isDynamicFeature

    fun isHybrid(variantData: BaseVariantData) = variantData.type.isHybrid

    fun getAnalyticsVariantType(variantData: BaseVariantData): GradleBuildVariant.VariantType =
        variantData.type.analyticsVariantType
}