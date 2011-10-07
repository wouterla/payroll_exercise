package HookUpProject.fixtures;

import HookUpProject.Employee;
import HookUpProject.EmployeeStore;
import fit.ColumnFixture;

public class IdentificationNumberFixture extends ColumnFixture {

    private final static EmployeeStore employeeStore = new EmployeeStore();
    public Long id;
    public String name;

    public boolean hasEmployee() {
        return employeeStore.findById(id) != null;
    }

    public boolean addEmployee() {
        final Employee employee = new Employee();
        employee.setName(name);
        employeeStore.save(employee);
        return true;
    }

    public String getEmployeeName() {
        final Employee employee = employeeStore.findById(id);
        return (employee.getName());
    }
}
