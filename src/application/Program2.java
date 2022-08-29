package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        Department department = departmentDao.findById(1);
        System.out.println("=== TEST 1: Department findById ===");
        System.out.println(department);

        System.out.println("=== TEST 2: Department findAll ===");
        List<Department> departments = departmentDao.findAll();

        departments.forEach(System.out::println);

        System.out.println("=== TEST 3: Department insert ===");
        department = new

                Department(null, "Technology");

        departmentDao.insert(department);

        System.out.println("Inserted! New ID: " + department.getId());

        System.out.println("=== TEST 3: Department update ===");
        department = departmentDao.findById(1);
        department.setName("Food");
        departmentDao.update(department);
        System.out.println("Updated!");

        System.out.println("=== TEST 3: Department deleteById ===");
        System.out.print("Enter a Department ID for DELETE: ");
        int id = sc.nextInt();

        departmentDao.deleteById(id);

        System.out.println("Deleted!");

        sc.close();
    }
}