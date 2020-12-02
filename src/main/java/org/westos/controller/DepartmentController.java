package org.westos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.westos.bean.Department;
import org.westos.bean.PageMsg;
import org.westos.service.DepartmentService;

import java.util.List;

@Controller
public class DepartmentController {
    //自动注入DepartmentService
    @Autowired
    private DepartmentService departmentService;
    @ResponseBody
    @RequestMapping("/depts")
    public PageMsg getDepts(){
        List<Department> list=departmentService.getDepts();
        PageMsg pageMsg = PageMsg.success().add("depts", list);
        return pageMsg;
    }
}
