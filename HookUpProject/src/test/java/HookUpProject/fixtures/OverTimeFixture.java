package HookUpProject.fixtures;

import java.math.BigDecimal;

import fit.Fixture;
import HookUpProject.EmployeeStore;
import HookUpProject.Employee;
import HookUpProject.Payroll;
import HookUpProject.PayStub;

public class OverTimeFixture extends Fixture {

	public final static EmployeeStore employeeStore = new EmployeeStore();

	public String name;
	public double hourlyRate;
	public double hoursWorked;
	public double overtimeHoursWorked;

	public PayStub lastCreatedPayStub;

	public double straightTime() {

		createPayStubForEmployee();
		return lastCreatedPayStub.getBasePay().doubleValue();
	}

	public double overTime() {
		return lastCreatedPayStub.getOvertimePay().doubleValue();
	}

	public double grossPay() {
		return lastCreatedPayStub.getGrossPay().doubleValue();
	}

	private void createPayStubForEmployee() {
		final Employee employee = new Employee();
		employee.setName(name);
		employee.setHourlyRate(new BigDecimal(hourlyRate));
		employeeStore.save(employee);
		int dummyQuarter = 1;
        Payroll payroll = new Payroll(employee);
        payroll.createPayStub(employee.getHoursWorked(), dummyQuarter);
	}
}
