package com.example.news_service_rest_api.model;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nameOfNews;


    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryOfNews category;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "oneNews", cascade = CascadeType.ALL)
    private List<CommentToNews> comments = new ArrayList<>();


    private Integer amountOfComments = 0;

    @CreationTimestamp
    private Instant createAt;

    @UpdateTimestamp
    private Instant updateAt;

}
