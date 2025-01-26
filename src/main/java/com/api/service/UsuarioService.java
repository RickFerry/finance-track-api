package com.api.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.model.Usuario;
import com.api.model.UsuarioSistema;
import com.api.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Page<Usuario> listarUsuarios(Pageable page) {
        return usuarioRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Long id) {
        return getUser(id);
    }

    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario user = getUser(id);
        BeanUtils.copyProperties(usuario, user, "codigo");
        return usuarioRepository.save(user);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        usuarioRepository.delete(getUser(id));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new UsuarioSistema(usuario, getPermissoes(usuario));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        usuario.getPermissoes().forEach(
                permissao -> authorities.add(new SimpleGrantedAuthority(permissao.getDescricao().toUpperCase())));
        return authorities;
    }

    private Usuario getUser(Long id) {
        Usuario user = usuarioRepository.findOne(id);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return user;
    }
}
