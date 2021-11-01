package me.refluxo.libary.bungee.utils.project;

import me.refluxo.libary.bungee.utils.language.LanguageAPI;

public class ProjectManager {
  String project;
  
  public ProjectManager(String project) {
    this.project = project;
  }
  
  public void addPermission(String perm) {
    PermissionList.addPermission(this.project, perm);
  }
  
  public String getMessage(String key, LanguageAPI.langs lang) {
    return (new LanguageAPI(this.project)).getMessageString(key, lang);
  }
  
  public void setMessage(String key, LanguageAPI.langs lang, String message) {
    (new LanguageAPI(this.project)).setMessageString(key, lang, message);
  }
}
