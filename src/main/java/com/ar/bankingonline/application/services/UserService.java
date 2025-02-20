package com.ar.bankingonline.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.bankingonline.api.dtos.UserDto;
import com.ar.bankingonline.api.mappers.UserMapper;
import com.ar.bankingonline.domain.exceptions.AccountNotFoundException;
import com.ar.bankingonline.domain.models.Account;
import com.ar.bankingonline.domain.models.User;
import com.ar.bankingonline.infrastructure.repositories.AccountRepository;
import com.ar.bankingonline.infrastructure.repositories.UserRepository;



@Service
public class UserService {

	// DEclaro una instancia del repositorio con @Autowired y sin la anotacion
	@Autowired
	private UserRepository repository;
	
	@Autowired
    private AccountRepository accountRepository;
	
    public UserService(UserRepository repository,AccountRepository accountRepository){

        this.repository = repository;
        this.accountRepository=accountRepository;
    }
	
	// primero generar los metodos del CRUD
	
	public List<UserDto> getUsers(){
		List<User> users = repository.findAll();
		return users.stream()
				.map(UserMapper::userMapToDto)
				.toList();

	}
	
	//TODO: refactor
	public UserDto getUserById(Long id) {
		return UserMapper.userMapToDto(repository.findById(id).get());
	}
	
	public UserDto createUser(UserDto user) {
		return UserMapper.userMapToDto(repository.save(UserMapper.dtoToUser(user)));
	}
	
	
	public UserDto update(Long id, UserDto user){

        Optional<User> userCreated = repository.findById(id);

        if (userCreated.isPresent()){
            User entity = userCreated.get();

            User accountUpdated = UserMapper.dtoToUser(user);
            accountUpdated.setAccounts(entity.getAccounts());

                if (user.getIdAccounts() != null) { // Verifica que la lista de cuentas no sea null
                List <Account> accountList = accountRepository.findAllById(user.getIdAccounts());
                List<Account> accountListFilter= accountList.stream()
                		.filter(e->!entity.getAccounts()
                		.contains(e))
                		.toList();
                accountUpdated.getAccounts().addAll(accountListFilter);
                accountUpdated.setAccounts(accountList);
            }

            accountUpdated.setId(entity.getId());

            User saved = repository.save(accountUpdated);

            return UserMapper.userMapToDto(saved);
        } else {
            throw new AccountNotFoundException("User not found with id: " + id);
        }
        
	}    
	
	public String delete(Long id) {
		
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Se ha eliminado la cuenta correctamente";
        } else {
            return "No se ha eliminado la cuenta";
        }
    }
	
}
