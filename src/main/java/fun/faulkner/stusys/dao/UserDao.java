package fun.faulkner.stusys.dao;

import fun.faulkner.stusys.entity.User;

public interface UserDao {
    User getById(int id);
    User getByUsername(String username);
}
