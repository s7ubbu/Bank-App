package bank.app.transactions.dto;


import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	private String bankName;
	private String ifsc;
	private Date createdOn;
	private Date modifiedOn;
	private String modifiedBy;
	
	
}
