package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Member extends User {
    public Member(int id, String username, String password, String email, String phone, String address, String city, String province, String postalCode, String role) {
        super(id, username, password, email, phone, address, city, province, postalCode, role);
    }
}
