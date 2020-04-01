package pack.radu;

public class DeveloperManager extends Developer implements Salary{

    private Integer subordinatesNr;

    public DeveloperManager(String name, Integer age, Integer serviceTime, Integer projectsCompleted, Integer modelsCreated, Integer subordinatesNr) {
        super(name, age, serviceTime, projectsCompleted, modelsCreated);
        this.subordinatesNr = subordinatesNr;
    }

    @Override
    public void displayEmployee() {
        System.out.println("Dev Manager Name is: " + this.getName());
        System.out.println("Dev Manager Age is: " + this.getAge());
        System.out.println("Dev Manager Salary is: " + this.getSalary());
        System.out.println("Dev Manager service time is: " + this.getServiceTime());
        System.out.println("Dev Manager projects completed is: " + this.getProjectsCompleted());
        System.out.println("Dev Manager bugs fixed is: " + this.getBugsFixed());
        System.out.println("Dev Manager nr of subordinates is: " + this.getSubordinatesNr());
        System.out.println("ID :" + getEmployeeID());
    }

    @Override
    public void calculateSalary() {
        Integer sal = Math.min(6000,3000 + getBugsFixed() * 10 + getServiceTime() * 200 + getProjectsCompleted() * 100);
        this.setSalary(sal);
    }

    public Integer getSubordinatesNr() {
        return subordinatesNr;
    }
}
