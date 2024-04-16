package com.store.dominguez.model;


import com.store.dominguez.model.base.BaseEntity;
import com.store.dominguez.model.enums.TipoSexo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@SuperBuilder
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "EmpleadoEntity")
@Table(name = "empleado")
public class EmpleadoEntity  extends BaseEntity implements UserDetails {

    @Id
    @Column(name="id_cliente", nullable = false, unique = true,length = 30)
    private String id;

    @Column(name="nombres", nullable = false, length = 50)
    private String nombres;

    @Column(name="apellidos", nullable = false, length = 50)
    private String apellidos;

    @Column(name="direccion ", nullable = false, length = 100)
    private String direccion;

    @Column(name="email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name="contraseña", nullable = false, length = 120)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rol;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento_identidad")
    private TipoDocumentoIdentidadEntity tipoDocumento;

    @Column(name="numero_documento", nullable = false, length = 11, unique = true)
    private String numeroDocumento;

    @Enumerated(EnumType.STRING)
    private TipoSexo sexo;

    @Column(name="telefono", length = 9, unique = true)
    private String telefono;

    @Column(name="estado", nullable = false)
    private boolean estado = true;



    @Override
    public String toString() {
        return "EmpleadoEntity{" +
                "id='" + id + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", sexo=" + sexo +
                ", telefono='" + telefono + '\'' +
                ", estado=" + estado +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.getNombre()));
    }

    @Override
    public String getUsername() {
        return nombres + apellidos;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
