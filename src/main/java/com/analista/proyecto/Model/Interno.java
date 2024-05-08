package com.analista.proyecto.Model;

import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "internos")
public class Interno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreApellido;

    @Column(name = "dni", length = 8, unique = true)
    private String dni;

    @Column(name = "activo", columnDefinition = "boolean default 1")
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id", referencedColumnName = "id")
    private Modulo modulo;

    @OneToMany(mappedBy = "interno", cascade = CascadeType.ALL)
    private List<Visita> visitas = new ArrayList<>();


    @OneToMany(mappedBy = "interno", cascade = CascadeType.ALL)
    private List<Elemento> elementos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motivo_id", referencedColumnName = "id")
    private Motivo motivo;
    
    public Interno() {
        activo = true;
    }

    public void addVisita(Visita nuevaVisita) {
        if (this.visitas == null) {
            this.visitas = new ArrayList<>();
        }
        this.visitas.add(nuevaVisita);
        nuevaVisita.setInterno(this);
    }

    public void addElemento(Elemento nuevoElemento) {
        if (this.elementos == null) {
            this.elementos = new ArrayList<>();
        }
        this.elementos.add(nuevoElemento);
        nuevoElemento.setInterno(this);
    }


    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo != null ? activo : false; 
    }
    
   

}
