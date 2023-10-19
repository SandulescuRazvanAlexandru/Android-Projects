# Working Environment
The development environment for mobile applications is Android Studio. 
Available [here](http://developer.android.com/sdk/index.html).

# In-Depth Information
* **Activity** -> the basic unit in an Android project. Every mobile application has at least one activity, which must be Main and Launcher. An activity consists of two components: a Java class - responsible for data handling; an XML file - responsible for building the graphical interface.
* **res** -> the folder that contains all the resources of the project. It consists of layout, drawable, mipmap, values(colors.xml, styles.xml, strings.xml), menu, etc.
* **layout** -> the folder that contains the XMLs corresponding to an activity.
* **strings.xml** -> contains all the text constants that appear on the mobile device's screen.
* **Activity Lifecycle** -> represents the states an activity goes through during its execution. It consists of the following methods:
  * **onCreate()** - _the only mandatory method that an activity has to implement. It's responsible for attaching the corresponding XML file (from layout) to the Java class_.
  * **onPause()/onStop()** - _can be executed multiple times. Triggered when the activity is no longer on the phone's main thread (e.g., when the app is sent to the background). Within these methods, various connections are closed_.
  * **onResume()/onStart()** - _can be executed multiple times. Triggered when the activity returns to the phone's main thread. Connections are restored inside these methods_.
  * **onDestroy** - _executed only once, when there's a need to destroy the activity. E.g., when the app is closed or the `finish()` method is called_.
* **Resource Usage** -> follows these rules:
  * **in XML files**:
    * '@<folder_name>/<file_name>' - for all folders except 'values'.
    * '@<file_name>/<property_value_name>' - for files in the 'values' folder.
    * '@id/<property_value_android:id>' - for visual components that have the android:id property populated.
  * **in Java**:
    * 'R.<folder_name>.<file_name>' - for all folders except 'values'.
    * 'R.<file_name>.<property_value_name>' - for files in the 'values' folder.
    * 'R.id.<property_value_android:id>' - for visual components that have the android:id property populated.
* **setContentView** -> method used to attach a file from res/layout to a Java class extending `AppCompatActivity` (parent class for marking an activity). The input parameter represents the path to the layout (R.layout.<file_name>).
* **Log** -> class used for writing various messages in the Logcat View in Android Studio. These messages are useful for debugging.
* **Bundle** -> a container class responsible for storing various pieces of information meant to be transmitted between activities or from an activity to a fragment. This class offers key-value type methods starting with 'put'. The **onCreate()** method has an input parameter of type Bundle, named onSavedInstanceState used for saving the state of an activity. If the value of **onSavedInstanceState** is null, the user has entered the activity for the first time.

### Saving/Restoring an Activity's State

Saving the state of an activity is automatically managed by the Android OS through the savedInstanceState object of the Bundle type, received as an input parameter by the onCreate method. The aim of this saving is to maintain user-input information on the mobile device's screen when the user performs operations like: rotating the device, sending the app to the background, etc.

However, there are situations where automatic saving doesn't work as expected, such as populating TextView visual components with dynamic information loaded following runtime actions. In these cases, the following methods should be overridden:
* **onSaveInstanceState** -> takes an input Bundle object used for storing the information we want to keep. Therefore, the purpose of this method is to save in RAM the useful information for the restoration operation.
* **onRestoreInstanceState** -> takes the same Bundle object used in **onSaveInstanceState** for storage and received by the **onCreate** method. The purpose of this method is to read the information from the Bundle and position it on the corresponding visual components.

The mechanisms for saving/restoring the state of an activity are detailed in the following links:
* [Android Developers - Activity Lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle)
* [Medium - Save and Restore Instance State Made Easy](https://medium.com/@doyouseeitmyway/save-and-restore-instance-state-made-easy-cf6f175f54b0)
