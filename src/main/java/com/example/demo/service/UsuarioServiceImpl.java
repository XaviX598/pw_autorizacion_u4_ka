package com.example.demo.service;

//debemos declarar explicitamente ya que es estatico
import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.repository.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements UserDetailsService {// no vamos a crear una interfaz, vamos implementar una
																// definida por spring security

	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = this.iUsuarioRepository.consultarUsuarioPorUsername(username);
		// crearmos un usuario de import
		// org.springframework.security.core.userdetails.User;
//recibe 3 parametros, el primero es el nombre del usuario, luego el password, y el tercero es una lista de roles que tiene ese usuario
		return new User(usuario.getUsername(), usuario.getPassword(), emptyList());
	}

}
