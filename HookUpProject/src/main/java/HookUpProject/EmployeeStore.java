package HookUpProject;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class EmployeeStore {

    private final Map<Long, Employee> employeesById = new HashMap<Long, Employee>();

    private long nextEmployeeId;

	public EmployeeStore() {
		nextEmployeeId = 1;
	}

	public void save(final Employee employee) {
        employee.setId(nextEmployeeId++);
        employeesById.put(employee.getId(), employee);
    }

    public Employee findById(final Long id) {
        return employeesById.get(id);
    }

    public Employee findByName(final String name) {
        for (final Employee employee : employeesById.values()) {
            if (name.equals(employee.getName())) {
                return employee;
            }
        }
        return null;
    }

	public Collection<Employee> findAll() {
		return employeesById.values();
	}
}
