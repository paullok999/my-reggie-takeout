package com.kalok.reggie.controller;

import com.kalok.reggie.common.R;
import com.kalok.reggie.entity.Employee;
import com.kalok.reggie.service.EmployeeService;
import com.kalok.reggie.vo.EmployeeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService; //自动注入需要使用的Service接口

    @PostMapping(path = "/login")
    @ResponseBody //返回JSON数据
    public R login(@RequestBody Employee employee,HttpServletRequest request){
        R r = employeeService.login(employee);
        if(r.getCode() == 0){
            return r;
        }
        Employee emp = (Employee) r.getData();
        //将员工ID放入session中
        HttpSession session = request.getSession();
        session.setAttribute("id", emp.getId());
        //仅给页面提供有限的数据
        EmployeeVo empVo = new EmployeeVo();
        BeanUtils.copyProperties(emp,empVo);
        return R.success(emp);
    }

}
