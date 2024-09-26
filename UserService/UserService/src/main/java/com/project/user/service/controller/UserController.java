package com.project.user.service.controller;

import com.project.user.service.Services.UserService;
import com.project.user.service.entity.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User saveUser) {
        User user = userService.saveUser(saveUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    int retrycount = 1;

    @GetMapping("/{id}")
    // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
    //  @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        logger.info("Retry Count :{}", retrycount++);

        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex) {

        // logger.info("Fallback is executed because service is down: {}", ex.getMessage());
        User user = User.builder()
                .emailId("dummy@gmail.com")
                .name("dummy")
                .about("User is created dummy because some service is down")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
