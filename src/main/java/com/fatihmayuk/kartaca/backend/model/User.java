package com.fatihmayuk.kartaca.backend.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users", indexes = {@Index(name = "idx_username",columnList = "uname")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "uname", length = 20, unique = true)
    private String username;

    @Column(name = "name_surname",length = 200)
    private String nameSurname;

    @Column(name = "pwd")
    private String password;

    @Column(name = "email", length = 100)
    private String email;

    @JoinColumn(name = "user_travel_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Travel> travel;
}
