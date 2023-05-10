package com.cw.plugins.antirepeatclick.adapter

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

class AntiRepeatClickMethodAdapter extends AdviceAdapter {
    private boolean needInsertCode = false
    private String methodName
    private String methodDesc
    private String className

    AntiRepeatClickMethodAdapter(MethodVisitor mv, int access, String methodName, String methodDesc,String className) {
        super(Opcodes.ASM7, mv,access,methodName,methodDesc)
        this.methodName = methodName
        this.methodDesc = methodDesc
        this.className = className

    }

    @Override
    AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (Constant.ANTI_REPEAT_CLICK_ANNO_DES == descriptor) {
            needInsertCode = true
        }
        return super.visitAnnotation(descriptor, visible)
    }


    @Override
    protected void onMethodEnter() {
        super.onMethodEnter()
        if (needInsertCode) {
            println("-----> start visit method $methodName /// $methodDesc /// $className")
        }
    }


}