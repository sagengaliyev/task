package com.sagengaliyev.task.models;

public enum Permission {
    READ("read"),DELETE("delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
