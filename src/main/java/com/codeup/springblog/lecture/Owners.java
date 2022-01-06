package com.codeup.springblog.lecture;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="owners")
public class Owners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @ManyToMany
    @JoinTable(name="vehicle_owners",
    joinColumns = {@JoinColumn(name="owner_id")}, inverseJoinColumns = {@JoinColumn(name="car_id")})
    private List<Car> vehicle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
