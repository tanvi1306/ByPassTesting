# ByPassTesting
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

Team Contributions
Saloni Maheshwari
Designed and executed test cases for:
Password validation during user registration.
ISBN validation during book addition.
Book Stock value test during book addition.

Tanvi Motwani
Designed and executed test cases for:
Email validation during user registration.
Availability and stock value type testing for books.
Issue date validation during book issuance.
