CREATE DATABASE IF NOT EXISTS detail_db;

USE detail_db;
CREATE TABLE IF NOT EXISTS news
(
    id                    INT(11) AUTO_INCREMENT PRIMARY KEY,
    title                 VARCHAR(255) NOT NULL,
    content               TEXT,
    view                  INT(11)      NOT NULL DEFAULT 0,
    img_url               VARCHAR(400),
    video_url             VARCHAR(400),
    video_preview_img_url VARCHAR(400),
    type                  VARCHAR(20)  NOT NULL,
    `publish_time`        DATETIME     NOT NULL,
    deleted               BOOLEAN               DEFAULT false
);


INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 1", "Content 1", 100, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "sports", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 2", "Content 2", 200, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "entertainment", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 3", "Content 3", 300, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "politics", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 4", "Content 4", 400, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "tech", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 5", "Content 5 include LiHUa", 500, "https://via.placeholder.com/150",
        "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "sports", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 6", "Content 6", 600, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "entertainment", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 7", "Content 7", 700, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "politics", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 8", "Content 8", 800, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "tech", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 9", "Content 9", 900, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "sports", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 10", "Content 10", 1000, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "entertainment", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 11", "Content 11", 1100, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "politics", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`)
VALUES ("Title 12", "Content 12", 1200, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "tech", NOW());
INSERT INTO news (title, content, view, img_url, video_url, video_preview_img_url, type, `publish_time`, deleted)
VALUES ("Title 13", "Content 1", 100, "https://via.placeholder.com/150", "https://www.youtube.com/embed/dQw4w9WgXcQ",
        "https://via.placeholder.com/150x150", "sports", NOW(), true);

# =====================================================================================

USE detail_db;

CREATE TABLE IF NOT EXISTS excellent_athletes
(
    id           INT(11)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(20) NOT NULL,
    age          INT(11)     NOT NULL,
    grade        VARCHAR(10) NOT NULL,
    img_url      VARCHAR(400),
    introduction TEXT        NOT NULL
) ENGINE = InnoDB;

INSERT INTO excellent_athletes (name, age, grade, img_url, introduction)
VALUES ("John", 25, "A", "https://via.placeholder.com/150", "John is a good athlete");
INSERT INTO excellent_athletes (name, age, grade, img_url, introduction)
VALUES ("Mary", 26, "B", "https://via.placeholder.com/150", "Mary is a good athlete");
INSERT INTO excellent_athletes (name, age, grade, img_url, introduction)
VALUES ("LiHUa", 27, "C", "https://via.placeholder.com/150", "Tom is a good athlete");
INSERT INTO excellent_athletes (name, age, grade, img_url, introduction)
VALUES ("Tom", 28, "D", "https://via.placeholder.com/150", "Jane is a good athlete");


USE detail_db;
CREATE TABLE IF NOT EXISTS leader
(
    id           INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(20)  NOT NULL,
    age          INT(11)      NOT NULL,
    img_url      VARCHAR(400) ,
    introduction TEXT         NOT NULL,
    position     VARCHAR(200) NOT NULL
) ENGINE = InnoDB;

INSERT INTO leader (name, age, img_url, introduction, position)
VALUES ("张三", 30, "https://via.placeholder.com/150", "John is a good leader", "President");
INSERT INTO leader (name, age, img_url, introduction, position)
VALUES ("李四", 31, "https://via.placeholder.com/150", "Mary is a good leader", "Vice President");

USE detail_db;
CREATE TABLE IF NOT EXISTS manager
(
    id        INT(11)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(20) NOT NULL,
    password  VARCHAR(64) NOT NULL
) ENGINE = InnoDB;
INSERT INTO manager (user_name, password)
VALUES ("admin", "a26b45b84ae59f3be8b917fbad74d7775eef3f8f68741634b571caf14307c0e7");
# ==================================================================================

CREATE TABLE IF NOT EXISTS news_img
(
    id       CHAR(32) PRIMARY KEY,
    img_data LONGBLOB NOT NULL
) ENGINE = InnoDB;

# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
CREATE TABLE IF NOT EXISTS news_video
(
    id         CHAR(32) PRIMARY KEY,
    video_data LONGBLOB NOT NULL
);

# ---
CREATE TABLE IF NOT EXISTS competitions
(
    competition_id          CHAR(32) PRIMARY KEY,
    competition_name        VARCHAR(255) NOT NULL,
    start_time              DATETIME     NOT NULL,
    end_time                DATETIME     NOT NULL,
    allowed_events          TEXT         NOT NULL,
    allowed_colleges        TEXT         NOT NULL,
    college_activity_limit  INT DEFAULT 2,
    athlete_activity_limit  INT DEFAULT 2,
    same_activity_max_limit INT DEFAULT 2,
    leader_phone            CHAR(11)     NOT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS competition_details
(
    competition_id CHAR(32) NOT NULL,
    detail_id      CHAR(32) NOT NULL,
    athlete_name   VARCHAR(10),
    athlete_id     CHAR(32),
    register_event TEXT,
    college        VARCHAR(255)
) ENGINE = InnoDB;
DROP TABLE IF EXISTS competition_details;
DROP TABLE IF EXISTS competitions;
