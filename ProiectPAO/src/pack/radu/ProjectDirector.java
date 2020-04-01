package pack.radu;

public class ProjectDirector extends Employee implements Salary {

    private Integer subordinatesNr;

    public ProjectDirector(String name, Integer age, Integer serviceTime, Integer projectsCompleted, Integer subordinatesNr) {
        super(name, age, serviceTime, projectsCompleted);
        this.subordinatesNr = subordinatesNr;
        //this.salary = this.calculateSalary();
        this.salary = 0;
    }

    @Override
    public void displayEmployee() {
        System.out.println("Director Name is: " + this.getName());
        System.out.println("Director Age is: " + this.getAge());
        System.out.println("Director Salary is: " + this.getSalary());
        System.out.println("Director service time is: " + this.getServiceTime());
        System.out.println("Director projects completed is: " + this.getProjectsCompleted());
        System.out.println("Director nr of subordinates is: " + this.getSubordinatesNr());
        System.out.println("ID :" + getEmployeeID());
    }


    public Integer getSubordinatesNr() {
        return subordinatesNr;
    }

    @Override
    public void calculateSalary() {
        Integer sal = Math.min(10000, 7000 + getServiceTime() * 200 + getProjectsCompleted() * 100 + getSubordinatesNr() * 200);
        this.setSalary(sal);
    }


}
