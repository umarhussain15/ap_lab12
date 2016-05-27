import attendance.entity.Attendance;
import attendance.entity.Course;
import attendance.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Umar on 09-May-16.
 */


public class BusinessObject {
    private SessionFactory sessionFactory;
    ClassPathXmlApplicationContext context;
    public BusinessObject() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory=(SessionFactory)context.getBean("hibernate4MappingSessionFactory");
    }

    public static void main(String[] args) throws IOException {
        Scanner s= new Scanner(System.in);
        BusinessObject bO= new BusinessObject();
        while (true){
//            Runtime.getRuntime().exec("cls");
            System.out.println("LAB 12\n Enter 1 to take Attendance");
            int f=s.nextInt();
            if( f>0 && f<=1){
                switch (f){
                    case 1:
                        bO.takeAttendance();
                }
            }

        }
    }
    public void takeAttendance(){
        try {
//            System.out.println(HibernateUtil.getSessionFactory());
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Course");
            List resultList = q.list();
            displayResult(resultList);
            int k=selectCourse();
            if (k==-1)
                System.out.println("Error No course by this ID");
            else{
                String QUERY = "from Attendance a where classid="+k;
                q=session.createQuery(QUERY);
                resultList=q.list();
                displayStudents(resultList,k);
                markAttendance(resultList,session);
            }
//
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    private void markAttendance(List resultList, Session session) {
        Scanner s= new Scanner(System.in);
        System.out.println("Enter Roll Number to mark attendance, -1 to end marking");
            int roll= s.nextInt();
        while (roll!=-1){
            System.out.print("Status: ");
            boolean st= s.nextBoolean();
            Query q = session.createQuery("from Attendance a where a.student=" + roll);
            Attendance a = (Attendance) q.uniqueResult();
            a.setIsPresent(st);

        }
    }

    private int selectCourse() {
       System.out.println("Select Course by ID to see student");
        return new Scanner(System.in).nextInt();
    }

    private void displayResult(List resultList) {
        System.out.println("ID\tTitle\tTeacher\tHrs\tSt\tEnd");
        for(Object o : resultList) {
            Course course = (Course)o;

            System.out.println(course.getCourseid()+"\t"+course.getClasstitle()+"\t"
                    +course.getTeacher().getFullname()
                    +"\t"+course.getCreditHours()+"\t"+course.getStarttime()+"\t"+course.getEndtime());
        }
    }
    private void displayStudents(List resultList,int cid){
        System.out.println("CID\tStudent\tRoll No\tDate\tisPresent");

        for(Object o : resultList) {
            Attendance attendance = (Attendance) o;
            System.out.println(attendance.getCourse().getCourseid()+"\t"+attendance.getStudent().getFullname()+"\t"
                    +attendance.getStudent().getRollno()
                    +"\t"+attendance.getDate()+"\t"+attendance.isIsPresent());
        }
    }

}
