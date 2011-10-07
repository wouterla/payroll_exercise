package HookUpProject;

import java.math.BigDecimal;

public class PayStub {

    private BigDecimal basePay;
    private BigDecimal overtimePay = new BigDecimal("0.00");
    private BigDecimal grossPay;
    private BigDecimal federalIncomeTax;
    private BigDecimal netPay;
    private BigDecimal hoursWorked;
    private BigDecimal hoursWorkedInOvertime = new BigDecimal("0.00");
    private int quarter;

	private BigDecimal yearlyGrossPaySum;
	private BigDecimal quarterlyGrossPaySum;

    public BigDecimal getBasePay() {
        return basePay;
    }

    public void setBasePay(final BigDecimal basePay) {
        this.basePay = basePay;
    }

    public void setOvertimePay(final BigDecimal overtimePay) {
        this.overtimePay = overtimePay;
    }

    public BigDecimal getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(final BigDecimal grossPay) {
        this.grossPay = grossPay;
    }

    public BigDecimal getFederalIncomeTax() {
        return federalIncomeTax;
    }

    public void setFederalIncomeTax(final BigDecimal federalIncomeTax) {
        this.federalIncomeTax = federalIncomeTax;
    }

    public BigDecimal getNetPay() {
        return netPay;
    }

    public void setNetPay(final BigDecimal netPay) {
        this.netPay = netPay;
    }

	public BigDecimal getHoursWorked() {
		return hoursWorked;
	}

    public void setHoursWorked(final BigDecimal hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

	public BigDecimal getHoursWorkedInOvertime() {
		return hoursWorkedInOvertime;
	}

	public void setHoursWorkedInOvertime(BigDecimal hoursWorkedInOvertime) {
		this.hoursWorkedInOvertime = hoursWorkedInOvertime;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(final int quarter) {
        this.quarter = quarter;
    }

	public BigDecimal getYearlyGrossPaySum() {
		return yearlyGrossPaySum;
	}

	public void setYearlyGrossPaySum(BigDecimal yearlyGrossPaySum) {
		this.yearlyGrossPaySum = yearlyGrossPaySum;
	}

	public BigDecimal getQuarterlyGrossPaySum() {
		return quarterlyGrossPaySum;
	}

	public void setQuarterlyGrossPaySum(BigDecimal quarterlyGrossPaySum) {
		this.quarterlyGrossPaySum = quarterlyGrossPaySum;
	}

	public BigDecimal getOvertimePay() {
		return overtimePay;
	}
}
