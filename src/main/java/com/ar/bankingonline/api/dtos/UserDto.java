package com.ar.bankingonline.api.dtos;


import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	
	public UserDto() {
		
	}
	
	private Long id;
	private String username;
	private String password;
	private List<Long> idAccounts;
	
	
}
