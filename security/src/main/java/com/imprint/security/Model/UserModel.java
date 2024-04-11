package com.imprint.security.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String username;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER,  cascade = {CascadeType.ALL})
    @JoinTable(
            name = "roleTable",
            joinColumns = @JoinColumn(name = "userId",referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId",referencedColumnName = "roleId")
    )
    Set<Role> roles = new HashSet<>();

}
