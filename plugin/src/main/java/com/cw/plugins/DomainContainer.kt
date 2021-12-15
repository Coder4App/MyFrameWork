package com.cw.plugins

import com.cw.plugins.entity.DomainTestEntity
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/7/15 下午8:27
 *
 * Description:
 */
class DomainContainer:Plugin<Project> {
    override fun apply(project: Project) {
        //创建一个扩展extension
        project.extensions.create("test",DomainTestEntity::class.java)
        project.tasks.register("testDomain"){
            it.group = "testDomain"
            println("----->${(project.extensions.findByName("test") as DomainTestEntity).p1}")
            print("----->${project.extensions.findByType(DomainTestEntity::class.java)?.p1}")
        }
    }
}