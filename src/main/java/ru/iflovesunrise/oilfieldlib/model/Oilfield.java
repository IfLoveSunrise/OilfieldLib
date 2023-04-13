package ru.iflovesunrise.oilfieldlib.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oilfield")
public class Oilfield {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    private String name;

    @Column(name = "foundation_date", columnDefinition = "DATE")
    private LocalDate foundationDate;

    @Column(name = "extent_of_production")
    private long extentOfProduction;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('PLANNED', 'COMMISSIONED', 'DECOMMISSIONED')")
    private OilfieldStage stage;

    @Column(name = "last_update_time", columnDefinition = "DATETIME")
    private LocalDateTime lastUpdateTime;

    @OneToMany(mappedBy = "oilfield", cascade = CascadeType.ALL)
    private List<OilWell> oilWells;
}
