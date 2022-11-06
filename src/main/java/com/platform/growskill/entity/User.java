package com.platform.growskill.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.growskill.enums.AccountType;
import com.platform.growskill.util.ZonedDateTimeAttributeConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public  Long id;

    @Column(name="first_name")
    public String firstName;

    @Column(name="last_name")
    public String lastName;

    @Column(name = "email")
    public String email;

    @JsonIgnore
    @Column(name = "password")
    public String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public AccountType accountType;

    @CreatedDate
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(name = "created_date")
    public ZonedDateTime createdDate = ZonedDateTime.now();


    @LastModifiedDate
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(name = "updated_date")
    public ZonedDateTime updatedDate = ZonedDateTime.now();


}
