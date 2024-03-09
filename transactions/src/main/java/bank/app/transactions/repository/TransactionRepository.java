package bank.app.transactions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bank.app.transactions.dto.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{
	public Transaction save(Transaction tsc);
	public List<Transaction> findByAccountNumber(Integer acno);
	
	

}
