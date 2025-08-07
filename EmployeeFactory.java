public class EmployeeFactory extends Employee{
    public Employee createEmployee( String type, String name, String surname, int salary, String startDate, String phone, String email, String identification_number, String gender, int id, int age ,String worksAt,String username,String password){
        if (type.equalsIgnoreCase("worker")) {
            return new Worker(type,name, surname, salary, startDate, phone, email, identification_number, gender, id, age,worksAt,username,password);
        } else if (type.equalsIgnoreCase("manager")) {
            return new Manager(type,name, surname, salary, startDate, phone, email, identification_number, gender, id, age,worksAt,username,password);
        } else {
            return null;
        }
    }
}
