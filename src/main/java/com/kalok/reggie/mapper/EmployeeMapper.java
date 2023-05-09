package com.kalok.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kalok.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * BaseMapper是mybatis-plus提供的类，包含了Dao层基本的CRUD操作
 * 泛型T:Mapper接口所需操作的表对应的实体类
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
