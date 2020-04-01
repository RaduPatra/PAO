package pack.radu;

public class DesignManager extends Designer implements Salary {

    private Integer subordinatesNr;

    public DesignManager(String name, Integer age, Integer serviceTime, Integer projectsCompleted, Integer levelsCreated, Integer subordinatesNr) {
        super(name, age, serviceTime, projectsCompleted, levelsCreated);
        this.subordinatesNr = subordinatesNr;
    }

    @Override
    public void displayEmployee() {
        System.out.println("Design Manager Name is: " + this.getName());
        System.out.println("Design Manager Age is: " + this.getAge());
        System.out.println("Design Manager Salary is: " + this.getSalary());
        System.out.println("Design Manager service time is: " + this.getServiceTime());
        System.out.println("Design Manager projects completed is: " + this.getProjectsCompleted());
        System.out.println("Design Manager levels created is: " + this.getLevelsCreated());
        System.out.println("Design Manager nr of subordinates is: " + this.getSubordinatesNr());
        System.out.println("ID :" + getEmployeeID());
    }

    @Override
    public void calculateSalary() {
        Integer sal = Math.min(6000, 3000 + getLevelsCreated() * 10 + getServiceTime() * 200 + getProjectsCompleted() * 100);
        this.setSalary(sal);
    }

    public Integer getSubordinatesNr() {
        return subordinatesNr;
    }
}
