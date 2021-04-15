package com.ly.tools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.tools.entity.po.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {

    List<Teacher> getAcctCharInfo();

}
