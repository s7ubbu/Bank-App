package bank.app.transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.app.transactions.requestVO.UserCreateRequestVO;
import bank.app.transactions.serviceI.BankServiceI;

@RestController
@RequestMapping("/bankapp")
public class BankAppController {
	
	@Autowired
	public BankServiceI service;
	
	@PostMapping("/accountcreate")
	public ResponseEntity<UserCreateRequestVO> userAccountCreation(@RequestBody UserCreateRequestVO requestVO ){
	
		UserCreateRequestVO response = service.createUserService(requestVO);
		ResponseEntity<UserCreateRequestVO> responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
		
		return  responseEntity;
	}
	
	@GetMapping("/getaccountNO")
	public ResponseEntity<Integer> geAccountNumber(@PathVariable String panNumber){
		Integer acNo = service.getAccountNumber(panNumber);
		
		ResponseEntity<Integer> responseEntity = new ResponseEntity(acNo, HttpStatus.OK);
		return responseEntity;
		
	}
	
	
	

}
