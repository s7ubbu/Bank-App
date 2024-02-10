package bank.app.transactions.serviceI;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import bank.app.transactions.requestVO.UserCreateRequestVO;

@Service
public interface BankServiceI {
	public UserCreateRequestVO createUserService(UserCreateRequestVO requestVO);
	public Integer getAccountNumber(String panNumber)throws RuntimeErrorException;

}
