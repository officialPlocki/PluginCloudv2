package me.tigerhix.lib.bossbar.common;

import java.lang.reflect.Field;

final class null implements Reflections.FieldAccessor<T> {
  public T get(Object target) {
    try {
      return (T)field.get(target);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Cannot access reflection.", e);
    } 
  }
  
  public void set(Object target, Object value) {
    try {
      field.set(target, value);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Cannot access reflection.", e);
    } 
  }
  
  public boolean hasField(Object target) {
    return field.getDeclaringClass().isAssignableFrom(target.getClass());
  }
}
