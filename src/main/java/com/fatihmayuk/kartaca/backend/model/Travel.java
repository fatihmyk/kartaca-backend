package com.fatihmayuk.kartaca.backend.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "travel")
public class Travel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "location",length = 100)
    private String location;

    @Column(name = "notes",length = 1000)
    private String notes;

    @JoinColumn(name = "travel_user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
