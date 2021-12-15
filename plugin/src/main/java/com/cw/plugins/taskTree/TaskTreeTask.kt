package com.cw.plugins.taskTree

import org.gradle.api.Project
import org.gradle.api.tasks.diagnostics.AbstractReportTask
import org.gradle.api.tasks.diagnostics.internal.ReportRenderer
import org.gradle.api.tasks.diagnostics.internal.TextReportRenderer
import org.gradle.execution.plan.DefaultExecutionPlan
import org.gradle.execution.plan.LocalTaskNode
import org.gradle.execution.plan.Node
import org.gradle.execution.plan.TaskNode
import org.gradle.execution.taskgraph.DefaultTaskExecutionGraph
import org.gradle.internal.graph.GraphRenderer
import org.gradle.internal.logging.text.StyledTextOutput

/**
 * Author: chenwei
 * E-mail: chenwei@qtshe.com
 * Date: 2021/9/9 下午7:36
 *
 * Description:
 */
open class TaskTreeTask : AbstractReportTask() {
    private val renderer: TextReportRenderer = TextReportRenderer()

    //用于打印依赖树的关键类
    private lateinit var graphRenderer: GraphRenderer

    override fun getRenderer(): ReportRenderer {
        return renderer
    }

    override fun generate(project: Project?) {
        project?.let {
            val metaData = clientMetaData
            val textOutPut = renderer.textOutput
            graphRenderer = GraphRenderer(textOutPut)

            /** 关键点1 获取当前要执行的task的taskGraph */
            val executionGraph = it.gradle.taskGraph

            if (executionGraph is DefaultTaskExecutionGraph) {

                try {
                    //反射获取executionPlan
                    val temp = executionGraph.javaClass.getDeclaredField("executionPlan")
                    temp.isAccessible = true
                    val executionPlan = temp.get(executionGraph)

                    //反射获取entryNodes
                    val temp2 = executionPlan.javaClass.getDeclaredField("entryNodes")
                    temp2.isAccessible = true
                    val set = temp2.get(executionPlan) as Set<*>

                    set.takeIf { s -> s.isNotEmpty() }?.filter { p->
                        p is TaskNode &&  p.task.project == project && p.task !is TaskTreeTask
                    }?.let { list ->
                        for (item in list) {
                            if (item is LocalTaskNode) {
                                rescue(item, true)
                            }
                        }
                    }

                } catch (e: Exception) {
                    println("error ---> ${e.toString()}")
                }

            }

            textOutPut.println()
        }
    }

    private fun rescue(taskNode: TaskNode, lastChild: Boolean) {
        val dependTask = taskNode.dependencySuccessors.filter {
             (it is TaskNode)
        }

        val hasChild = dependTask.isNotEmpty()
        /** 关键点2 用这个api打印方法 */
        graphRenderer.visit({ styledTextOutput ->
            //打印task name
            styledTextOutput.withStyle(StyledTextOutput.Style.Identifier).text(taskNode.task.path)
        }, lastChild)


        if (hasChild){
            graphRenderer.startChildren()

            for (item in dependTask){
                rescue(item as TaskNode,false)
            }

            graphRenderer.completeChildren()
        }else{
            return
        }



    }
}