# Final Report: Home Tutor Search and Booking System

## 1. Introduction

The Home Tutor Search and Booking System is a Java web application that allows users to manage students/parents, tutors, subjects, tutor bookings, and reviews. The system uses Spring Boot and JSP for the web interface. Text files are used for data storage instead of a database.

## 2. Objectives

- Apply OOP concepts such as encapsulation, inheritance, polymorphism, abstraction, and information hiding.
- Implement CRUD operations for project modules.
- Use Java file handling for data storage.
- Create a user-friendly web interface.
- Maintain GitHub commit history for individual contributions.

## 3. Main Modules

| Module | Text File | Main Classes |
|---|---|---|
| User Management | `users.txt` | `User`, `StudentUser`, `ParentUser`, `AdminUser`, `UserService` |
| Tutor Management | `tutors.txt` | `Tutor`, `OnlineTutor`, `HomeVisitTutor`, `TutorService` |
| Subject Management | `subjects.txt` | `Subject`, `AcademicSubject`, `SkillSubject`, `SubjectService` |
| Booking Management | `bookings.txt` | `Booking`, `BookingService` |
| Review Management | `reviews.txt` | `Review`, `PublicReview`, `VerifiedReview`, `ReviewService` |

## 4. CRUD Operations

Each module supports create, read/search, update, and delete operations. Controllers receive form inputs from JSP pages and call service classes. Service classes use `FileRepository` to read and write records in text files.

## 5. OOP Concepts

### Encapsulation

Model classes keep fields private and provide getters and setters. Example: `User`, `Tutor`, `Booking`, and `Review`.

### Inheritance

`StudentUser`, `ParentUser`, and `AdminUser` inherit from `User`. `OnlineTutor` and `HomeVisitTutor` inherit from `Tutor`.

### Polymorphism

Methods such as `displayProfile()`, `calculateSessionFee()`, and `displayReview()` behave differently depending on the object type.

### Abstraction

`User`, `Tutor`, `Subject`, and `Review` are abstract classes. `AbstractCrudService` contains common CRUD behavior used by service classes.

### Information Hiding

Controllers do not directly read or write files. File handling is hidden inside `FileRepository`.

## 6. File Handling

Data is stored in pipe-separated text files inside the `data` folder. Example:

```text
ONLINE|T001|Ayesha Silva|ayesha@example.com|0761234567|Mathematics|BSc Mathematics|Colombo|1500.0|Available|Zoom
```

The application reads all lines from the file, maps each line into an object, and writes updated records back to the file after create, update, or delete operations.

## 7. User Interfaces

- Dashboard
- User management pages
- Tutor management pages
- Subject management pages
- Booking management pages
- Review management pages

## 8. GitHub Commit History

Paste screenshots or a table of commit history here.

| Member | Commit Message | Feature |
|---|---|---|
| Member 1 | Added user model and CRUD service | User Management |
| Member 2 | Added tutor management pages | Tutor Management |
| Member 3 | Added booking CRUD operations | Booking Management |

## 9. Conclusion

The project demonstrates Java OOP principles, CRUD operations, file handling, and web UI design through a complete home tutor search and booking workflow.
