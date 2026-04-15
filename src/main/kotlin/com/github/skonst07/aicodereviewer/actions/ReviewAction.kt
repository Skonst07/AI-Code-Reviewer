package com.github.skonst07.aicodereviewer.actions

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

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

        Notification(
            "com.github.skonst07.aicodereviewer.actions",
            "AI Code Reviewer",
            "Selected: $selectedText",
            NotificationType.INFORMATION
        ).let { Notifications.Bus.notify(it, project) }

    }
}