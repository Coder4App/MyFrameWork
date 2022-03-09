package com.cw.plugins.privacyComplianceScan

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2022/3/2 下午3:07
 *
 * Description: ASM的Class(类) Visitor扫描代码 符合要求的插入Log日志
 */
class ReportPrivacyComplianceClassVisitor(classVisitor: ClassVisitor) :
    ClassVisitor(Opcodes.ASM6, classVisitor) {

    private var className: String? = null

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        className = name
        PrivacyComplianceScanUtil.printVisitorLog(className)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        PrivacyComplianceScanUtil.printVisitorMethod(name)
        val  methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions)

        return ReportPrivacyComplianceMethodVisitor(methodVisitor,className!!)
    }
}