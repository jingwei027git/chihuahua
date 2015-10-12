-- ==================================================================
-- TABLES - MySQL
-- ==================================================================

-- VW_JSLOG_ERROR_CLIENT
DROP VIEW IF EXISTS `VW_JSLOG_ERROR_CLIENT`;
CREATE VIEW `VW_JSLOG_ERROR_CLIENT` AS (
SELECT t.id, t.create_user, t.create_time, t.modify_user, t.modify_time,
       t.app_id, t.client_id, t.screenshot_id, t.sourcecode_id,
       t.err_msg, t.url, t.line, t.col, t.err_count, t.err_obj,
       c.navigator_app_version, c.navigator_cookie_enabled, c.navigator_language, c.navigator_platform, c.navigator_user_agent, c.navigator_vendor,
       c.screen_width, c.screen_height, c.screen_avail_width, c.screen_avail_height, c.screen_color_depth, c.screen_pixel_depth,
       c.location_href, c.location_hostname, c.location_pathname, c.location_protocol,
       c.document_width, c.document_height
  FROM jslog_error t
  JOIN jslog_client c ON (c.id = t.client_id)
);
