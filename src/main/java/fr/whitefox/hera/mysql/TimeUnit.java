package fr.whitefox.hera.mysql;

import java.util.HashMap;

public enum TimeUnit {

    SECONDE("Seconde(s)", "s", 1),
    MINUTE("Minute(s)", "m", 60),
    HEURE("Heure(s)", "h", 60 * 60),
    JOUR("Jour(s)", "j", 60 * 60 * 24);

    private static HashMap<String, TimeUnit> id_shortcuts = new HashMap<String, TimeUnit>();

    static {
        for (TimeUnit units : values()) {
            id_shortcuts.put(units.shortcut, units);
        }
    }

    private String name;
    private String shortcut;
    private long toSecond;

    TimeUnit(String name, String shortcut, long toSecond) {
        this.name = name;
        this.shortcut = shortcut;
        this.toSecond = toSecond;
    }

    public static TimeUnit getFromShortcut(String shortcut) {
        return id_shortcuts.get(shortcut);
    }

    public static boolean existFromShortcut(String shortcut) {
        return id_shortcuts.containsKey(shortcut);
    }

    public String getName() {
        return name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public long getToSecond() {
        return toSecond;
    }

}