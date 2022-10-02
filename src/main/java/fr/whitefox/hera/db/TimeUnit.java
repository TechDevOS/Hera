package fr.whitefox.hera.db;

import java.util.HashMap;

public enum TimeUnit {

    SECONDE("Seconde(s)", "s", 1),
    MINUTE("Minute(s)", "m", 60),
    HEURE("Heure(s)", "h", 60 * 60),
    JOUR("Jour(s)", "j", 60 * 60 * 24);

    private static final HashMap<String, TimeUnit> id_shortcuts = new HashMap<>();

    static {
        for (TimeUnit units : values()) {
            id_shortcuts.put(units.shortcut, units);
        }
    }

    private final String name;
    private final String shortcut;
    private final long toSecond;

    TimeUnit(String name, String shortcut, long toSecond) {
        this.name = name;
        this.shortcut = shortcut;
        this.toSecond = toSecond;
    }

    public String getName() {
        return name;
    }

    public long getToSecond() {
        return toSecond;
    }

}