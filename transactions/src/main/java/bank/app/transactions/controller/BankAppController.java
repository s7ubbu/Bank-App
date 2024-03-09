package bank.app.transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.app.transactions.requestVO.DepositeRequestVO;
import bank.app.transactions.requestVO.SetPasswordRequestVO;
import bank.app.transactions.requestVO.UserCreateRequestVO;
import bank.app.transactions.serviceI.BankServiceI;
import bank.app.transactions.serviceimpl.BankServiceImpl;

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
	
	@GetMapping("/getaccountNO/{panNumber}")
	public ResponseEntity<Integer> geAccountNumber(@PathVariable String panNumber){
		Integer acNo =null;
		try {
			acNo = service.getAccountNumber(panNumber);
		}
		catch (Exception e) {
			return new ResponseEntity("account Number is not found in this pannumber ", HttpStatus.BAD_REQUEST);
			
		}
		ResponseEntity<Integer> responseEntity = new ResponseEntity(acNo, HttpStatus.OK);
		
		return responseEntity;
		
	}
	
	@PutMapping("/setpassword")
	public ResponseEntity<String> setPassword(@RequestBody  SetPasswordRequestVO request) throws Exception{
		
		if(!request.getPassword().equals(request.getConfirmPassword())) {
			return new ResponseEntity("password not matching with confirm password, Please correct", HttpStatus.BAD_REQUEST);
		}
		
		else {
			Integer resultCount =service.setPassword(request);
			if(resultCount>0) {
				return new ResponseEntity("User account "+request.getAcctountNumber()+" Successfully updated password", HttpStatus.OK);
			}
			else {
				return new ResponseEntity("Not able  update account password please try later", HttpStatus.BAD_REQUEST);

			}
		}
		
	}
	
	@PostMapping("/deposit")
	public ResponseEntity<String> depositAmount(@RequestBody DepositeRequestVO request) throws Exception{
		
		if(request.getAccountNumber() == null|| request.getAccountNumber().isBlank()) {
			throw new RuntimeException("accountNumber is missing it is required and  must be valid account");
		}
		if(request.getIfsc() == null|| request.getIfsc().isBlank()) {
			throw new RuntimeException("Ifsc is missing it is required and  must be valid Data");
		}
		

		if(request.getUserName() == null|| request.getUserName().isBlank()) {
			throw new RuntimeException("UserName is missing it is required and  must be valid Data");
		}
		if(request.getBankName() == null|| request.getBankName().isBlank()) {
			throw new RuntimeException("BankName is missing it is required and  must be valid Data");
		}
		if(request.getDeposit()<1) {
			throw new RuntimeException("deposit amount must be more than 1 rs");
		}
		
		Integer resultCount =service.depositAmount(request);
		
		if(resultCount>0) {
			return new ResponseEntity("Successfully deposite amount to "+ request.getUserName()+ " user", HttpStatus.OK);

		}
		else {
			throw new RuntimeException("not able to deposite amount please try later");
		}
	}
	
	
	
	

}
