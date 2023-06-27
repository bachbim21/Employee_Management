package com.example.ytb_employee_management_system.Controller;

import com.example.ytb_employee_management_system.Entity.Employee;
import com.example.ytb_employee_management_system.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller //tầng Controller, nơi đón nhận các request từ phía người dùng, và chuyển tiếp xử lý xuống tầng Service.
public class EmtyController {
    @Autowired
    private EmpService empService;

    // tra ve danh sach emp
    @GetMapping("/")//giao dien chinh
    public String home(Model model){ //Model là một đối tượng được sử dụng để truyền dữ liệu từ controller đến html,
        // Nó cung cấp các phương thức để thêm và truy cập dữ liệu trong môi trường web..
        List<Employee> emp = empService.getAllEmp();// tra ve list emp
        model.addAttribute("emp", emp);//cap nhat list realtime
        return "index";// tra ve file index.html
    }

    //vao giao dien addemp
    @GetMapping("/add")
    public String addEmp(){
        return "addEmp";
    }//them emp, tra ve file addEmp.html

    //cap nhat du lieu vao db: them moi
    //ung voi thymeleaf:<form th:action="@{/register}" method="post">
    @PostMapping("/register")//them data
    public String register(@ModelAttribute Employee employee,HttpSession httpSession){//Một HTML form tạo ra post request. @ModelAttribute dùng để hứng dữ liệu dạng form.
        //HttpSession de tao thong bao
        empService.addEmp(employee);
        httpSession.setAttribute("msg","add successfully" );// thong bao ra man hinh
        return "redirect:/";// tro lai trang index(dau / danh dau url index)
    }

    //nhan lenh edit du lieu, dua ve edit.html ung vs id da chon
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model){  //@PathVariable lấy ra thông tin trong URL dựa vào tên của thuộc tính đã định nghĩa trong ngoặc kép
        Employee employee = empService.getById(id);//lay ttin emp theo id
        model.addAttribute("emp", employee);//Dòng này thêm một thuộc tính vào đối tượng Model để truyền dữ liệu của nhân viên tới edit.html.
        //Thuộc tính được đặt tên là "emp" và giá trị là đối tượng employee, tương ứng với thông tin của nhân viên cần chỉnh sửa.
        return "edit";// ve lai file edit.html theo id da chon
    }

    //update lai vao db
    // ung voi           <form th:action="@{/update}" method="post" th:object="${emp}">
    //            <input type="hidden" th:value="${emp.id}" name="id">
    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee employee){
        empService.addEmp(employee);
        return "redirect:/";
    }

    //xoa emp khoi db
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id,HttpSession httpSession){
        empService.deleteEmp(id);// xoa theo id
        httpSession.setAttribute("msg","delete successfully" );
        return "redirect:/";
    }
/*    @PostMapping("/register")
    public String register(@ModelAttribute Employee employee, HttpSession httpSession){
        empService.addEmp(employee);
        httpSession.setAttribute("add successfully", "msg");
        return "redirect:/";
    }*/



}
