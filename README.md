# ReadMe

Hi to AgileEngine team. Please read instruction and my comments about task below.

To run the application you should have preinstalled Maven, Git and JDK 14.
Please do the following steps:
1. git clone https://github.com/dpratsun/agileengine-test.git
2. cd agileengine-test
3. mvn package
4. java -jar target/imagegallery-0.0.1-SNAPSHOT.jar

Web server is starting on the default port 8080.

I have implemented next endpoints:
/images - get first images page
/images?page= - get images by page
/images/{id} - get image by id
/search/{searchTerm} - get images with author and tags meta fields which matching the search term.

All data is taken from cache without making requests to AgileEngine api endpoints.

Unfortunately I didn't use all meta fields for search in case of limit time amount.

For search implementing I used prefix tree algorithm:
https://www.educative.io/edpresso/what-is-a-prefix-tree  

I tried to make my code readable, loosely coupled and easy maintainable.

Of course there are many things still left to do like:
1. Tests(all the time I tried to develop with TDD) 
2. Proper exception handling
3. Swagger could be added to make RESTful services documentation
4. Renew of a cache can be implemented with scheduling
5. Make processing of response status codes more clear
6. Search algorithm should be separated from cache implemetation file

