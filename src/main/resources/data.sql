INSERT INTO subjects (id,name)
VALUES
    (1, 'テスト基礎'),
    (2, 'AWS基礎'),
    (3, 'DevOps');
INSERT INTO class_pause
    (subject_id, date,
    period,type, substitute_date,
    substitute_period,remarks)
VALUES
    (1, '2024-06-10', 1, '休講', '2024-06-17', 1, '担当教員体調不良'),
    (2, '2024-06-11', 2, '補講', '2024-06-18', 2, '補講日程調整済み'),
    (3, '2024-06-12', 3, '休講', '2024-06-19', 3, '会議のため休講'),
    (1, '2024-06-13', 4, '補講', '2024-06-20', 4, '補講日程確定'),
    (2, '2024-06-14', 5, '休講', '2024-06-21', 1, 'その他理由');
