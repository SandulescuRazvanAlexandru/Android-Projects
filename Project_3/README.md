## **Detailed Information**

* **startActivityForResult()**
  - A method used to start an activity with the purpose of returning specific processed information. It has two inputs:
    * The intent containing the target activity.
    * `requestCode` - an integer variable that represents a unique call code, used in **onActivityResult** to recognize the information source. This method can be called multiple times within an activity, and each call can open a different activity. Therefore, the uniqueness of the `requestCode` is vital.
  
* **onActivityResult()**
  - A method overridden by the activity that called **startActivityForResult()**. The execution time of this method is determined by the completion of the **setResult()** and **finish()** calls. The method has three parameters:
    * `requestCode` - must match the one from **startActivityForResult()**.
    * `resultCode` - must match the one from **setResult()**.
    * `data` - should be non-null if there's a need to retrieve information. The purpose of this method is to fetch data from the intent. The information received via the Intent is lost if not stored (in memory/files/database) by the end of this method's execution.
  
* **setResult()**
  - A method used in the class opened with the `startActivityForResult()` method to return the expected results to the calling activity. The method takes two parameters:
    * `resultCode` - marks the type of response (for a correct result, `RESULT_OK` is used).
    * `data` - The Intent with which the activity was opened and which contains the information we want to convey.

* **finish()**
  - A method used to finalize an activity's lifecycle.

* **Parcel**
  - A message container. Used to transmit Java objects between activities.

* **Parcelable**
  - An interface implemented by Java classes that we want to transmit between activities through a Parcel. It allows writing and reading from a Parcel. Two methods need to be implemented: **writeToParcel()**, **describeContents()**. A public static object named `CREATOR` of the `Parcelable.Creator` type must be defined to enable the reading operation. The Creator object implements the **createFromParcel()** method which performs the reading. The implementation has one rule: the reading order must match the writing order.

* **putExtra()**
  - A method available at the level of the `Intent` class, used to save a custom object in the `Intent`. The saved object's class implements the `Parcelable/Serializable` interface.

* **getParcelableExtra()** & **getSerializableExtra()**
  - Methods available at the level of the `Intent` class, used to retrieve a custom object from the `Intent`. The fetched object's class implements the `Parcelable` or `Serializable` interface, respectively. The input parameter represents the key on which the object was saved through the **putExtra()** method.

* **getText()**
  - A method available for visual input components such as: `EditText`, `TextInputEditText`, `RadioButton`, etc. It captures the user-entered information.

* **Toast**
  - A Java class that displays temporary messages on the mobile device screen. To create an instance, the **makeText()** method is used, which has three inputs:
    * Application context.
    * The resource ID from `strings.xml` representing the displayed message.
    * Display duration measured in milliseconds.
  
  To display the message on the screen, the **show()** method is called.

* **getSelectedItem()**
  - A method available for `Spinner` objects. It returns the value selected by the user from the list of options.

* **getCheckedRadioButtonId()**
  - A method available for `RadioGroup` objects. It returns the ID of the `RadioButton` inside the group that has the property **checked = true**.

* **SimpleDateFormat**
  - A core Java class used for conversion between `String` and `Date`, and vice-versa. It accepts a date format to apply during conversion. The methods used for conversion are:
    * **parse()** - used to extract a `Date` object from a `String`. Execution occurs within a try-catch block, as the method throws an exception that needs to be handled.
    * **format()** - used to transform a `Date` object into a `String`.

* **Parameter in Strings.xml**
  - In `strings.xml`, static messages with a parameter can be added. The formation rule is: **%<param_number>$<data_type>**, where `param_number` is a value > 0, representing the parameter's position in the **getString** method; `data_type` can be s(string), i(int), d(double). Example: `<string name="name_key">Hello %1$s</string>`

* **getString**
  - A method provided by the core Android package. Used to load text from `strings.xml`. It has two implementations:
    * The first accepts only the resource ID from `strings.xml`.
    * The second accepts the resource ID and a variable number of arguments which represent the dynamic values declared in `strings.xml`. These arguments replace format specifiers in the string resource, such as `%1$s` or `%2$d`. More details can be found [here](https://developer.android.com/guide/topics/resources/string-resource#formatting-strings).

