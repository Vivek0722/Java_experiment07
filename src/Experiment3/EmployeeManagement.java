package Experiment3;

//Import Scanner class for taking input from the user
import java.util.Scanner;

//Parent class
class Employee {

 // Data members of Employee
 int empId;
 String name;
 double salary;

 // Method to take employee details from the user
 void getEmployeeDetails(Scanner sc) {

     System.out.print("Enter Employee ID: ");
     empId = sc.nextInt();   // Taking employee ID

     System.out.print("Enter Employee Name: ");
     name = sc.next();       // Taking employee name

     System.out.print("Enter Salary: ");
     salary = sc.nextDouble(); // Taking salary
 }

 // Method to display employee details
 void display() {
     System.out.println("\nEmployee ID: " + empId);
     System.out.println("Employee Name: " + name);
     System.out.println("Salary: " + salary);
 }

 // Method to show employee work
 void work() {
     System.out.println("Employee is working.");
 }
}


//Child class (Inheritance)
class Manager extends Employee {

 // Additional data member
 String department;

 // Method to take manager department
 void getManagerDetails(Scanner sc) {
     System.out.print("Enter Department: ");
     department = sc.next();
 }

 // Method Overriding (same method as parent but different implementation)
 @Override
 void work() {
     System.out.println("Manager manages the team.");
 }

 // Method to display department
 void showDepartment() {
     System.out.println("Department: " + department);
 }
}


//Class to demonstrate Method Overloading
class SalaryCalculator {

 // Method 1: calculate salary using only basic salary
 double calculateSalary(double basic) {
     return basic;
 }

 // Method 2: calculate salary using basic salary + bonus
 double calculateSalary(double basic, double bonus) {
     return basic + bonus;
 }
}


//Main class
public class EmployeeManagement {

 public static void main(String[] args) {

     // Creating Scanner object for user input
     Scanner sc = new Scanner(System.in);

     // Creating Manager object
     Manager m = new Manager();

     // Taking employee details
     m.getEmployeeDetails(sc);

     // Taking manager department
     m.getManagerDetails(sc);

     // Taking bonus input
     System.out.print("Enter Bonus: ");
     double bonus = sc.nextDouble();

     // Display employee details
     System.out.println("\nEmployee Details");
     m.display();

     // Display department
     m.showDepartment();

     // Calling overridden method
     m.work();

     // Creating object for salary calculation
     SalaryCalculator sc1 = new SalaryCalculator();

     // Method overloading used here
     double totalSalary = sc1.calculateSalary(m.salary, bonus);

     // Display total salary
     System.out.println("Total Salary with Bonus: " + totalSalary);
 }
}
