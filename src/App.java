import dao.*;
import entity.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * My main App. 
 * <p>
 This executes everything.
 */

public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //CLOS
        CloDao cloDao= context.getBean(CloDao.class);
        Clo aCLO1 = new Clo();
        Clo aCLO2 = new Clo();
        aCLO1.setName("CLO 1");
        aCLO2.setName("CLO 2");
        aCLO1.setDescription("Design efficient solutions for algorithmic problems");
        aCLO2.setDescription("Blah Blah Blah");
        aCLO1.setPlo("2");
        aCLO2.setPlo("2");
        Clo bCLO1 = new Clo();
        Clo bCLO2 = new Clo();
        bCLO1.setName("CLO 1");
        bCLO2.setName("CLO 2");
        bCLO1.setDescription("Design efficient solutions for algorithmic problems");
        bCLO2.setDescription("Blah Blah Blah");
        bCLO1.setPlo("2");
        bCLO2.setPlo("2");
        // CLO SAVED
        cloDao.addClo(aCLO1);
        cloDao.addClo(aCLO2);
        cloDao.addClo(bCLO1);
        cloDao.addClo(bCLO2);
        CourseDao courseDao= (CourseDao)context.getBean(CourseDao.class);
        TeacherDao teacherDao= context.getBean(TeacherDao.class);
        Course apCourse = new Course();
        Teacher fahadSatti = new Teacher();
        fahadSatti.setName("Fahad Satti");

        apCourse.setEndsOn(new Date());
        apCourse.setStartsOn(new Date());
        apCourse.setCreditHours(4);
        apCourse.setClasstitle("Advance Programming");
        apCourse.setTeacher(fahadSatti);
        Set <Course> fsCourses= new HashSet<>();
        fsCourses.add(apCourse);
        fahadSatti.setCourses(fsCourses);

        // TEACHER SAVED
        teacherDao.addTeacher(fahadSatti);

        apCourse.setTeacher(fahadSatti);

        StudentDao studentDao= context.getBean(StudentDao.class);
        Student umar = new Student();
        umar.setName("Umar Hussain");
        Set<Course> uhCourses= new HashSet<>();
        uhCourses.add(apCourse);
        umar.setCourses(uhCourses);
        // STUDENT SAVED
        studentDao.addStudent(umar);

        ContentDao contentDao= context.getBean(ContentDao.class);
        Content apCourseContent= new Content();
        apCourseContent.setTitle("Parallel Programming");
        //Check
        apCourseContent.setCourse(apCourse);
            List<Clo> apCourseContentCloList= new ArrayList<>();
            apCourseContentCloList.add(aCLO1);
            apCourseContentCloList.add(aCLO2);
        apCourseContent.setClo(apCourseContentCloList);
        apCourseContent.setStarttime(new Date());
        apCourseContent.setEndtime(new Date());
        apCourseContent.setDescription("a quick brown fox jumps over the lazy dog");
            Set<Student> apCourseContentStudentSet = new HashSet<>();
            apCourseContentStudentSet.add(umar);
        apCourseContent.setStudents(apCourseContentStudentSet);

            GradeDao gradeDao = context.getBean(GradeDao.class);
            Grade apCourseContentGrade = new Grade();
            apCourseContentGrade.setName("A");
            apCourseContentGrade.setScore(10);

        // CONTENT SAVED
        contentDao.addContent(apCourseContent);
        apCourseContentGrade.setContentItem(apCourseContent);
        // GRADE SAVED
        gradeDao.addGrade(apCourseContentGrade);
            Set<Content> apCourseContentSet = new HashSet<>();
            apCourseContentSet.add(apCourseContent);
        apCourse.setContents(apCourseContentSet);

        Set<Student> apCourseStudentSet = new HashSet<>();
        apCourseStudentSet.add(umar);
        apCourse.setStudents(apCourseStudentSet);
        courseDao.addCourse(apCourse);

                for (Course iter : courseDao.getAllCourses()) {
            System.out.println(iter);
        }
//        Set<Content> apCourseContent= new HashSet<>();
//
//        apCourse.setContents(apCourseContent);

//        GradeDao  gradeDao = new GradeDao();
//        StudentDao studentDao = new StudentDao();
//        TeacherDao teacherDao = new TeacherDao();
//        ContentDao contentDao= new ContentDao();
//        // CLOS
//        Clo aCLO1 = new Clo();
//        Clo aCLO2 = new Clo();
//        aCLO1.setName("CLO 1");
//        aCLO2.setName("CLO 2");
//        aCLO1.setDescription("Design efficient solutions for algorithmic problems");
//        aCLO2.setDescription("Blah Blah Blah");
//        aCLO1.setPlo("2");
//        aCLO2.setPlo("2");
//        Clo bCLO1 = new Clo();
//        Clo bCLO2 = new Clo();
//        bCLO1.setName("CLO 1");
//        bCLO2.setName("CLO 2");
//        bCLO1.setDescription("Design efficient solutions for algorithmic problems");
//        bCLO2.setDescription("Blah Blah Blah");
//        bCLO1.setPlo("2");
//        bCLO2.setPlo("2");
//
//        Course ap= new Course();
//        ap.setClasstitle("Advance Programming");
//        ap.setCreditHours(4);
//        ap.setStartsOn( new Date());
//        ap.setEndsOn( new Date());
//
//        Teacher satti= new Teacher();
//        satti.setName("Fahad Satti");
//
//        Student umar= new Student();
//        umar.setName("umar");
//        Student hamza= new Student();
//        hamza.setName("hamza");
//        Content apcontent= new Content();
//        List<Clo> cloList= new ArrayList<>();
//            cloList.add(aCLO1);
//            cloList.add(aCLO2);
//            apcontent.setClo(cloList);
//            apcontent.setCourse(ap);
//            apcontent.setDescription("qeqweqeq");
//            apcontent.setEndtime(new Date());
//        apcontent.setStarttime(new Date());
//
//        apcontent.setTitle("EX 1");
//        Content apcontent2= new Content();
//        List<Clo> cloList2= new ArrayList<>();
//        cloList2.add(bCLO1);
//        cloList2.add(bCLO2);
//        apcontent2.setClo(cloList2);
//        apcontent2.setCourse(ap);
//        apcontent2.setDescription("ooooooooo");
//        apcontent2.setEndtime(new Date());
//        apcontent2.setStarttime(new Date());
//
//        apcontent2.setTitle("EX 2");
//
//        Grade g1= new Grade();
//        Grade g2= new Grade();
//
//
//
//
//
//        /**
//         * Save CLO to DB
//         */
//        CloDao clodao = new CloDao();
//            clodao.addClo(aCLO1);
//            clodao.addClo(aCLO2);
//            clodao.addClo(bCLO1);
//            clodao.addClo(bCLO2);
//        // AP list
//        ap.setTeacher(satti);
//
//
//            Set<Content> cc=new HashSet<>();
//            cc.add(apcontent);
//            cc.add(apcontent2);
//        ap.setContents(cc);
//
//
//            Set<Student> s1= new HashSet<>();
//            s1.add(umar);
//            s1.add(hamza);
//        apcontent.setStudents(s1);
//
//            Set<Student> s2= new HashSet<>();
//            s2.add(umar);
//            s2.add(hamza);
//        apcontent2.setStudents(s2);
//
//
//
//        g1.setName("adasda");
//        g1.setScore(10);
//
//
//        g2.setName("sdfsdfsfs");
//        g2.setScore(20);
//
//        Set<Grade> ap1g= new HashSet<>();
//        ap1g.add(g1);
//        apcontent.setGrades(ap1g);
//        g1.setContentItem(apcontent);
//
//
//        Set<Grade> ap2g= new HashSet<>();
//        ap2g.add(g2);
//        apcontent2.setGrades(ap2g);
//        g2.setContentItem(apcontent2);
//
//        contentDao.addContent(apcontent);
//        contentDao.addContent(apcontent2);
//        cd.addCourse(ap);
//        gradeDao.addGrade(g1);
//        gradeDao.addGrade(g2);
//        Set<Course> umarc= new HashSet<>();
//        umarc.add(ap);
//        umar.setCourses(umarc);
//        hamza.setCourses(umarc);
//        satti.setCourses(umarc);
//
//
//
//
//
//
//        teacherDao.addTeacher(satti);
//
//
//        studentDao.addStudent(umar);
//        studentDao.addStudent(hamza);
//

//
//        // Get aCLO1 by id
////        System.out.println(clodao.getCloById(1));
//
//
//    }
    }


}