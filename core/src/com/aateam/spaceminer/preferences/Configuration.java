package com.aateam.spaceminer.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public abstract class Configuration {

    public Configuration() {
        restoreDefaults();
    }

    /** Returns filename of the configuration file. */
    public abstract String getPrefsFileName();

    /** Returns full path to the prefs file. */
    protected final String getPrefsPath(){
        return getPrefsFileName();
    }

    /** Accessor for preference object. */
    private Preferences getPreferences(){
        return Gdx.app.getPreferences(getPrefsPath());
    }

    /** Saves all changes to the configuration file. */
    protected final void flush(){
        if (getPreferences() == null) throw new IllegalStateException("Preferences hasn't been prepared.");
        getPreferences().flush();
    }

    /** Generalized setter for key/value pairs. */
    protected final <T> void put(String key, T value){
        if (value instanceof Integer){
            getPreferences().putInteger(key, (Integer) value);
        }
        else if (value instanceof Float){
            getPreferences().putFloat(key, (Float) value);
        }
        else if (value instanceof String){
            getPreferences().putString(key, (String) value);
        }
        else if (value instanceof Long){
            getPreferences().putLong(key, (Long) value);
        }
        else if (value instanceof Boolean){
            getPreferences().putBoolean(key, (Boolean) value);
        }
    }

    /** Generalized getter for key/value pairs. */
    protected final <T> T get(String key, T defaultValue){
        if (defaultValue instanceof Integer){
            return (T)(Integer)getPreferences().getInteger(key, (Integer) defaultValue);
        }
        else if (defaultValue instanceof Float){
            return (T)(Float)getPreferences().getFloat(key, (Float) defaultValue);
        }
        else if (defaultValue instanceof String){
            return (T)getPreferences().getString(key, (String) defaultValue);
        }
        else if (defaultValue instanceof Long){
            return (T)(Long)getPreferences().getLong(key, (Long) defaultValue);
        }
        else if (defaultValue instanceof Boolean){
            return (T)(Boolean)getPreferences().getBoolean(key, (Boolean) defaultValue);
        }
        else
            return null;
    }

    /** Defines a way to store. */
    public abstract void save();

    /** Defines a way to load all properties. */
    public abstract void load();

    /** Defines a way to restore default values for properties. */
    public abstract void restoreDefaults();
}
