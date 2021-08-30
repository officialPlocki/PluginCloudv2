package ro.menast.libary.utils.projects.language;

import org.bukkit.configuration.file.YamlConfiguration;
import ro.menast.libary.utils.filebuilder.FileBuilder;

public class Language {

    private final YamlConfiguration en;
    private final YamlConfiguration de;

    private final FileBuilder deBuilder;
    private final FileBuilder enBuilder;

    public Language(String project) {
        deBuilder = new FileBuilder("language/"+ project +"Lang/DE");
        enBuilder = new FileBuilder("language/"+ project +"Lang/EN");
        en = enBuilder.getYaml();
        de = deBuilder.getYaml();
    }

    public String getKey(String key, Languages lang) {
        if(lang.equals(Languages.DE)) {
            if(de.isSet(key)) {
                return de.getString(key);
            } else {
                de.set(key, "nicht gesetzt");
                deBuilder.save();
                return de.getString(key);
            }
        } else {
            if(en.isSet(key)) {
                return en.getString(key);
            } else {
                en.set(key, "not set");
                enBuilder.save();
                return en.getString(key);
            }
        }
    }

    public void setKey(String key, String value, Languages lang) {
        if(lang.equals(Languages.DE)) {
            de.set(key, value);
            deBuilder.save();
        } else {
            en.set(key, value);
            enBuilder.save();
        }
    }

}
