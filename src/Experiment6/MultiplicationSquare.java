package Experiment6;

import java.util.Scanner;
public class MultiplicationSquare {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
// Taking user input
System.out.print("Enter a number: ");
int number = sc.nextInt();
// Calculating square
int square = number * number;
System.out.println("Square of " + number + " is: " + square);
// Multiplication Table
System.out.println("Multiplication Table of " + number + ":");
for(int i = 1; i <= 10; i++) {
System.out.println(number + " x " + i + " = " + (number * i));
}

sc.close();
}
}
