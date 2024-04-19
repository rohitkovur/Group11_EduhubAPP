drop database group11_eduhubdb;

create database if not exists group11_eduhubdb;

use group11_eduhubdb;

create table Eduhub_User(
user_id INT PRIMARY KEY AUTO_INCREMENT,
email_id varchar(255),
name varchar(255),
role varchar(255)
);

create table Course(
course_id INT PRIMARY KEY AUTO_INCREMENT,
title varchar(255),
description varchar(4000),
user_id INT REFERENCES Eduhub_User(user_id),
course_price FLOAT(8,4),
image_url varchar(255),
subtitle varchar(255)
);

create table Lesson(
lesson_id INT PRIMARY KEY AUTO_INCREMENT,
title varchar(255),
description varchar(4000),
course_id INT REFERENCES Course(course_id),
subtitle varchar(255),
image_url varchar(255)
);

create table Quiz(
quiz_id INT PRIMARY KEY AUTO_INCREMENT,
title varchar(255),
lesson_id INT REFERENCES Lesson(lesson_id)
);

create table Question(
question_id INT PRIMARY KEY AUTO_INCREMENT,
question_number INT,
quiz_id INT REFERENCES Quiz(quiz_id),
question_description varchar(255),
option1 varchar(255),
option2 varchar(255),
option3 varchar(255),
option4 varchar(255),
correct_answer varchar(255)
);

create table Enrollment(
enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT REFERENCES Eduhub_User(user_id),
course_id INT REFERENCES Course(course_id),
enrollment_date DATE,
completion_date DATE,
is_complete boolean default false
);

create table Certificate(
internal_certificate_id INT PRIMARY KEY AUTO_INCREMENT,
external_certificate_id varchar(255),
user_id INT REFERENCES Eduhub_User(user_id),
user_name varchar(255) references Eduhub_User(name),
course_id INT REFERENCES Course(course_id),
course_name varchar(255) REFERENCES Course(title)
);

create table Submission(
submission_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT REFERENCES Eduhub_User(user_id),
quiz_id INT REFERENCES Quiz(quiz_id),
score FLOAT
);

create table Article(
article_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT REFERENCES Eduhub_User(user_id),
title varchar(255),
description varchar(255),
published_date DATE,
subtitle varchar(255),
image_url varchar(255)
);



INSERT INTO Eduhub_User (email_id, name, role) VALUES
('user1@example.com', 'John Doe', 'Instructor'),
('user2@example.com', 'Jane Smith', 'Student'),
('user3@example.com', 'Mike Johnson', 'Instructor'),
('user4@example.com', 'Emily Davis', 'Student'),
('user5@example.com', 'Chris Wilson', 'Student'),
('user6@example.com', 'Jessica Brown', 'Instructor'),
('user7@example.com', 'David Martinez', 'Student'),
('user8@example.com', 'Sarah Anderson', 'Instructor'),
('user9@example.com', 'Daniel Taylor', 'Student'),
('user10@example.com', 'Olivia Thomas', 'Instructor'),
('user11@example.com', 'James Jackson', 'Student'),
('user12@example.com', 'Sophia White', 'Instructor'),
('user13@example.com', 'William Harris', 'Student'),
('user14@example.com', 'Emma Clark', 'Instructor'),
('user15@example.com', 'Alexander Lewis', 'Student'),
('user16@example.com', 'Ava Lee', 'Instructor'),
('user17@example.com', 'Michael Walker', 'Student'),
('user18@example.com', 'Mia Hall', 'Instructor'),
('user19@example.com', 'Ethan Green', 'Student'),
('user20@example.com', 'Isabella King', 'Instructor');

INSERT INTO Course (title, description, user_id, course_price, image_url, subtitle) VALUES
('Java Programming', 'Learn Java programming from basics to advanced concepts.', 1, 49.99, 'image1.jpg', 'Java Basics'),
('Python for Beginners', 'A comprehensive course on Python for absolute beginners.', 2, 29.99, 'image2.jpg', 'Python Basics'),
('Web Development Fundamentals', 'Master the fundamentals of web development with HTML, CSS, and JavaScript.', 3, 39.99, 'image3.jpg', 'Web Development Basics'),
('Data Science Essentials', 'Get started with data science and machine learning techniques.', 4, 59.99, 'image4.jpg', 'Data Science Basics'),
('Android App Development', 'Learn to develop Android apps using Java and Android Studio.', 5, 49.99, 'image5.jpg', 'Android Development Basics'),
('Full-Stack Web Development', 'Become a full-stack web developer with this comprehensive course.', 6, 79.99, 'image6.jpg', 'Full-Stack Development Basics'),
('JavaScript Mastery', 'Master JavaScript programming with hands-on projects and exercises.', 7, 34.99, 'image7.jpg', 'JavaScript Basics'),
('Cloud Computing Fundamentals', 'Learn the basics of cloud computing with AWS, Azure, and Google Cloud.', 8, 44.99, 'image8.jpg', 'Cloud Computing Basics'),
('Cybersecurity Essentials', 'Understand cybersecurity concepts and best practices for secure computing.', 9, 54.99, 'image9.jpg', 'Cybersecurity Basics'),
('Machine Learning Basics', 'Introduction to machine learning algorithms and applications.', 10, 64.99, 'image10.jpg', 'Machine Learning Basics'),
('iOS App Development', 'Develop iOS apps using Swift and Xcode.', 11, 49.99, 'image11.jpg', 'iOS Development Basics'),
('Network Administration', 'Learn network administration skills for managing enterprise networks.', 12, 59.99, 'image12.jpg', 'Network Administration Basics'),
('UI/UX Design Fundamentals', 'Explore user interface and user experience design principles.', 13, 39.99, 'image13.jpg', 'UI/UX Design Basics'),
('DevOps for Beginners', 'Introduction to DevOps practices for continuous integration and deployment.', 14, 49.99, 'image14.jpg', 'DevOps Basics'),
('Blockchain Essentials', 'Understand blockchain technology and its applications in various industries.', 15, 69.99, 'image15.jpg', 'Blockchain Basics'),
('Digital Marketing Fundamentals', 'Learn digital marketing strategies for online businesses.', 16, 34.99, 'image16.jpg', 'Digital Marketing Basics'),
('Artificial Intelligence Basics', 'Introduction to artificial intelligence and its real-world applications.', 17, 54.99, 'image17.jpg', 'AI Basics'),
('Big Data Analytics', 'Explore big data analytics tools and techniques for data-driven insights.', 18, 74.99, 'image18.jpg', 'Big Data Basics'),
('Computer Vision Fundamentals', 'Learn computer vision algorithms and applications.', 19, 59.99, 'image19.jpg', 'Computer Vision Basics'),
('Game Development Basics', 'Introduction to game development using popular game engines.', 20, 49.99, 'image20.jpg', 'Game Development Basics');

INSERT INTO Lesson (title, description, course_id, subtitle, image_url) VALUES
('Introduction to Java', 'Basic concepts of Java programming language.', 1, 'Java Basics', 'image1.jpg'),
('Python Data Types', 'Understanding different data types in Python.', 2, 'Python Basics', 'image2.jpg'),
('HTML and CSS Basics', 'Fundamentals of HTML and CSS for web development.', 3, 'Web Development Basics', 'image3.jpg'),
('Data Visualization Techniques', 'Visualizing data using charts and graphs.', 4, 'Data Science Basics', 'image4.jpg'),
('Android User Interface', 'Designing user interfaces for Android apps.', 5, 'Android Development Basics', 'image5.jpg'),
('Frontend Development Tools', 'Tools and libraries for frontend web development.', 6, 'Full-Stack Development Basics', 'image6.jpg'),
('JavaScript Functions', 'Working with functions in JavaScript.', 7, 'JavaScript Basics', 'image7.jpg'),
('Cloud Storage Solutions', 'Managing data storage in cloud environments.', 8, 'Cloud Computing Basics', 'image8.jpg'),
('Cybersecurity Threats', 'Common cybersecurity threats and mitigation strategies.', 9, 'Cybersecurity Basics', 'image9.jpg'),
('Supervised Learning Algorithms', 'Understanding supervised learning algorithms.', 10, 'Machine Learning Basics', 'image10.jpg'),
('iOS App Design', 'Designing user interfaces for iOS apps.', 11, 'iOS Development Basics', 'image11.jpg'),
('Network Protocols', 'Common network protocols and their functionalities.', 12, 'Network Administration Basics', 'image12.jpg'),
('UI/UX Design Principles', 'Principles of user interface and user experience design.', 13, 'UI/UX Design Basics', 'image13.jpg'),
('Continuous Integration Practices', 'Implementing continuous integration in DevOps.', 14, 'DevOps Basics', 'image14.jpg'),
('Blockchain Technology Overview', 'Overview of blockchain technology and its components.', 15, 'Blockchain Basics', 'image15.jpg'),
('Digital Marketing Strategies', 'Effective digital marketing strategies for businesses.', 16, 'Digital Marketing Basics', 'image16.jpg'),
('AI in Healthcare', 'Applications of AI in the healthcare industry.', 17, 'AI Basics', 'image17.jpg'),
('Big Data Processing', 'Processing and analyzing large volumes of data.', 18, 'Big Data Basics', 'image18.jpg'),
('Computer Vision Applications', 'Real-world applications of computer vision.', 19, 'Computer Vision Basics', 'image19.jpg'),
('Game Development Tools', 'Tools and engines for game development.', 20, 'Game Development Basics', 'image20.jpg');

INSERT INTO Quiz (title, lesson_id) VALUES
('Java Quiz 1', 1),
('Python Quiz 1', 2),
('Web Development Quiz 1', 3),
('Data Science Quiz 1', 4),
('Android Development Quiz 1', 5),
('Full-Stack Development Quiz 1', 6),
('JavaScript Quiz 1', 7),
('Cloud Computing Quiz 1', 8),
('Cybersecurity Quiz 1', 9),
('Machine Learning Quiz 1', 10),
('iOS Development Quiz 1', 11),
('Network Administration Quiz 1', 12),
('UI/UX Design Quiz 1', 13),
('DevOps Quiz 1', 14),
('Blockchain Quiz 1', 15),
('Digital Marketing Quiz 1', 16),
('AI Quiz 1', 17),
('Big Data Quiz 1', 18),
('Computer Vision Quiz 1', 19),
('Game Development Quiz 1', 20);

INSERT INTO Question (question_number, quiz_id, question_description, option1, option2, option3, option4, correct_answer) VALUES
(1, 1, 'What is Java?', 'A programming language', 'A fruit', 'A car', 'A planet', 'A programming language'),
(2, 1, 'What is Java Virtual Machine (JVM)?', 'A virtual reality game', 'A software that converts Java bytecode into machine language', 'A type of coffee', 'A spacecraft', 'A software that converts Java bytecode into machine language'),
(3, 1, 'What is an IDE?', 'A type of insect', 'An Integrated Development Environment', 'An Indian dish', 'A programming language', 'An Integrated Development Environment'),
(4, 1, 'What is a variable?', 'A constant value', 'A storage location associated with a symbolic name', 'A mathematical function', 'A programming error', 'A storage location associated with a symbolic name'),
(5, 1, 'What is a loop?', 'A type of knot', 'A programming construct that repeats a sequence of instructions', 'A data structure', 'A type of shoe', 'A programming construct that repeats a sequence of instructions'),
(6, 2, 'What is Python?', 'A snake', 'A programming language', 'A food item', 'A type of tree', 'A programming language'),
(7, 2, 'What is a list?', 'A sequence of ordered elements', 'A musical instrument', 'A type of bird', 'A programming error', 'A sequence of ordered elements'),
(8, 2, 'What is a tuple?', 'A type of dance', 'An ordered immutable sequence of elements', 'A data structure', 'A type of drink', 'An ordered immutable sequence of elements'),
(9, 2, 'What is an if statement?', 'A conditional statement', 'An abbreviation for "if else"', 'A type of function', 'A programming language', 'A conditional statement'),
(10, 2, 'What is a dictionary?', 'A book containing words and their meanings', 'An unordered collection of key-value pairs', 'A type of food', 'A programming error', 'An unordered collection of key-value pairs'),
(11, 3, 'What is HTML?', 'A programming language', 'A markup language for creating web pages', 'A type of car', 'A planet', 'A markup language for creating web pages'),
(12, 3, 'What is CSS?', 'A programming language', 'A styling language for web pages', 'A type of food', 'A planet', 'A styling language for web pages'),
(13, 3, 'What is a div?', 'A type of insect', 'A container for content in HTML', 'A type of dance', 'A programming error', 'A container for content in HTML'),
(14, 3, 'What is a class?', 'A group of students', 'A style definition in CSS', 'A type of food', 'A programming error', 'A style definition in CSS'),
(15, 3, 'What is a selector?', 'A programming language', 'A CSS rule that selects elements to apply styles', 'A type of car', 'A planet', 'A CSS rule that selects elements to apply styles');
-- Add more questions as needed...

INSERT INTO Enrollment (user_id, course_id, enrollment_date, completion_date, is_complete) VALUES
(1, 1, '2023-01-01', '2023-02-28', true),
(2, 2, '2023-02-15', '2023-04-15', true),
(3, 3, '2023-03-10', '2023-05-10', false),
(4, 4, '2023-04-05', NULL, false),
(5, 5, '2023-05-20', NULL, false),
(6, 6, '2023-06-01', NULL, false),
(7, 7, '2023-07-10', NULL, false),
(8, 8, '2023-08-05', NULL, false),
(9, 9, '2023-09-15', NULL, false),
(10, 10, '2023-10-01', NULL, false),
(11, 11, '2023-11-10', NULL, false),
(12, 12, '2023-12-05', NULL, false),
(13, 13, '2024-01-15', NULL, false),
(14, 14, '2024-02-01', NULL, false),
(15, 15, '2024-03-10', NULL, false),
(16, 16, '2024-04-05', NULL, false),
(17, 17, '2024-05-20', NULL, false),
(18, 18, '2024-06-01', NULL, false),
(19, 19, '2024-07-10', NULL, false),
(20, 20, '2024-08-05', NULL, false);


INSERT INTO Certificate (external_certificate_id, user_id, user_name, course_id, course_name) VALUES
('CERT-123456', 1, 'John Doe', 1, 'Java Programming'),
('CERT-234567', 2, 'Jane Smith', 2, 'Python for Beginners'),
('CERT-345678', 3, 'Mike Johnson', 3, 'Web Development Fundamentals'),
('CERT-456789', 4, 'Emily Davis', 4, 'Data Science Essentials'),
('CERT-567890', 5, 'Chris Wilson', 5, 'Android App Development'),
('CERT-678901', 6, 'Jessica Brown', 6, 'Full-Stack Web Development'),
('CERT-789012', 7, 'David Martinez', 7, 'JavaScript Mastery'),
('CERT-890123', 8, 'Sarah Anderson', 8, 'Cloud Computing Fundamentals'),
('CERT-901234', 9, 'Daniel Taylor', 9, 'Cybersecurity Essentials'),
('CERT-012345', 10, 'Olivia Thomas', 10, 'Machine Learning Basics'),
('CERT-123456', 11, 'James Jackson', 11, 'iOS App Development'),
('CERT-234567', 12, 'Sophia White', 12, 'Network Administration'),
('CERT-345678', 13, 'William Harris', 13, 'UI/UX Design Fundamentals'),
('CERT-456789', 14, 'Emma Clark', 14, 'DevOps for Beginners'),
('CERT-567890', 15, 'Alexander Lewis', 15, 'Blockchain Essentials'),
('CERT-678901', 16, 'Ava Lee', 16, 'Digital Marketing Fundamentals'),
('CERT-789012', 17, 'Michael Walker', 17, 'Artificial Intelligence Basics'),
('CERT-890123', 18, 'Mia Hall', 18, 'Big Data Analytics'),
('CERT-901234', 19, 'Ethan Green', 19, 'Computer Vision Fundamentals'),
('CERT-012345', 20, 'Isabella King', 20, 'Game Development Basics');

INSERT INTO Submission (user_id, quiz_id, score) VALUES
(1, 1, 90),
(2, 2, 85),
(3, 3, 92),
(4, 4, 78),
(5, 5, 88),
(6, 6, 95),
(7, 7, 87),
(8, 8, 93),
(9, 9, 82),
(10, 10, 89),
(11, 11, 91),
(12, 12, 84),
(13, 13, 90),
(14, 14, 88),
(15, 15, 94),
(16, 16, 86),
(17, 17, 92),
(18, 18, 79),
(19, 19, 85),
(20, 20, 93);


INSERT INTO Article (user_id, title, description, published_date, subtitle, image_url) VALUES
(1, 'Java Basics', 'Understanding the basics of Java programming language.', '2023-01-10', 'Java Programming', 'image1.jpg'),
(2, 'Python Fundamentals', 'Fundamental concepts of Python programming.', '2023-02-20', 'Python Basics', 'image2.jpg'),
(3, 'Web Development Trends', 'Exploring the latest trends in web development.', '2023-03-15', 'Web Development Basics', 'image3.jpg'),
(4, 'Data Science Tools', 'Tools and software for data science and analysis.', '2023-04-05', 'Data Science Basics', 'image4.jpg'),
(5, 'Android App Design', 'Design principles for Android app development.', '2023-05-25', 'Android Development Basics', 'image5.jpg'),
(6, 'Full-Stack Development Strategies', 'Strategies for becoming a successful full-stack developer.', '2023-06-05', 'Full-Stack Development Basics', 'image6.jpg'),
(7, 'JavaScript Best Practices', 'Best practices for writing efficient JavaScript code.', '2023-07-15', 'JavaScript Basics', 'image7.jpg'),
(8, 'Cloud Computing Benefits', 'Benefits of adopting cloud computing for businesses.', '2023-08-10', 'Cloud Computing Basics', 'image8.jpg'),
(9, 'Cybersecurity Measures', 'Essential measures for enhancing cybersecurity.', '2023-09-20', 'Cybersecurity Basics', 'image9.jpg'),
(10, 'Machine Learning Applications', 'Real-world applications of machine learning algorithms.', '2023-10-05', 'Machine Learning Basics', 'image10.jpg'),
(11, 'iOS App Development Trends', 'Trends shaping iOS app development.', '2023-11-15', 'iOS Development Basics', 'image11.jpg'),
(12, 'Network Administration Tips', 'Useful tips for network administrators.', '2023-12-10', 'Network Administration Basics', 'image12.jpg'),
(13, 'UI/UX Design Principles', 'Key principles of effective UI/UX design.', '2024-01-25', 'UI/UX Design Basics', 'image13.jpg'),
(14, 'DevOps Strategies', 'Effective strategies for implementing DevOps.', '2024-02-15', 'DevOps Basics', 'image14.jpg'),
(15, 'Blockchain Applications', 'Real-world applications of blockchain technology.', '2024-03-20', 'Blockchain Basics', 'image15.jpg'),
(16, 'Digital Marketing Trends', 'Current trends in digital marketing.', '2024-04-10', 'Digital Marketing Basics', 'image16.jpg'),
(17, 'AI Innovations', 'Innovations in artificial intelligence.', '2024-05-05', 'AI Basics', 'image17.jpg'),
(18, 'Big Data Challenges', 'Challenges faced in big data analytics.', '2024-06-15', 'Big Data Basics', 'image18.jpg'),
(19, 'Computer Vision Advances', 'Recent advances in computer vision technology.', '2024-07-01', 'Computer Vision Basics', 'image19.jpg'),
(20, 'Game Development Insights', 'Insights into the game development industry.', '2024-08-10', 'Game Development Basics', 'image20.jpg');