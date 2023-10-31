## **Layout Types**

Layouts define the structure of the graphical interface in mobile applications. Their role is to host various visual components (widgets) in a particular order.

### **1. LinearLayout**
- Aligns all visual components in one direction (vertical/horizontal). The direction is specified using the `android:orientation` property.
- Allows dynamic space distribution between visual components using:
  - `weightSum`: Populated at the level of the `<LinearLayout>` tag; its value is an integer (the exact value doesn't matter) representing the number of units the container can divide.
  - `layout_weight`: Populated at the visual components level. Its value is in the range [0, weightSum], representing the proportion of space occupied by the visual component in the container. For these properties to be used, `layout_width` or `layout_height` must have a value of `0dp`.
    
[More details](https://developer.android.com/reference/android/widget/LinearLayout)

### **2. RelativeLayout**
- Displays visual elements in relative positions. The position can be determined both based on the parent and the other visual components. The position is set from the top-left point.
    
[More details](https://developer.android.com/reference/android/widget/RelativeLayout)

### **3. ConstraintsLayout**
- Represents a combination of LinearLayout and RelativeLayout.
    
[More details](https://developer.android.com/reference/android/support/constraint/ConstraintLayout)

### **4. DrawerLayout**
- Used for drawing navigable menus.

[More details](https://developer.android.com/reference/android/support/v4/widget/DrawerLayout)

### **5. FrameLayout**
- Displays a single component on the screen. It is especially used to display fragments.

[More details](https://developer.android.com/reference/android/widget/FrameLayout)

### **6. CoordinatorLayout**
- Derived from FrameLayout, it is used to display Chrome or the NavigationDrawer component.

[More details](https://developer.android.com/reference/android/support/design/widget/CoordinatorLayout)

## **Visual Components**

### **1. RadioGroup**
- A special container used to host multiple RadioButtons. It has the same properties as LinearLayout. It ensures the creation of a special context for its RadioButtons, ensuring the choice of only one option from a set.

[More details](https://developer.android.com/reference/android/widget/RadioGroup)

### **2. RadioButton**
- A visual component that has a `checked` property to store the user's response.

[More details](https://developer.android.com/guide/topics/ui/controls/radiobutton)

### **3. TextInputLayout**
- A visual component consisting of an EditText. When the user completes the field, the EditText hint is transformed into a TextView.

[More details](https://developer.android.com/reference/android/support/design/widget/TextInputLayout)

### **4. Spinner**
- A visual component that ensures the selection of an option from a dropdown list. The list is loaded into a Spinner using an Adapter.

[More details](https://developer.android.com/guide/topics/ui/controls/spinner)

### **5. FloatingActionButton**
- A recent visual component in Android versions, representing an improved version of the Button. It has a circular shape and replaces the label with a vector image that can be added to `res/drawable`.

[More details](https://developer.android.com/reference/android/support/design/widget/FloatingActionButton)

## **Other Elements**

- **findViewById()**: A method belonging to AppCompatActivity. Responsible for instantiating a Java object of the type of visual components with its layout counterpart (XML files). It takes a single int parameter, representing the widget's ID (the value of the `android:id` property in XML).
- **getApplicationContext**: A method of the AppCompatActivity class, used to obtain the mobile application context. The context represents the environment to which all visual components and activities are linked.
- **setOnClickListener**: A method belonging to components like Button or FloatingActionButton. Triggered when the user presses the visual component. It takes as an input parameter an instance of a class that must implement the View.OnClickListener interface. 
- **Intent**: A class that allows opening an activity from within another. It is also used to transmit various data types between activities.
- **startActivity**: A method of the AppCompatActivity class, the purpose of which is to open one activity from inside another.
- **Adapter**: A utility class that acts as a bridge between visual components and a list of elements. Creates views for each element of a list of objects.
- **ArrayAdapter**: An adapter that uses an object array.
- **string-array**: Tag used to define a static array in `res/values/strings.xml`.