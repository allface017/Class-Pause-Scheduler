package com.example.classpausescheduler.service;

import com.example.classpausescheduler.entity.ClassPause;
import com.example.classpausescheduler.entity.Subject;
import com.example.classpausescheduler.repository.ClassPauseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ClassPauseService {

    private final ClassPauseRepository classPauseRepository;
    private final SubjectService subjectService;

    public ClassPauseService(ClassPauseRepository classPauseRepository, SubjectService subjectService) {
        this.classPauseRepository = classPauseRepository;
        this.subjectService = subjectService;
    }

    public List<ClassPause> getAllClassPause() {
        return classPauseRepository.getAllClassPause();
    }

    // 休講情報の登録
    public void insertClassPause(ClassPause classPause) {
        classPauseRepository.insertClassPause(classPause);
    }

    // 休講情報の一括登録
    public void bulkInsertClassPause(String bulkData) {
        if (bulkData == null || bulkData.trim().isEmpty()) {
            return;
        }

        // 全科目情報を取得してマップに変換（名前→ID）
        List<Subject> allSubjects = subjectService.getAllSubject();
        Map<String, Long> subjectNameToId = allSubjects.stream()
                .collect(Collectors.toMap(Subject::getName, Subject::getId));

        // テキストデータを分割して処理
        String[] sections = bulkData.split("-{5,}");

        for (String section : sections) {
            if (section.trim().isEmpty()) {
                continue;
            }

            // 科目名を抽出
            String subjectName = extractValue(section, "科目名：(.+)");
            if (subjectName == null || !subjectNameToId.containsKey(subjectName)) {
                continue; // 該当する科目が見つからない場合はスキップ
            }
            Long subjectId = subjectNameToId.get(subjectName);

            // 対象日と振替日のセクションを抽出
            String targetDatesText = extractSection(section, "対象日：", "振替日：");
            String substituteDatesText = extractSection(section, "振替日：", null);

            // 日付と時限の抽出パターン（例: 5/7(水) 1限）
            Pattern datePattern = Pattern.compile("(\\d+)/(\\d+)\\([^\\)]+\\)\\s*(\\d+)限");

            // 対象日を解析
            List<DatePeriodPair> targetDates = parseDates(targetDatesText, datePattern);

            // 振替日を解析
            List<DatePeriodPair> substituteDates = parseDates(substituteDatesText, datePattern);

            // 休講情報を作成して登録
            for (int i = 0; i < targetDates.size(); i++) {
                ClassPause classPause = new ClassPause();
                classPause.setSubjectId(subjectId);
                classPause.setDate(targetDates.get(i).date);
                classPause.setPeriod(targetDates.get(i).period);
                classPause.setType("休講");

                // 振替日がある場合は設定
                if (i < substituteDates.size()) {
                    classPause.setSubstitute_date(substituteDates.get(i).date);
                    classPause.setSubstitute_period(substituteDates.get(i).period);
                }

                // 登録処理
                insertClassPause(classPause);
            }
        }
    }

    // セクションからパターンに一致する値を抽出
    private String extractValue(String text, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        return m.find() ? m.group(1).trim() : null;
    }

    // 開始キーワードと終了キーワードの間のテキストを抽出
    private String extractSection(String text, String startKeyword, String endKeyword) {
        int startIndex = text.indexOf(startKeyword);
        if (startIndex == -1) {
            return "";
        }
        startIndex += startKeyword.length();

        int endIndex = endKeyword != null ? text.indexOf(endKeyword, startIndex) : text.length();
        if (endIndex == -1) {
            endIndex = text.length();
        }

        return text.substring(startIndex, endIndex).trim();
    }

    // 日付と時限のペアをパースして返す
    private List<DatePeriodPair> parseDates(String text, Pattern pattern) {
        List<DatePeriodPair> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        int currentYear = LocalDate.now().getYear();

        while (matcher.find()) {
            int month = Integer.parseInt(matcher.group(1));
            int day = Integer.parseInt(matcher.group(2));
            int period = Integer.parseInt(matcher.group(3));

            // 年月日から LocalDate を作成（月が12より大きい場合は次の年として扱う）
            LocalDate date = LocalDate.of(month > 12 ? currentYear + 1 : currentYear, month, day);

            result.add(new DatePeriodPair(date, period));
        }

        return result;
    }

    // 日付と時限のペアを格納するための内部クラス
    private static class DatePeriodPair {
        private final LocalDate date;
        private final int period;

        public DatePeriodPair(LocalDate date, int period) {
            this.date = date;
            this.period = period;
        }
    }
}
