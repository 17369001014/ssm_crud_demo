package org.westos.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.westos.bean.Employee;
import org.westos.bean.PageMsg;
import org.westos.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @ResponseBody
    @RequestMapping(path = "/emps")
    public PageMsg getEmpsWithJSON(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        System.out.println("请求来了");
        //使用pagehelper设置分页信息
        //1.在查询所有之前使用PageHelper来设置页码和每页展示的条数
        PageHelper.startPage(pageNum, 5);
        //2.那么这个查询就是一个分页查询了
        List<Employee> list = employeeService.getAll();
        //3.把查询出的结果list包装到PageInfo里面,5是可选参数表示连续显示5页的页码
        PageInfo pageInfo = new PageInfo(list, 5);
        PageMsg pageMsg = PageMsg.success().add("pageInfo", pageInfo);
        return pageMsg;
    }
    //提供保存员工的方法
    @RequestMapping(path = "/emp",method = RequestMethod.POST)
    public PageMsg saveEmp(Employee employee){
        //调用service
        employeeService.saveEmp(employee);
        //返回插入成功的结果
        return PageMsg.success();
    }
    @RequestMapping(path = "/checkEmpName", method = RequestMethod.POST)
    public PageMsg checkEmpName(String empName) {
        //调用service
        boolean  b=employeeService.checkEmpName(empName);
        if(b){
            return PageMsg.success();
        }else{
            return PageMsg.fail();
        }
    }
    /*查询员工信息*/
    @RequestMapping(path = "/emp/{id}", method = RequestMethod.GET)
    public PageMsg getEmpInfoByID(@PathVariable("id") Integer id){
        //调用service
        Employee employee= employeeService.getEmpInfoByID(id);
        return PageMsg.success().add("emp",employee);
    }
    //提供修改员工的方法
    @RequestMapping(path = "/emp/{empId}", method = RequestMethod.PUT)  //更新采用PUT请求
    public PageMsg updateEmpInfoByID(Employee employee) {//这里不要使用@PathVariable注解
        //调用service
        employeeService.updateEmp(employee);
        return PageMsg.success();
    }
    //提供删除单个员工的方法
    @RequestMapping(path = "/emp/{id}", method = RequestMethod.DELETE)
    public PageMsg delEmpInfoByID(@PathVariable("id") Integer id) {
        //调用service
        employeeService.delEmp(id);
        return PageMsg.success();
    }
    @RequestMapping(path = "/emp/delAll/{ids}", method = RequestMethod.DELETE)  //删除采用DELETE请求
    public PageMsg delAllEmp(@PathVariable("ids") String ids) {
        System.out.println("全部删除请求来了"+"==="+ids);
        //调用service
        employeeService.delAllEmp(ids);
        return PageMsg.success();
    }
    /*模糊查询员工信息*/
    @GetMapping("/emps/like")
    public PageMsg likeEmps(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Employee employee) {
        //System.out.println(employee);
        PageHelper.startPage(pageNum, 8);
        String name = employee.getEmpName();
        String email = employee.getEmail();
        List<Employee> list = employeeService.likeEmps(name, email);
        PageInfo<Employee> pageInfo = new PageInfo<>(list, 5);//导航页数
        PageMsg pageMsg = PageMsg.success().add("pageInfo", pageInfo);
        return pageMsg;
    }


}
