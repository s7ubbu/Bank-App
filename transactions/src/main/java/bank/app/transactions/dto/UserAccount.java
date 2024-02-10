package bank.app.transactions.dto;

import jakarta.persistence.Id;
import lombok.Data;

@jakarta.persistence.Entity
@Data
public class UserAccount {
	@Id
	@jakarta.persistence.GeneratedValue
	private Integer accountNumber;
	private String userName;
	private String adharNo;
	private String panNo;
	private Long phoneNo;
	private String address;
	private String acType;
	private String password;
	

}
