package com.github.skonst07.aicodereviewer.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.credentialStore.Credentials
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.generateServiceName
import com.intellij.openapi.application.ApplicationManager

data class  SettingsState(
    var model: String = "gemini-3.1-pro",
    var MAX_TOKENS: Int = 2048,
)

@State(
    name = "AIcodeReviewerSettings",
    storages = [Storage("AIcodeReviewerSettings.xml")]
)

class PluginSettings : PersistentStateComponent<SettingsState> {
    private var state = SettingsState()
    override fun getState() = state
    override fun loadState(s: SettingsState) {state = s}

    var model: String
        get() = state.model
        set(v) {state.model = v}

    var token: Int
        get() = state.MAX_TOKENS
        set(v) {state.MAX_TOKENS = v}

    fun credentialAttributes() = CredentialAttributes(
        generateServiceName("AI Code Reviewer", "api-key")
    )

    companion object{
        fun getInstance() : PluginSettings = ApplicationManager.getApplication().getService(PluginSettings::class.java)
    }

    var apiKey: String
        get() = PasswordSafe.instance.getPassword(credentialAttributes()) ?: ""
        set(value) = PasswordSafe.instance.set(credentialAttributes(), Credentials("apiKey", value))
}