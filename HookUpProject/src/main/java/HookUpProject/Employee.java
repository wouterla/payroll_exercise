package HookUpProject;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Employee {

    private Long id;
    private String name;
    private BigDecimal hourlyRate;
    private String homeAddress;
    private String bankAccountNumber;
    private String bankRoutingNumber;
    private String payType;
	private String socialSecurityNumber;
	
	private List<PayStub> payStubsInThisYear = new ArrayList<PayStub>();

    public Long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(final BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(final String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(final String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankRoutingNumber() {
        return bankRoutingNumber;
    }

    public void setBankRoutingNumber(final String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(final String payType) {
        this.payType = payType;
    }

    public PayStub getLastPayStub() {
    	List<PayStub> payStubs = getPayStubsInThisYear();
    	return payStubs.get(payStubs.size() - 1);
    }
    
    public BigDecimal getHoursWorked() {
    	return getLastPayStub().getHoursWorked();
    }
    
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public void addPayStubInThisYear(PayStub payStub) {
		this.payStubsInThisYear.add(payStub);
	}
	
	public List<PayStub> getPayStubsInThisYear() {
		return payStubsInThisYear;
	}

	public List<PayStub> getPayStubsInQuarter(int quarter) {
		List<PayStub> payStubsInThisQuarter = new ArrayList<PayStub>();
		for (PayStub payStub : payStubsInThisYear) {
			if (payStub.getQuarter() == quarter) {
				payStubsInThisQuarter.add(payStub);
			}
		}

		return payStubsInThisQuarter;
	}

	public void removePayStubsInYear() {
		payStubsInThisYear.clear();
	}
}
