package HookUpProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class PayrollTest {

    private PayStub createPayStub(final double payrollHourlyRate, final double payrollHoursWorked) {
		return createPayStub(payrollHourlyRate, payrollHoursWorked, 0);
	}

	private PayStub createPayStub(final double payrollHourlyRate, final double payrollHoursWorked, double payRollHoursOvertime) {
        final Employee employee = new Employee();
        employee.setHourlyRate(new BigDecimal(payrollHourlyRate));
        final Payroll payroll = new Payroll(employee);
		final int dummyQuarter = 1;
        final PayStub payStub = payroll.createPayStub(new BigDecimal(payrollHoursWorked), dummyQuarter, new BigDecimal(payRollHoursOvertime));
        return payStub;
    }

    @Test
    public void testZeroHoursWorked() throws Exception {
        final PayStub payStub = createPayStub(30, 0);
        final BigDecimal basePay = payStub.getBasePay();
        assertEquals(0, basePay.longValue());
    }

    @Test
    public void testBrokenNumbers() throws Exception {
        final PayStub payStub = createPayStub(1.5, 2.5);
        final BigDecimal basePay = payStub.getBasePay();
        assertEquals(new BigDecimal(3.75), basePay);
    }

    @Test
    public void testGrossPay() throws Exception {
        final PayStub payStub = createPayStub(1.5, 2.5);
        final BigDecimal grossPay = payStub.getGrossPay();
        assertEquals(new BigDecimal(3.75), grossPay);
    }

    @Test
    public void testFederalIncomeTax() throws Exception {
        final PayStub payStub = createPayStub(1.5, 2.5);
        final BigDecimal federalIncomeTax = payStub.getFederalIncomeTax();
        final BigDecimal expected = new BigDecimal("0.94");
        assertEquals(expected, federalIncomeTax);
    }

    @Test
    public void testNetPay() throws Exception {
        final PayStub payStub = createPayStub(1.5, 2.5);
        final BigDecimal netPay = payStub.getNetPay();
        final BigDecimal expected = new BigDecimal("2.81");
        assertEquals(expected, netPay);
    }

    @Test
    public void testPayStubIsAddedToEmployee() {
        final Employee employee = new Employee();
        employee.setHourlyRate(new BigDecimal(1.5));
        final Payroll payroll = new Payroll(employee);
		final int dummyQuarter = 1;
        final PayStub payStub = payroll.createPayStub(new BigDecimal(2.5), dummyQuarter);
        assertTrue(employee.getPayStubsInThisYear().contains(payStub));
    }

    @Test
    public void testGetYearTotalOfBasePayZero() throws Exception {
        final Employee employee = new Employee();
        employee.setHourlyRate(new BigDecimal(100.0));
        final Payroll payroll = new Payroll(employee);
        assertEquals(new BigDecimal("0.00"), payroll.calculateYearlyGrossPay());
    }

    @Test
    public void testGetQuarterlyTotalOfBasePayZero() throws Exception {
        final Employee employee = new Employee();
        employee.setHourlyRate(new BigDecimal(100.0));
        final Payroll payroll = new Payroll(employee);
        final int dummyQuarter = 1;
        assertEquals(new BigDecimal("0.00"), payroll.calculateQuartelyGrossPay(dummyQuarter));
    }

    @Test
    public void testGetYearTotalOfGrossPayWithOneStub() throws Exception {
        final Employee employee = new Employee();
        employee.setHourlyRate(new BigDecimal(100.0));
        final Payroll payroll = new Payroll(employee);
        final int dummyQuarter = 1;
        final PayStub payStub = payroll.createPayStub(new BigDecimal(4), dummyQuarter);

        assertEquals(400, payStub.getYearlyGrossPaySum().doubleValue(), 0);
    }

    @Test
    public void testGetYearTotalOfGrossPayWithMultipleStubs() throws Exception {
        final Employee employee = new Employee();
        employee.setHourlyRate(new BigDecimal(100.0));
        final Payroll payroll = new Payroll(employee);

        final int quarter1 = 1;
        final int quarter2 = 2;
        final PayStub payStub1 = payroll.createPayStub(new BigDecimal(4), quarter1);
        final PayStub payStub2 = payroll.createPayStub(new BigDecimal(4), quarter1);
        final PayStub payStub3 = payroll.createPayStub(new BigDecimal(4), quarter2);

        assertEquals(400, payStub1.getYearlyGrossPaySum().doubleValue(), 0);
        assertEquals(800, payStub2.getYearlyGrossPaySum().doubleValue(), 0);
        assertEquals(1200, payStub3.getYearlyGrossPaySum().doubleValue(), 0);
    }

    @Test
    public void testGetOvertimePayZero() throws Exception {
        int payrollHoursOvertime1 = 0;
		final PayStub payStub = createPayStub(1.5, 2.5, payrollHoursOvertime1);
        final BigDecimal overtimePay = payStub.getOvertimePay();
        final BigDecimal expected = new BigDecimal("0.00");
        assertEquals(expected, overtimePay);
    }

    @Test
    public void testGetOvertimePayNonZero() throws Exception {
    	double payrollHourlyRate = 1.5;
		double payrollHoursWorked = 2.5;
		int payrollHoursOvertime = 10;
		final PayStub payStub = createPayStub(payrollHourlyRate, payrollHoursWorked, payrollHoursOvertime);
        final BigDecimal overtimePay = payStub.getOvertimePay();
        final BigDecimal expected = new BigDecimal("22.50");
        assertEquals(expected, overtimePay);
    }

    @Test
    public void testGetQuarterlyTotalOfBasePay() throws Exception {
        final Employee employee = new Employee();
        employee.setHourlyRate(new BigDecimal(100.0));
        final Payroll payroll = new Payroll(employee);

        final int quarter1 = 1;
        final int quarter2 = 2;
        final PayStub payStub1 = payroll.createPayStub(new BigDecimal(4), quarter1);
        final PayStub payStub2 = payroll.createPayStub(new BigDecimal(4), quarter1);
        final PayStub payStub3 = payroll.createPayStub(new BigDecimal(4), quarter2);

        assertEquals(400, payStub1.getQuarterlyGrossPaySum().doubleValue(), 0);
        assertEquals(800, payStub2.getQuarterlyGrossPaySum().doubleValue(), 0);
        assertEquals(400, payStub3.getQuarterlyGrossPaySum().doubleValue(), 0);
    }

}
