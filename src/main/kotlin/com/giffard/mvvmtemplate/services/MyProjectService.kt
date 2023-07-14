package com.giffard.mvvmtemplate.services

import com.intellij.openapi.project.Project
import com.giffard.mvvmtemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
