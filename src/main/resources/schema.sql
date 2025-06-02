-- 科目テーブルの作成
CREATE TABLE subjects (
  id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 科目ID（自動採番）
  name VARCHAR(255) NOT NULL           -- 科目名（例: テスト基礎, AWS基礎, DevOps）
);

-- 休講情報テーブルの作成
CREATE TABLE class_pause (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 休講情報ID（自動採番）
    subject_id BIGINT NOT NULL,           -- 科目ID（subjectsテーブルのIDを参照）
    date DATE NOT NULL,                   -- 対象日（休講日）
    period INT NOT NULL,                  -- 対象時限（例: 1限, 2限）
    type VARCHAR(50) NOT NULL,            -- 休講の種類（例: 振替, 通常休講）
    substitute_date DATE NOT NULL,        -- 振替日（振替がある場合のみ）
    substitute_period INT,                -- 振替時限（NULL許可に修正）
    remarks TEXT,                         -- 備考（補足情報やメモ）
    FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE -- 科目IDに紐づく外部キー制約
);
