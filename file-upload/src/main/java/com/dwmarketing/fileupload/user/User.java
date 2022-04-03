package com.dwmarketing.fileupload.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {

    @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "userSequenceGenerator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
    @Id
    private long id;
    private String name;

    public User(String name) {
        this.name = name;
    }
}
