package HookUpProject.fixtures;

import java.math.BigDecimal;

import HookUpProject.Employee;
import HookUpProject.EmployeeStore;
import HookUpProject.Payroll;
import fit.ColumnFixture;

public class CheckOrEFTPaymentFixture extends ColumnFixture {

    private final static EmployeeStore employeeStore = new EmployeeStore();

    public Long id;
    public String name;
    public double hourlyRate;
    public double hoursWorked;
    public double overtimeHoursWorked;
    public double doubleTimeHoursWorked;
    public String homeAddress;
    public String bankAccountNumber;
    public String bankRoutingNumber;
    public String payType;

    private void resetFields() {
        name = "";
        hourlyRate = 0;
        hoursWorked = 0;
        overtimeHoursWorked = 0;
        doubleTimeHoursWorked = 0;
        homeAddress = "";
        bankAccountNumber = "";
        bankRoutingNumber = "";
        payType = "";
    }

    public boolean store() {
        final Employee employee = new Employee();
        employee.setName(name);
        employee.setHourlyRate(new BigDecimal(hourlyRate));
        employee.setHomeAddress(homeAddress);
        employee.setBankAccountNumber(bankAccountNumber);
        employee.setBankRoutingNumber(bankRoutingNumber);
        employee.setPayType(payType);
        employeeStore.save(employee);
        
        createPayStubForEmployee(employee);
        
        resetFields();
        return true;
    }

    public double hourlyRate() {
        return findEmployee().getHourlyRate().doubleValue();
    }

    public String homeAddress() {
        return findEmployee().getHomeAddress();
    }

    public String bankAccountNumber() {
        return findEmployee().getBankAccountNumber();
    }

    public String bankRoutingNumber() {
        return findEmployee().getBankRoutingNumber();
    }

    public String payType() {
        return findEmployee().getPayType();
    }

    public double grossPay() {
        final Employee employee = findEmployee();        
        return employee.getLastPayStub().getGrossPay().doubleValue();
    }

    public double hoursWorked() {
        return findEmployee().getHoursWorked().doubleValue();
    }

    private Employee findEmployee() {
		Employee employee;
        if (name == null) {
			employee = employeeStore.findById(id);
        } else {
        	employee = employeeStore.findByName(name);
		}
		return employee;
	}

    private void createPayStubForEmployee(final Employee employee) {
		int dummyQuarter = 1;
        Payroll payroll = new Payroll(employee);
        payroll.createPayStub(new BigDecimal(hoursWorked), dummyQuarter);
	}
}
