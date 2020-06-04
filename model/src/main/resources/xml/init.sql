SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE `attack_path`;
TRUNCATE TABLE `attack_model`;
TRUNCATE TABLE `security_variant`;
TRUNCATE TABLE `security_threat`;
TRUNCATE TABLE `security_function`;
TRUNCATE TABLE `service_testcase`;
TRUNCATE TABLE `single_test`;
TRUNCATE TABLE `device_threat`;
TRUNCATE TABLE `device_security`;
TRUNCATE TABLE `user_privilege`;
TRUNCATE TABLE `device_privilege`;
TRUNCATE TABLE `device_dataflow`;
TRUNCATE TABLE `device_service`;
TRUNCATE TABLE `device_route`;
TRUNCATE TABLE `device_network`;
TRUNCATE TABLE `device_connect`;
TRUNCATE TABLE `device`;
SET FOREIGN_KEY_CHECKS=1;










COMMIT;
