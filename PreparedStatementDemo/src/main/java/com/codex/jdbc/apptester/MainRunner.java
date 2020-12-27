package com.codex.jdbc.apptester;

import java.util.Scanner;

import com.codex.jdbc.dao.UserDao;

public class MainRunner {

	public static void main(String[] args) {
		UserDao dao = new UserDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("------MENU-------");
		System.out.println("Press 1 to insert");
		System.out.println("Press 2 to update");
		System.out.println("Press 3 to delete");
		System.out.println("Press 4 to read");
		System.out.println("Press 5 to search");
		while (true) {
			System.out.println("Enter choice:");
			int ch = sc.nextInt();
			if (ch == 1) {
				while (true) {
					System.out.println("Enter name:");
					String name = sc.next();
					System.out.println("Enter age:");
					int age = sc.nextInt();
					System.out.println("Enter salary:");
					double sal = sc.nextDouble();
					int n = dao.createUser(name, age, sal);
					if (n > 0) {
						System.out.println(n + " row successfully inserted");
					}
					System.out.println("Do you want to insert more rows?");
					String op1 = sc.next();
					if (op1.equalsIgnoreCase("NO")) {
						break;
					}
				}
			} else if (ch == 2) {
				while (true) {
					System.out.println("Enter the name you want update:");
					String name = sc.next();
					int chk = dao.findAllUsersByName(name);
					if (chk == 1) {
						System.out.println("Enter the new salary:");
						double sal = sc.nextDouble();
						int n = dao.updateUserByName(name, sal);
						if (n > 0) {
							System.out.println(n + " record updated successfully");
						}
					} else {
						System.out.println("Sorry! Invalid Record");
					}
					System.out.println("Do you want to update more records?");
					String op1 = sc.next();
					if (op1.equalsIgnoreCase("NO")) {
						break;
					}
				}

			} else if (ch == 3) {
				while (true) {
					System.out.println("Enter the name you want delete:");
					String name = sc.next();
					int chk = dao.findAllUsersByName(name);
					if (chk == 1) {
						int n = dao.deleteUserByName(name);
						if (n > 0) {
							System.out.println(n + " record deleted successfully");
						}
					} else {
						System.out.println("Sorry ! Invalid Record");
					}
					System.out.println("Do you want to delete more records?");
					String op2 = sc.next();
					if (op2.equalsIgnoreCase("NO")) {
						break;
					}
				}
			} else if (ch == 4) {
				while (true) {
					System.out.println("Enter name you want to read:");
					String name = sc.next();
					int n = dao.findAllUsersByName(name);
					if (n == 0) {
						System.out.println("Record not present");
					} else {
						dao.readUserByName(name);
					}
					System.out.println("Do you want to read more data?");
					String op2 = sc.next();
					if (op2.equalsIgnoreCase("NO")) {
						break;
					}
				}
			} else if (ch == 5) {
				while (true) {
					System.out.println("Enter name you want to search:");
					String name = sc.next();
					int n = dao.findAllUsersByName(name);
					if (n == 1) {
						System.out.println("Record present");
					} else {
						System.out.println("Record not present");
					}
					System.out.println("Do you want to search more records?");
					String op2 = sc.next();
					if (op2.equalsIgnoreCase("NO")) {
						break;
					}
				}
			}
			System.out.println("Do you want to perform more operations?");
			String op = sc.next();
			if (op.equalsIgnoreCase("NO")) {
				break;
			}
		}
		sc.close();
	}
}
