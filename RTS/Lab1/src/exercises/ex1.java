package exercises;
import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // object of type scan
        int choice;

        //menu
        System.out.println("Complex Number Calculator");
        System.out.println("------------------------");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.print("Enter your choice: ");
        //read choice
        choice = scanner.nextInt();

        //initialize 2 complex no(objects) of type ComplexNumber
        ComplexNumber num1, num2;

        // ask user for first no
        System.out.println("Enter the real part of the first complex number:");
        double real1 = scanner.nextDouble();
        System.out.println("Enter the imaginary part of the first complex number:");
        double imaginary1 = scanner.nextDouble();
        num1 = new ComplexNumber(real1, imaginary1);

        // ask user for sec no.
        System.out.println("Enter the real part of the second complex number:");
        double real2 = scanner.nextDouble();
        System.out.println("Enter the imaginary part of the second complex number:");
        double imaginary2 = scanner.nextDouble();
        num2 = new ComplexNumber(real2, imaginary2);

        switch(choice) {
            case 1:
                System.out.println("Add");
                ComplexNumber resultAdd = num1.add(num2);
                System.out.println("Result: " + resultAdd);
                break;
            case 2:
                System.out.println("Subtract");
                ComplexNumber resultSub = num1.subtract(num2);
                System.out.println("Result: " + resultSub);
                break;
            case 3:
                System.out.println("Multiply");
                ComplexNumber resultMul = num1.multiply(num2);
                System.out.println("Result: " + resultMul);
                break;
            default:
                System.out.println("Invalid choice!");
        }

        scanner.close();
    }
}

class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary)  //constructor
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber add(ComplexNumber num)
    {
        return new ComplexNumber(this.real + num.real, this.imaginary + num.imaginary);
    }

    public ComplexNumber subtract(ComplexNumber num)
    {
        return new ComplexNumber(this.real - num.real, this.imaginary - num.imaginary);
    }

    public ComplexNumber multiply(ComplexNumber num)
    {
        double realPart = this.real * num.real - this.imaginary * num.imaginary;
        double imaginaryPart = this.real * num.imaginary + this.imaginary * num.real;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    @Override
    public String toString() {
        return this.real + " + " + this.imaginary + "i";
    }
}

