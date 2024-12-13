package com.manjiri.smart_utility_management;

import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class Main {

    private static final ServicePersonDAO servicePersonDAO = new ServicePersonDAO();
    private static final UserDAO userDAO = new UserDAO();
    private static final ServiceLogDAO serviceLogDAO = new ServiceLogDAO();
    private static final AdminDAO adminDAO = new AdminDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Smart Utility Management System");
            System.out.println("Are you a: ");
            System.out.println("1. Service Person");
            System.out.println("2. User");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    servicePersonMenu(scanner);
                    break;
                case 2:
                    userMenu(scanner);
                    break;
                case 3:
                    adminMenu(scanner);
                    break;
                case 4:
                    System.out.println("Exiting system. Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Service Person Menu
    private static void servicePersonMenu(Scanner scanner) {
        while (true) {
            System.out.println("Service Person Menu");
            System.out.println("1. Register as a Service Person");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerServicePerson(scanner);
                    break;
                case 2:
                    boolean loggedIn = loginServicePerson(scanner);
                    if (loggedIn) {
                        servicePersonOptions(scanner);
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to register a Service Person
    private static void registerServicePerson(Scanner scanner) {
        scanner.nextLine(); // consume newline
        System.out.println("Enter Service Person Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Contact Number: ");
        String contact = scanner.nextLine();

        System.out.println("Enter Email: ");
        String email = scanner.nextLine();

        System.out.println("Enter Service Type (Plumber, Electrician, etc.): ");
        String serviceType = scanner.nextLine();

        System.out.println("Enter Available Time (e.g., 9 AM - 5 PM): ");
        String availableTime = scanner.nextLine();

        System.out.println("Enter Additional Details: ");
        String additionalDetails = scanner.nextLine();

        ServicePerson servicePerson = new ServicePerson(name, contact, email, serviceType, availableTime, additionalDetails);
        servicePersonDAO.saveServicePerson(servicePerson);
        System.out.println("Service Person registered successfully!");

        // Ask to login or exit after successful registration
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            boolean loggedIn = loginServicePerson(scanner);
            if (loggedIn) {
                servicePersonOptions(scanner);
            }
        }
    }

    // Method to login a Service Person
    private static boolean loginServicePerson(Scanner scanner) {
        scanner.nextLine(); // consume newline
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();

        boolean isAuthenticated = servicePersonDAO.authenticateServicePerson(email, contact);
        if (isAuthenticated) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid email or contact number. Please try again.");
            return false;
        }
    }

    // Method to display options after successful login
    private static void servicePersonOptions(Scanner scanner) {
        while (true) {
            System.out.println("Service Person Options");
            System.out.println("1. View Available Services");
            System.out.println("2. Update Your Details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAllServicePersons(); // Display available services
                    break;
                case 2:
                    updateServicePersonDetails(scanner);
                    break;
                case 3:
                    System.out.println("Exiting. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to update Service Person details
    private static void updateServicePersonDetails(Scanner scanner) {
        System.out.print("Enter Your Service Person ID: ");
        Long servicePersonId = scanner.nextLong();
        scanner.nextLine(); // consume newline

        ServicePerson servicePerson = servicePersonDAO.getServicePersonById(servicePersonId);
        if (servicePerson == null) {
            System.out.println("Service Person not found.");
            return;
        }

        System.out.println("Enter New Details for Service Person (leave blank to keep existing value):");
        
        System.out.print("Name (" + servicePerson.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) servicePerson.setName(name);

        System.out.print("Contact (" + servicePerson.getContact() + "): ");
        String contact = scanner.nextLine();
        if (!contact.isEmpty()) servicePerson.setContact(contact);

        System.out.print("Email (" + servicePerson.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) servicePerson.setEmail(email);

        System.out.print("Service Type (" + servicePerson.getServiceType() + "): ");
        String serviceType = scanner.nextLine();
        if (!serviceType.isEmpty()) servicePerson.setServiceType(serviceType);

        System.out.print("Available Time (" + servicePerson.getAvailabilityTime() + "): ");
        String availableTime = scanner.nextLine();
        if (!availableTime.isEmpty()) servicePerson.setAvailabilityTime(availableTime);

        System.out.print("Additional Details (" + servicePerson.getAdditionalDetails() + "): ");
        String additionalDetails = scanner.nextLine();
        if (!additionalDetails.isEmpty()) servicePerson.setAdditionalDetails(additionalDetails);

        servicePersonDAO.updateServicePerson(servicePerson);
        System.out.println("Service Person updated successfully!");
    }


 

    // User Menu
    private static void userMenu(Scanner scanner) {
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("User Menu");
            System.out.println("1. Register as a User");
            System.out.println("2. Login");
            System.out.println("3. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loggedIn = loginUser(scanner);
                    if (loggedIn) {
                        userOptions(scanner);
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userOptions(Scanner scanner) {
        while (true) {
            System.out.println("User Options");
            System.out.println("1. View Available Services");
            System.out.println("2. Book a Service");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableServices(scanner);
                    break;
                case 2:
                    bookService(scanner);
                    break;
                case 3:
                    System.out.println("Exiting. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    // Method to register a User
    private static void registerUser(Scanner scanner) {
        System.out.println("Registering as a User");
        scanner.nextLine();
        System.out.println("Enter User Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Contact Number: ");
        String contact = scanner.nextLine();

        System.out.println("Enter Email: ");
        String email = scanner.nextLine();

        // Validate user details here
        if (userDAO.userExists(email)) {
            System.out.println("User with this email already exists. Please try again.");
            return;
        }

        User user = new User(name, contact, email);
        userDAO.saveUser(user);
        System.out.println("User registered successfully! Please log in to access services.");
    }

    // Method to log in a User
    private static boolean loginUser(Scanner scanner) {
        System.out.println("Login Menu");
        System.out.print("Enter Email: ");
        String email = scanner.next();
        
        System.out.print("Enter Contact Number: ");
        String contact = scanner.next();

        boolean isAuthenticated = userDAO.authenticateUser(email, contact);
        if (isAuthenticated) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid email or contact number. Please try again.");
            return false;
        }
    }

    // Method to view available services for a User in tabular format
 // Method to view available services for a User in tabular format
    private static void viewAvailableServices(Scanner scanner) {
        System.out.println("View Available Services");
        System.out.println("1. View All Services");
        System.out.println("2. Filter by Service Type");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            List<ServicePerson> servicePersons = servicePersonDAO.getAllServicePersons();
            if (servicePersons.isEmpty()) {
                System.out.println("No services available.");
            } else {
                System.out.printf("%-5s %-20s %-15s %-30s %-15s %-20s %-30s%n", 
                    "ID", "Name", "Contact", "Email", "Service Type", "Available Time", "Additional Details");
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                for (ServicePerson sp : servicePersons) {
                    System.out.printf("%-5d %-20s %-15s %-30s %-15s %-20s %-30s%n", 
                        sp.getId(), sp.getName(), sp.getContact(), sp.getEmail(), sp.getServiceType(), sp.getAvailabilityTime(), sp.getAdditionalDetails());
                }
            }
        } else if (choice == 2) {
            System.out.print("Enter Service Type to Filter: ");
            String serviceType = scanner.nextLine();
            List<ServicePerson> servicePersons = servicePersonDAO.getServicePersonsByType(serviceType);
            if (servicePersons.isEmpty()) {
                System.out.println("No services found for the specified type.");
            } else {
                System.out.printf("%-5s %-20s %-15s %-30s %-15s %-20s %-30s%n", 
                    "ID", "Name", "Contact", "Email", "Service Type", "Available Time", "Additional Details");
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                for (ServicePerson sp : servicePersons) {
                    System.out.printf("%-5d %-20s %-15s %-30s %-15s %-20s %-30s%n", 
                        sp.getId(), sp.getName(), sp.getContact(), sp.getEmail(), sp.getServiceType(), sp.getAvailabilityTime(), sp.getAdditionalDetails());
                }
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    // Method to book a service
    private static void bookService(Scanner scanner) {
        System.out.print("Enter Service Person ID: ");
        Long servicePersonId = scanner.nextLong();
        
        System.out.print("Enter Your User ID: ");
        Long userId = scanner.nextLong();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Service Type: ");
        String serviceType = scanner.nextLine();

        System.out.print("Enter Service Date (yyyy-mm-dd): ");
        String serviceDateStr = scanner.nextLine();
        Date serviceDate = java.sql.Date.valueOf(serviceDateStr);

        ServiceLog serviceLog = new ServiceLog(servicePersonId, userId, serviceType, serviceDate);
        serviceLogDAO.saveServiceLog(serviceLog);
        System.out.println("Service booked successfully!");
    }

    // Admin Menu
    private static void adminMenu(Scanner scanner) {
        // First, call the login method to authenticate admin
        if (!loginAdmin(scanner)) {
            // If login fails, display an error message and return to the main menu
            System.out.println("Invalid admin username or password. Please try again.");
            return;
        }
        

        // If login is successful, show admin options
        while (true) {  // Keep the admin menu in a loop
            System.out.println("Admin Menu");
            System.out.println("1. View All Services");
            System.out.println("2. View Service Logs");
            System.out.println("3. Remove Service Person");
            System.out.println("4. Update Service Person");
            System.out.println("5. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAllServicePersons();
                    break;
                case 2:
                    viewServiceLogs();
                    break;
                case 3:
                    removeServicePerson(scanner);
                    break;
                case 4:
                    updateServicePerson(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to authenticate Admin
    private static boolean loginAdmin(Scanner scanner) {
        System.out.print("Enter Admin Username: ");
        String username = scanner.next();
        
        System.out.print("Enter Admin Password: ");
        String password = scanner.next();
        
        return adminDAO.authenticateAdmin(username, password);
    }
   

    // Method to view all Service Persons in tabular format
    private static void viewAllServicePersons() {
        List<ServicePerson> servicePersons = servicePersonDAO.getAllServicePersons();
        if (servicePersons.isEmpty()) {
            System.out.println("No Service Persons found.");
        } else {
            System.out.printf("%-5s %-20s %-15s %-30s %-15s %-20s%n", "ID", "Name", "Contact", "Email", "Service Type", "Available Time");
            System.out.println("-------------------------------------------------------------------------------------");
            for (ServicePerson sp : servicePersons) {
                System.out.printf("%-5d %-20s %-15s %-30s %-15s %-20s%n", sp.getId(), sp.getName(), sp.getContact(), sp.getEmail(), sp.getServiceType(), sp.getAvailabilityTime());
            }
        }
    }

   
    // Method to view all service logs
    private static void viewServiceLogs() {
        List<ServiceLog> serviceLogs = serviceLogDAO.getAllServiceLogs();
        if (serviceLogs.isEmpty()) {
            System.out.println("No service logs found.");
        } else {
            System.out.printf("%-5s %-15s %-10s %-20s %-10s%n", "ID", "Service Person ID", "User ID", "Service Type", "Service Date");
            System.out.println("----------------------------------------------------------------------------------------");
            for (ServiceLog log : serviceLogs) {
                System.out.printf("%-5d %-15d %-10d %-20s %-10s%n", log.getId(), log.getServicePersonId(), log.getUserId(), log.getServiceType(), log.getServiceDate());
            }
        }
    }

    // Method to remove a Service Person
    private static void removeServicePerson(Scanner scanner) {
        System.out.print("Enter Service Person ID to Remove: ");
        Long servicePersonId = scanner.nextLong();
        servicePersonDAO.deleteServicePerson(servicePersonId);
        System.out.println("Service Person removed successfully!");
    }

    // Method to update a Service Person
    private static void updateServicePerson(Scanner scanner) {
        System.out.print("Enter Service Person ID to Update: ");
        Long servicePersonId = scanner.nextLong();
        scanner.nextLine(); // consume newline

        ServicePerson servicePerson = servicePersonDAO.getServicePersonById(servicePersonId);
        if (servicePerson == null) {
            System.out.println("Service Person not found.");
            return;
        }

        System.out.println("Enter New Details for Service Person (leave blank to keep existing value):");
        
        System.out.print("Name (" + servicePerson.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) servicePerson.setName(name);

        System.out.print("Contact (" + servicePerson.getContact() + "): ");
        String contact = scanner.nextLine();
        if (!contact.isEmpty()) servicePerson.setContact(contact);

        System.out.print("Email (" + servicePerson.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) servicePerson.setEmail(email);

        System.out.print("Service Type (" + servicePerson.getServiceType() + "): ");
        String serviceType = scanner.nextLine();
        if (!serviceType.isEmpty()) servicePerson.setServiceType(serviceType);

        System.out.print("Available Time (" + servicePerson.getAvailabilityTime() + "): ");
        String availableTime = scanner.nextLine();
        if (!availableTime.isEmpty()) servicePerson.setAvailabilityTime(availableTime);

        System.out.print("Additional Details (" + servicePerson.getAdditionalDetails() + "): ");
        String additionalDetails = scanner.nextLine();
        if (!additionalDetails.isEmpty()) servicePerson.setAdditionalDetails(additionalDetails);

        servicePersonDAO.updateServicePerson(servicePerson);
        System.out.println("Service Person updated successfully!");
    }
}
