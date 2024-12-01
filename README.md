# Nimap Task Application

## Overview
The **Nimap Task Application** is a Spring Boot project designed to manage categories and products. It provides RESTful APIs for performing CRUD operations on these entities with database persistence using JPA. Additionally, it supports pagination for retrieving data efficiently.

---

## Technologies Used
- **Java**: 17  
- **Spring Boot**: 3.x  
- **Spring Data JPA**  
- **Hibernate**  
- **MySQL** (Database)  
- **Jackson** (for JSON serialization/deserialization)  
- **Maven** (Dependency management)  

---

## Features
### Category Management
- Add, update, delete, and retrieve categories.
- Paginated API to fetch categories.

### Product Management
- Add, update, delete, and retrieve products.
- Products are linked to categories using a many-to-one relationship.
- Paginated API to fetch products.

---

## API Endpoints

### **Category Endpoints**
| HTTP Method | Endpoint                                        | Description                         |
|-------------|-------------------------------------------------|-------------------------------------|
| GET         | `http://localhost:8080/api/categories?page=3`   | Get all categories with pagination. |
| GET         | `http://localhost:8080/api/categories/{id}`     | Get a category by ID.               |
| POST        | `http://localhost:8080/api/categories`          | Create a new category.              |
| PUT         | `http://localhost:8080/api/categories/{id}`     | Update a category by ID.            |
| DELETE      | `http://localhost:8080/api/categories/{id}`     | Delete a category by ID.            |

### **Product Endpoints**
| HTTP Method | Endpoint                                       | Description                            |
|-------------|------------------------------------------------|----------------------------------------|
| GET         | `http://localhost:8080/api/products?page=2`    | Get all products with pagination.      |
| GET         | `http://localhost:8080/api/products/{id}`      | Get a product by ID.                   |
| POST        | `http://localhost:8080/api/products`           | Create a new product under a category. |
| PUT         | `http://localhost:8080/api/products/{id}`      | Update a product by ID.                |
| DELETE      | `http://localhost:8080/api/products/{id}`      | Delete a product by ID.                |

---

## Pagination Example
To fetch paginated categories or products, use query parameters:

- `GET /api/categories?page=0&size=5`
- `GET /api/products?page=0&size=10`

---

## Class Responsibilities

### **1. Model Layer**

#### **Category**
- Represents a category entity in the database.
- Contains attributes: `cId`, `cName`, `cDescription`.
- Maintains a one-to-many relationship with `Product`.
- Annotated with `@Entity` and mapped to a table named `Category`.

#### **Product**
- Represents a product entity in the database.
- Contains attributes: `pId`, `pName`, `pDescription`, `pPrice`, `pQuantity`.
- Maintains a many-to-one relationship with `Category`.
- Annotated with `@Entity` and mapped to a table named `Product`.

---

### **2. Repository Layer**

#### **CategoryRepo**
- Extends `JpaRepository` for CRUD operations on `Category`.
- Provides additional functionality like paginated queries.

#### **ProductRepo**
- Extends `JpaRepository` for CRUD operations on `Product`.
- Includes a custom method to find products by ID and paginate product data.

---

### **3. Service Layer**

#### **CategoryService**
- Handles business logic for managing `Category` entities.
- Contains methods to:
  - Retrieve all categories (with pagination).
  - Retrieve a single category by ID.
  - Save a new category.
  - Update an existing category.
  - Delete a category by ID.

#### **ProductService**
- Handles business logic for managing `Product` entities.
- Contains methods to:
  - Retrieve all products (with pagination).
  - Retrieve a single product by ID.
  - Save a new product.
  - Update an existing product.
  - Delete a product by ID.

---

### **4. Controller Layer**

#### **CategoryController**
- Exposes REST endpoints for managing categories.
- Maps HTTP requests to appropriate service methods:
  - `/api/categories`: GET, POST, PUT, DELETE for category operations.

#### **ProductController**
- Exposes REST endpoints for managing products.
- Maps HTTP requests to appropriate service methods:
  - `/api/products`: GET, POST, PUT, DELETE for product operations.
- Ensures products are associated with an existing category while saving or updating.

---

## Application Workflow
1. A client (e.g., frontend or Postman) sends an HTTP request.
2. The Controller processes the request and delegates it to the Service layer.
3. The Service interacts with the Repository layer for database operations.
4. The database performs CRUD operations on the `Category` and `Product` tables.
5. The response is returned to the client in JSON format.

---

## Key Features
- **Category-Product Relationship**: Products are linked to categories using a foreign key.
- **Pagination**: Fetch large data in chunks using page and size parameters.
- **CRUD Operations**: Full support for creating, reading, updating, and deleting entities.

---

## Configure the Database
Add the following configurations to your `application.properties` file:

```properties
# Datasource configurations
spring.datasource.url=jdbc:mysql://localhost:3306/nimaptask
spring.datasource.username=root
spring.datasource.password=pratik123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# DDL generation
spring.jpa.generate-ddl=true
# Hibernate dialect for MySQL
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
