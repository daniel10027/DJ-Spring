package com.octogone.sms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.octogone.sms.entity.Student;

public interface StudentRepository  extends JpaRepository<Student, Long>{

}
