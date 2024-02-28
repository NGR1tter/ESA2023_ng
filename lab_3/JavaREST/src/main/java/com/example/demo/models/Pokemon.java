package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NamedEntityGraph(
        name = "with-coach",
        attributeNodes = {
                @NamedAttributeNode(value = "coach")
        }
)
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@ToString
@Table(name = "pokemon")
public class Pokemon extends BaseId<Integer> {
    private String name;

    private Integer attack;

    private Integer life;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;
}