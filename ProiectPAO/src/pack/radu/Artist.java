package pack.radu;

public class Artist extends Employee implements Salary {

    private Integer modelsCreated;

    public Artist(String name, Integer age, Integer serviceTime, Integer projectsCompleted, Integer modelsCreated) {
        super(name, age, serviceTime, projectsCompleted);
        this.modelsCreated = modelsCreated;
        //this.salary = this.calculateSalary();
        this.salary = 0;
    }

    @Override
    public void displayEmployee() {
        System.out.println("Artist Name is: " + this.getName());
        System.out.println("Artist Age is: " + this.getAge());
        System.out.println("Artist Salary is: " + this.getSalary());
        System.out.println("Artist service time is: " + this.getServiceTime());
        System.out.println("Artist projects completed is: " + this.getProjectsCompleted());
        System.out.println("Artist models created is: " + this.getModelsCreated());
        System.out.println("ID :" + getEmployeeID());
    }

    @Override
    public void calculateSalary() {
        Integer sal = Math.min(6000, 3000 + getModelsCreated() * 10 + getServiceTime() * 200 + getProjectsCompleted() * 100);
        this.setSalary(sal);
    }

    public Integer getModelsCreated() {
        return modelsCreated;
    }
}
