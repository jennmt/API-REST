package com.example.empleados.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/empleados")
public class EmpleadosController {
	
	@Autowired
	private EmpleadosRepository emepleadosRepository;
	
	@GetMapping("")
	List<Empleados> index(){
		return emepleadosRepository.findAll();
	}
	
	@PostMapping("")
	Empleados create(@RequestBody Empleados empleado) {
		return emepleadosRepository.save(empleado);
	}
	
	@PutMapping("/{id}")
	Empleados update(@PathVariable Long id, @RequestBody Empleados empleado) {
		Empleados empleadoFromDb = emepleadosRepository
				.findById(id)
				.orElseThrow(RuntimeException::new);
		
		empleadoFromDb.setNombre(empleado.getNombre());
		empleadoFromDb.setApellidoPaterno(empleado.getApellidoPaterno());
		empleadoFromDb.setApellidoMaterno(empleado.getApellidoMaterno());
		empleadoFromDb.setFechaNacimiento(empleado.getFechaNacimiento());


		return emepleadosRepository.save(empleadoFromDb);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Empleados empleado = emepleadosRepository
				.findById(id)
				.orElseThrow(RuntimeException::new);
		
		emepleadosRepository.delete(empleado);
	}

}



