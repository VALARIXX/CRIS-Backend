package com.cris.auth.service;

import com.cris.auth.entity.User;

public interface UserService {
User getUserByEmail(String email);

User CreateUser(User user);


}
