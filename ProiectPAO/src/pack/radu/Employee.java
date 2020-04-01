package pack.radu;

public abstract class Employee implements Comparable<Employee> {

    private static Integer instances=0;
    private Integer employeeID;
    private String name;
    private Integer age;
    protected Integer salary;
    private Integer projectsCompleted;
    private Integer serviceTime;

    public Employee(String name, Integer age, Integer serviceTime, Integer projectsCompleted) {
        this.name = name;
        this.age = age;
        this.serviceTime = serviceTime;
        this.projectsCompleted = projectsCompleted;
        //this.salary = this.calculateSalary();
        employeeID = instances;
        instances++;

    }

    public abstract void displayEmployee();

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public Integer getProjectsCompleted() {
        return projectsCompleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    @Override
    public int compareTo(Employee comparesto) {
        int comparesalary = ((Employee) comparesto).getSalary();
        /* For Ascending order*/
        //return this.salary-comparesalary;

        /* For Descending order do like this */
        return comparesalary - this.salary;
    }


}
