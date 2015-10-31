# Pizzeria System
A Servlet-based JAVA 8 Project for emulating Pizza Ordering and Management system.
  - OpenJDK 8
  - Tomcat 8.0.28
  - Ceated using Eclipse Mars EE 4.5.1

# Important Paths:
    - GET "/" : Home page and Customer Frontend
    - GET "/root": Admin frontend and Update panel
    - GET "/NewOrder": New Order Page of Customer page
    - POST "/CreateOrder": Create new order and Details
    - GET "/FinalPage": Final Page showing details of the order and the status

# Performance:
  - Tested using python script present in the project.
  - Response to Denial-Of-Service Attack : 500 GET requests per second to /NewOrder
  - Connection Abort after about 30000 requests, server still working.
