package com.github.skonst07.aicodereviewer.UI

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class AIreviewerToolWindow : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = AIreviewerPanel()
        panels[project] = panel
        val content = ContentFactory.getInstance().createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }

    companion object {
        private val panels = mutableMapOf<Project, AIreviewerPanel>()
        fun getPanel(project: Project): AIreviewerPanel? = panels[project]
    }
}