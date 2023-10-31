### In-Depth Information

* **Bundle**: This is a container class responsible for storing various information that is intended to be transmitted between activities or from an activity to a fragment. Technically, it has the same structure as a Map. It has key-value methods whose names start with 'put'. The `onCreate()` method has a Bundle input parameter called `onSavedInstanceState` used to save the state of an activity.
* **Fragment**: Represents a reusable part of an interface. A fragment is similar to an activity and consists of: a Java class - which extends _Fragment_; an XML file - located in res/layout representing the fragment's interface. It has its own lifecycle, but it is dependent on the lifecycle of the activity in which it is used. Therefore, a fragment can only be used inside an activity. The lifecycle methods include:
   * onAttach() - called when the fragment is attached to an activity.
   * onCreate() - creates the Java class in memory.
   * onCreateView() - attaches the XML file to the Fragment Java class.
   * onStop(), onPause(), onResume(), onStart() - similar to activities.
   * onDestroyView() - ensures the removal of the link between the Fragment Java class and its corresponding XML.
   * onDestroy() - destroys the Fragment Java class.
   * onDetach() - called when the fragment is no longer linked to an activity.

  An activity can use fragments if it contains a FrameLayout visual component. Also, multiple fragments can be added in parallel within an activity, with the condition that there is a FrameLayout for each. The advantage of using fragments is to load dynamic content into an activity/activities. More details can be found [here](https://developer.android.com/reference/android/app/Fragment).
* **FragmentManager**: A utility class used to add a fragment within an activity.
* **beginTransaction()**: A method available at the **FragmentManager** level. Its call specifies to the activity that a fragment is about to be attached.
* **replace()**: A method available at the **FragmentManager** level. Its call ensures the replacement of the `<FrameLayout>` component with the content of a fragment. It has two input data:
   * The ID of the `<FrameLayout>` component in the activity that we want to replace.
   * The fragment instance.
* **commit()**: A method available at the **FragmentManager** level. Its call ensures the display of the fragment on the mobile device screen.
* **setArguments()**: A method available at the **Fragment** level. It ensures the transfer of information from an activity to a fragment. It has a single input parameter of the type Bundle.
