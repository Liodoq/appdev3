package com.gabriel.emplms.transform;
import com.gabriel.emplms.entity.StudentData;
import com.gabriel.emplms.model.Student;
import org.springframework.stereotype.Service;
@Service
public class TransformStudentServiceImpl implements TransformStudentService {
	@Override
	public StudentData transform(Student student){
		StudentData studentData = new StudentData();
        studentData.setId(employee.getId());
        studentData.setName(student.getName());
        studentData.setCourse(student.getCourse());
        studentData.setStudentNumber(student.getStudentNumber());

		return studentData;
	}
	@Override
	public Student transform(StudentData studentData){;
		Student student = new Student();
		student.setId(employeeData.getId());
		student.setStudentNumber(student.getStudentNumber());
		student.setName(employeeData.getName());
        student.setCourse(student.getCourse());
		return student;
	}
}
