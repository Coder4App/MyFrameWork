package com.cw.plugins.antirepeatclick.Weaver

import com.cw.plugins.antirepeatclick.adapter.AntiRepeatClickClassAdapter
import com.quinn.hunter.transform.asm.BaseWeaver
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

class AntiRepeatClickWeaver extends BaseWeaver {

    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new AntiRepeatClickClassAdapter(classWriter)
    }
}