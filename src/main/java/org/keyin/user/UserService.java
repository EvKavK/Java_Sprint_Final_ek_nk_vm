package org.keyin.user;

import org.keyin.cli._UserRoles;
import org.keyin.user.childclasses.Admin;
import org.keyin.user.childclasses.Member;
import org.keyin.user.childclasses.Trainer;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    // constructor
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // password handling and hashing
    private String hashPassword(String plainPass) {
        return BCrypt.hashpw(plainPass, BCrypt.gensalt(12));
    }
    
    private boolean verifyPassword(String plainPass, String hashedPass) {
        return BCrypt.checkpw(plainPass, hashedPass);
    }

    // consolidated user reg
    public User registerUser(String username, String password, String email, String phone, String address, String city, String province, String postal, _UserRoles role) throws SQLException {
        // hash pass before storing
        String hashedPass = hashPassword(password);
        
        User user;
        switch (role) {
            case MEMBER -> user = new Member(0, username, hashedPass, email, phone, address, city, province, postal, role.toDbValue());
            case TRAINER -> user = new Trainer(0, username, hashedPass, email, phone, address, city, province, postal, role.toDbValue());
            case ADMIN -> user = new Admin(0, username, hashedPass, email, phone, address, city, province, postal, role.toDbValue());
            default -> throw new IllegalArgumentException("Invalid role specified");
        }
        return userDao.createUser(user);
    }

    
    // userauth
    public User authUser(String username, String password) throws SQLException {
        User user = userDao.getUserByUsername(username);
        if (user != null && verifyPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }


    // profile management
    public User updateUser(User user) throws SQLException {
        return userDao.updateUser(user);
    }

    public User updateUserPassword(User user, String newPassword) throws SQLException {
        // hash new password before updating
        user.setPassword(hashPassword(newPassword));
        return userDao.updateUser(user);
    }

    public User deleteUser(User user) throws SQLException {
        return userDao.deleteUser(user);
    }


    // admin
    public User getUserByName(String username) throws SQLException {
        return userDao.getUserByUsername(username);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }


    // helpers
    public boolean isAdmin(User user) {
        return _UserRoles.ADMIN.equals(_UserRoles.fromDbValue(user.getRole()));
    }

    public boolean isTrainer(User user) {
        return _UserRoles.TRAINER.equals(_UserRoles.fromDbValue(user.getRole()));
    }

    public boolean isMember(User user) {
        return _UserRoles.MEMBER.equals(_UserRoles.fromDbValue(user.getRole()));
    }
}
