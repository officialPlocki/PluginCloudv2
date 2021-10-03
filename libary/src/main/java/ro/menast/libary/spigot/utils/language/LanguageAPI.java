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
    String prefix = "§b§lMenastro§8┃ §7";
    if(fileBuilder.getYaml().isSet(lang.name()+"."+key)) {
      return prefix+this.fileBuilder.getYaml().getString(lang.name() + "." + key);
    } else {
      fileBuilder.getYaml().set(lang.name() + "." + key, "not set");
      fileBuilder.save();
      return prefix+"for message '" + lang.name() + "." + key + "' is no message set.";
    }
  }
  
  public void setMessageString(String key, langs lang, String message) {
    if(!fileBuilder.getYaml().isSet(lang.name() + "." + key)) {
      this.fileBuilder.getYaml().set(lang.name() + "." + key, message);
      this.fileBuilder.save();
    }
  }
}
