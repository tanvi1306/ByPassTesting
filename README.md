# ByPassTesting
Overview
This project focuses on Client-Side Web Applications Testing (Bypass Testing) by manipulating HTTP request data to bypass client-side validation and evaluate the application's response to tampered or corrupted inputs. The aim is to identify potential vulnerabilities by assessing the system’s resilience to altered input.

Key Features
Client-Side Validation: Verifies input data using HTML attributes and JavaScript before sending data to the server.
Bypass Testing: Inputs that intentionally violate client-side validation rules are submitted directly to the server to test its handling of such scenarios.
Test Scope: Includes adding and managing users, books, availability, and issuing books in a Library Management System.
Tools Used
Burp Suite Community Edition: Intercepted HTTP traffic to manipulate request data, bypass validation, and assess server response.
Selenium: Automated the web browser to simulate user interactions and execute tests using Java.

Team Contributions<br/>
Saloni Maheshwari<br/>
Designed and executed test cases for:<br/>
Password validation during user registration.<br/>
ISBN validation during book addition.<br/>
Book Stock value test during book addition.<br/>

Tanvi Motwani<br/>
Designed and executed test cases for:<br/>
Email validation during user registration.<br/>
Availability and stock value type testing for books.<br/>
Issue date validation during book issuance.<br/>
