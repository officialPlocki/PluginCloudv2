package ro.menast.libary.bungee.utils.language;

import ro.menast.libary.bungee.utils.filebuilder.FileBuilder;

public class LanguageAPI {
  private final FileBuilder fileBuilder;
  
  public enum langs {
    DE, EN;
  }
  
  public LanguageAPI(String project) {
    this.fileBuilder = new FileBuilder("langs/" + project + ".lang");
  }
  
  public boolean isSet(String key) {
    return (this.fileBuilder.getYaml().getString(langs.DE.name() + "." + key) != null || this.fileBuilder.getYaml().getString(langs.EN.name() + "." + key) != null);
  }
  
  public String getMessageString(String key, langs lang) {
    if (isSet(key))
      return this.fileBuilder.getYaml().getString(lang.name() + "." + key); 
    setMessageString(key, langs.DE, "no message set");
    setMessageString(key, langs.EN, "no message set");
    return "for message '" + lang.name() + "." + key + "' is no message set.";
  }
  
  public void setMessageString(String key, langs lang, String message) {
    this.fileBuilder.getYaml().set(lang.name() + "." + key, message);
    this.fileBuilder.save();
  }
}
