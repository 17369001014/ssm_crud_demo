package org.westos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.westos.bean.Employee;
import org.westos.bean.EmployeeExample;
import org.westos.dao.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    //注入mapper
    @Autowired
    private EmployeeMapper employeeMapper;
    public List<Employee> getAll(){
        //因为查询所有，所以条件就传null
        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
        return employees;

    }
//保存员工
    public void saveEmp(Employee employee) {
        //有条件的插入
        employeeMapper.insertSelective(employee);
    }
//调用dao查询员工名是否存在
    public boolean checkEmpName(String empName) {
        //创建 EmployeeExample条件对象
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);//根据条件统计个数
        return count==0; //员工姓名的个数==0 说明还没有这个员工
    }
//查询员工信息
    public Employee getEmpInfoByID(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }
//更新的方法
    public void updateEmp(Employee employee) {
        //调用service
        //根据主键，有选择的更新，也就是employee中有哪些数据，就更新哪些数据,比如我们的员工名字是不需要更新的。
        employeeMapper.updateByPrimaryKeySelective(employee);
    }
//删除单个员工的方法
    public void delEmp(Integer id) {
        //调用mapper
        employeeMapper.deleteByPrimaryKey(id);
    }
    //批量删除的方法
    public void delAllEmp(String ids) {
        //把多个的组成的字符串转换成一个List集合
        String[] strings = ids.split("-");
        ArrayList<Integer> list = new ArrayList<>();
        for (String s : strings) {
            list.add(Integer.parseInt(s));
        }
        //根据条件删除
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        //把集合放到条件中
        criteria.andEmpIdIn(list);
        employeeMapper.deleteByExample(employeeExample);
    }
//模糊查询
    public List<Employee> likeEmps(String name, String email) {
        String empName = "%" + name + "%";
        String empEmail = "%" + email + "%";
        List<Employee> employees = employeeMapper.likeEmployees(empName, empEmail);
        return employees;
    }
}
