package net.veloxia.logis.objects

import kotlinx.serialization.Serializable

/**
 * Data class representing the language JSON file.
 * @author xRookieFight
 */
@Serializable
data class LanguageData(
    val name: String,
    val prettyName: String,
    val flag: String,
    val translators: List<String>,
    val translations: Map<String, String>
) {
    override fun toString() : String = super.toString()
}
