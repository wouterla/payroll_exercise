package HookUpProject.fixtures;

import java.math.BigDecimal;

import HookUpProject.Employee;
import HookUpProject.EmployeeStore;
import HookUpProject.PayStub;
import HookUpProject.Payroll;
import fit.ColumnFixture;

public class QuarterFixture extends ColumnFixture {

    public int quarter;
    public final static EmployeeStore employeeStore = new EmployeeStore();
    public Long id;
    public String name;
    public double hourlyRate;
    public double hoursWorked;
    public String homeAddress;
    public String socialSecurityNumber;

	public PayStub lastCreatedPayStub;

	private static int currentQuarter;

	public boolean initializePayrollSystem() {
		currentQuarter = quarter;
		return true;
    }

    public boolean storeEmployee() {
        final Employee employee = new Employee();
        employee.setName(name);
        employee.setHourlyRate(new BigDecimal(hourlyRate));
        employee.setHomeAddress(homeAddress);
        employee.setSocialSecurityNumber(socialSecurityNumber);
        employeeStore.save(employee);
        
        createPayStubForEmployee(employee);
        
        resetFields();
        return true;
    }


    public boolean hasEmployee() {
        return employeeStore.findById(id) != null;
    }

    public double grossPay() {
        final Payroll payroll = new Payroll(findEmployee());
		BigDecimal grossPay = payroll.calculateGrossPay(new BigDecimal(hoursWorked));
		return grossPay.doubleValue();
    }

    public boolean doPayment() {
		final Payroll payroll = new Payroll(findEmployee());
		lastCreatedPayStub = payroll.createPayStub(new BigDecimal(hoursWorked), currentQuarter);
        return true;
    }

    public double quarterlyGrossSum() {
		return lastCreatedPayStub.getQuarterlyGrossPaySum().doubleValue();
    }

	public double yearlyGrossSum() {
		return lastCreatedPayStub.getYearlyGrossPaySum().doubleValue();
	}

    public int nextQuarter() {
        currentQuarter++;
        if (currentQuarter > 4) {
			for (Employee employee : employeeStore.findAll()) {
				employee.removePayStubsInYear();
			}
			currentQuarter = 1;
        }
        return currentQuarter;
    }

    private Employee findEmployee() {
        return employeeStore.findById(id);
    }

    private void resetFields() {
        name = "";
        hourlyRate = 0;
        hoursWorked = 0;
        homeAddress = "";
        socialSecurityNumber = "";
    }

	private void createPayStubForEmployee(final Employee employee) {
		int dummyQuarter = 1;
        Payroll payroll = new Payroll(employee);
        payroll.createPayStub(employee.getHoursWorked(), dummyQuarter);
	}
}
