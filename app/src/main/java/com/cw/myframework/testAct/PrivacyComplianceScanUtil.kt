package com.cw.myframework.testAct

import android.util.Log

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2022/3/2 下午2:34
 *
 * Description:打印日志
 */
object PrivacyComplianceScanUtil {
    fun printPermissionNameLog(permissionName: String) {
        Log.e("Privacy", "使用到权限${permissionName}")
    }


    fun printVisitorLog(className: String?) {
        println("PrivacyComplianceScanUtil className $className")
    }

    fun printVisitorMethod(method: String?) {
        println("PrivacyComplianceScanUtil      method $method")
    }

    fun printVisitorInsn(insn: String?) {
        println("PrivacyComplianceScanUtil             Insn $insn")
    }

    fun printVisitorOwner(owner: String?) {
        println("PrivacyComplianceScanUtil             Owner $owner")
    }


    fun printVisitorDescriptor(descriptor: String?) {
        println("PrivacyComplianceScanUtil             Descriptor $descriptor")
    }

    fun printStep(info: String?) {
        println("PrivacyComplianceScanUtil  $info")
    }
}