package com.example.classpausescheduler.mapper;

import com.example.classpausescheduler.entity.ClassPause;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassPauseMapper {
    @Select("SELECT * FROM class_pause")
    List<ClassPause> selectAllclasspause();
}
