-- TODO dockerのinit.sqlと統合すること
CREATE TABLE IF NOT EXISTS todo (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    status int DEFAULT 0
);