package com.cw.plugins.antirepeatclick.transform

import com.cw.plugins.antirepeatclick.Weaver.AntiRepeatClickWeaver
import com.quinn.hunter.transform.HunterTransform
import org.gradle.api.Project

class AntiRepeatClickTransform extends HunterTransform {

    AntiRepeatClickTransform(Project project) {
        super(project)
        this.bytecodeWeaver = new AntiRepeatClickWeaver()
    }

    //测试代码记得删除 测试阶段关闭增量编译
    @Override
    boolean isIncremental() {
        return false
    }
}