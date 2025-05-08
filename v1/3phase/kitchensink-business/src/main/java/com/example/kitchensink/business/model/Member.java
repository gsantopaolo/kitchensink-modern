package com.example.kitchensink.business.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Member {
    @Id @GeneratedValue
    private Long id;

    @NotNull @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String name;

    @NotNull @Email @NotEmpty
    private String email;

    @NotNull @Size(min = 10, max = 12)
    private String phoneNumber;

    // getters and setters omitted for brevity
}
