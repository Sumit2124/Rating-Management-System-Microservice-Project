package com.project.user.service.Services;

import com.project.user.service.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUser();
    public User getUser(String userId);

}
