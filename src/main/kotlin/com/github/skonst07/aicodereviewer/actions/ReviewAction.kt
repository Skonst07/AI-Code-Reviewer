package com.github.skonst07.aicodereviewer.actions

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ReviewAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        val notification = Notification(
            "com.github.skonst07.aicodereviewer",
            "AI Code Reviewer",
            "Hello World",
            NotificationType.INFORMATION
        )

        Notifications.Bus.notify(notification, project)
    }
}