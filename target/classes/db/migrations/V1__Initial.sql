-- Active: 1695017461309@@127.0.0.1@5432@chat@public

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255), 
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE chat_rooms (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE chat_room_users (
    chat_room_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (chat_room_id) REFERENCES chat_rooms (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    PRIMARY KEY (chat_room_id, user_id)
);


CREATE TABLE messages (
    id SERIAL PRIMARY KEY,
    text TEXT,
    send_date TIMESTAMP,
    chat_room_id BIGINT,
    sender_id BIGINT,
    FOREIGN KEY (chat_room_id) REFERENCES chat_rooms (id),
    FOREIGN KEY (sender_id) REFERENCES users (id)
);