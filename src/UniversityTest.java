import java.util.ArrayList;
import java.util.List;

class University {
    private List<Faculty> faculties = new ArrayList<>();
    private String name;

    public University(String name) {
        this.name = name;
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public String getName() {
        return name;
    }

    class Faculty {
        private List<Department> departments = new ArrayList<>();
        private String name;

        public Faculty(String name) {
            this.name = name;
        }

        public void addDepartment(Department department) {
            departments.add(department);
        }

        public List<Department> getDepartments() {
            return departments;
        }

        public String getName() {
            return name;
        }

        class Department {
            private List<String> courses = new ArrayList<>();
            private String name;

            public Department(String name) {
                this.name = name;
            }

            public void addCourse(String course) {
                courses.add(course);
            }

            public List<String> getCourses() {
                return courses;
            }

            public String getName() {
                return name;
            }
        }
    }
}

public class UniversityTest {
    public static void main(String[] args) {
        University university = new University("National University");

        University.Faculty scienceFaculty = university.new Faculty("Faculty of Science");
        University.Faculty artsFaculty = university.new Faculty("Faculty of Arts");

        University.Faculty.Department physicsDepartment = scienceFaculty.new Department("Department of Physics");
        University.Faculty.Department chemistryDepartment = scienceFaculty.new Department("Department of Chemistry");

        physicsDepartment.addCourse("Quantum Mechanics");
        physicsDepartment.addCourse("Electrodynamics");

        chemistryDepartment.addCourse("Organic Chemistry");
        chemistryDepartment.addCourse("Inorganic Chemistry");

        scienceFaculty.addDepartment(physicsDepartment);
        scienceFaculty.addDepartment(chemistryDepartment);

        University.Faculty.Department historyDepartment = artsFaculty.new Department("Department of History");
        University.Faculty.Department literatureDepartment = artsFaculty.new Department("Department of Literature");

        historyDepartment.addCourse("Ancient History");
        historyDepartment.addCourse("Modern History");

        literatureDepartment.addCourse("English Literature");
        literatureDepartment.addCourse("World Literature");

        artsFaculty.addDepartment(historyDepartment);
        artsFaculty.addDepartment(literatureDepartment);

        university.addFaculty(scienceFaculty);
        university.addFaculty(artsFaculty);

        System.out.println("University: " + university.getName());
        for (University.Faculty faculty : university.getFaculties()) {
            System.out.println("  Faculty: " + faculty.getName());
            for (University.Faculty.Department department : faculty.getDepartments()) {
                System.out.println("    Department: " + department.getName());
                for (String course : department.getCourses()) {
                    System.out.println("      Course: " + course);
                }
            }
        }
    }
}
