package com.eventstoday.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title", nullable = false, length = 50)
    private String title;
    @Column(name="author", nullable = true, length = 50)
    private String author;
    @Column(name="description", nullable = true, length = 50)
    private String description;
    @Column(name="price", nullable = false)
    private Integer  price;
    @Column(name="start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name="end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name="url_Img", nullable = false, length = 50)
    private String urlImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer ownerId;


}
