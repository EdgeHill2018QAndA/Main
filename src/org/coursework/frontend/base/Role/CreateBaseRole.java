package org.coursework.frontend.base.Role;

import org.coursework.Main;
import org.coursework.backend.roles.Role;

public interface CreateBaseRole {
    
    public String getRoleName();
    
    public default Role createRole(){
        return new Role(getRoleName());
    }
    
    public default boolean registerRole(Role role){
        if (Main.getRoles().stream().anyMatch(r -> r.equals(role))){
            return false;
        }
        if(role.getDisplayName().replace(" ", "").equalsIgnoreCase("")){
            return false;
        }
        Main.register(role);
        return true;
    }
    
}
