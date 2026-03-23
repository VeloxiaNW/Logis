package net.veloxia.logis.objects;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Data class representing the language JSON file.
 *
 * @author xRookieFight
 * @since 23/03/2026
 */
@Data
@NoArgsConstructor
public class LanguageData {

    private String name;
    private String prettyName;
    private String flag;
    private List<String> translators;
    private Map<String, String> translations;
}