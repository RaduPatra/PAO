package pack.radu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Services myServices = new Services();
        System.out.println("Hello!\n");

        System.out.println("Instructions:\n" +
                "\t1) Add an employee\n" +
                "\t2) Display sorted employees by salary(descending order)\n" +
                "\t3) Calculate total salary across all employees\n" +
                "\t4) Display the oldest developer\n" +
                "\t5) Display total number of employees\n" +
                "\t6) Display all artists(todo) \n" +
                "\t7) todo\n" +
                "\t8) todo \n" +
                "\t9) Increase salary (id,ammount) - (todo) \n" +
                "\t10) todo\n" +
                "\t0) Quit the application\n"
        );

        Integer choice = null;
        Integer newChoice = null;
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        do {
            System.out.println("Select a choice: ");
            choice = scan.nextInt();
            switch (choice) {

                case 1:

                    System.out.println(
                            "Select an employee type!:\n" +
                                    "\t1) Developer\n" +
                                    "\t2) Artist \n" +
                                    "\t3) Designer\n" +
                                    "\t4) DeveloperManager\n" +
                                    "\t5) ArtistManager\n" +
                                    "\t6) DesignManager\n" +
                                    "\t7) ProjectDirector\n"
                    );
                    newChoice = scan2.nextInt();
                    switch (newChoice) {
                        case 1:
                            myServices.addDeveloper();
                            break;
                        case 2:
                            myServices.addArtist();
                            break;
                        case 3:
                            myServices.addDesigner();
                            break;
                        case 4:
                            myServices.addDeveloperManager();
                            break;
                        case 5:
                            myServices.addArtistManager();
                            break;
                        case 6:
                            myServices.addDesignManager();
                            break;
                        case 7:
                            myServices.addProjectDirector();
                            break;

                    }

                    System.out.println("You are back to main menu\n");
                    break;
                case 2:
                    myServices.sortAndDisplayEmployee();
                    break;
                case 3:
                    System.out.println("Total company salary is:" + myServices.totalSalary());
                    break;
                case 4:
                    myServices.displayOldestDeveloper();
                    break;
                case 5:
                    System.out.println("Total employees:" + myServices.getTotalEmployees());
                    break;
                case 6:
                    myServices.DisplayArtists();
                    break;
                case 7:
                    Scanner s = new Scanner(System.in);
                    System.out.print("Increase salary for employee with ID: ");
                    Integer ID = s.nextInt();
                    System.out.println("by: ");
                    Integer ammount = s.nextInt();
                    myServices.increaseSalary(ID, ammount);
                    break;
                case 8:
                    Scanner s1 = new Scanner(System.in);
                    System.out.print("Delete employee with ID: ");
                    ID = s1.nextInt();
                    myServices.deleteEmployeeById(ID);
                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 0:
                    System.out.println("Application closed");
                    break;
                default:
                    System.out.println("Wrong option!");
                    break;
            }
        } while (!choice.equals(-1));
    }

}