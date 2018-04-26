-- urls insert
INSERT INTO lms_v1.api_url (id, url) VALUES (1, '/security/user-api/quick-find');
INSERT INTO lms_v1.api_url (id, url) VALUES (2, '/security/user-api/find');
INSERT INTO lms_v1.api_url (id, url) VALUES (3, '/security/user-api/authorized-user');
INSERT INTO lms_v1.api_url (id, url) VALUES (4, '/security/user-api/update');
INSERT INTO lms_v1.api_url (id, url) VALUES (5, '/security/user-api/save');
INSERT INTO lms_v1.api_url (id, url) VALUES (6, '/security/user-api/find-by-username/{username}');
INSERT INTO lms_v1.api_url (id, url) VALUES (7, '/security/user-api/user/{id}');
INSERT INTO lms_v1.api_url (id, url) VALUES (8, '/security/role-api/quick-find');
INSERT INTO lms_v1.api_url (id, url) VALUES (9, '/security/role-api/find');
INSERT INTO lms_v1.api_url (id, url) VALUES (10, '/security/role-api/update');
INSERT INTO lms_v1.api_url (id, url) VALUES (11, '/security/role-api/save');
INSERT INTO lms_v1.api_url (id, url) VALUES (12, '/security/role-api/delete/{id}');
INSERT INTO lms_v1.api_url (id, url) VALUES (13, '/security/role-api/privileges');
INSERT INTO lms_v1.api_url (id, url) VALUES (14, 'atom/material-type-api/quick-find');
INSERT INTO lms_v1.api_url (id, url) VALUES (15, 'atom/material-type-api/find');
INSERT INTO lms_v1.api_url (id, url) VALUES (16, 'atom/material-type-api/update');
INSERT INTO lms_v1.api_url (id, url) VALUES (17, 'atom/material-type-api/save');
INSERT INTO lms_v1.api_url (id, url) VALUES (18, 'atom/material-type-api/materialType/{id}');

-- privilege inserts
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (1, 'user_view', 'Security', 'User View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (2, 'user_manage', 'Security', 'User Manage');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (3, 'role_view', 'Security', 'Role View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (4, 'role_manage', 'Security', 'Role Manage');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (5, 'material_type_view', 'Resource', 'Material Type View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (6, 'material_type_manage', 'Resource', 'Material Type Manage');
-- URLS
-- user view
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (1, 1);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (1, 2);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (1, 3);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (1, 6);
-- user manage
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (2, 4);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (2, 5);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (2, 7);
-- role view
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (3, 8);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (3, 9);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (3, 13);
-- role manage
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (4, 10);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (4, 11);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (4, 12);
-- material type view
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (5, 14);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (5, 15);
-- material type manage
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (6, 16);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (6, 17);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (6, 18);

-- user role
INSERT INTO lms_v1.userrole (id, color, name) VALUES (1, '#fdad9e', 'role');
-- user role privileges
INSERT INTO lms_v1.userrole_role_privileges (role_id, role_privileges_id) VALUES (1, 1);
INSERT INTO lms_v1.userrole_role_privileges (role_id, role_privileges_id) VALUES (1, 2);
INSERT INTO lms_v1.userrole_role_privileges (role_id, role_privileges_id) VALUES (1, 3);
INSERT INTO lms_v1.userrole_role_privileges (role_id, role_privileges_id) VALUES (1, 4);
-- system user
INSERT INTO lms_v1.systemuser (id, creation_date, email, first_name, last_name, modification_date, password, phone, username, active)
VALUES (1, null, 'admin.admin@gmail.com', 'Admin', 'Admin', null, '$2a$10$/awf01qGA8.9tmMljOfwQuYXJn/H80dtEAct214u.ihhx0EVjDZBG', '123123123', 'admin', true);
-- system user and user role
INSERT INTO lms_v1.systemuser_roles (user_id, roles_id) VALUES (1, 1);

