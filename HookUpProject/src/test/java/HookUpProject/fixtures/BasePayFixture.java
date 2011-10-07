package HookUpProject.fixtures;

import java.math.BigDecimal;

import HookUpProject.Employee;
import HookUpProject.PayStub;
import HookUpProject.Payroll;
import fit.ColumnFixture;

public class BasePayFixture extends ColumnFixture {
    public String name;
    public double hourlyRate;
    public double hoursWorked;

    public double basePay() {
        return getPayStub().getBasePay().doubleValue();
    }

    public double grossPay() {
        return getPayStub().getGrossPay().doubleValue();
    }

    public double federalIncomeTax() {
        return getPayStub().getFederalIncomeTax().doubleValue();
    }

    public double netPay() {
        return getPayStub().getNetPay().doubleValue();
    }

    private PayStub getPayStub() {
        final int dummyQuarter = 1;
        final Employee employee = new Employee();
        employee.setHourlyRate(new BigDecimal(hourlyRate));
        final Payroll payroll = new Payroll(employee);
        return payroll.createPayStub(new BigDecimal(hoursWorked), dummyQuarter);
    }
}
