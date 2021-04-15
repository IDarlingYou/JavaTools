package com.ly.tools.service.impl;

import com.ly.tools.entity.po.Teacher;
import com.ly.tools.mapper.TeacherMapper;
import com.ly.tools.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LY
 * @Date: 2021/4/7 10:57
 * @Description:
 **/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> selectAllTeacher() {
        return teacherMapper.getAcctCharInfo();
    }
}
