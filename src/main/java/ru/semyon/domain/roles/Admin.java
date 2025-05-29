package ru.semyon.domain.roles;

import java.time.LocalDateTime;

import static ru.semyon.Constants.SET_ACCESS_LEVEL;

public class Admin extends User {
    private int accessLevel;
    private String department;
    private LocalDateTime lastLoginDate;
    private boolean canManageUsers;
    private boolean isSuperAdmin;

    public Admin(String name, String surname, String login, String password, String email) {
        super(name, surname, login, password, email);
        this.accessLevel = 1;
        this.canManageUsers = false;
        this.isSuperAdmin = false;
    }

    public void promoteToSuperAdmin() {
        this.isSuperAdmin = true;
        this.accessLevel = 3;
    }

    public void updateLastLogin() {
        this.lastLoginDate = LocalDateTime.now();
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int level) {
        if (level >= 1 && level <= SET_ACCESS_LEVEL) {
            this.accessLevel = level;
        }
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public boolean isCanManageUsers() {
        return canManageUsers;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }
}
