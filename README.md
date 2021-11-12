##### 3D configurator Backend application

Implement backenend  application that will be used by the frontend application to retrieve and manage configuration data of the model (3D object) stored in database using **NodeJs and MongoDb**.
- 3D object can be a BoxGeometry, ConeGeometry, CylinderGeometry or SphereGeometry.

##### Overview endpoints


|  Methods       |ASCII                          |HTML                                                                     |
|----------------|-------------------------------|------------------------------------------------------------------------
|Post            |/shape/add                     |create new 3D object and store it in data base                           |
|Put             |/shape/update?id=              |update configuration data of the selected object                         |
|get             |/shape/get?id=                 |retrives configuration data of the selected id                           |
|get             |/shape/getAll                  |retrives configuration data of all objects stored in database            |
|delete          |/shape/delete?id=              |remove selected object from database                                     |

