package com.clickcart.ClickCart.repository;

import com.clickcart.ClickCart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    public User findByName(String name);
    public User findByEmail(String email);
    public User findByMobile(String mobile);
    public User findByUserName(String userName);
    public User findById(int id);
}
