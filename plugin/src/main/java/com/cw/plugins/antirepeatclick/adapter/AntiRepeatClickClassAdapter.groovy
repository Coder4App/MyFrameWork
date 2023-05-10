package com.cw.plugins.antirepeatclick.adapter


import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class AntiRepeatClickClassAdapter extends ClassVisitor {

    private String className;

    AntiRepeatClickClassAdapter(ClassVisitor classVisitor) {
        super(Opcodes.ASM7, classVisitor)
    }

    @Override
    void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        className = name
        super.visit(version, access, name, signature, superName, interfaces)
    }


    @Override
    MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions)
        return mv == null ? null : new AntiRepeatClickMethodAdapter(mv, access, name, descriptor,className)
    }

}