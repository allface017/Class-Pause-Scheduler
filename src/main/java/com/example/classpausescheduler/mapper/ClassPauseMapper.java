package com.example.classpausescheduler.mapper;

import com.example.classpausescheduler.entity.ClassPause;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface ClassPauseMapper {
    @Select("SELECT * FROM class_pause")
    List<ClassPause> selectAllclasspause();

    // 休講情報の登録
    @Insert("INSERT INTO class_pause (subject_id, date, period, type, substitute_date, substitute_period, remarks) VALUES (#{subjectId}, #{date}, #{period}, #{type}, #{substitute_date}, #{substitute_period}, #{remarks})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertClassPause(ClassPause classPause);
}
