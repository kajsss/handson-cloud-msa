package com.samsungsds.eshop.user;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "MY_USER")
@Getter
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;

    @Setter
    @Column(nullable = false)
    private String pw;

    @Setter
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Setter
    private boolean isEnable;


    public boolean getIsEnable() {
        return this.isEnable;
    }
}
