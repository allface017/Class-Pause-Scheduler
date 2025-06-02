package com.example.classpausescheduler.controller;

import com.example.classpausescheduler.entity.ClassPause;
import com.example.classpausescheduler.entity.Subject;
import com.example.classpausescheduler.service.ClassPauseService;
import com.example.classpausescheduler.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ClassPauseService classPauseService;
    private final SubjectService subjectService;

    public HomeController(ClassPauseService classPauseService, SubjectService subjectService) {
        this.classPauseService = classPauseService;
        this.subjectService = subjectService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Subject> subjects = subjectService.getAllSubject();
        List<ClassPause> class_pauses = classPauseService.getAllClassPause();
        model.addAttribute("subjects", subjects);
        model.addAttribute("class_pauses", class_pauses);
        return "Home/index";
    }

    // 休講情報登録画面表示
    @GetMapping("/classpause/new")
    public String showClassPauseForm(Model model) {
        model.addAttribute("classPause", new ClassPause());
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "Home/classpause_form";
    }

    // 休講情報登録処理
    @PostMapping("/classpause/new")
    public String submitClassPause(@ModelAttribute ClassPause classPause) {
        classPauseService.insertClassPause(classPause);
        return "redirect:/";
    }
}
