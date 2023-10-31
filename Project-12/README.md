### Detailed Information:

* **AlertDialog**
  * A class provided by the Android package, used to display pop-up messages on the screen of the mobile device. To define an **AlertDialog** instance, the **Builder** class is used, which takes the application context as an input parameter. More details can be found [here](https://developer.android.com/reference/android/app/AlertDialog).

* **setTitle**
  * A method provided by **AlertDialog.Builder** to set the title that appears on the action bar of the pop-up. It has two implementations: it accepts a string as an input parameter or the ID of a resource from strings.xml.

* **setMessage**
  * A method provided by **AlertDialog.Builder** to set the message that appears on the pop-up. It has two implementations: it accepts a string as an input parameter or the ID of a resource from strings.xml.

* **setPositiveButton**
  * A method provided by **AlertDialog.Builder** to add a button, representing the action to be taken if the user **agrees** with the displayed message. The method expects the label of the button as an input parameter, as well as an implementation of the associated click event.

* **setNegativeButton**
  * A method provided by **AlertDialog.Builder** to add a button, representing the action to be taken if the user **disagrees** with the displayed message. The method expects the label of the button as an input parameter, as well as an implementation of the associated click event.

* **create**
  * A method belonging to **AlertDialog.Builder**, its role is to initialize an **AlertDialog** variable in memory.

* **show**
  * A method that belongs to **AlertDialog**, ensuring the pop-up display on the mobile device screen.
