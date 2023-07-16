package com.ar.bankingonline.api.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountDto {

	private Long id;
	private BigDecimal amount;
	private UserDto owner;
	
}
