CREATE TABLE auftrag (
    id UUID NOT NULL,
    num VARCHAR(20),
    description VARCHAR(200),
    PRIMARY KEY (id)
);

CREATE TABLE auftrag_info_texts (
    auftrag_id UUID NOT NULL,
    order_idx INTEGER NOT NULL,
    text VARCHAR(500),
    PRIMARY KEY(auftrag_id, order_idx),
    CONSTRAINT auftrag_info_texts_auftrag_info_id FOREIGN KEY (auftrag_id) REFERENCES auftrag (id)
);

CREATE INDEX auftrag_info_texts_ux 
ON auftrag_info_texts(auftrag_id, order_idx);