package bank.app.transactions.requestVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestVO {
	
	
	private String userName;
	private String adharNo;
	private String panNo;
	private Long phoneNo;
	private String address;
	private String acType;

	
	
	

}
