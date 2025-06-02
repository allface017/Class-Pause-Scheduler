package com.example.classpausescheduler.service;

import com.example.classpausescheduler.entity.Subject;
import com.example.classpausescheduler.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService (SubjectRepository subjectRepository) {
            this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubject() {
        return subjectRepository.getAllSubject();
    }
}
