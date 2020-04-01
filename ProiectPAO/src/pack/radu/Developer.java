package pack.radu;

public class Developer extends Employee implements Salary {

    private Integer bugsFixed;

    public Developer(String name, Integer age, Integer serviceTime, Integer projectsCompleted, Integer bugsFixed) {
        super(name, age, serviceTime, projectsCompleted);
        this.bugsFixed = bugsFixed;
        //this.salary = this.calculateSalary();
        this.salary = 0;
    }

    @Override
    public void displayEmployee() {
        System.out.println("Developer Name is: " + this.getName());
        System.out.println("Developer Age is: " + this.getAge());
        System.out.println("Developer Salary is: " + this.getSalary());
        System.out.println("Developer service time is: " + this.getServiceTime());
        System.out.println("Developer projects completed time is: " + this.getProjectsCompleted());
        System.out.println("Developer nr of bugs fixed is: " + this.getBugsFixed());
        System.out.println("ID :" + getEmployeeID());
    }

    @Override
    public void calculateSalary() {
        //Integer sal = Math.min(7000,4000+ getBugsFixed() * 5 + getServiceTime() * 200 + getProjectsCompleted() * 150);
      /*  Integer sal= 100;
        this.setSalary(sal);*/
        Integer sal = Math.min(6000, 3000 + getBugsFixed() * 10 + getServiceTime() * 200 + getProjectsCompleted() * 100);
        this.setSalary(sal);
    }

    public Integer getBugsFixed() {
        return bugsFixed;
    }
}
