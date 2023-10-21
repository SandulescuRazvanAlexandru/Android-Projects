### Layout Types Used
1. **DrawerLayout** - Used for designing navigational menus.  
   More details [here](https://developer.android.com/reference/android/support/v4/widget/DrawerLayout).

2. **FrameLayout** - Displays a single component on the screen. It is especially used for displaying fragments.
   More details [here](https://developer.android.com/reference/android/widget/FrameLayout).

### Other Elements
* **menu** - The folder that contains the xml files corresponding to a menu.
* **Toolbar** - The Java class corresponding to the visual component `<Toolbar>`. Represents the action bar in a mobile application.
* **setSupportActionBar()** - A method that belongs to `AppCompatActivity`. It's used to attach a `Toolbar` type object to an activity. Visually, the action bar appears. It has a single input parameter of type Toolbar.
* **DrawerLayout** - The Java class corresponding to the `DrawerLayout` container. It's used for constructing the navigation menu and handling its events.
* **ActionBarDrawerToggle** - A Java class with the following responsibilities:
  * Ensures the association between the Toolbar (action bar) and DrawerLayout (side menu).
  * Displays an action bar burger menu on the top-left, which, when pressed, performs the open/close action of the side menu. The class constructor receives an instance of type Activity, a DrawerLayout, a Toolbar, and two IDs from `strings.xml` that should have the following names: `navigation_drawer_open` and `navigation_drawer_close` (representing the two states the ActionBarDrawerToggle can be in). If the two IDs aren't present in `strings.xml`, they should be added.
* **syncState()** - Method at the level of `ActionBarDrawerToggle`. Ensures the synchronization of the state of the called instance.
* **<NavigationView>** - Visual component used for implementing the side menu. The container it belongs to is `DrawerLayout`. It consists of two elements:
  * _**header**_ - Represents the top part of the menu. The used property is 'app:headerLayout' - its value being an xml file from `res/layout`.
  * _**menu**_ - Represents the side menu options. The used property is 'app:menu' - its value being an xml file from `res/menu`.
* **NavigationView** - The Java class corresponding to the visual component `<NavigationView>`, representing the side menu. In Java, it's used to interact with the side menu options.
* **OnNavigationItemSelectedListener** - An interface that belongs to the `NavigationView` class, responsible for intercepting the moment one of the side menu options is pressed.
* **onCreateOptionsMenu** - A method implemented in an activity to set up a menu. The input parameter is an object `Menu` (Java class type resource corresponding to the xml file from `res/menu`).
* **MenuInflater** - A utility class that ensures the association between a Java object of type `Menu` and its corresponding xml from `res/menu`. The used method is `inflate`, which receives the path to the xml file and the Java instance of type `Menu`.
* **Parameter in Strings.xml** - In `strings.xml`, static messages can be added with parameters. The rule for forming these is: **%<param_number>$<data_type>**, where `param_number` is a value > 0, representing the position of the parameter in the **getString** method; `data_type` can be displayed as s(string), i(int), d(double). Example: _<string name="name_key">Hello %1$s</string>_.  
* **getString** - A method provided by the core Android package. It's used to load text from `strings.xml`. It has two implementations:
  * The first only accepts the resource ID from `strings.xml`.
  * The second accepts both the resource ID and a variable number of arguments which represent the dynamic values declared in `strings.xml`. More details can be found [here](https://developer.android.com/guide/topics/resources/string-resource#formatting-strings).

### Implementation
1. Right-click on the `res` folder -> New -> Android Resource Directory -> Resource type: menu.
2. Right-click on the `menu` folder (located in `res`) -> New -> Menu resource file -> in File name, enter the desired filename.
3. In the previously created file, add the desired items.
4. In the Java class associated with the activity, override the `onCreateOptionsMenu` method (Use CTRL+O to search for the method and override it in the class).
5. In `onCreateOptionsMenu`: `getMenuInflater().inflate(<path to the file in res/menu>, menu);`
