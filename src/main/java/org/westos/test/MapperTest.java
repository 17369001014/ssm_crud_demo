package org.westos.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.westos.bean.Department;
import org.westos.bean.Employee;
import org.westos.dao.DepartmentMapper;
import org.westos.dao.EmployeeMapper;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MapperTest {
//注入
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private SqlSession sqlSession;
    @Test
    public void test(){
        //1.先来插入几个部门
        Department dept1 = new Department();
        dept1.setDeptName("后勤部");
        departmentMapper.insertSelective(dept1);

        Department dept2 = new Department();
        dept2.setDeptName("艺术部");
        departmentMapper.insertSelective(dept2);
    }
    @Test
    public void test2(){
        //插入一个员工
        Employee employee = new Employee();
        employee.setEmpName("刘德华");
        employee.setGender("M");
        employee.setEmail("liudehua@163.com");
        employee.setdId(1);
        employeeMapper.insertSelective(employee);
    }
    @Test
    public void test3(){
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(1);
        System.out.println(employee);
    }
    @Test
    public void test4(){
        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
        System.out.println(employees);
    }
    @Test
    public void test5(){
        //批量插入员工
        //通过sqlSession 获取一个可以执行批量操作的mapper
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee(null, "zhangxueyou" + i, "M", "zhangxueyou@163.com", 1, null);
            mapper.insertSelective(employee);
        }
    }
}