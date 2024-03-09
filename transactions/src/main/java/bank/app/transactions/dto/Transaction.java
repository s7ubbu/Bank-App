package bank.app.transactions.dto;

import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
	@Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "transactionId")
	private String transactionId;
	private String ifsc;
	private Integer accountNumber;
	private String accountType;
	private double diposit;
	private double withdraws;
	private double avilablebalance;
	private Date lastUpdated;
	private Date createOn;
	private String modifiedBy;
	
	
	
}
