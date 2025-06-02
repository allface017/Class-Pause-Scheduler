package com.example.classpausescheduler.repository;

import com.example.classpausescheduler.entity.Subject;
import com.example.classpausescheduler.mapper.SubjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectRepository {
    private SubjectMapper subjectMapper;

    public SubjectRepository(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    public List<Subject> getAllSubject() {
        return subjectMapper.selectAllSubject();
    }
}
