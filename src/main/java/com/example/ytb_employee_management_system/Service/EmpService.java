package com.example.ytb_employee_management_system.Service;

import com.example.ytb_employee_management_system.Entity.Employee;
import com.example.ytb_employee_management_system.Repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  //Tầng Service, chịu trách nhiệm thực hiện các xử lý logic, business, hỗ trợ cho tầng Controller.
public class EmpService {

    @Autowired
    private EmpRepo empRepo;

    public void addEmp(Employee employee){
        empRepo.save(employee);
    }

    public List<Employee> getAllEmp(){
        return empRepo.findAll();
    }

    public Employee getById(int id){
        Optional<Employee> optionalEmployee = empRepo.findById(id);
        if (optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        return null;
    }
    public void deleteEmp(int id){
        empRepo.deleteById(id);
    }
}
