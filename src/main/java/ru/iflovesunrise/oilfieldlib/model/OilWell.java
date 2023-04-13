package ru.iflovesunrise.oilfieldlib.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oil_well")
public class OilWell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int number;

    @Column(columnDefinition = "VARCHAR(255)")
    private String purpose;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Oilfield oilfield;

    @Column
    private long debit;

    @Column
    private boolean active;
}
