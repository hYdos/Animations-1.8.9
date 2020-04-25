/*
 * Decompiled with CFR <Could not determine version>.
 */
package me.hydos.animations.util;

import java.lang.reflect.Field;

public class FieldWrapper<T> {
    private static Field modifiersField;
    private Field field;

    public FieldWrapper(String fieldName, Class<?> clazz) {
        try {
            this.field = clazz.getDeclaredField(fieldName);
            this.field.setAccessible(true);
        }
        catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
    }

    public T get(Object obj) {
        try {
            return (T)this.field.get(obj);
        }
        catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void set(Object obj, T value) {
        try {
            this.field.set(obj, value);
        }
        catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void setFinal(Object obj, T value) {
        try {
            modifiersField.setInt(this.field, this.field.getModifiers() & -17);
            this.field.set(obj, value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
        }
        catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
    }
}

