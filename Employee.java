public abstract class Employee {
    private String name;
    private String surname;
    private int salary;
    private String startDate;
    private String phone;
    private String email;
    private String identification_number;
    private String gender;
    private int id;
    private int age;
    private String worksAt;
    private String type;
    private String username;
    private String password;


    public Employee(String type,String name, String surname, int salary, String startDate, String phone, String email, String identification_number, String gender, int id, int age, String worksAt,String username, String password) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.startDate = startDate;
        this.phone = phone;
        this.email = email;
        this.identification_number = identification_number;
        this.gender = gender;
        this.id = id;
        this.age = age;
        this.worksAt = worksAt;
        this.username = username; 
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setWorksAt(String worksAt) {
		this.worksAt = worksAt;
	}

	public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentification_number() {
        return identification_number;
    }

    public void setIdentification_number(String identification_number) {
        this.identification_number = identification_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

	public String getWorksAt() {
		return worksAt;
	}
}

