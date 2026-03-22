# Logis

[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.21-blue.svg)](https://kotlinlang.org/) [![License](https://img.shields.io/badge/License-MIT-green.svg)](./LICENSE)

Logis is a lightweight, JSON-based, **multi-language system** for Veloxia Network designed to make translations easy and efficient. It supports loading multiple languages, retrieving translations with fallbacks, and provides metadata like flags and translators.

---

> [!IMPORTANT]
> This library is on a beta version, bugs may occur. If you find an issue please report it from issues page.

---

## Features

- Load multiple JSON language files dynamically.
- Caches languages for fast runtime access.
- Fallback to default language if a key or language is missing.
- Access full language metadata (`prettyName`, `flag`, `translators`).
- Exception handling with `LanguageNotFoundException`.

---

## JSON Structure

Each language file should follow this structure:

```json
{
  "name": "en-US",
  "prettyName": "English",
  "flag": "https://cdn.veloxia.net/img/flags/english.png",
  "translators": ["xRookieFight"],
  "translations": {}
}
