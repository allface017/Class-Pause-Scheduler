-- 科目テーブルの作成
CREATE TABLE subjects (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

-- 休講情報テーブルの作成
CREATE TABLE class_pause (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             subject_id BIGINT NOT NULL,
                             date DATE NOT NULL,
                             period INT NOT NULL,
                             type VARCHAR(50) NOT NULL,
                             substitute_date DATE,
                             substitute_period INT,
                             remarks TEXT,
                             FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE
);