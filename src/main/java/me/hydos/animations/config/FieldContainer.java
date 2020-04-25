/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 */
package me.hydos.animations.config;

import java.lang.reflect.Field;
import net.minecraft.client.gui.Gui;

public class FieldContainer {
    protected Field field;
    protected ConfigurationHolder.ConfigOpt annotation;
    protected Gui element;
    private ConfigurationHolder config;

    public FieldContainer(Field field, ConfigurationHolder.ConfigOpt annotation, ConfigurationHolder config) {
        this.field = field;
        this.annotation = annotation;
        this.config = config;
    }

    public String name() {
        return this.annotation.name();
    }

    public String category() {
        return this.annotation.category();
    }

    public Field getField() {
        return this.field;
    }

    public void setValue(String value) {
        try {
            if (this.field.getType().isAssignableFrom(Integer.TYPE)) {
                if (value.isEmpty()) {
                    this.field.set(this.config, 0);
                } else {
                    this.field.set(this.config, Integer.valueOf(value));
                }
            } else if (this.field.getType().isAssignableFrom(Boolean.TYPE)) {
                if (value.equalsIgnoreCase("true")) {
                    this.field.setBoolean(this.config, true);
                } else if (value.equalsIgnoreCase("false")) {
                    this.field.setBoolean(this.config, false);
                } else {
                    new Exception("Error updating " + this.annotation.name()).printStackTrace();
                }
            } else if (this.field.getType().isAssignableFrom(Double.TYPE)) {
                if (value.isEmpty()) {
                    this.field.set(this.config, 0);
                } else {
                    this.field.setDouble(this.config, Double.parseDouble(value));
                }
            } else {
                this.field.set(this.config, value);
            }
            this.config.save();
            this.config.load();
            return;
        }
        catch (Exception e) {
            System.out.println("Error updating " + this.annotation.name());
            e.printStackTrace();
        }
    }

    public String getValue() {
        try {
            return this.field.get(this.config) + "";
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "Error";
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "Error";
    }
}

