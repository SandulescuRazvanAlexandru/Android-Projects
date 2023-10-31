### Detailed Information:

* **FirebaseDatabase**
  * This class is used to establish a connection between the mobile application and the Firebase database. An instance of this class can be obtained using the following method call: `FirebaseDatabase.getInstance();`

* **DatabaseReference**
  * Acts similarly to @Dao in Room. It ensures a connection to a parent node in the database. All CRUD operations in Firebase are executed through this instance.

* **getReference()**
  * A method available within the **FirebaseDatabase** class, it initializes an object of the **DatabaseReference** type. It has a single input parameter, a string that represents the name of the node on which various operations are desired.

* **push()**
  * A method available in the **DatabaseReference** class. It generates a new node and adds it to the **DatabaseReference** instance (known as root). This method facilitates an operation similar to the insert function in a relational database.

* **getKey()**
  * Used to retrieve the value entered into a root by previously calling the **push()** method.

* **setValue()**
  * Used for updating operations in a relational database. It sends information to a node. Often used in combination with the **child()** method call to add the respective data to a node generated via the **push()** method. If this chaining is not done, the information added by the **setValue** method will be added directly to the parent node. This method has a single input parameter of type Object.

* **getValue()**
  * The counterpart to the **setValue()** method, ensuring the conversion from a Firebase node to a Java instance. This method belongs to the **DataSnapshot** class and has a single input parameter representing the Java object type (.class) desired for deserialization. The Java class should contain a parameterless constructor, getter, and setter. The **getValue** method relies on reflection for object construction.

* **removeValue()**
  * Used for node deletion. Often combined with **child()** to ensure the removal of a single node from the root. If called at the root level, it will delete all nodes contained therein.

* **child()**
  * A method in the **DatabaseReference** class that returns a child node from within the parent node. The type of node returned is **DatabaseReference**. In Firebase, the **child** method call is equivalent to the _where id=<value>_ condition in relational databases. This method has a single input parameter, a string representing the node's name.

* **addValueEventListener()**
  * Available within the **DatabaseReference** class, it notifies the mobile application when changes (CRUD) occur in the Firebase database. The method can be added to a child or parent level. If the event is added at the parent level, any change to a child triggers this event. If **addValueEventListener** is attached at both parent and child levels, then when a child is modified, both events will trigger. Therefore, it's advised to avoid adding this event to child nodes if it's already set at the parent level.

* **removeEventListener()**
  * Has properties similar to **addValueEventListener**, but triggers when the **removeValue()** method is called.

* **DataSnapshot**
  * This class is used for the conversion from Firebase's JSON to Java objects. It is fetched through the **addValueEventListener** and **removeEventListener** events.
