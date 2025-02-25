package com.enigma.loanapp.entity;

import com.enigma.loanapp.util.enums.EInstalmentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mst_instalment_type")
public class InstalmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "instalment_type", nullable = false, unique = true)
    private EInstalmentType instalmentType;
}
