package com.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @Size(min = 3, max = 50)
    private String nome;

    @NotNull
    @Column(unique = true)
    @Email(message = "E-mail inválido")
    private String email;

    @NotNull
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_permissao",
            joinColumns = @JoinColumn(name = "codigo_usuario"),
            inverseJoinColumns = @JoinColumn(name = "codigo_permissao")
    )
    private List<Permissao> permissoes;
}


