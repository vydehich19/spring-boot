package com.client.sample.model;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "clientdata")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clientId;

    @Column
    @NotBlank(message = "First Name is required")
    private String firstName;

    @Column
    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Column(unique = true)
    @Email(message = "Invalid email format")
    private String email;

    @Column
    @NotBlank(message = "City is required")
    private String city;

    @Column
    @NotBlank(message = "State is required")
    private String state;

    @Column
    @NotBlank(message = "Country is required")
    private String country;

}
