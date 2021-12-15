package com.cw.plugins.taskTree

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/9/9 下午5:10
 *
 * Description:
 */
class TaskTreePlugin : Plugin<Project> {
    companion object {
        const val TASK_TREE_TASK_NAME = "taskTreeGraph"
    }


    override fun apply(project: Project) {
       var isAndroidPlugin =  project.pluginManager.hasPlugin("com.android.base")
        println("-->2 isAndroidPlugin")
        project.allprojects { p ->

//            p.subprojects {
//                it.pluginManager.apply(TaskTreePlugin::class.java)
//            }

            p.tasks.register(TASK_TREE_TASK_NAME, TaskTreeTask::class.java)

            /**
             * 关键点
             * 这里注册一个坚听，当配置阶段执行完后调用(此时需要执行的task的有向无环图已经生成)，
             * 把除了TaskTreeTask以外的所有task都关闭，这样其他task在执行阶段就不会真正执行，
             * 而TaskTreeTask照常执行并根据生成的taskGraph输出任务的依赖树
             */
            p.gradle.taskGraph.whenReady {
                if (project.gradle.taskGraph.allTasks.any { task -> task is TaskTreeTask }) {
                    p.tasks.configureEach { task ->
                        if (task !is TaskTreeTask) {
                            task.enabled = false
                        }
                    }
                }
            }
        }
    }
}