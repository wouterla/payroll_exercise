package HookUpProject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Payroll {

    public static final BigDecimal FEDERAL_INCOME_RATE = new BigDecimal(0.25);
    public static final BigDecimal OVERTIME_PAYMENT_FACTOR = new BigDecimal(1.50);
    private final Employee employee;

    public Payroll(final Employee employee) {
        this.employee = employee;
    }

    public PayStub createPayStub(final BigDecimal hoursWorked, final int quarter) {
    	return createPayStub(hoursWorked, quarter, new BigDecimal("0.00"));
    }

    public PayStub createPayStub(final BigDecimal hoursWorked, final int quarter, final BigDecimal hoursWorkedInOvertime) {
        final PayStub payStub = new PayStub();
        payStub.setHoursWorked(hoursWorked);
        payStub.setHoursWorkedInOvertime(hoursWorkedInOvertime);
        payStub.setQuarter(quarter);
        employee.addPayStubInThisYear(payStub);
        calculate(payStub);
        return payStub;
    }

    public BigDecimal calculateBasePay(final BigDecimal hoursWorked) {
        return currencyRound(employee.getHourlyRate().multiply(hoursWorked));
    }

    public BigDecimal calculateOvertimePay(final BigDecimal hoursWorkedInOvertime) {
        BigDecimal hourlyOvertimeRate = employee.getHourlyRate().multiply(OVERTIME_PAYMENT_FACTOR);
		return currencyRound(hourlyOvertimeRate.multiply(hoursWorkedInOvertime));
    }

    public BigDecimal calculateGrossPay(final BigDecimal hoursWorked) {
        return calculateBasePay(hoursWorked);
    }

    public BigDecimal calculateFederalIncomeTax(final BigDecimal grossPay) {
        return currencyRound(grossPay.multiply(FEDERAL_INCOME_RATE));
    }

    public BigDecimal calculateNetPay(final BigDecimal grossPay, final BigDecimal federalIncomeTax) {
        return grossPay.subtract(federalIncomeTax);
    }

    private BigDecimal currencyRound(final BigDecimal valueToRound) {
        return valueToRound.setScale(2, RoundingMode.HALF_UP);
    }

	public BigDecimal calculateQuartelyGrossPay(final int quarter) {
		return currencyRound(calculateGrossPaySum(employee.getPayStubsInQuarter(quarter)));
	}

	public BigDecimal calculateYearlyGrossPay() {
		return currencyRound(calculateGrossPaySum(employee.getPayStubsInThisYear()));
	}


	private void calculate(final PayStub payStub) {
        final BigDecimal hoursWorked = payStub.getHoursWorked();
        payStub.setBasePay(calculateBasePay(hoursWorked));
        final BigDecimal hoursWorkedInOvertime = payStub.getHoursWorkedInOvertime();
        payStub.setOvertimePay(calculateOvertimePay(hoursWorkedInOvertime));
        final BigDecimal grossPay = calculateGrossPay(hoursWorked);
        payStub.setGrossPay(grossPay);
        final BigDecimal federalIncomeTax = calculateFederalIncomeTax(grossPay);
        payStub.setFederalIncomeTax(federalIncomeTax);
        payStub.setNetPay(calculateNetPay(grossPay, federalIncomeTax));

        payStub.setYearlyGrossPaySum(calculateYearlyGrossPay());
        payStub.setQuarterlyGrossPaySum(calculateQuartelyGrossPay(payStub.getQuarter()));
    }

    private BigDecimal calculateGrossPaySum(final List<PayStub> payStubs) {
        BigDecimal sum = new BigDecimal(0);
        for (final PayStub payStub: payStubs) {
            sum = sum.add(payStub.getGrossPay());
        }
        return sum;
    }
}
