# Trackrr - An Employee Management System

## Overview

This JavaFX application is designed to manage employee data with features for login authentication, data display, and CRUD operations. The application uses a graphical user interface (GUI) built with JavaFX and is structured into two main views: the login screen and the dashboard.

## Features

- **Login Screen**:
    - Users can log in using their username and password.
    - Displays error messages for invalid login attempts.
    - Passwords are hashed using MD5 for security.

- **Dashboard**:
    - Displays a table of employee information.
    - Allows searching employees by various attributes including ID, name, position, gender, phone number, and email.
    - Provides functionality to add, update, and remove employee records.
    - The table view refreshes to reflect the current data state.

## Technologies

- **JavaFX**: For building the user interface.
- **Maven**: For project management and dependencies.
- **JUnit**: For unit testing.
- **Mockito**: For mocking and testing interactions.

## Project Structure

1. **LoginController**: Handles login functionality and navigation to the dashboard.
2. **DashboardController**: Manages the display and manipulation of employee data.
3. **FXML Files**:
    - `login.fxml`: Layout for the login screen.
    - `dashboard.fxml`: Layout for the dashboard view where employee data is managed.

## Usage

1. **Run the Application**: Execute the main class to start the application.
2. **Login**: Enter your username and password to access the dashboard.
3. **Manage Employees**:
    - Use the search options to find specific employees.
    - Add new employees or update existing ones.
    - Remove employees as needed.

## Testing

- **Unit Tests**: Written using JUnit to ensure the reliability of the application's core functionalities.
- **Mocking**: Utilizes Mockito to simulate database interactions for testing purposes.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/mustafa-maj/trackrr.git
2. Navigate to the project directory:
    ```bash
   cd trackrr
3. Build the project using Maven:
    ```bash
       mvn clean install

## Contributing
Feel free to submit issues and pull requests. Contributions are welcome to enhance the application's functionality and fix any bugs.