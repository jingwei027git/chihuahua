-- ==================================================================
-- SAMPLE DATA
-- ==================================================================

-- ------------------------------------------------------------------
-- JSLOG_APP
INSERT INTO JSLOG_APP (
	ID, CREATE_USER, CREATE_TIME, MODIFY_USER, MODIFY_TIME,
	SYS_STATUS, NAME, APP_KEY, SITE)
VALUES (
	1, 'SYSTEM', SYSDATE(), 'SYSTEM', SYSDATE(),
	'Y', 'AttractionSuite_dev', '3d9a24a783494c0b9ef6eb88a811a4a5', 'http://localhost:8080/AttractionSuite'
);


-- ------------------------------------------------------------------
-- JSLOG_USER
INSERT INTO JSLOG_USER (
	ID, CREATE_USER, CREATE_TIME, MODIFY_USER, MODIFY_TIME,
	SYS_STATUS, USERNAME, FULLNAME, EMAIL, PASSWORD, DESCRIPTION)
VALUES (
	1, 'SYSTEM', SYSDATE(), 'SYSTEM', SYSDATE(),
	'Y', 'softpower', 'softpower', 'mis@softpower.com.tw', '$2a$10$JVwzn6zqiyOlwNxjAyEkTubecL2Ag1ccSR4QYBibMsLg.8sMqdx7a', NULL
);


-- ------------------------------------------------------------------
-- JSLOG_APP_USER
INSERT INTO JSLOG_APP_USER (
	APP_ID, USER_ID)
VALUES (
	1, 1
);


-- ------------------------------------------------------------------
-- JSLOG_CLIENT


-- ------------------------------------------------------------------
-- JSLOG_FEEDBACK


-- ------------------------------------------------------------------
-- JSLOG_ONERROR


-- ------------------------------------------------------------------
-- JSLOG_SCREENSHOT

