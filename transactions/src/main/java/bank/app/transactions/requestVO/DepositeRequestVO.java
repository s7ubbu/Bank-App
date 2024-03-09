package bank.app.transactions.requestVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositeRequestVO {

	private String accountNumber;
	private String bankName;
	private String ifsc;
	private String userName;
	private double deposit;
	private String accountType;
	
	
}
