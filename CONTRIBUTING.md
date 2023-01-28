I intend to open this project to Hacktoberfest 2023 contributors.

# Guidelines for Hacktoberfest 2023 contributors

* Java 8, JUnit 5. The only dependencies for this project are the Java 
 Development Kit (JDK) and JUnit Jupiter.
* Build tool is IntelliJ IDEA's default. Don't bother me about the annoying 
 Maven and certainly not about the even more annoying sbt. Issues or pull 
 requests demanding the use of any particular build tool will be summarily 
 rejected as spam.
* Don't add dependencies. There are some nice collection frameworks out there 
 that you can use for your own projects if the collections in the JDK don't meet 
 your needs, but that's not the point of this repository.
* Package names will not include arbitrary reverse domains (e.g., 
 `org.example`). Issues or pull requests demanding package names with reverse 
 domains will be summarily rejected as spam.
* Column width is 80.
* Don't delete any tests, unless you can clearly show that the test is no longer 
 needed. This actually needs to be said. Also, given the inheritance 
 hierarchies, it is better to err on the side of having too many tests.
* All constructors go at the end of the class source, except when there are 
 nested classes. In a Scala project it would make more sense to put auxiliary 
 constructors towards the top, but since Java allows some fairly arbitrary 
 placements, I'm going with my perhaps outmoded preference.
* Prefer small commits, and pull requests with many commits.