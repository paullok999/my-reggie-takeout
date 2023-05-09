package com.kalok.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kalok.reggie.common.R;
import com.kalok.reggie.entity.Employee;
import com.kalok.reggie.mapper.EmployeeMapper;
import com.kalok.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * ServiceImpl是mybatis-plus提供的类，包含了Service层基本的CRUD操作
 * 泛型M:Service接口实现类所需操作的表对应的Mapper接口
 * 泛型T:Service接口实现类所需操作的表对应的实体类
 */
@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Override
    public R login(Employee employee) {
        EmployeeMapper mapper = getBaseMapper();
        //创建QueryWrapper添加查询条件
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",employee.getUsername());
        Employee emp = mapper.selectOne(queryWrapper);
        //当前用户名未注册
        if(emp.getUsername() == null){
            return R.error("用户不存在");
        }
        String pwdFromRequest = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        String pwdFromDb = emp.getPassword();
        //密码错误
        if(!pwdFromRequest.equals(pwdFromDb)){
            return R.error("密码错误");
        }
        //当前用户已被禁用
        if(emp.getStatus() == 0){
            return R.error("用户已被禁用");
        }
        return R.success(emp);
    }
}
