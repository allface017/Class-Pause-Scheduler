package com.example.classpausescheduler.mapper;


import com.example.classpausescheduler.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubjectMapper {
    @Select("SELECT * FROM subjects")
    List<Subject> selectAllSubject();

}
