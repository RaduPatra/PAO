package pack.radu;

import java.util.*;


public class Services {
    private ArrayList<Employee> employees;
    private ArrayList<Artist> artists;
    private ArrayList<Developer> developers;
    private ArrayList<Designer> designers;
    private HashMap<Integer, Employee> employeesMap;

    public Services() {
        this.employees = new ArrayList<>();
        this.artists = new ArrayList<Artist>();
        this.developers = new ArrayList<Developer>();
        this.designers = new ArrayList<Designer>();
        this.employeesMap = new HashMap<Integer, Employee>();
    }

    public List<Object> addEmployee() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Employee Name is: ");
        String name = scan.nextLine();
        System.out.println("Employee age is: ");
        Integer age = scan.nextInt();
        System.out.println("Employee service time is: ");
        Integer serviceTime = scan.nextInt();
        System.out.println("Employee projects completed is: ");
        Integer projectsCompleted = scan.nextInt();
        return Arrays.asList(name, age, serviceTime, projectsCompleted);

    }

    //todo update manager subordinates when new employee is added
    public void addArtist() {
        Scanner scan = new Scanner(System.in);
        List<Object> employee = addEmployee();
        System.out.println("Artist nr of models created is: ");
        Integer modelsCreated = scan.nextInt();
        Artist newArtist = new Artist((String) employee.get(0), (Integer) employee.get(1), (Integer) employee.get(2), (Integer) employee.get(3), modelsCreated);
        newArtist.calculateSalary();
        employees.add(newArtist);
        this.artists.add(newArtist);
        employeesMap.put(newArtist.getEmployeeID(), newArtist);

    }

    public void addDeveloper() {
        Scanner scan = new Scanner(System.in);
        List<Object> employee = addEmployee();
        System.out.println("Developer nr of bugsfixed is: ");
        Integer bugsFixed = scan.nextInt();
        Developer newDeveloper = new Developer((String) employee.get(0), (Integer) employee.get(1), (Integer) employee.get(2), (Integer) employee.get(3), bugsFixed); //name,age,serviceTime
        newDeveloper.calculateSalary();
        employees.add(newDeveloper);
        developers.add(newDeveloper);
        employeesMap.put(newDeveloper.getEmployeeID(), newDeveloper);
    }

    public void addDesigner() {
        Scanner scan = new Scanner(System.in);
        List<Object> employee = addEmployee();
        System.out.println("Designer nr of levels created is: ");
        Integer levelsCreated = scan.nextInt();
        Designer newDesigner = new Designer((String) employee.get(0), (Integer) employee.get(1), (Integer) employee.get(2), (Integer) employee.get(3), levelsCreated); //name,age,serviceTime
        newDesigner.calculateSalary();
        employees.add(newDesigner);
        designers.add(newDesigner);
        employeesMap.put(newDesigner.getEmployeeID(), newDesigner);

    }

    public void addArtistManager() {
        Scanner scan = new Scanner(System.in);
        List<Object> employee = addEmployee();
        System.out.println("Artist Manager nr of models created is: ");
        Integer modelsCreated = scan.nextInt();
        Integer subordinatesNr = getTotalArtists();
        ArtManager newArtManager = new ArtManager((String) employee.get(0), (Integer) employee.get(1), (Integer) employee.get(2), (Integer) employee.get(3), modelsCreated, subordinatesNr);
        newArtManager.calculateSalary();
        employees.add(newArtManager);
        employeesMap.put(newArtManager.getEmployeeID(), newArtManager);
        //this.artists.add(newArtManager);
    }

    public void addDeveloperManager() {
        Scanner scan = new Scanner(System.in);
        List<Object> employee = addEmployee();
        System.out.println("Developer Manager nr of bugs fixed is: ");
        Integer bugsFixed = scan.nextInt();
        Integer subordinatesNr = getTotalDevelopers();
        DeveloperManager newDevManager = new DeveloperManager((String) employee.get(0), (Integer) employee.get(1), (Integer) employee.get(2), (Integer) employee.get(3), bugsFixed, subordinatesNr);
        newDevManager.calculateSalary();
        employees.add(newDevManager);
        employeesMap.put(newDevManager.getEmployeeID(), newDevManager);
        //this.developers.add(newArtManager);
    }

    public void addDesignManager() {
        Scanner scan = new Scanner(System.in);
        List<Object> employee = addEmployee();
        System.out.println("Design Manager levels created fixed is: ");
        Integer levelsCreated = scan.nextInt();
        Integer subordinatesNr = getTotalDesigners();
        System.out.println("subord " + subordinatesNr);
        DesignManager newDesignManager = new DesignManager((String) employee.get(0), (Integer) employee.get(1), (Integer) employee.get(2), (Integer) employee.get(3), levelsCreated, subordinatesNr);
        newDesignManager.calculateSalary();
        employees.add(newDesignManager);
        employeesMap.put(newDesignManager.getEmployeeID(), newDesignManager);
        //this.developers.add(newArtManager);
    }

    public void addProjectDirector() {
        Scanner scan = new Scanner(System.in);
        List<Object> employee = addEmployee();
        Integer subordinatesNr = getTotalEmployees();
        ProjectDirector newProjectDirector = new ProjectDirector((String) employee.get(0), (Integer) employee.get(1), (Integer) employee.get(2), (Integer) employee.get(3), subordinatesNr);
        employees.add(newProjectDirector);
        employeesMap.put(newProjectDirector.getEmployeeID(), newProjectDirector);
    }

    public void displayEmployee() {
        int index = 0;
        for (Employee emp : employees) {
            System.out.println("Employee " + index + ':');
            emp.displayEmployee();
            index++;
        }
    }

    public void sortAndDisplayEmployee() {
        Collections.sort(employees);
        for (Employee emp : employees) {
            System.out.println("");
            emp.displayEmployee();
        }
    }

    public void DisplayArtists() {
        //int index = 0;
        System.out.println("All company artists are: ");
        for (Employee art : artists) {
            System.out.println("");
            art.displayEmployee();
            //index++;
        }
    }

    public void increaseSalary(Integer ID, Integer ammount) {
        Employee x = employeesMap.get(ID);
        x.setSalary(x.getSalary() + ammount);
    }

    public void deleteEmployeeById(Integer ID) {

        artists.removeIf(emp -> ID == emp.getEmployeeID());
        developers.removeIf(emp -> ID == emp.getEmployeeID());
        designers.removeIf(emp -> ID == emp.getEmployeeID());
        employees.removeIf(emp -> ID == emp.getEmployeeID());
        employeesMap.remove(ID);
    }

    public void displaybyID(Integer ID) {
        System.out.println("emp id: " + employeesMap.get(ID).getName());
    }

    public Integer totalSalary() {
        Integer sum = 0;
        for (Employee emp : employees) {
            sum += emp.getSalary();
        }
        return sum;
    }

    public void displayOldestDeveloper() {

        Integer Age = -1, maxAge = 0;
        String name = null;
        for (Employee emp : employees) {
            if (emp instanceof Developer) {
                Age = emp.getAge();
            }
            if (Age > maxAge) {
                maxAge = Age;
                Employee empsol = emp;
                name = empsol.getName();
            }
        }
        System.out.println("Oldest developer is: " + name + " at " + maxAge + " years old.");
    }

    public Integer getTotalEmployees() {
        Integer size = employees.size();
        return size;
    }

    public Integer getTotalArtists() {
        Integer size = artists.size();
        return size;
    }

    public Integer getTotalDevelopers() {
        Integer size = developers.size();
        return size;
    }

    public Integer getTotalDesigners() {
        Integer size = designers.size();
        return size;
    }

}
