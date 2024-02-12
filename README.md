Please change the database configurations in the application.properties file.

Links for the API
### http://localhost:8080/api/v1/createGateway
### http://localhost:8080/api/v1/getAllGateways
### http://localhost:8080/api/v1/getGatewayBySerialNumber/mon
### http://localhost:8080/api/v1/updateGateway/mon
### http://localhost:8080/api/v1/deleteGateway/mon
### http://localhost:8080/api/v1/updateGatewayName/abc
### http://localhost:8080/api/v1/createPeripheral/abc
### http://localhost:8080/api/v1/getAllPeripherals
### http://localhost:8080/api/v1/getPeripheralByUID/13
### http://localhost:8080/api/v1/updatePeripheral/13
### http://localhost:8080/api/v1/deletePeripheral/13
### http://localhost:8080/api/v1/updatePeripheralStatus/12

Link for the POSTMAN
### https://www.postman.com/uomminions/workspace/networkmanagementsystem/collection/26097316-22427fc2-e366-4042-9f23-f943f25044b5?action=share&creator=26097316

Assumptions
1. I assume that in order to create a peripheral for given gateway, gateway should be in the database. Therefore, to save a peripheral, Serial number should be passed in the URL. If that serial number is not available, save a peripheral will not be successful. Even though we can give a serial number in the body, that given serial number will be ignored and serial number in the URL will be considered.
2. I have validated the status of the peripheral. Status of the peripheral should be either "online" or "offline". If user enter any other value than the given value, that will not get updated.
3. In the patchmapping method, I use update the status of the peripheral and update the name of the gateway.
