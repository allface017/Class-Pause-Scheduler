package com.example.classpausescheduler.controller;

import com.example.classpausescheduler.entity.ClassPause;
import com.example.classpausescheduler.entity.Subject;
import com.example.classpausescheduler.service.ClassPauseService;
import com.example.classpausescheduler.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/classpause")
public class ClassPauseApiController {

    private final ClassPauseService classPauseService;
    private final SubjectService subjectService;

    public ClassPauseApiController(ClassPauseService classPauseService, SubjectService subjectService) {
        this.classPauseService = classPauseService;
        this.subjectService = subjectService;
    }

    @GetMapping("/events")
    public ResponseEntity<List<Map<String, Object>>> getClassPauseEvents(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end) {

        // デバッグログ
        System.out.println("API呼び出し: /api/classpause/events");
        System.out.println("パラメータ: start=" + start + ", end=" + end);

        // 全ての休講情報を取得
        List<ClassPause> classPauses = classPauseService.getAllClassPause();
        System.out.println("取得した休講情報数: " + classPauses.size());

        // 科目情報をマップに変換（ID→名前）
        Map<Long, String> subjectIdToName = subjectService.getAllSubject().stream()
                .collect(Collectors.toMap(Subject::getId, Subject::getName));
        System.out.println("取得した科目情報数: " + subjectIdToName.size());

        // カレンダー表示用のイベントリストに変換
        List<Map<String, Object>> events = new ArrayList<>();

        if (classPauses.isEmpty()) {
            // テスト用のダミーイベントを追加（データがない場合）
            Map<String, Object> testEvent = new HashMap<>();
            testEvent.put("id", "test-1");
            testEvent.put("title", "テスト休講イベント");
            testEvent.put("start", LocalDate.now().toString());
            testEvent.put("allDay", true);
            testEvent.put("classNames", "class-pause-event");
            testEvent.put("period", 2);
            testEvent.put("type", "pause");
            testEvent.put("remarks", "テスト用のイベントです");

            events.add(testEvent);
            System.out.println("テストイベントを追加しました");
        } else {
            for (ClassPause cp : classPauses) {
                try {
                    // 休講情報をイベントとして追加
                    Map<String, Object> pauseEvent = new HashMap<>();
                    String subjectName = subjectIdToName.getOrDefault(cp.getSubjectId(), "未設定の科目");

                    pauseEvent.put("id", "pause-" + cp.getId());
                    pauseEvent.put("title", subjectName + " (休講)");
                    pauseEvent.put("start", cp.getDate().toString());
                    pauseEvent.put("allDay", true); // 終日イベントとして表示
                    pauseEvent.put("classNames", "class-pause-event");
                    pauseEvent.put("period", cp.getPeriod());
                    pauseEvent.put("type", "pause");
                    pauseEvent.put("remarks", cp.getRemarks() != null ? cp.getRemarks() : "");

                    events.add(pauseEvent);
                    System.out.println("休講イベント追加: " + pauseEvent.get("title") + " - " + pauseEvent.get("start"));

                    // 補講情報がある場合は別のイベントとして追加
                    if (cp.getSubstitute_date() != null) {
                        Map<String, Object> substituteEvent = new HashMap<>();

                        substituteEvent.put("id", "substitute-" + cp.getId());
                        substituteEvent.put("title", subjectName + " (補講)");
                        substituteEvent.put("start", cp.getSubstitute_date().toString());
                        substituteEvent.put("allDay", true); // 終日イベントとして表示
                        substituteEvent.put("classNames", "substitute-event");
                        substituteEvent.put("period", cp.getSubstitute_period());
                        substituteEvent.put("type", "substitute");
                        substituteEvent.put("remarks", cp.getRemarks() != null ? cp.getRemarks() : "");

                        events.add(substituteEvent);
                        System.out.println("補講イベント追加: " + substituteEvent.get("title") + " - " + substituteEvent.get("start"));
                    }
                } catch (Exception e) {
                    System.err.println("イベント変換中にエラーが発生: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        return ResponseEntity.ok(events);
    }
}
