package com.analista.proyecto.Model;

import java.util.ArrayList;

import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "visitas")
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    private String dni;

    private String domicilio;

    private String imagen;

    private String edad;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String fechaNacimiento;

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL)
    private List<FechaVisita> fechasVisitas = new ArrayList<>();

    @Column(name = "activo", columnDefinition = "boolean default 1")
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentesco_id", referencedColumnName = "id")
    private Parentesco parentesco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id", referencedColumnName = "id")
    private Genero genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoVisita_id", referencedColumnName = "id")
    private TipoVisita tipoVisita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interno_id", referencedColumnName = "id")
    private Interno interno;

    public void addFechaVisita(FechaVisita nuevaFecha) {
        if (this.fechasVisitas == null) {
            this.fechasVisitas = new ArrayList<>();
        }
        this.fechasVisitas.add(nuevaFecha);
        nuevaFecha.setVisita(this);
    }

    public Visita() {
        activo = true;
    }

}
