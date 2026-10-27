CREATE DATABASE IF NOT EXISTS smartfind_aiot
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE smartfind_aiot;

CREATE TABLE IF NOT EXISTS users (
  user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  display_name VARCHAR(120) NOT NULL,
  email VARCHAR(190) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role ENUM('STUDENT','STAFF','ADMIN') NOT NULL DEFAULT 'STUDENT',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS item_reports (
  report_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  reporter_id BIGINT NOT NULL,
  report_type ENUM('LOST','FOUND') NOT NULL,
  category VARCHAR(80) NOT NULL,
  description TEXT NOT NULL,
  approximate_location VARCHAR(255),
  incident_date DATE,
  photo_path VARCHAR(500),
  status ENUM('OPEN','UNDER_REVIEW','CLAIMED','RETURNED','CLOSED') NOT NULL DEFAULT 'OPEN',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_report_user FOREIGN KEY (reporter_id) REFERENCES users(user_id),
  INDEX idx_report_type_status (report_type, status),
  FULLTEXT INDEX ft_report_text (description, category, approximate_location)
);

CREATE TABLE IF NOT EXISTS match_candidates (
  match_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  source_report_id BIGINT NOT NULL,
  candidate_report_id BIGINT NOT NULL,
  similarity_score DECIMAL(6,5) NOT NULL,
  review_status ENUM('SUGGESTED','REVIEWED','REJECTED','CONFIRMED') NOT NULL DEFAULT 'SUGGESTED',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_match_source FOREIGN KEY (source_report_id) REFERENCES item_reports(report_id),
  CONSTRAINT fk_match_candidate FOREIGN KEY (candidate_report_id) REFERENCES item_reports(report_id),
  CONSTRAINT uq_match_pair UNIQUE (source_report_id, candidate_report_id),
  CONSTRAINT chk_different_reports CHECK (source_report_id <> candidate_report_id)
);

CREATE TABLE IF NOT EXISTS collection_points (
  collection_point_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  location VARCHAR(255) NOT NULL,
  device_id VARCHAR(100) NOT NULL UNIQUE,
  device_secret_hash VARCHAR(255),
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS intake_events (
  intake_event_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  event_uuid VARCHAR(100) NOT NULL UNIQUE,
  collection_point_id BIGINT NOT NULL,
  report_id BIGINT NULL,
  event_type ENUM('ITEM_RECEIVED','ITEM_STORED','ITEM_COLLECTED') NOT NULL,
  device_timestamp DATETIME NULL,
  received_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  event_status ENUM('ACCEPTED','REJECTED','NEEDS_REVIEW') NOT NULL DEFAULT 'NEEDS_REVIEW',
  payload_json JSON NULL,
  CONSTRAINT fk_intake_point FOREIGN KEY (collection_point_id) REFERENCES collection_points(collection_point_id),
  CONSTRAINT fk_intake_report FOREIGN KEY (report_id) REFERENCES item_reports(report_id),
  INDEX idx_intake_received (received_at)
);

CREATE TABLE IF NOT EXISTS claims (
  claim_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  report_id BIGINT NOT NULL,
  claimant_id BIGINT NOT NULL,
  private_evidence_note TEXT,
  verification_status ENUM('PENDING','VERIFIED','REJECTED','MORE_INFO_REQUIRED') NOT NULL DEFAULT 'PENDING',
  reviewed_by BIGINT NULL,
  reviewed_at DATETIME NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_claim_report FOREIGN KEY (report_id) REFERENCES item_reports(report_id),
  CONSTRAINT fk_claim_user FOREIGN KEY (claimant_id) REFERENCES users(user_id),
  CONSTRAINT fk_claim_reviewer FOREIGN KEY (reviewed_by) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS handovers (
  handover_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  claim_id BIGINT NOT NULL UNIQUE,
  verified_by BIGINT NOT NULL,
  handed_over_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  confirmation_note TEXT,
  CONSTRAINT fk_handover_claim FOREIGN KEY (claim_id) REFERENCES claims(claim_id),
  CONSTRAINT fk_handover_staff FOREIGN KEY (verified_by) REFERENCES users(user_id)
);
