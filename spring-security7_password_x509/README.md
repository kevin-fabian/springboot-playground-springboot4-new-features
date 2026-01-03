# Getting Started

This project demonstrates how to set up X.509 certificate-based authentication using Spring Security 7. It includes a simple Spring Boot application that secures endpoints with X.509 certificates.

## Prerequisites
1. Add the cert in /resources/client.p12 to your browser. The password is "123455"


## API Endpoints
1. GET `/`: Accessible with x509.
2. GET `/secure`: Accessible with x509 and Password Authority.
