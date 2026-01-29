# Here is the Executive Summary of Cloud Native E-Commerce Platform:

Distributed System similar to how Amazon, Netflix, or Uber operate.

Old Way (Monolith): One giant code folder, one giant database. If one part breaks, everything breaks. Hard to scale.

New Way (Microservices): Small, independent applications that talk to each other.

If "Inventory" crashes, "Product Browsing" still works.

If "Orders" get busy, we can launch 5 more "Order Services" without touching the others.

The Architecture:

## A. The Business Logic (The Workers)
These are the services that actually do something. They each have their own Database (Isolation).

### Product Service (Port 8081):

Role: The Catalog. Stores/retrieves product details (Name, Price, SKU).

Tech: Spring Boot Web, PostgreSQL.

### Order Service (Port 8082):

Role: The Salesman. Accepts orders, generates Order IDs.

Tech: Spring Boot Web, PostgreSQL.

### Inventory Service (Port 8083):

Role: The Warehouse. Checks if stock exists (true/false).

Tech: Spring Boot Web, PostgreSQL.

## The Infrastructure (The Managers)
These services manage the traffic and health of the system. 
### Discovery Server (Netflix Eureka - Port 8761): * Role: The Phonebook. * Concept: Services register dynamically. 
"I am Product Service, I am at IP 127.0.0.1." * Benefit: We don't need to hardcode IP addresses. If an IP changes, Eureka updates automatically. 

### API Gateway (Spring Cloud Gateway - Port 8080): * Role: The Receptionist (Single Entry Point). * Concept: The outside world only talks to Port 8080.
The Gateway routes the traffic. * Key Tech: lb://product-service (Load Balancer Client). It asks Eureka for the address and forwards the request.

### Key Concepts & Patterns
Client-Side Load Balancing
Old Way: You buy a hardware box (F5, NGINX) to sit in front of servers.

Your Way: The Gateway itself is smart. It downloads the "Phonebook" from Eureka and decides which server to call. This removes bottlenecks.

Service Registration
Self-Registration: Services announce themselves to Eureka on startup.

Heartbeats: Every 30 seconds, they ping Eureka ("I'm still alive!"). If they stop pinging, Eureka deletes them from the phonebook.

Reactive Programming (Gateway)
Your Gateway uses Spring WebFlux (Netty), not standard Tomcat. This allows it to handle thousands of concurrent connections with very little memory, just passing data through without blocking.
