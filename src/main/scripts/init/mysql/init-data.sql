-- ==================================================================
-- SAMPLE DATA
-- ==================================================================

-- ------------------------------------------------------------------
-- JSLOG_APP
TRUNCATE JSLOG_APP;
INSERT INTO JSLOG_APP (
	ID, CREATE_USER, CREATE_TIME, MODIFY_USER, MODIFY_TIME,
	SYS_STATUS, NAME, APP_KEY, SITE)
VALUES (
	1, 'SYSTEM', SYSDATE(), 'SYSTEM', SYSDATE(),
	'Y', 'AttractionSuite_dev', '3d9a24a783494c0b9ef6eb88a811a4a5', 'http://localhost:8080/AttractionSuite'
);
INSERT INTO JSLOG_APP (
	ID, CREATE_USER, CREATE_TIME, MODIFY_USER, MODIFY_TIME,
	SYS_STATUS, NAME, APP_KEY, SITE)
VALUES (
	2, 'SYSTEM', SYSDATE(), 'SYSTEM', SYSDATE(),
	'Y', 'AttractionSuite_prod', 'f3e24a542d744b2b81892de2c332816a', 'https://booking.attractionsuite.com/Attraction/'
);


-- ------------------------------------------------------------------
-- JSLOG_USER
TRUNCATE JSLOG_USER;
INSERT INTO JSLOG_USER (
	ID, CREATE_USER, CREATE_TIME, MODIFY_USER, MODIFY_TIME,
	SYS_STATUS, USERNAME, FULLNAME, EMAIL, PASSWORD, DESCRIPTION)
VALUES (
	1, 'SYSTEM', SYSDATE(), 'SYSTEM', SYSDATE(),
	'Y', 'softpower', 'softpower', 'mis@softpower.com.tw', '$2a$10$JVwzn6zqiyOlwNxjAyEkTubecL2Ag1ccSR4QYBibMsLg.8sMqdx7a', NULL
);


-- ------------------------------------------------------------------
-- JSLOG_APP_USER
TRUNCATE JSLOG_APP_USER;
INSERT INTO JSLOG_APP_USER (
	APP_ID, USER_ID)
VALUES (
	1, 1
);
INSERT INTO JSLOG_APP_USER (
	APP_ID, USER_ID)
VALUES (
	2, 1
);


-- ------------------------------------------------------------------
-- JSLOG_CLIENT
TRUNCATE JSLOG_CLIENT;

-- ------------------------------------------------------------------
-- JSLOG_FEEDBACK
TRUNCATE JSLOG_FEEDBACK;

-- ------------------------------------------------------------------
-- JSLOG_ERROR
TRUNCATE JSLOG_ERROR;

-- ------------------------------------------------------------------
-- JSLOG_SCREENSHOT
TRUNCATE JSLOG_SCREENSHOT;

-- ------------------------------------------------------------------
-- JSLOG_SOURCECODE
TRUNCATE JSLOG_SOURCECODE;
