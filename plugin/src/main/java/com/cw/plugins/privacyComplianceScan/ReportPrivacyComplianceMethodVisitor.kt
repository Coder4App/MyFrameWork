package com.cw.plugins.privacyComplianceScan

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

import org.objectweb.asm.Label
import org.objectweb.asm.Opcodes.*


/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2022/3/3 上午9:38
 *
 * Description: 处理具体方法里的字节码内容
 */
class ReportPrivacyComplianceMethodVisitor(
    methodVisitor: MethodVisitor,
    private val className: String
) :
    MethodVisitor(Opcodes.ASM6, methodVisitor) {


    override fun visitCode() {
        super.visitCode()
    }

    override fun visitMethodInsn(
        opcode: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean
    ) {
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
        PrivacyComplianceScanUtil.printVisitorInsn(name)
        PrivacyComplianceScanUtil.printVisitorOwner(owner)
        PrivacyComplianceScanUtil.printVisitorDescriptor(descriptor)
//        if (owner == "android/content/pm/PackageManager"){
//            PrivacyComplianceScanUtil.printStep("有用到 $className / $name")
//        }

        if (needInsert(owner, name)) {
            PrivacyComplianceScanUtil.printStep("插入 $className / $name")
//            val label2 = Label()
//            mv.visitLabel(label2)
//            mv.visitLineNumber(44, label2)
//            mv.visitFieldInsn(
//                GETSTATIC,
//                "com/cw/myframework/testAct/PrivacyComplianceScanUtil",
//                "INSTANCE",
//                "Lcom/cw/myframework/testAct/PrivacyComplianceScanUtil;"
//            )
//            mv.visitLdcInsn(name)
//            mv.visitMethodInsn(
//                INVOKEVIRTUAL,
//                "com/cw/myframework/testAct/Pjava/lang/RuntimerivacyComplianceScanUtil",
//                "printPermissionNameLog",
//                "(Ljava/lang/String;)V",
//                false
//            )


            val label5 = Label()
            mv.visitLabel(label5)
            mv.visitLineNumber(50, label5)
            mv.visitLdcInsn("------------>")
            mv.visitLdcInsn("$className $name")
            mv.visitMethodInsn(
                INVOKESTATIC,
                "android/util/Log",
                "e",
                "(Ljava/lang/String;Ljava/lang/String;)I",
                false
            )
            mv.visitInsn(POP)

        } else if (owner == "java/lang/Runtime" && name == "exec") {
            PrivacyComplianceScanUtil.printStep("adb写入 $className / $name")
        }
    }

    private fun needInsert(owner: String?, name: String?): Boolean {
        return owner == "android/content/pm/PackageManager" && (name == "getInstalledApplications" || name == "queryIntentActivities" || name == "getPackageInfo" || name == "getInstalledPackages")
    }
}