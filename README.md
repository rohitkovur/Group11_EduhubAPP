# Group11_EduhubApplication

Eduhub is an online learning platform desired for aspiring creators. With its wide toolkit, the program lets you get started as a creator by enabling you to create and market digital downloads, online courses, and interesting articles. For safe payment processing, this platform has a user-friendly interface and smooth connectivity with Stripe. Rich text editing, picture addition, and other features will be available on EduHub to enhance the visual attractiveness of the material. Content production is made simple and pleasant with EduHub, regardless of the creator's level of experience. Furthermore, the platform facilitates the posting of unlisted blogs, allowing you to distribute unique material to a targeted audience. 


## Installation

### Prerequisites

- Java 17 or later
- Node.js and npm
- MySQL

### Step 1: Clone the Repository

bash
```
git clone https://github.com/yourusername/project.git

cd project
```

### Step 2: Backend Setup (Sprinboot)
1. Open the backend folder in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
2. Update application.properties in src/main/resources with your MySQL database configuration.
```
spring.datasource.url=jdbc:mysql://localhost:3306/group11_eduhubdb
spring.datasource.username=root
spring.datasource.password=mysql@123
```

3. Build and run the Spring Boot application.

### Step 3: Frontend Setup (Angular)
1. Open the frontend folder in a terminal.
2. Install Angular CLI if you haven't already.
```
npm install -g @angular/cli
```

3. Install project dependencies.
```
npm install

```

4. Start the Angular development server.
```
ng serve
```

### Step 4: Database Setup
1. Create a new database in MySQL.
2. Run the SQL scripts located in backend/src/main/resources/ to create tables and seed data.

### Step 5: Running the project
1. Start the Spring Boot backend.
2. Start the Angular frontend.
3. Open your web browser and navigate to http://localhost:4200 to view the application.

## Architecture
![Architecture](https://github.com/rohitkovur/Group11_EduhubApplication/assets/167348702/361c38a4-5ecb-48d4-9103-7678fbf40ee1)

## Important Features
### Login/ Signup : 
Eduhub uses google authentication to ensure a secure authentication of users. Users who are new will be automatically added to the application and existing users will be signed in.
![image](https://github.com/rohitkovur/Group11_EduhubAPP/assets/167348702/6da7d8bc-3d30-43e8-841d-014d1eed913f)

### Secure Payment Gateway:
Eduhub has an embedded secure payment gateway to offer a secure check out, on completing which they will be enrolled into the course.
![image](https://github.com/rohitkovur/Group11_EduhubAPP/assets/167348702/ed019e0e-efb3-48c0-8365-4a99d36eda57)



