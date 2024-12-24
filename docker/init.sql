CREATE TABLE IF NOT EXISTS todo (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    completed BOOLEAN DEFAULT FALSE
);

INSERT INTO todo (title, completed) VALUES
     ('Learn Spring Boot', FALSE),
     ('Build a Todo App', FALSE),
     ('Test Docker Integration', TRUE);
