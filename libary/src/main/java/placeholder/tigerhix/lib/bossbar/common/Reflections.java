package placeholder.tigerhix.lib.bossbar.common;

import java.lang.reflect.Field;

public final class Reflections {
  public static <T> FieldAccessor<T> getField(Class<?> target, String name, Class<T> fieldType) {
    return getField(target, name, fieldType, 0);
  }
  
  private static <T> FieldAccessor<T> getField(Class<?> target, String name, Class<T> fieldType, int index) {
    for (Field field : target.getDeclaredFields()) {
      if ((name == null || field.getName().equals(name)) && fieldType.isAssignableFrom(field.getType()) && index-- <= 0) {
        field.setAccessible(true);
        return new FieldAccessor<T>() {
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
          };
      } 
    } 
    if (target.getSuperclass() != null)
      return getField(target.getSuperclass(), name, fieldType, index); 
    throw new IllegalArgumentException("Cannot find field with type " + fieldType);
  }
  
  public interface FieldAccessor<T> {
    T get(Object param1Object);
    
    void set(Object param1Object1, Object param1Object2);
    
    boolean hasField(Object param1Object);
  }
}
