package com.BankingApplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.entity.Account;
import com.BankingApplication.mapper.AccountMapper;
import com.BankingApplication.repository.AccountRepository;
import com.BankingApplication.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {

		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto);
		
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	
	@Override
	public AccountDto getAccountById(long id) {
		Account account = accountRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Account not found"));

		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto addBalance(long id, double amount) {
		
		Account account = accountRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Account not found"));

		double newAmount =account.getBalance() + amount;
		account.setBalance(newAmount);
		
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(long id, double amount) {
		Account account = accountRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Account not found"));

		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		double newBalance=account.getBalance()-amount;
		account.setBalance(newBalance);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccount() {
		
		return   accountRepository.findAll()
	            .stream()
	            .map(account -> AccountMapper.mapToAccountDto(account))
	            .collect(Collectors.toList());
	}


	@Override
	public void deleteAccount(long id) {
		Account account = accountRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Account not found"));

		
		accountRepository.delete(account);
		
	}

}
