package HookUpProject.fixtures;

import java.math.BigDecimal;

import HookUpProject.EmployeeStore;
import HookUpProject.Employee;
import fit.ColumnFixture;

public class MaintainEmployeeDataFixture extends ColumnFixture {

	private final static EmployeeStore employeeStore = new EmployeeStore();

	public String name;
	public double hourlyRate;
	public String homeAddress;
	public String socialSecurityNumber;

	public Long identificationNumber;

	public boolean store() {
		Employee employee = new Employee();
		employee.setName(name);
		employee.setHourlyRate(new BigDecimal(hourlyRate));
		employee.setHomeAddress(homeAddress);
		employee.setSocialSecurityNumber(socialSecurityNumber);
		employeeStore.save(employee);
		return true;
	}

	public double hourlyRate() {
		return employeeStore.findById(identificationNumber).getHourlyRate().doubleValue();
	}
	
	public String homeAddress() {
		return employeeStore.findById(identificationNumber).getHomeAddress();
	}

	public String name() {
		return employeeStore.findById(identificationNumber).getName();
	}

	public String socialSecurityNumber() {
		return employeeStore.findById(identificationNumber).getSocialSecurityNumber();
	}
}
