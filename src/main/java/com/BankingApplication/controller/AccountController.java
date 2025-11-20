package com.BankingApplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
		
		AccountDto createAccount = accountService.createAccount(accountDto);
		
		return ResponseEntity.ok(createAccount);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getById(@PathVariable long id) {
		
		AccountDto accountById = accountService.getAccountById(id);
		return ResponseEntity.ok(accountById);
	}
	
	@PutMapping("/deposite/{id}")
	public ResponseEntity<AccountDto> addBalance(@PathVariable long id,@RequestBody Map<String, Double> request) {
		
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.addBalance(id, amount);
		
		return ResponseEntity.ok(accountDto);
	}
	
	
	@PutMapping("/withdraw/{id}")
	public ResponseEntity<AccountDto> withdrawBalance(@PathVariable long id,@RequestBody Map<String, Double> request) {
		
		Double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		
		return ResponseEntity.ok(accountDto);
	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccount() {
		
		List<AccountDto> allAccount = accountService.getAllAccount();
		
		return ResponseEntity.ok(allAccount);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable long id) {
		
		accountService.deleteAccount(id);
		
		return ResponseEntity.ok("Account Deleted Successfully");
	}
	
	
}
