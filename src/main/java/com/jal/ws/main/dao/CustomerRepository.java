package com.jal.ws.main.dao;

import com.jal.ws.main.AppConfig;
import com.jal.ws.main.annotation.ConfigAutowireable;
import com.jal.ws.main.entity.User;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.Result;

import java.util.List;

@ConfigAutowireable
@Dao
public interface CustomerRepository {

    //ここにコメントを書いてみる。
    @Select
    public List<User> findAllOrderByName();

    @Select
    public User findById(Integer id);

    @Insert
    public Result<User> insert(User user);

    @Update
    public Result<User> update(User user);

    @Delete
    public Result<User> delete(User user);
}