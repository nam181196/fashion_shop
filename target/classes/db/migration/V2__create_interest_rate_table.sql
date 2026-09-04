CREATE TABLE interest_rate (
    id BIGSERIAL PRIMARY KEY,
    bank_id BIGINT NOT NULL REFERENCES bank(id),
    deposit_type VARCHAR(50) NOT NULL,
    term_months INT,
    term_label VARCHAR(100),
    interest_rate DECIMAL(5,2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    channel VARCHAR(50) NOT NULL,
    collected_at TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_interest_rate_bank_id ON interest_rate(bank_id);
CREATE INDEX idx_interest_rate_term ON interest_rate(term_months);
CREATE INDEX idx_interest_rate_collected_at ON interest_rate(collected_at);
