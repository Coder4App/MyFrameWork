package com.cw.plugins.antirepeatclick.plugin

import com.android.build.gradle.AppExtension
import com.cw.plugins.antirepeatclick.transform.AntiRepeatClickTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

class AntiRepeatClickPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def appExtension = project.extensions.getByType(AppExtension::class.java)
        appExtension.registerTransform(new AntiRepeatClickTransform(project), Collections.EMPTY_LIST)
    }
}