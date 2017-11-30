INSERT INTO T_Server(ipAddress, port) VALUES('192.168.240.129', 8080);

INSERT INTO T_Application(name, size, packageName, startActivity) VALUES('ZAKER',0,'com.myzaker.ZAKER_Phone','com.myzaker.ZAKER_Phone.view.LogoActivity');

INSERT INTO T_Emulator(server_id, serialNumber, os, width, height, dpi, state) VALUES(1,'emulator-5554', 'Android 2.3.3',320,480,'320*480',1);

INSERT INTO T_Deployment(application_id, emulator_id) VALUES(1,1);
