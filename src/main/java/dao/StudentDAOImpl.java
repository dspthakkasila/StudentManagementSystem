package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;

import model.LeaveRequest;
import model.Student;
import util.DBConnection;

import model.Test;


public class StudentDAOImpl implements StudentDAO {

	Connection con = DBConnection.getConnection();

	// ADD STUDENT
	@Override
	public boolean addStudent(Student s) {

		boolean status = false;

		try {

			String query =

					"INSERT INTO students" +

"(name,email,phone,course,marks,photo,password) "
+ "VALUES(?,?,?,?,?,?,?)";

			PreparedStatement ps =

					con.prepareStatement(query);

			ps.setString(1, s.getName());

			ps.setString(2, s.getEmail());

			ps.setString(3, s.getPhone());

			ps.setString(4, s.getCourse());

			ps.setDouble(5, s.getMarks());

			ps.setString(6, s.getPhoto());
			
			ps.setString(7, s.getPassword());

			int rows = ps.executeUpdate();

			if(rows > 0) {

				status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	// VIEW ALL STUDENTS
	@Override
	public List<Student> getAllStudents() {

		List<Student> list = new ArrayList<>();

		try {

			String query = "SELECT * FROM students";

			PreparedStatement ps =
					con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				Student s = new Student();

				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setPhone(rs.getString("phone"));
				s.setCourse(rs.getString("course"));
				s.setMarks(rs.getDouble("marks"));
				s.setPhoto(rs.getString("photo"));

				list.add(s);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	// GET STUDENT BY ID
	@Override
	public Student getStudentById(int id) {

		Student s = null;

		try {

			String query =
					"SELECT * FROM students WHERE id=?";

			PreparedStatement ps =
					con.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				s = new Student();

				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setPhone(rs.getString("phone"));
				s.setCourse(rs.getString("course"));
				s.setMarks(rs.getDouble("marks"));
				s.setPhoto(rs.getString("photo"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return s;
	}

	// UPDATE STUDENT
	@Override
	public boolean updateStudent(Student s) {

		boolean status = false;

		try {

			String query =

					"UPDATE students "

            + "SET name=?, email=?, "

            + "phone=?, course=?, "

            + "marks=?, photo=? "

            + "WHERE id=?";

			PreparedStatement ps =

					con.prepareStatement(query);

			ps.setString(1, s.getName());

			ps.setString(2, s.getEmail());

			ps.setString(3, s.getPhone());

			ps.setString(4, s.getCourse());

			ps.setDouble(5, s.getMarks());

			ps.setString(6, s.getPhoto());

			ps.setInt(7, s.getId());

			int rows = ps.executeUpdate();

			if(rows > 0) {

				status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	// DELETE STUDENT
	@Override
	public boolean deleteStudent(int id) {

		boolean status = false;

		try {

			String query =
					"DELETE FROM students WHERE id=?";

			PreparedStatement ps =
					con.prepareStatement(query);

			ps.setInt(1, id);

			int rows = ps.executeUpdate();

			if(rows > 0) {
				status = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	//Search Students by keyword    
	@Override
	public List<Student> searchStudents(String keyword) {

		List<Student> list = new ArrayList<>();

		try {

			String query =
					"SELECT * FROM students " +
							"WHERE LOWER(name) LIKE LOWER(?) " +
							"OR LOWER(course) LIKE LOWER(?)";

			PreparedStatement ps =
					con.prepareStatement(query);

			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				Student s = new Student();

				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setPhone(rs.getString("phone"));
				s.setCourse(rs.getString("course"));
				s.setMarks(rs.getDouble("marks"));
				s.setPhoto(rs.getString("photo"));

				list.add(s);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	// Total Marks
	public int getTotalStudents() {

		int count = 0;

		try {

			String query =
					"SELECT COUNT(*) FROM students";

			PreparedStatement ps =
					con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				count = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return count;
	}

	// Average Marks
	public double getAverageMarks() {

		double avg = 0;

		try {

			String query =
					"SELECT AVG(marks) FROM students";

			PreparedStatement ps =
					con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				avg = rs.getDouble(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return avg;
	}

	//Highest Marks
	public double getHighestMarks() {

		double max = 0;

		try {

			String query =
					"SELECT MAX(marks) FROM students";

			PreparedStatement ps =
					con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {

				max = rs.getDouble(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return max;
	}

	// Get students by page(pagination)
	@Override
	public List<Student> getStudentsByPage(int start,
			int total) {

		List<Student> list =
				new ArrayList<>();

		try {

			String query =
					"SELECT * FROM students LIMIT ?, ?";

			PreparedStatement ps =
					con.prepareStatement(query);

			ps.setInt(1, start);
			ps.setInt(2, total);

			ResultSet rs =
					ps.executeQuery();

			while(rs.next()) {

				Student s = new Student();

				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setPhone(rs.getString("phone"));
				s.setCourse(rs.getString("course"));
				s.setMarks(rs.getDouble("marks"));
				s.setPhoto(rs.getString("photo"));

				list.add(s);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	// Get students count
	@Override
	public int getStudentCount() {

		int count = 0;

		try {

			String query =
					"SELECT COUNT(*) FROM students";

			PreparedStatement ps =
					con.prepareStatement(query);

			ResultSet rs =
					ps.executeQuery();

			if(rs.next()) {

				count = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return count;
	}

	public List<Student>
	getStudentsWithPagination(

			int start,
			int limit,
			String search,
			String course,
			String sort){

		List<Student> list =
				new ArrayList<>();

		try {

			String query =
					"SELECT * FROM students WHERE 1=1";

			// SEARCH

			if(search != null &&
					!search.isEmpty()) {

				query +=
						" AND (name LIKE ? " +
								"OR course LIKE ?)";
			}

			// FILTER

			if(course != null &&
					!course.isEmpty()) {

				query +=
						" AND course=?";
			}

			// SORT

			if(sort != null &&
					!sort.isEmpty()) {

				switch(sort) {

				case "nameAsc":

					query +=
					" ORDER BY name ASC";
					break;

				case "nameDesc":

					query +=
					" ORDER BY name DESC";
					break;

				case "marksHigh":

					query +=
					" ORDER BY marks DESC";
					break;

				case "marksLow":

					query +=
					" ORDER BY marks ASC";
					break;

				default:

					query +=
					" ORDER BY id ASC";
				}

			} else {

				// DEFAULT PAGINATION ORDER

				query +=
						" ORDER BY id ASC";
			}

			// PAGINATION

			query +=
					" LIMIT ?, ?";

			PreparedStatement ps =
					con.prepareStatement(query);

			int index = 1;

			// SEARCH PARAMETERS

			if(search != null &&
					!search.isEmpty()) {

				ps.setString(index++,
						"%" + search + "%");

				ps.setString(index++,
						"%" + search + "%");
			}

			// FILTER PARAMETER

			if(course != null &&
					!course.isEmpty()) {

				ps.setString(index++,
						course);
			}

			// PAGINATION PARAMETERS

			ps.setInt(index++, start);

			ps.setInt(index++, limit);

			ResultSet rs =
					ps.executeQuery();

			while(rs.next()) {

				Student s =
						new Student();

				s.setId(
						rs.getInt("id"));

				s.setName(
						rs.getString("name"));

				s.setEmail(
						rs.getString("email"));

				s.setPhone(
						rs.getString("phone"));

				s.setCourse(
						rs.getString("course"));

				s.setMarks(
						rs.getDouble("marks"));

				s.setPhoto(
						rs.getString("photo"));

				list.add(s);
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int getTotalStudentCount(

			String search,
			String course){

		int count = 0;

		try {

			String query =
					"SELECT COUNT(*) FROM students WHERE 1=1";

			if(search != null &&
					!search.isEmpty()) {

				query +=
						" AND (name LIKE ? " +
								"OR course LIKE ?)";
			}

			if(course != null &&
					!course.isEmpty()) {

				query +=
						" AND course=?";
			}

			PreparedStatement ps =
					con.prepareStatement(query);

			int index = 1;

			if(search != null &&
					!search.isEmpty()) {

				ps.setString(index++,
						"%" + search + "%");

				ps.setString(index++,
						"%" + search + "%");
			}

			if(course != null &&
					!course.isEmpty()) {

				ps.setString(index++,
						course);
			}

			ResultSet rs =
					ps.executeQuery();

			if(rs.next()) {

				count = rs.getInt(1);
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return count;
	}

	//Student Login
	public Student studentLogin(

			String email,
			String password){

		Student s = null;

		try {

			String query =

					"SELECT * FROM students " +

	        "WHERE email=? " +

	        "AND password=?";

			PreparedStatement ps =

					con.prepareStatement(
							query);

			ps.setString(1, email);

			ps.setString(2, password);

			ResultSet rs =
					ps.executeQuery();

			if(rs.next()) {

				s = new Student();

				s.setId(rs.getInt("id"));

				s.setName(rs.getString("name"));

				s.setEmail(rs.getString("email"));

				s.setPhone(rs.getString("phone"));

				s.setCourse(rs.getString("course"));

				s.setMarks(rs.getDouble("marks"));

				s.setPhoto(rs.getString("photo"));
				
				s.setPassword(
						rs.getString("password"));
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return s;
	}

	// TOTAL COURSES

	public int getTotalCourses() {

		int count = 0;

		try {

			String query =
					"SELECT COUNT(DISTINCT course) FROM students";

			PreparedStatement ps =
					con.prepareStatement(query);

			ResultSet rs =
					ps.executeQuery();

			if(rs.next()) {

				count = rs.getInt(1);
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return count;
	}

	//pie chart
	public Map<String,Integer>
	getCourseWiseCount() {

		Map<String,Integer> data =
				new HashMap<>();

		try {

			Connection con =
					DBConnection.getConnection();

			String query =

					"SELECT course, COUNT(*) AS total " +

	        "FROM students " +

	        "GROUP BY course";

			PreparedStatement ps =
					con.prepareStatement(query);

			ResultSet rs =
					ps.executeQuery();

			while(rs.next()) {

				data.put(

						rs.getString("course"),

						rs.getInt("total")
						);
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return data;
	}

	public Student getTopperStudent() {

		Student s = null;

		try {

			Connection con =
					DBConnection.getConnection();

			String query =
					"SELECT * FROM students " +
							"ORDER BY marks DESC LIMIT 1";

			PreparedStatement ps =
					con.prepareStatement(query);

			ResultSet rs =
					ps.executeQuery();

			if(rs.next()) {

				s = new Student();

				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setPhone(rs.getString("phone"));
				s.setCourse(rs.getString("course"));
				s.setMarks(rs.getDouble("marks"));
				s.setPhoto(rs.getString("photo"));

				System.out.println(
						"Topper Found: "
								+ s.getName());
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return s;
	}

	// Student SkillSet
	public List<String> getSkillsByStudentId(int studentId) {

		List<String> skills =
				new ArrayList<>();

		try {

			Connection con =
					DBConnection.getConnection();

			String query =
					"SELECT skill FROM student_skills WHERE student_id=?";

			PreparedStatement ps =
					con.prepareStatement(query);

			ps.setInt(1, studentId);

			ResultSet rs =
					ps.executeQuery();

			while(rs.next()) {

				skills.add(
						rs.getString("skill"));
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return skills;
	}

	//Achievements
	public List<String>
	getAchievementsByStudentId(int studentId) {

		List<String> achievements =
				new ArrayList<>();

		try {

			Connection con =
					DBConnection.getConnection();

			String query =
					"SELECT achievement FROM achievements WHERE student_id=?";

			PreparedStatement ps =
					con.prepareStatement(query);

			ps.setInt(1, studentId);

			ResultSet rs =
					ps.executeQuery();

			while(rs.next()) {

				achievements.add(
						rs.getString("achievement"));
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return achievements;
	}

	//Notifications MEthod
	public List<String>
	getNotificationsByStudentId(int studentId){

		List<String> list =
				new ArrayList<>();

		try{

			Connection con =
					DBConnection.getConnection();

			String query =

					"SELECT message FROM notifications "
							+ "WHERE student_id=? "
							+ "OR type='global' "
							+ "ORDER BY id DESC";

			PreparedStatement ps =
					con.prepareStatement(query);

			ps.setInt(1, studentId);

			ResultSet rs =
					ps.executeQuery();

			while(rs.next()){

				list.add(
						rs.getString("message"));
			}

		}catch(Exception e){

			e.printStackTrace();
		}

		return list;
	}


	//Marks students method in student dashboard
	public Map<String,Double>
	getSubjectMarks(int studentId) {

		Map<String,Double> map =
				new LinkedHashMap<>();

		try {

			Connection con =
					DBConnection.getConnection();

			String query =
					"SELECT * FROM student_subject_marks WHERE student_id=?";

			PreparedStatement ps =
					con.prepareStatement(query);

			ps.setInt(1, studentId);

			ResultSet rs =
					ps.executeQuery();

			while(rs.next()) {

				map.put(

						rs.getString("subject_name"),

						rs.getDouble("marks"));
			}

		} catch(Exception e) {

			e.printStackTrace();
		}

		return map;
	}

	//Test
	public List<Test>
	getTestsByStudentId(int studentId){

		List<Test> list =
				new ArrayList<>();

		try{

			Connection con =
					DBConnection.getConnection();

			String query =

					"SELECT * FROM tests "
							+ "WHERE student_id=? "
							+ "OR type='global' "
							+ "ORDER BY test_date ASC";

			PreparedStatement ps =
					con.prepareStatement(query);

			ps.setInt(1, studentId);

			ResultSet rs =
					ps.executeQuery();

			while(rs.next()){

				Test t = new Test();

				t.setId(
						rs.getInt("id"));

				t.setStudentId(
						rs.getInt("student_id"));

				t.setSubject(
						rs.getString("subject"));

				t.setTestDate(
						rs.getString("test_date"));

				t.setTotalMarks(
						rs.getInt("total_marks"));

				t.setType(
						rs.getString("type"));

				list.add(t);
			}

		}catch(Exception e){

			e.printStackTrace();
		}

		return list;
	}	
	
	//Apply leaves
	public List<LeaveRequest>
	getLeavesByStudentId(int studentId){

	    List<LeaveRequest> list =
	    new ArrayList<>();

	    try{

	        Connection con =
	        DBConnection.getConnection();

	        String query =

	        "SELECT * FROM leave_requests "
	      + "WHERE student_id=?";

	        PreparedStatement ps =
	        con.prepareStatement(query);

	        ps.setInt(1,
	        studentId);

	        ResultSet rs =
	        ps.executeQuery();

	        while(rs.next()){

	            LeaveRequest l =
	            new LeaveRequest();

	            l.setId(
	            rs.getInt("id"));

	            l.setReason(
	            rs.getString("reason"));

	            l.setFromDate(
	            rs.getString("from_date"));

	            l.setToDate(
	            rs.getString("to_date"));

	            l.setStatus(
	            rs.getString("status"));

	            list.add(l);
	        }

	    }catch(Exception e){

	        e.printStackTrace();
	    }

	    return list;
	}


}