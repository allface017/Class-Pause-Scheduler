package com.example.classpausescheduler.service;

import com.example.classpausescheduler.entity.ClassPause;
import com.example.classpausescheduler.repository.ClassPauseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassPauseService {

    private final ClassPauseRepository classPauseRepository;

    public ClassPauseService(ClassPauseRepository classPauseRepository) {
        this.classPauseRepository = classPauseRepository;
    }

    public List<ClassPause> getAllClassPause() {
        return classPauseRepository.getAllClassPause();
    }
}
