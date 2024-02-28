package com.example.demo.jms.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
public class WatchDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String object;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    public WatchDog(String object, @NonNull EventType eventType) {
        this.object = object;
        this.eventType = eventType;
    }
}
