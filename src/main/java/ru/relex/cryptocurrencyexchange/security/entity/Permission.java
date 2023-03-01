package ru.relex.cryptocurrencyexchange.security.entity;

public enum Permission {
    READ("READ"),
    WRITE("WRITE"),
    ADMIN_READ("ADMIN_READ"),
    ADMIN_WRITE("ADMIN_WRITE");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
