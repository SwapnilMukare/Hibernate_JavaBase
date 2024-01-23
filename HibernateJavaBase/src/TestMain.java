import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TestMain {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            while (true) {
                System.out.println(" 1. Insert \n 2. Update \n 3. Delete \n 4. Show Data \n 5. Exit");
                System.out.println("Enter your choice:");
                int choice = s.nextInt();

                switch (choice) {
                    case 1:
                        Insert(s, session);
                        break;
                    case 2:
                        Update(s, session);
                        break;
                    case 3:
                        Delete(s, session);
                        break;
                    case 4:
                        Show_Data(session);
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter a valid number....");
                }
            }
        } finally {
            // Close the session when you are done
            session.close();
        }
    }

    private static void Show_Data(Session session) {
        List<Teacher> teachers = session.createQuery("from Teacher", Teacher.class).getResultList();
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getTid() + "\t" + teacher.getName() + "\t" + teacher.getSalary());
        }
    }

    private static void Delete(Scanner s, Session session) {
        System.out.println("Enter tid whose record you want to Delete?");
        int tid = s.nextInt();

        Teacher teacher = session.get(Teacher.class, tid);
        if (teacher != null) {
            Transaction tx = session.beginTransaction();
            session.delete(teacher);
            tx.commit();
            System.out.println("Data deleted successfully");
        } else {
            System.out.println("Teacher with id " + tid + " not found.");
        }
    }

    private static void Update(Scanner s, Session session) {
        System.out.println("Enter tid whose record you want to update?");
        int tid = s.nextInt();

        Teacher teacher = session.get(Teacher.class, tid);
        if (teacher != null) {
            Transaction tx = session.beginTransaction();

            System.out.print("Enter new name for the teacher: ");
            String name = s.next();
            teacher.setName(name);

            System.out.print("Enter new salary for the teacher: ");
            int salary = s.nextInt();
            teacher.setSalary(salary);

            session.update(teacher);
            tx.commit();
            System.out.println("Data updated successfully");
        } else {
            System.out.println("Teacher with id " + tid + " not found.");
        }
    }

    private static void Insert(Scanner s, Session session) {
        Transaction tx = session.beginTransaction();

        try {
            Teacher teacher = new Teacher();

            System.out.println("Enter Name And Salary");
            String name = s.next();
            int salary = s.nextInt();

            teacher.setName(name);
            teacher.setSalary(salary);

            session.save(teacher);
            tx.commit();
            System.out.println("Data inserted successfully");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
}
