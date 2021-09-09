package me.tigerhix.lib.bossbar.common;

public interface FieldAccessor<T> {
  T get(Object paramObject);
  
  void set(Object paramObject1, Object paramObject2);
  
  boolean hasField(Object paramObject);
}
