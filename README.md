This is the code of a simple website for an ecommerce platform, which I developed!
In order to create this website, I used HTML, CSS, JAVA, SQL and JAVASCRIPT following the MVC paradigm, using object oriented programming.
This project is composed of:
1.	webpages:
  -	home webpage and login webpage
  -	login is for vendors and for buyers 
  -	buyer webpage in which buyers can buy items and search dynamically
  -	vendor webpage in which sellers can insert new items (or delete)
2.	servlet:
  -	there are four backend servlets to manage the requests from the HTML webpages
3.	JSP:
  -	there are four JSP pages managed by the four servlets which pass the values to fill the webpages 
4.	database:
  -	to store all the information about buyers, sellers and items a derby SQL db is used
5.	JAVASCRIP page:
  -	in the client webpage there is the possibility to search an item dynamically in the db every time a key of the keyboard is pressed. In order to add this functionality, I wrote a JAVASCRIPT function with an AJAX request to the db using JSON to communicate

To run this website use netbeans!
