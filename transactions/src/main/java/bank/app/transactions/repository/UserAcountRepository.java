package bank.app.transactions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bank.app.transactions.dto.UserAccount;

@Repository
public interface UserAcountRepository extends JpaRepository<UserAccount, Integer>{
	public UserAccount save(UserAccount acc);
	public List<UserAccount> findAll();
	public UserAccount findByAccountNumber(Integer acNo);
	
}
