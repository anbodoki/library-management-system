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
INSERT INTO lms_v1.api_url (id, url) VALUES (14, '/atom/material-type-api/quick-find');
INSERT INTO lms_v1.api_url (id, url) VALUES (15, '/atom/material-type-api/find');
INSERT INTO lms_v1.api_url (id, url) VALUES (16, '/atom/material-type-api/update');
INSERT INTO lms_v1.api_url (id, url) VALUES (17, '/atom/material-type-api/save');
INSERT INTO lms_v1.api_url (id, url) VALUES (18, '/atom/material-type-api/materialType/{id}');
INSERT INTO lms_v1.api_url (id, url) VALUES (19, '/atom/resource-api/quick-find');
INSERT INTO lms_v1.api_url (id, url) VALUES (20, '/atom/resource-api/find');
INSERT INTO lms_v1.api_url (id, url) VALUES (21, '/atom/resource-api/update');
INSERT INTO lms_v1.api_url (id, url) VALUES (22, '/atom/resource-api/save');
INSERT INTO lms_v1.api_url (id, url) VALUES (23, '/atom/resource-api/resource/{id}');
INSERT INTO lms_v1.api_url (id, url) VALUES (24, '/atom/language-api/quick-find');
INSERT INTO lms_v1.api_url (id, url) VALUES (25, '/atom/language-api/find');
INSERT INTO lms_v1.api_url (id, url) VALUES (26, '/atom/language-api/update');
INSERT INTO lms_v1.api_url (id, url) VALUES (27, '/atom/language-api/save');
INSERT INTO lms_v1.api_url (id, url) VALUES (28, '/atom/language-api/language/{id}');
INSERT INTO lms_v1.api_url (id, url) VALUES (29, '/configuration/configuration-property-api/quick-find');
INSERT INTO lms_v1.api_url (id, url) VALUES (30, '/configuration/configuration-property-api/find');
INSERT INTO lms_v1.api_url (id, url) VALUES (31, '/configuration/configuration-property-api/update');
INSERT INTO lms_v1.api_url (id, url) VALUES (32, '/configuration/configuration-property-api/save');
INSERT INTO lms_v1.api_url (id, url) VALUES (33, '/configuration/configuration-property-api/delete/{id}');
INSERT INTO lms_v1.api_url (id, url) VALUES (34, '/atom/resource-api/find-by-id/{id}');
INSERT INTO lms_v1.api_url (id, url) VALUES (35, '/atom/category-api/quick-find');
INSERT INTO lms_v1.api_url (id, url) VALUES (36, '/atom/category-api/find');
INSERT INTO lms_v1.api_url (id, url) VALUES (37, '/atom/category-api/update');
INSERT INTO lms_v1.api_url (id, url) VALUES (38, '/atom/category-api/save');
INSERT INTO lms_v1.api_url (id, url) VALUES (39, '/atom/category-api/category/{id}');
INSERT INTO lms_v1.api_url (id, url) VALUES (40, '/client/school-api/quick-find');
INSERT INTO lms_v1.api_url (id, url) VALUES (41, '/client/school-api/find');
INSERT INTO lms_v1.api_url (id, url) VALUES (42, '/client/school-api/update');
INSERT INTO lms_v1.api_url (id, url) VALUES (43, '/client/school-api/save');
INSERT INTO lms_v1.api_url (id, url) VALUES (44, '/client/school-api/language/{id}');
INSERT INTO lms_v1.api_url (id, url) VALUES (45, '/client/client-api/quick-find');
INSERT INTO lms_v1.api_url (id, url) VALUES (46, '/client/client-api/find');
INSERT INTO lms_v1.api_url (id, url) VALUES (47, '/client/client-api/activate');
INSERT INTO lms_v1.api_url (id, url) VALUES (48, '/client/client-api/deactivate');
INSERT INTO lms_v1.api_url (id, url) VALUES (49, '/atom/resource-api/add-resource-copy');
INSERT INTO lms_v1.api_url (id, url) VALUES (50, '/atom/resource-api/resource-copy/{id}');
INSERT INTO lms_v1.api_url (id, url) VALUES (51, '/atom/resource-api/get-resource-copy-borrow-history');
INSERT INTO lms_v1.api_url (id, url) VALUES (52, '/atom/resource-api/get-client-resource-copy-borrow-history');
INSERT INTO lms_v1.api_url (id, url) VALUES (53, '/client/client-api/update');

-- privilege inserts
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (1, 'user_view', 'Security', 'User View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (2, 'user_manage', 'Security', 'User Manage');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (3, 'role_view', 'Security', 'Role View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (4, 'role_manage', 'Security', 'Role Manage');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (5, 'material_type_view', 'Resource', 'Material Type View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (6, 'material_type_manage', 'Resource', 'Material Type Manage');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (7, 'resource_view', 'Resource', 'Resource View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (8, 'resource_manage', 'Resource', 'Resource Manage');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (9, 'language_view', 'Resource', 'Language View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (10, 'language_manage', 'Resource', 'Language Manage');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (11, 'configuration_property_view', 'Configuration', 'Configuration Property View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (12, 'configuration_property_manage', 'Configuration', 'Configuration Property Manage');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (13, 'category_view', 'Resource', 'Category View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (14, 'category_manage', 'Resource', 'Category Manage');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (15, 'school_view', 'Client', 'School View');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (16, 'school_manage', 'Client', 'School Manage');
INSERT INTO lms_v1.privilege (id, code, group_name, name) VALUES (17, 'client_view', 'Client', 'Client View');
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
-- resource view
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (7, 19);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (7, 20);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (7, 34);
-- resource manage
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (8, 21);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (8, 22);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (8, 23);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (8, 49);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (8, 50);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (8, 51);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (8, 52);
-- language view
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (9, 24);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (9, 25);
-- language manage
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (10, 26);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (10, 27);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (10, 28);
-- configuration view
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (11, 29);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (11, 30);
-- configuration manage
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (12, 31);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (12, 32);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (12, 33);
-- category view
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (13, 35);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (13, 36);
-- category manage
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (14, 37);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (14, 38);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (14, 39);
-- school view
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (15, 40);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (15, 41);
-- school manage
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (16, 42);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (16, 43);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (16, 44);
-- client view
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (17, 45);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (17, 46);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (17, 47);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (17, 48);
INSERT INTO lms_v1.privilege_urls (privilege_id, urls_id) VALUES (17, 53);

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

