package bank.app.transactions.requestVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetPasswordRequestVO {

	private String acctountNumber;
	private String password;
	private String confirmPassword;
	
	
}
