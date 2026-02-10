package com.gabriel.emplms.serviceimpl;
import com.gabriel.emplms.entity.StudentData;
import com.gabriel.emplms.model.Student;
import com.gabriel.emplms.repository.StudentDataRepository;
import com.gabriel.emplms.service.StudentService;
import com.gabriel.emplms.transform.TransformStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class StudentServiceImpl implements StudentService {
	Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	@Autowired
	StudentDataRepository StudentDataRepository;
	@Autowired
	TransformStudentService tansformerStudentService;
	@Override
	public Student[] getAll() {
		List<StudentData> StudentsData = new ArrayList<>();
		List<Student> Students = new ArrayList<>();
		StudentDataRepository.findAll().forEach(StudentsData::add);
		Iterator<StudentData> it = StudentsData.iterator();
		while(it.hasNext()) {
			StudentData StudentData = it.next();
			Student Student = tansformerStudentService.transform(StudentData);
			Students.add(Student);
		}
		Student[] array = new Student[Students.size()];
		for  (int i=0; i<Students.size(); i++){
			array[i] = Students.get(i);
		}
		return array;
	}
	@Override
	public Student create(Student Student) {
		logger.info(" add:Input " + Student.toString());
		StudentData StudentData = tansformerStudentService.transform(Student);
		StudentData = StudentDataRepository.save(StudentData);
		logger.info(" add:Input " + StudentData.toString());
		Student newStudent = tansformerStudentService.transform(StudentData);
		return newStudent;
	}
	@Override
	public Student update(Student Student) {
		StudentData StudentData = tansformerStudentService.transform(Student);
		StudentData = StudentDataRepository.save(StudentData);
		Student newStudent = tansformerStudentService.transform(StudentData);
		return newStudent;
	}
	@Override
	public Student get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Optional<StudentData> optional = StudentDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			StudentData StudentDatum = optional.get();
			Student Student = tansformerStudentService.transform(StudentDatum);
			return Student;
		}
		logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		return null;
	}
	@Override
	public void delete(Integer id) {
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<StudentData> optional = StudentDataRepository.findById(id);
		if( optional.isPresent()) {
			StudentData StudentDatum = optional.get();
			StudentDataRepository.delete(StudentDatum);
			logger.info(" Success >> " + StudentDatum.toString());
		}
		else {
			logger.info(" Failed >> unable to locateStudent id:" +  Integer.toString(id));
		}
	}
}
