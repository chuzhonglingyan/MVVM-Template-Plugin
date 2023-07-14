package com.giffard.mvvmtemplate.listeners

import com.giffard.mvvmtemplate.services.MyProjectService
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

internal class MyProjectManagerListener : ProjectManagerListener {

   companion object {
        var projectInstance: Project? = null
    }

    override fun projectOpened(project: Project) {
        projectInstance = project
        project.getService(MyProjectService::class.java)
    }

    override fun projectClosing(project: Project) {
        projectInstance = null
        super.projectClosing(project)
    }
}
