package pack.radu;

public class Designer extends Employee implements Salary{
    private Integer levelsCreated;

    public Designer(String name, Integer age, Integer serviceTime, Integer projectsCompleted, Integer levelsCreated) {
        super(name, age, serviceTime, projectsCompleted);
        this.levelsCreated = levelsCreated;
        //this.salary = this.calculateSalary();
        this.salary=0;
    }

    @Override
    public void displayEmployee() {
        System.out.println("Designer Name is: " + this.getName());
        System.out.println("Designer Age is: " + this.getAge());
        System.out.println("Designer Salary is: " + this.getSalary());
        System.out.println("Designer service time is: " + this.getServiceTime());
        System.out.println("Designer nr of projects completed is: " + this.getProjectsCompleted());
        System.out.println("Designer nr of levels created is: " + this.getLevelsCreated());
        System.out.println("ID :" + getEmployeeID());
    }

    @Override
    public void calculateSalary() {
        Integer sal = Math.min(5500, 2500 + (getLevelsCreated() * 10) + getServiceTime() * 250 + getProjectsCompleted() * 100);
        this.setSalary(sal);
    }

    public Integer getLevelsCreated() {
        return levelsCreated;
    }
}
