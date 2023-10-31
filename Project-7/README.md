### Classes used for asynchronous operations

- **`Executor`**
  - **Package**: `java.io`
  - **Description**: Manages the execution of various threads that applications want to run. It decouples the system's call to start a thread (new `Runnable(){...}` or new `Thread()`) and the actual moment it begins processing (calls the `run()` method). It optimizes resource usage on the OS. [More details](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html)

- **`Handler`**
  - **Platform**: Android-specific
  - **Description**: Manages a message queue of type `Runnable`. Used to convey information processed on parallel threads to the main thread of an activity. It analyzes available OS resources and decides the optimal moment to relay the desired information to the main thread. [More details](https://developer.android.com/reference/android/os/Handler)

### Classes used for network connection

- **`URL`**: Used for validating a URL and obtaining a connection.
- **`HttpURLConnection`**: Represents a connection to a specific URL. Ensures the retrieval of information from the desired location.
- **`InputStream`**: Used for grabbing chunks of information from a specified location.
- **`InputStreamReader`**: Divides an `InputStream` into smaller processing units.
- **`BufferedReader`**: Used to divide an `InputStreamReader` into smaller units, avoiding errors like `TimeOut` or `OutOfMemory`.

### JSON - JavaScript Object Notation

JSON provides a structured way to store information. It can be either homogeneous (object representation) or heterogeneous. Supported data types: numeric (int, double, float), boolean, and string.

#### Key JSON Elements:
- **`{}`**: Represents an object. In Java, a class can be defined containing attributes within these brackets.
- **`[]`**: Represents an array. In Java, arrays can be constructed from values or if `[` is followed by `{`, it indicates an array/list of objects.
- **`:`**: Dual representation. The left side is the attribute name, while the right side is its value.
- **`,`**: Attribute separator within an object or array.

#### Classes used for JSON parsing:
- **`JSONObject`**: Used to load a JSON object (received as a String) into Java memory. Loaded as a Map. Validates the JSON by checking for the '{' character at position 0. Provides 'get' methods for information extraction.
- **`JSONArray`**: Used to load a JSON array (received as a String) into Java memory. Loaded as a Map with the key as an int, representing the array's index. Validates the JSON by checking for the '[' character at position 0. Allows extraction of `JSONObject` using an index.
