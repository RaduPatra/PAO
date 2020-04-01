package pack.radu;

public class ArtManager extends Artist implements Salary {

    private Integer subordinatesNr;

    public ArtManager(String name, Integer age, Integer serviceTime, Integer projectsCompleted, Integer modelsCreated, Integer subordinatesNr) {
        super(name, age, serviceTime, projectsCompleted, modelsCreated);
        this.subordinatesNr = subordinatesNr;
        //this.salary = this.calculateSalary();
        this.salary = 0;
    }

    @Override
    public void displayEmployee() {
        System.out.println("Art Manager Name is: " + this.getName());
        System.out.println("Art Manager Age is: " + this.getAge());
        System.out.println("Art Manager Salary is: " + this.getSalary());
        System.out.println("Art Manager service time is: " + this.getServiceTime());
        System.out.println("Art Manager projects completed is: " + this.getProjectsCompleted());
        System.out.println("Art Manager models created is: " + this.getModelsCreated());
        System.out.println("Art Manager nr of subordinates is: " + this.getSubordinatesNr());
        System.out.println("ID :" + getEmployeeID());
    }


    public Integer getSubordinatesNr() {
        return subordinatesNr;
    }

    @Override
    public void calculateSalary() {
        Integer sal = Math.min(6000, 3000 + getModelsCreated() * 10 + getServiceTime() * 200 + getProjectsCompleted() * 100 + getSubordinatesNr() * 200);
        this.setSalary(sal);
    }


}
