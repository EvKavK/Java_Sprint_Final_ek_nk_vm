package org.keyin.user;

import org.keyin.user.childclasses.Admin;
import org.keyin.user.childclasses.Member;
import org.keyin.user.childclasses.Trainer;

import java.sql.SQLException;

public class UserService {
    private final UserDao userDao;

    // constructor
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    // user registration
    public User registerMember(String username, String password, String email, String phone, String address, String city, String province, String postal) throws SQLException {
        Member member = new Member(0, username, password, email, phone, address, city, province, postal, "member");
        return userDao.createUser(member);
    }

    public User registerTrainer(String username, String password, String email, String phone, String address, String city, String province, String postal) throws SQLException {
        Trainer trainer = new Trainer(0, username, password, email, phone, address, city, province, postal, "trainer");
        return userDao.createUser(trainer);
    }

    public User registerAdmin(String username, String password, String email, String phone, String address, String city, String province, String postal) throws SQLException {
        Admin admin = new Admin(0, username, password, email, phone, address, city, province, postal, "admin");
        return userDao.createUser(admin);
    }


    // userauth
    public User authUser(String username, String password) throws SQLException {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


    // profile management
    public User updateUser(User user) throws SQLException {
        return userDao.updateUser(user);
    }

    public User deleteUser(User user) throws SQLException {
        return userDao.deleteUser(user);
    }


    // admin
    public User getUserByName(String username) throws SQLException {
        return userDao.getUserByUsername(username);
    }


    // helpers
    public boolean isAdmin(User user) {
        return user.getRole().equalsIgnoreCase("admin");
    }

    public boolean isTrainer(User user) {
        return user.getRole().equalsIgnoreCase("trainer");
    }

    public boolean isMember(User user) {
        return user.getRole().equalsIgnoreCase("member");
    }
}
