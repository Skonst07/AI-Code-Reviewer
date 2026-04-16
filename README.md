# AI Code Reviewer — IntelliJ Plugin

An IntelliJ IDEA plugin that sends your selected code to **Groq** for an instant AI-powered review, streamed live into a dedicated tool window.

---

## Features

- **Right-click → Review with AI** on any selection
- Live **streaming** output — results appear token-by-token, no waiting
- Structured review: summary · issues with severity · positives · score
- Configurable **focus areas** (bugs, security, performance, style…)
- **API key stored in PasswordSafe** — never written to disk in plain text
- Cancellable background task — won't freeze the IDE

---

## Quick start

### 1. Prerequisites

| Requirement   | Version |
|---------------|---|
| JDK           | 21+ |
| IntelliJ IDEA | 2024.3+ (Community or Ultimate) |
| Groq API key  | [console.groq.com](https://console.groq.com/) |

### 2. Build the plugin

```bash
git clone https://github.com/your-handle/ai-code-reviewer.git
cd AI-Code-Reviewer
./gradlew buildPlugin        # produces build/distributions/AI-Code-Reviewer-*.zip
```

### 3. Install in IntelliJ

1. Open **Settings → Plugins → ⚙ → Install Plugin from Disk…**
2. Select the `.zip` from `build/distributions/`
3. Restart the IDE

### 4. Configure

Go to **Preferences → Tools → AI Code Reviewer** and paste your Groq API key.

### 5. Use

1. Select any code in the editor
2. Right-click → **Review with AI**
3. Watch the **AI Review** panel at the bottom stream the review

---

## Run from source (sandbox IDE)

```bash
./gradlew runIde
```

This launches a fresh IntelliJ sandbox with the plugin pre-installed.

---

## Project structure

```
src/main/kotlin/com/aicodereviewer/
├── actions/
│   └── ReviewAction.kt          # AnAction: validates, launches background task
├── api/
│   └── GroqApiClient.kt         # HTTP + SSE streaming client
├── settings/
│   ├── PluginSettings.kt        # PersistentStateComponent + PasswordSafe
│   └── PluginConfigurable.kt    # Preferences UI panel
└── ui/
    ├── AIreviewerPanel.kt           # Swing panel
    └── AIreviewerToolWindow.kt
```

---

## Configuration options

| Setting | Default | Description                   |
|---|---|-------------------------------|
| Model | `llama-3.3-70b-versatile` | AI model slug                 |
| Max tokens | 2048 | Upper bound on response length |
| Focus areas | `bugs,security,performance,style` | Comma-separated hints for the prompt |
| Streaming | ✅ enabled | Toggle SSE vs. single-response mode |

---
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-
-

![Build](https://github.com/Skonst07/AI-Code-Reviewer/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Adjust the [pluginGroup](./gradle.properties) and [pluginName](./gradle.properties), as well as the [id](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [ ] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the `MARKETPLACE_ID` in the above README badges. You can obtain it once the plugin is published to JetBrains Marketplace.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.
- [ ] Configure the [CODECOV_TOKEN](https://docs.codecov.com/docs/quick-start) secret for automated test coverage reports on PRs

<!-- Plugin description -->
This Fancy IntelliJ Platform Plugin is going to be your implementation of the brilliant ideas that you have.

This specific section is a source for the [plugin.xml](/src/main/resources/META-INF/plugin.xml) file which will be extracted by the [Gradle](/build.gradle.kts) during the build process.

To keep everything working, do not remove `<!-- ... -->` sections. 
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "AI-Code-Reviewer"</kbd> >
  <kbd>Install</kbd>

- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/Skonst07/AI-Code-Reviewer/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
