-- Users

-- 1. Teacher
INSERT INTO users (first_name, last_name, username, email, password, role, created_at, updated_at, deleted_at)
VALUES ('John', 'Doe', 'johndoe', 'string', 'iwillbeupdated', 'teacher', NOW(), NOW(), '1970-01-01 00:00:00+00');


-- 2. Teacher
INSERT INTO users (first_name, last_name, username, email, password, role, created_at, updated_at, deleted_at)
VALUES ('Jane', 'Doe', 'janedoe', 'string', 'itwillbeupdated', 'teacher', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- Courses

-- 1. Java
INSERT INTO courses (owner_id, name, description, created_at, updated_at, deleted_at)
VALUES (1, 'Java', 'Java course', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 2. Python
INSERT INTO courses (owner_id, name, description, created_at, updated_at, deleted_at)
VALUES (1, 'Python', 'Python course', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 3. C++
INSERT INTO courses (owner_id, name, description, created_at, updated_at, deleted_at)
VALUES (2, 'C++', 'C++ course', NOW(), NOW(), '1970-01-01 00:00:00+00');


--- Students

-- 1. Student of Java
INSERT INTO students (course_id, name, email, created_at, updated_at, deleted_at)
VALUES (1, 'Alice', 'alice@alice.com', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 2. Student of Java
INSERT INTO students (course_id, name, email, created_at, updated_at, deleted_at)
VALUES (1, 'Bob', 'bob@bob.com', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 3. Student of Java
INSERT INTO students (course_id, name, email, created_at, updated_at, deleted_at)
VALUES (1, 'Charlie', 'charlie@charlie.com', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 4. Student of Python
INSERT INTO students (course_id, name, email, created_at, updated_at, deleted_at)
VALUES (2, 'David', 'david@david.com', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 5. Student of C++
INSERT INTO students (course_id, name, email, created_at, updated_at, deleted_at)
VALUES (3, 'Eve', 'eve@eve.com', NOW(), NOW(), '1970-01-01 00:00:00+00');


-- Classrooms

-- 1. Java Classroom
INSERT INTO classrooms (course_id, name, description, code, created_at, updated_at, deleted_at)
VALUES (1, 'Java Classroom 1', 'Desc Java classroom 1', 'J4V4', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 2. Python Classroom
INSERT INTO classrooms (course_id, name, description, code, created_at, updated_at, deleted_at)
VALUES (2, 'Python Classroom 1', 'Desc Python classroom 1', 'PYTH0N', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 3. C++ Classroom
INSERT INTO classrooms (course_id, name, description, code, created_at, updated_at, deleted_at)
VALUES (3, 'C++ Classroom 1', 'Desc C++ classroom 1', 'C++', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 4. Java Classroom 2
INSERT INTO classrooms (course_id, name, description, code, created_at, updated_at, deleted_at)
VALUES (1, 'Java Classroom 2', 'Desc Java classroom 2', 'J4V4', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 5. Python Classroom 2
INSERT INTO classrooms (course_id, name, description, code, created_at, updated_at, deleted_at)
VALUES (2, 'Python Classroom 2', 'Desc Python classroom 2', 'PYTH0N', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- 6. C++ Classroom 2
INSERT INTO classrooms (course_id, name, description, code, created_at, updated_at, deleted_at)
VALUES (3, 'C++ Classroom 2', 'Desc C++ classroom 2', 'C++', NOW(), NOW(), '1970-01-01 00:00:00+00');

-- Classroom x Students

-- 1. Alice in Java Classroom 1
INSERT INTO students_classrooms (student_id, classroom_id)
VALUES (1, 1);

-- 2. Bob in Java Classroom 1
INSERT INTO students_classrooms (student_id, classroom_id)
VALUES (2, 1);

-- 3. Charlie in Java Classroom 1
INSERT INTO students_classrooms (student_id, classroom_id)
VALUES (3, 1);

-- 4. David in Python Classroom 1
INSERT INTO students_classrooms (student_id, classroom_id)
VALUES (4, 2);

-- 5. Eve in C++ Classroom 1
INSERT INTO students_classrooms (student_id, classroom_id)
VALUES (5, 3);

-- 6. Alice in Java Classroom 2
INSERT INTO students_classrooms (student_id, classroom_id)
VALUES (1, 4);

-- 7. Bob in Java Classroom 2
INSERT INTO students_classrooms (student_id, classroom_id)
VALUES (2, 4);

