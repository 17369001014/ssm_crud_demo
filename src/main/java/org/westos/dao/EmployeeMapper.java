package org.westos.dao;

import org.apache.ibatis.annotations.Param;
import org.westos.bean.Employee;
import org.westos.bean.EmployeeExample;

import java.util.List;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    //根据条件查询多个员工并包含部门信息
    List<Employee> selectByExampleWithDept(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    //根据id查询单个员工并包含部门信息
    Employee selectByPrimaryKeyWithDept(Integer empId);
    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> likeEmployees(String empName, String empEmail);
}