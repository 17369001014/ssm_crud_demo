package org.westos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.westos.bean.Department;
import org.westos.dao.DepartmentMapper;

import java.util.List;
@Service
public class DepartmentService {
    //注入dao
    @Autowired
    private DepartmentMapper departmentMapper;
    public List<Department> getDepts() {
        //查询所有部门信息，因为是查询所有，没有条件，所以条件传入null
        List<Department> departmentList = departmentMapper.selectByExample(null);
        return departmentList;
    }
}
