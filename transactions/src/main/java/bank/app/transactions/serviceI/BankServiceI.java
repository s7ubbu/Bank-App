package bank.app.transactions.serviceI;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import bank.app.transactions.requestVO.DepositeRequestVO;
import bank.app.transactions.requestVO.SetPasswordRequestVO;
import bank.app.transactions.requestVO.UserCreateRequestVO;

@Service
public interface BankServiceI {
	public UserCreateRequestVO createUserService(UserCreateRequestVO requestVO);
	public Integer getAccountNumber(String panNumber)throws RuntimeErrorException;
	public Integer setPassword(SetPasswordRequestVO request) throws Exception;
	public Integer depositAmount(DepositeRequestVO request) throws Exception;
	

}
