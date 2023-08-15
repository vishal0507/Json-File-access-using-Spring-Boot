package YamlApplication;

public class CreditCustomerModal {
	private String CustomerId;
	private String InstitutionId;
	private String CardProxyNumber;
	private String CardHolderName;
	private String CVV;
	private String ExpDate;
	
	public CreditCustomerModal(String customerId, String institutionId, String cardProxyNumber, String cardHolderName,
			String cVV, String expDate) {
		super();
		CustomerId = customerId;
		InstitutionId = institutionId;
		CardProxyNumber = cardProxyNumber;
		CardHolderName = cardHolderName;
		CVV = cVV;
		ExpDate = expDate;
	}
	public CreditCustomerModal() {
		// TODO Auto-generated constructor stub
	}
	public String getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	public String getInstitutionId() {
		return InstitutionId;
	}
	public void setInstitutionId(String institutionId) {
		InstitutionId = institutionId;
	}
	public String getCardProxyNumber() {
		return CardProxyNumber;
	}
	public void setCardProxyNumber(String cardProxyNumber) {
		CardProxyNumber = cardProxyNumber;
	}
	public String getCardHolderName() {
		return CardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		CardHolderName = cardHolderName;
	}
	public String getCVV() {
		return CVV;
	}
	public void setCVV(String cVV) {
		CVV = cVV;
	}
	public String getExpDate() {
		return ExpDate;
	}
	public void setExpDate(String expDate) {
		ExpDate = expDate;
	}

	
}
