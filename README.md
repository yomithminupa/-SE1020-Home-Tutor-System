# Home Tutor Search and Booking System

SE1020 Object Oriented Programming project built with Java, Spring Boot, JSP pages, and text-file storage.

## Main Features

- User management with create, read/search, update, and delete.
- Tutor management with create, read/search, update, and delete.
- Subject management with create, read/search, update, and delete.
- Booking management with create, read/search, update, and delete/cancel.
- Review management with create, read/search, update, and delete.
- Text files are used for storage inside the `data` folder.

## OOP Concepts Used

- Encapsulation: model fields are private and accessed using getters/setters.
- Inheritance: `StudentUser`, `ParentUser`, and `AdminUser` inherit from `User`; tutor, subject, and review types also use inheritance.
- Polymorphism: subclasses override methods such as `displayProfile()`, `calculateSessionFee()`, and `displayReview()`.
- Abstraction: common CRUD behavior is handled by `AbstractCrudService` and `FileRepository`.
- Information hiding: controllers call service methods without directly handling file read/write logic.

## How To Run In IntelliJ IDEA

1. Open this folder as a Maven project.
2. Make sure the project SDK is Java 17 or newer.
3. Wait for Maven dependencies to load.
4. Run `HomeTutorApplication.java`.
5. Open `http://localhost:8080`.

## Sample Data Files

- `data/users.txt`
- `data/tutors.txt`
- `data/subjects.txt`
- `data/bookings.txt`
- `data/reviews.txt`
