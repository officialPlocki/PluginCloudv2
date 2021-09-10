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
    if(fileBuilder.getYaml().isSet(lang.name()+"."+key)) {
      return this.fileBuilder.getYaml().getString(lang.name() + "." + key);
    } else {
      fileBuilder.getYaml().set(lang.name() + "." + key, "not set");
      fileBuilder.save();
      return "The message \""+lang.name() + "." + key+"\" was not set.";
    }
  }
  
  public void setMessageString(String key, langs lang, String message) {
    this.fileBuilder.getYaml().set(lang.name() + "." + key, message);
    this.fileBuilder.save();
  }
}
