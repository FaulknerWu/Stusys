package fun.faulkner.stusys.service;

import fun.faulkner.stusys.dao.UserDao;
import fun.faulkner.stusys.entity.User;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(int id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getByUsername(username);
    }
}
