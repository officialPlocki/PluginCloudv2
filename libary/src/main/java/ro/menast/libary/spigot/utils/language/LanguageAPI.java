package ro.menast.libary.spigot.utils.language;

import ro.menast.libary.spigot.utils.filebuilder.FileBuilder;

public class LanguageAPI {
  private final FileBuilder fileBuilder;
  
  public enum langs {
    DE, EN;
  }
  
  public LanguageAPI(String project) {
    this.fileBuilder = new FileBuilder("langs/" + project + ".lang");
  }
  
  public String getMessageString(String key, langs lang) {
    return this.fileBuilder.getYaml().getString(lang.name() + "." + key);
  }
  
  public void setMessageString(String key, langs lang, String message) {
    this.fileBuilder.getYaml().set(lang.name() + "." + key, message);
    this.fileBuilder.save();
  }
}
