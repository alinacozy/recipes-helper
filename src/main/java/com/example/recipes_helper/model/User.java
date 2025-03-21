package com.example.recipes_helper.model;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "users", schema = "recipes-helper-db")
public class User {
    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="user_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name")
    private String userName;

    @Column
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserHistory> userHistories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserProduct> userProducts;

    protected User() {}

    public User(Long userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Long getUserId(){
        return this.userId;
    }

    public String getUserName(){
        return this.userName;
    }

    public Boolean checkPassword(String hashPassword, String password){
        return BCrypt.checkpw(password, hashPassword);
    }

}
