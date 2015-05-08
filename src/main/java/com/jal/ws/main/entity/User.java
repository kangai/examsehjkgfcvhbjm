package com.jal.ws.main.entity;

import lombok.Getter;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(naming = NamingType.SNAKE_UPPER_CASE, immutable = true)
@Table(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "seq_customers")
    @Getter
    private final Integer id;

    @Getter
    private final String name;
    @Getter
    private final Integer age;
    @Getter
    private final String address;

    public User(String name, Integer age, String address) {
        this.id = null;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public User(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }


    public String toString() {
        return "com.jal.ws.main.entity.User(id=" + this.id + ", name=" + this.name + ", age=" + this.age + ", address=" + this.address + ")";
    }
}
