package co.com.gestorusuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.gestorusuarios.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{

	List<Usuario> findByNombreLike(String string);
	Usuario findById(int id);
	boolean existsByNombre(String nombre);
	
	
}
