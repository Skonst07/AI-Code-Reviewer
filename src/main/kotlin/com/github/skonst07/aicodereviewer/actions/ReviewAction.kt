package com.github.skonst07.aicodereviewer.actions

import com.github.skonst07.aicodereviewer.UI.AIreviewerToolWindow
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.github.skonst07.aicodereviewer.settings.PluginSettings
import com.github.skonst07.aicodereviewer.api.GroqApiClient
import com.intellij.openapi.wm.ToolWindowManager

class ReviewAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val selectedText = editor.selectionModel.selectedText

        if (selectedText == null) {
            Notification(
                "com.github.skonst07.aicodereviewer.actions",
                "AI Code Reviewer",
                "Please select the code you want to review.",
                NotificationType.WARNING
            ).let { Notifications.Bus.notify(it, project) }
            return
        }

        val settings = PluginSettings.getInstance()
        val apiKey = settings.apiKey

        if (apiKey.isBlank()) {
            Notification(
                "com.github.skonst07.aicodereviewer",
                "AI Code Reviewer",
                "No API key set. Go to File → Settings → Tools → AI Code Reviewer.",
                NotificationType.ERROR
            ).let { Notifications.Bus.notify(it, project) }
            return
        }

        val prompt = "Review this code and identify bugs, issues, and improvements:\n\n$selectedText"
        val apiClient = GroqApiClient()

        apiClient.review(
            apiKey = apiKey,
            prompt = prompt,
            onResult = { result ->
                val toolWindow = ToolWindowManager.getInstance(project)
                    .getToolWindow("AI Review")
                toolWindow?.activate(null)

                AIreviewerToolWindow.getPanel(project)?.setText(result)
            },
            onError = { error ->
                AIreviewerToolWindow.getPanel(project)?.printError(error)
            }

        )
    }
}