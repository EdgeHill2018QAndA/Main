package org.coursework.backend.roles;

public class Role {

    String displayName;

    public Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Role)) {
            return false;
        }
        Role role = (Role) obj;
        if (!(role.getDisplayName().equalsIgnoreCase(getDisplayName()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

}
