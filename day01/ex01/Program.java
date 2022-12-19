package ex01;

import ex03.Transaction;

public class Program {
    public static void main(String[] args) {
        User vasya = new User("Vasya", 10);
        System.out.println("Vasya id: " + vasya.getIdentifier() + "\nVasya name: " + vasya.getName());
        User jeka = new User("Jeka", -100);
        System.out.println("Jeka id: " + jeka.getIdentifier() + "\nJeka name: " + jeka.getName() +
                "\nJeka balance: " + jeka.getBalance());
    }
}