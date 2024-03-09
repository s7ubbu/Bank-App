package bank.app.transactions.serviceimpl;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.app.transactions.dto.Transaction;
import bank.app.transactions.dto.UserAccount;
import bank.app.transactions.repository.TransactionRepository;
import bank.app.transactions.repository.UserAcountRepository;
import bank.app.transactions.requestVO.DepositeRequestVO;
import bank.app.transactions.requestVO.SetPasswordRequestVO;
import bank.app.transactions.requestVO.UserCreateRequestVO;
import bank.app.transactions.serviceI.BankServiceI;

@Service
public class BankServiceImpl implements BankServiceI{
	
	@Autowired
	private UserAcountRepository userAcountRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public UserCreateRequestVO createUserService(UserCreateRequestVO requestVO) {
		
		UserAccount userAccount = new UserAccount();
		BeanUtils.copyProperties(requestVO, userAccount);
		UserAccount responseUserAccount = userAcountRepository.save(userAccount);
		UserCreateRequestVO responseRequestVO = new UserCreateRequestVO();
		BeanUtils.copyProperties(responseUserAccount, responseRequestVO);
		return responseRequestVO;
	}
	@Override
	public Integer getAccountNumber(String panNumber) throws RuntimeErrorException {
		List<UserAccount> userList = userAcountRepository.findAll();
		boolean status = false;
		Integer accNo = 0;
		for(UserAccount us: userList) {
			if(us.getPanNo().equalsIgnoreCase(panNumber)) {
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

	@Override
	public Integer setPassword(SetPasswordRequestVO request) throws Exception{
		UserAccount userAccount = userAcountRepository.findByAccountNumber(Integer.parseInt(request.getAcctountNumber()));
		userAccount.setPassword(request.getPassword());
		UserAccount responseUserAccount = userAcountRepository.save(userAccount);
		if(responseUserAccount!= null) {
			return 1;
		}
		else {
			return 0;
		}

		
	}
	@Override
	public Integer depositAmount(DepositeRequestVO request) throws Exception{
		Transaction reqTransaction = new Transaction();
		List<Transaction> restrasactionList = transactionRepository.findByAccountNumber(Integer.parseInt(request.getAccountNumber()));
		if(restrasactionList.isEmpty() ) {
			reqTransaction.setAvilablebalance(reqTransaction.getAvilablebalance()+ request.getDeposit());
			reqTransaction.setCreateOn(new Date(System.currentTimeMillis()));
		}
		else {
			Transaction filterResult=restrasactionList.stream().max(Comparator.comparing(Transaction:: getLastUpdated)).get();
			reqTransaction.setAvilablebalance(filterResult.getAvilablebalance()+ request.getDeposit());
		}
		reqTransaction.setAccountNumber(Integer.parseInt(request.getAccountNumber()));
		reqTransaction.setDiposit(request.getDeposit());
		reqTransaction.setIfsc(request.getIfsc());
		reqTransaction.setLastUpdated(new Date(System.currentTimeMillis()));
		reqTransaction.setModifiedBy(request.getUserName());
		reqTransaction.setAccountType(request.getAccountType());
		Transaction transactionResponse = transactionRepository.save(reqTransaction);
		if(transactionResponse != null) {
			return 1;
		}
		else {
			return 0;
		}
	
	}
	
}
