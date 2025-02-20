package com.ar.bankingonline.api.controllers;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ar.bankingonline.api.dtos.UserDto;
import com.ar.bankingonline.application.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	
	private UserService service;
	
	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}
	
	// metodos HTTP
	
	// GET
	@GetMapping(value = "/users")
	public ResponseEntity<List<UserDto>> getUsers() {

	        // 1) Obtener la lista de todos los DTO user de la DB
	        // Agregar el servicio a la implementación del método del controlador
	        List<UserDto> usuarios = service.getUsers();

	        // 2) Devolver la lista y enviar como respuesta
	        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	    
	}
	
	//GET USER
	@GetMapping(value= "/users/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(id));
	}
	
	//POST
	@PostMapping(value = "/users")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(dto));
	}
	
	//PUT
	@PutMapping(value = "/users/{id}") 
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, user));
	}
	
	//DELETE
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
	}
	
}
