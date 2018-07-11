Programming school management. Users belong to user groups and are assigned programming exercises they submit solutions to. This project's aim is to show basic grasp of classes, objects and MySQL queries.

- Classes: User, Group, Exercise and Solution are mirrored in MySQL database structure. Each of the classes implements basic CRUD model as well as some additional methods.
- Connection to MySQL database is implemented via externally imported MySQL Connector Driver.
- User interface is a console - based system of menus.
- It's possible to interact with the program as both user and admin - with more options accessible to the latter.
- Basic security measures are used in form of jbcrypt library to encrypt user passwords.