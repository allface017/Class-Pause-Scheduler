package com.example.classpausescheduler.repository;

import com.example.classpausescheduler.entity.ClassPause;
import com.example.classpausescheduler.mapper.ClassPauseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassPauseRepository {

    private final ClassPauseMapper classPauseMapper;

    public ClassPauseRepository (ClassPauseMapper classPauseMapper) {
        this.classPauseMapper = classPauseMapper;
    }

    public List<ClassPause> getAllClassPause() {
        return classPauseMapper.selectAllclasspause();
    }

    // 休講情報の登録
    public void insertClassPause(ClassPause classPause) {
        classPauseMapper.insertClassPause(classPause);
    }
}
