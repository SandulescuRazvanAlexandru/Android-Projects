# Android Room Framework & SQLite Integration

This README provides a comprehensive overview of the Android Room framework and how it integrates with the SQLite database system.

## In-Depth Information

### SQLite
- **SQLite**: Android platform uses SQLite as its database management system. It's an open-source SQL database that stores data in a text file on the mobile device. Being natively integrated into the OS, SQLite boasts all the features of a relational database. Using SQLite in mobile applications doesn't require a connector like JDBC or ODBC. Data types supported by SQLite are: TEXT, NUMERIC, REAL, INTEGER, BLOB.

### Room
- **Room**: This is a framework providing an abstraction layer over SQLite, simplifying mobile application access to the database. The framework offers annotations that can be applied to classes, attributes, or methods. These annotations generate the necessary code for performing DDL and DML operations on the database. Room has three main components:
  * _**database**_: An abstract class extending RoomDatabase, responsible for designing the database and managing connections between the mobile application and SQLite. It contains a list of tables and abstract methods returning instances of classes annotated with @Dao.
  * _**entity**_: Represented by Java classes annotated with @Entity, which are the database tables.
  * _**Dao**_: Represented by Java interfaces annotated with @Dao. Their purpose is to conduct DML operations on the previously defined entities.

For more details about Room, you can find it [here](https://developer.android.com/training/data-storage/room).

### Annotations
- **@Database**: Used at the level of the abstract class representing the database manager. This annotation has three properties:
  * entities: Contains a list of Java classes annotated with @Entity, representing the database tables.
  * exportSchema: A boolean variable, ensuring the creation of a file on the mobile device containing the database schema, if set to true.
  * version: An int variable representing the database version.
- **@Dao**: Used for a Java interface, has no properties.
- **@Entity**: Used for Java classes, representing the database table structure. Contains a String property specifying the table name.
- **@ColumnInfo**: Applied to fields within the class annotated with @Entity, representing a table column.
- **@Insert**: Applied to a method within the interface annotated with @Dao, allowing the compiler to extract the SQL script for the insert operation at runtime.
- **@Update**: Applied to a method within the interface annotated with @Dao, allowing the compiler to extract the SQL script for the update operation at runtime.
- **@Delete**: Applied to a method within the interface annotated with @Dao, allowing the compiler to extract the SQL script for the delete operation at runtime.
- **@Query**: Applied to a method within the interface annotated with @Dao. Contains a mandatory property with the SQL code representing the select statement that Room calls at execution.
- **@TypeConverter**: Applied to methods, ensuring conversion between two heterogeneous data types. Mainly used for converting between Date and Long/int/string and vice versa.
- **@TypeConverters**: Applied to the abstract class extending RoomDatabase. Contains a single property listing the converters that Room should apply during the execution of select, insert, and update operations.

### Room Gradle Dependencies
```gradle
implementation 'android.arch.persistence.room:runtime:1.1.1'
annotationProcessor 'android.arch.persistence.room:compiler:1.1.1'
```

