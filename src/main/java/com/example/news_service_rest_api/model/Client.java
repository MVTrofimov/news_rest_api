package com.example.news_service_rest_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<News> news = new ArrayList<>();



}
