package com.github.skonst07.aicodereviewer.settings

import com.intellij.openapi.options.Configurable
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPasswordField
import com.intellij.ui.components.JBTextField
import javax.swing.JComponent
import com.intellij.util.ui.FormBuilder

class PluginConfigurable : Configurable{

    val settings = PluginSettings.getInstance()
    val apiKeyField = JBPasswordField()
    val modelField = JBTextField()

    override fun getDisplayName() = "AI Code Reviewer"

    override fun createComponent(): JComponent? {
        reset()
        return FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("API Key:"), apiKeyField)
            .addLabeledComponent(JBLabel("Model: "), modelField)
            .panel
    }

    override fun isModified() = String(apiKeyField.password) != settings.apiKey || modelField.text != settings.model

    override fun apply() {
        settings.apiKey = String(apiKeyField.password)
        settings.model = modelField.text.trim()
    }

    override fun reset() {
        apiKeyField.text = settings.apiKey
        modelField.text = settings.model
    }
}