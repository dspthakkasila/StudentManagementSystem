package model;

public class Student {

	private int id;
	private String name;
	private String email;
	private String phone;
	private String course;
	private double marks;
	private String photo;
	private String password;

	public Student() {

	}

	public Student(String name,
	        String email,
	        String phone,
	        String course,
	        double marks,
	        String photo,
	        String password) {

		this.name = name;
		this.email = email;
		this.phone = phone;
		this.course = course;
		this.marks = marks;
		this.photo = photo;
		this.password = password;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

}