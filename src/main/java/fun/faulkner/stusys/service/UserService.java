package fun.faulkner.stusys.service;

import fun.faulkner.stusys.entity.User;

public interface UserService {
    User getUserById(int id);
    User getUserByUsername(String username);
}
