package com.BankingApplication.service;

import java.util.List;

import com.BankingApplication.dto.AccountDto;

public interface AccountService {
	
	public AccountDto createAccount(AccountDto accountDto);
	
	public AccountDto getAccountById(long id);
	
	public AccountDto addBalance(long id,double amount);
	
	public AccountDto withdraw(long id, double amount);
	
	public List<AccountDto> getAllAccount();	
	
	public void deleteAccount(long id);

}
