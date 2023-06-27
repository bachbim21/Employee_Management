package com.example.ytb_employee_management_system.Repository;

import com.example.ytb_employee_management_system.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Tầng Repository, chịu trách nhiệm giao tiếp với Database. Chúng ta sử dụng Spring JPA.
public interface EmpRepo extends JpaRepository<Employee,Integer> {
}
