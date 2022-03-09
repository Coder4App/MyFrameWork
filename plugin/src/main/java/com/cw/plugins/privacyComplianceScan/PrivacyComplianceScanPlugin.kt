package com.cw.plugins.privacyComplianceScan

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2022/3/1 下午7:43
 *
 * Description: 隐私合规扫描plugin
 */
class PrivacyComplianceScanPlugin:Plugin<Project> {

    override fun apply(target: Project) {
        PrivacyComplianceScanUtil.printStep("apply")

        val appExtension = target.extensions.getByType(AppExtension::class.java)
        appExtension.registerTransform(PrivacyComplianceScanTransform())
    }
}