package bank.app.transactions.serviceimpl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.app.transactions.dto.UserAccount;
import bank.app.transactions.repository.BankRepository;
import bank.app.transactions.requestVO.UserCreateRequestVO;
import bank.app.transactions.serviceI.BankServiceI;

@Service
public class BankServiceImpl implements BankServiceI{
	
	@Autowired
	private BankRepository repository;
	@Override
	public UserCreateRequestVO createUserService(UserCreateRequestVO requestVO) {
		
		UserAccount userAccount = new UserAccount();
		BeanUtils.copyProperties(requestVO, userAccount);
		UserAccount responseUserAccount = repository.save(userAccount);
		UserCreateRequestVO responseRequestVO = new UserCreateRequestVO();
		BeanUtils.copyProperties(responseUserAccount, responseRequestVO);
		return responseRequestVO;
	}
	@Override
	public Integer getAccountNumber(String panNumber) throws RuntimeErrorException {
		List<UserAccount> userList = repository.findAll();
		boolean status = false;
		Integer accNo = 0;
		for(UserAccount us: userList) {
			if(us.getPanNo().equals(panNumber)) {
				status = true;
				accNo =us.getAccountNumber();
				break;
			}
		}
		if(status == false) {
			throw new RuntimeErrorException(null, " panNumber is not found please try with correct pan number");
		}
		return accNo; //repository.getaccountNumber(panNumber);
	}

	
	
}
