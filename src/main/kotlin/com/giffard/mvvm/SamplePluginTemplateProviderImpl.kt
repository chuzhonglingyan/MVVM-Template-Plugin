package com.giffard.mvvm
import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.giffard.mvvm.activity.mvvmActivityTemplate

class SamplePluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        mvvmActivityTemplate
    )
}