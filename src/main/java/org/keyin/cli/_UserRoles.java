package org.keyin.cli;

// user roles
public enum _UserRoles {
    ADMIN("admin"), 
    TRAINER("trainer"), 
    MEMBER("member");
    
    private final String dbValue;
    
    _UserRoles(String dbValue) {
        this.dbValue = dbValue;
    }
    
    public String toDbValue() {
        return dbValue;
    }
    
    public static _UserRoles fromDbValue(String role) {
        if (role == null) return null;
        
        String lowerRole = role.toLowerCase();
        for (_UserRoles userRole : _UserRoles.values()) {
            if (userRole.dbValue.equals(lowerRole)) {
                return userRole;
            }
        }
        return null;
    }
} 