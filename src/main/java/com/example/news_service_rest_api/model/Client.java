package com.example.news_service_rest_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<News> news = new ArrayList<>();

    @ElementCollection(targetClass = RoleType.class, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "client_id"))
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            foreignKey = @ForeignKey(
                    name = "user_fk",
                    foreignKeyDefinition = "FOREIGN KEY (client_id) REFERENCES Client(id) ON DELETE CASCADE")
    )
    @Column(name = "roles", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<RoleType> roles = new HashSet<>();



}
