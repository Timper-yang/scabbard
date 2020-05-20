package com.github.scabbard.gradle

import com.android.build.gradle.internal.pipeline.TransformTask
import com.github.scabbard.kotlinx.call
import com.github.scabbard.kotlinx.get
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionAdapter

/**
 * User: tangpeng.yang
 * Date: 2020/5/18
 * Description:
 * FIXME
 */
class TransformTaskExecutionListener(private val project: Project) : TaskExecutionAdapter() {
    override fun beforeExecute(task: Task) {
        task.takeIf {
            it.project == project && it is TransformTask && it.transform.scopes.isNotEmpty()
        }?.run {
            task["outputStream"]?.call<Unit>("init")
        }
    }
}