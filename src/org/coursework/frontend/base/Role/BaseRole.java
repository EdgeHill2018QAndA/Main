package org.coursework.frontend.base.Role;

import org.coursework.Main;
import org.coursework.backend.roles.Role;

public interface BaseRole {
    
    public String getRoleName();
    
    public default Role createRole(){
        return new Role(getRoleName());
    }
    
    public default boolean registerRole(Role role){
        if (Main.getRoles().stream().anyMatch(r -> r.equals(role))){
            return false;
        }
        Main.register(role);
        return true;
    }
    
}
