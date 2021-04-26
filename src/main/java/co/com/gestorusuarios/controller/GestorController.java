package co.com.gestorusuarios.controller;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.com.gestorusuarios.model.Usuario;
import co.com.gestorusuarios.repository.IUsuarioRepo;

@RestController
public class GestorController {
		
		@Autowired
		private IUsuarioRepo usrRepo;
		
		@GetMapping("/listarUsuarios")
		public ResponseEntity<List<Usuario>> listarUsuarios(@RequestParam String nombre){
			return ResponseEntity.ok(usrRepo.findByNombreLike("%"+nombre+"%"));
		}
		
		@PostMapping("/crearUsuario")
		public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usr) {
			if(StringUtils.isAllBlank(usr.getNombre())) {
				return new ResponseEntity(("El campo Usuario no puede estar vacío"), HttpStatus.BAD_REQUEST);
			}
			if(StringUtils.isAllBlank(String.valueOf(usr.getActivo()))) {
				return new ResponseEntity(("El campo Activo no puede estar vacío"), HttpStatus.BAD_REQUEST);
			}
			if(usrRepo.existsByNombre(usr.getNombre())) {
				return new ResponseEntity(("El usuario ya existe"), HttpStatus.BAD_REQUEST);
			}
			usrRepo.save(usr);
			return new ResponseEntity(("Usuario creado"), HttpStatus.OK);
		}
		
		@PutMapping("/editarUsuario")
		public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usr) {
			
			if(StringUtils.isAllBlank(usr.getNombre())) {
				return new ResponseEntity(("El campo Usuario no puede estar vacío"), HttpStatus.BAD_REQUEST);
			}
			if(StringUtils.isAllBlank(String.valueOf(usr.getActivo()))) {
				return new ResponseEntity(("El campo Activo no puede estar vacío"), HttpStatus.BAD_REQUEST);
			}
			if(usrRepo.existsByNombre(usr.getNombre())) {
				usr.setNombre(usrRepo.findById(usr.getId()).getNombre());
			}
			if(!usrRepo.existsById(usr.getId())) {
				return new ResponseEntity(("El usuario no existe"), HttpStatus.BAD_REQUEST);
			}
			usrRepo.save(usr);
			return new ResponseEntity(("Usuario actualizado"), HttpStatus.OK);
		}
		
		@DeleteMapping("/eliminarUsuario")
		public ResponseEntity<Usuario> eliminar(@RequestParam int id) {
			if(!usrRepo.existsById(id)) {
				return new ResponseEntity(("El usuario no existe"), HttpStatus.BAD_REQUEST);
			}
			usrRepo.deleteById(id);
			return new ResponseEntity(("Usuario eliminado"), HttpStatus.OK);
		}

}
