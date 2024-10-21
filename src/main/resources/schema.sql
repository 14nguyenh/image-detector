CREATE TABLE image
(
    uuid   UUID PRIMARY KEY,
    name   VARCHAR(255),
    width  INT,
    height INT,
    type   INT,
    size   INT
);

CREATE TABLE detectedObject
(
    uuid       UUID PRIMARY KEY,
    fkImage    UUID         NOT NULL,
    name       VARCHAR(255) NOT NULL,
    confidence DOUBLE       NOT NULL,
    FOREIGN KEY (fkImage) REFERENCES image (uuid) ON DELETE CASCADE
);