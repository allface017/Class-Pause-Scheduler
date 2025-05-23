-- 科目データの挿入
INSERT INTO subjects (id, name) VALUES
                                    (1, 'テスト基礎'),
                                    (2, 'AWS基礎'),
                                    (3, 'DevOps');

-- 休講情報データの挿入
-- テスト基礎
INSERT INTO class_pause (subject_id, date, period, type, substitute_date, substitute_period, remarks) VALUES
                                                                                                          (1, '2023-05-07', 1, '休講', NULL, NULL, NULL),
                                                                                                          (1, '2023-05-08', 2, '休講', NULL, NULL, NULL),
                                                                                                          (1, '2023-05-22', 2, '休講', NULL, NULL, NULL),
                                                                                                          (1, '2023-05-29', 4, '振替', NULL, NULL, NULL),
                                                                                                          (1, '2023-06-05', 4, '振替', NULL, NULL, NULL),
                                                                                                          (1, '2023-06-12', 4, '振替', NULL, NULL, NULL);

-- AWS基礎
INSERT INTO class_pause (subject_id, date, period, type, substitute_date, substitute_period, remarks) VALUES
                                                                                                          (2, '2023-05-08', 1, '休講', NULL, NULL, NULL),
                                                                                                          (2, '2023-05-12', 2, '休講', NULL, NULL, NULL),
                                                                                                          (2, '2023-05-22', 1, '休講', NULL, NULL, NULL),
                                                                                                          (2, '2023-06-16', 2, '休講', NULL, NULL, NULL),
                                                                                                          (2, '2023-05-20', 4, '振替', NULL, NULL, NULL),
                                                                                                          (2, '2023-05-27', 4, '振替', NULL, NULL, NULL),
                                                                                                          (2, '2023-06-03', 4, '振替', NULL, NULL, NULL),
                                                                                                          (2, '2023-06-10', 4, '振替', NULL, NULL, NULL);

-- DevOps
INSERT INTO class_pause (subject_id, date, period, type, substitute_date, substitute_period, remarks) VALUES
                                                                                                          (3, '2023-05-07', 2, '休講', NULL, NULL, NULL),
                                                                                                          (3, '2023-05-12', 1, '休講', NULL, NULL, NULL),
                                                                                                          (3, '2023-06-16', 1, '休講', NULL, NULL, NULL),
                                                                                                          (3, '2023-05-21', 4, '振替', NULL, NULL, NULL),
                                                                                                          (3, '2023-05-28', 4, '振替', NULL, NULL, NULL),
                                                                                                          (3, '2023-06-04', 4, '振替', NULL, NULL, NULL);