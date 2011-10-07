package HookUpProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.math.BigDecimal;

import org.junit.Test;

public class EmployeeTest {

    @Test
    public void testEmployeeFound() throws Exception {
        final Employee employee = new Employee();
		final EmployeeStore employeeStore = new EmployeeStore();
        employeeStore.save(employee);
        final Employee savedEmployee = employeeStore.findById(employee.getId());
        assertEquals(employee.getId(), savedEmployee.getId());
    }

    @Test
    public void testUniqueId() throws Exception {
        final Employee employee1 = new Employee();
        final Employee employee2 = new Employee();
        final EmployeeStore employeeStore = new EmployeeStore();
        employeeStore.save(employee1);
        employeeStore.save(employee2);
        assertFalse(employee1.getId().equals(employee2.getId()));
    }

    @Test
    public void testEmployeeFoundByName() throws Exception {
        final Employee employee = new Employee();
        employee.setName("fred");

        final EmployeeStore employeeStore = new EmployeeStore();
        employeeStore.save(employee);

        final Employee savedEmployee = employeeStore.findByName(employee.getName());
        assertEquals(employee.getId(), savedEmployee.getId());
		assertEquals("fred", savedEmployee.getName());
	}

    @Test
    public void testAddPayStubInYear() throws Exception {
        final Employee employee = new Employee();
        PayStub payStub = new PayStub();
        employee.addPayStubInThisYear(payStub);
        List<PayStub> receivedPayStubs = employee.getPayStubsInThisYear();
        assertEquals(1, receivedPayStubs.size());
    }

    @Test
    public void testRemovePayStubsInYear() throws Exception {
        final Employee employee = new Employee();
        PayStub payStub = new PayStub();
        employee.addPayStubInThisYear(payStub);
        List<PayStub> receivedPayStubs = employee.getPayStubsInThisYear();
        assertEquals(1, receivedPayStubs.size());

        employee.removePayStubsInYear();

        assertEquals(0, employee.getPayStubsInThisYear().size());
    }

    @Test
    public void testAddPayStubInQuarter() throws Exception {
        final Employee employee = new Employee();
        PayStub payStub = new PayStub();
        payStub.setQuarter(2);
        employee.addPayStubInThisYear(payStub);
        List<PayStub> payStubsInQ2 = employee.getPayStubsInQuarter(2);
        assertEquals(1, payStubsInQ2.size());

        List<PayStub> payStubsInQ1 = employee.getPayStubsInQuarter(1);
        assertEquals(0, payStubsInQ1.size());
    }

    @Test
    public void testGetLastPayStub() {
    	int dummyQuarter = 2;
    	Employee employee = new Employee();
        PayStub payStub = new PayStub();
        payStub.setQuarter(dummyQuarter);
        employee.addPayStubInThisYear(payStub);
        assertEquals(payStub, employee.getLastPayStub());
    }

    @Test
    public void testGetHoursWorked() {
    	Employee employee = new Employee();
        PayStub payStub = new PayStub();
        BigDecimal hoursInput = new BigDecimal(3.0);
        payStub.setHoursWorked(hoursInput);
		int dummyQuarter = 2;
        payStub.setQuarter(dummyQuarter);
        employee.addPayStubInThisYear(payStub);
        assertEquals(hoursInput, employee.getHoursWorked());
    }
}
