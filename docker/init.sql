CREATE TABLE IF NOT EXISTS todos (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    completed BOOLEAN DEFAULT FALSE
);

INSERT INTO todos (title, completed) VALUES
     ('Learn Spring Boot', FALSE),
     ('Build a Todo App', FALSE),
     ('Test Docker Integration', TRUE);
