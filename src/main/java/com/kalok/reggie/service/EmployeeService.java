package com.kalok.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kalok.reggie.common.R;
import com.kalok.reggie.entity.Employee;

/**
 * IService是mybatis-plus提供的类，包含了Service层基本的CRUD操作接口
 * 泛型T:Service接口需操作的表对应的实体类
 */
public interface EmployeeService extends IService<Employee> {
    R login(Employee employee);
}
