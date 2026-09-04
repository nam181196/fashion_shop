CREATE TABLE crawler_execution (
    id BIGSERIAL PRIMARY KEY,
    bank_code VARCHAR(50) NOT NULL,
    started_at TIMESTAMP WITH TIME ZONE NOT NULL,
    finished_at TIMESTAMP WITH TIME ZONE,
    status VARCHAR(20) NOT NULL,
    records_fetched INT DEFAULT 0,
    records_saved INT DEFAULT 0,
    error_message TEXT
);
