import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<Account> accounts = new LinkedList<>();
        Scanner input = new Scanner(System.in);
        Scanner miscInput = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("+=================+");
            System.out.println("Welcome to EzCash");
            System.out.println("+=================+");
            System.out.println("(1) Sign-In");
            System.out.println("(2) Sign-Up");
            System.out.println("(3) Exit");
            System.out.println("+=================+");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    boolean signedIn = false;
                    int signInAttempts = 0;
                    Account currentAccount = null;

                    while (signInAttempts < 3) {
                        System.out.print("Enter Username: ");
                        String userInput = input.nextLine();
                        System.out.print("Enter Password: ");
                        String passwordInput = input.nextLine();

                        for (Account account : accounts) {
                            if (account.username.equals(userInput) && account.password.equals(passwordInput)) {
                                System.out.println("+=================+");
                                System.out.println("Sign-In successful! Welcome, " + account.name + "!");
                                signedIn = true;
                                currentAccount = account; 
                                break;
                            }
                        }

                        if (signedIn) {
                            break;
                        } else {
                            System.out.println("Invalid username or password. Try again.");
                            signInAttempts++;
                        }
                    }

                    if (signedIn) {
                        while (true) {
                            System.out.println("+=================+");
                            System.out.println("Welcome, " + currentAccount.name + "!");
                            System.out.println("(1) Misc");
                            System.out.println("(2) History");
                            System.out.println("(3) Sign-Out");
                            System.out.println("+=================+");
                            System.out.print("Enter Choice: ");
                            int menu = miscInput.nextInt();
                            switch (menu) {
                                case 1:
                                    handleMiscellaneousMenu(miscInput, currentAccount);
                                    break;
                                case 2:
                                    displayPurchaseHistory(currentAccount);
                                    break;
                                case 3:
                                    System.out.println("Signing out...");
                                    signedIn = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }

                            if (!signedIn) {
                                break;
                            }
                        }
                        
                    } else {
                        System.out.println("------------------");
                        System.out.println("Too many attempts. What would you like to do?");
                        System.out.println("(1) Signup");
                        System.out.println("(2) Exit");
                        System.out.println("------------------");
                        System.out.print("Enter Choice: ");
                        int actionChoice = input.nextInt();
                        input.nextLine();

                        if (actionChoice == 1) {
                            continue;
                        } else {
                            System.out.println("Exiting the program. Goodbye!");
                            input.close();
                            return;
                        }
                    }
                    break;

                case 2:
                    boolean signupSuccessful = false;
                    while (true) {
                        System.out.println("------------------");
                        System.out.print("Enter Name: ");
                        String name = input.nextLine();
                        System.out.println("------------------");
                        System.out.print("Student No.: ");
                        String studNo = input.nextLine();
                        System.out.println("------------------");
                        System.out.print("Student Course and Block: ");
                        String course = input.nextLine();
                        System.out.println("------------------");
                        System.out.print("Enter email: ");
                        String email = input.nextLine();
                        System.out.println("------------------");
                        System.out.print("Enter phone no.: ");
                        String phone = input.nextLine();
                        System.out.println("------------------");
                        System.out.print("Enter Username: ");
                        String user = input.nextLine();
                        System.out.println("------------------");
                        System.out.print("Enter the password: ");
                        String pass = input.nextLine();
                        System.out.println("------------------");
                        int counter = 0;
                        boolean passwordMatched = false;

                        while (counter < 3) {
                            System.out.print("Re-Enter password: ");
                            String rePass = input.nextLine();
                            if (rePass.equals(pass)) {
                                passwordMatched = true;
                                System.out.println("Signup successful!");
                                accounts.add(new Account(user, name, studNo, course, email, phone, pass));
                                signupSuccessful = true;
                                break;
                            } else {
                                System.out.println("Password Mismatch. Try Again.");
                                counter++;
                            }
                        }
                        if (!passwordMatched) {
                            System.out.println("Too many attempts. Returning to signup.");
                        }
                        break; // Exit the signup loop
                    }

                    if (signupSuccessful) {
                        System.out.println("Returning to the main menu...");
                    }
                    break;

                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    input.close();
                    return; // Exit the program

                default:
                    System.out.println("Invalid choice. Please try again.");
            } // switch closed
        } // while closed
    } // main closed

    private static void handleMiscellaneousMenu(Scanner miscInput, Account currentAccount) {
        while (true) {
            System.out.println("==================");
            System.out.println("Welcome to Misc");
            System.out.println("==================");
            System.out.println("(1) Books");
            System.out.println("(2) Uniform");
            System.out.println("(3) Other Items");
            System.out.println("(4) Back to Main Menu");
            System.out.print("Enter Choice: ");
            int misc = miscInput.nextInt();
            switch (misc) {
                case 1:
                    books(miscInput, currentAccount);
                    break;
                case 2:
                	uniform(miscInput, currentAccount);           
                	break;
                case 3:
                	 otherItems(miscInput, currentAccount);
                    break;
                case 4:
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void otherItems(Scanner miscInput, Account currentAccount) {
    	while (true) {
            System.out.println("==================");
            System.out.println("List of Other Items");
            System.out.println("==================");
            System.out.println("(1) NSTP Bandage (80)");
            System.out.println("(2) NSTP Uniform (250)");
            System.out.println("(3) Garison Belt (70)");
            System.out.println("(4) Back");
            System.out.print("Enter Choice: ");
            int otherChoice = miscInput.nextInt();
            switch(otherChoice) {
            case 1:
            	otherItemsProcess(currentAccount, "NSTP Bandage", 80);
            	break;
            case 2:
            	otherItemsProcess(currentAccount, "NSTP Uniform", 250);
            	break;
            case 3:
            	otherItemsProcess(currentAccount, "Garison Belt", 70);
            	break;
            case 4:
            	return;
            default: 
                System.out.println("Invalid choice. Please try again.");

            }
    	}
    }
    private static void otherItemsProcess(Account currentAccount, String Items, int Price) {
        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to make a receipt? (Y/N): ");
        char yn = input.next().charAt(0);
        if (yn == 'Y' || yn == 'y') {
            String receipt = "=======================\n" +
            				 "RECEIPT\n" +
                             "Name: " + currentAccount.name + "\n" +
                             "Student No: " + currentAccount.studNo + "\n" +
                             "Course: " + currentAccount.course + "\n" +
                             "Item: " + Items + "\n" + 
                             "Price: " + Price +
                             "\n=======================\n";
            System.out.println(receipt);
            currentAccount.purchaseHistory.add(receipt); 
        } else {
            System.out.println("No receipt generated.");
        }
    }
    
    public static void uniform(Scanner miscInput, Account currentAccount) {
        while (true) {
            System.out.println("==================");
            System.out.println("List of Uniform");
            System.out.println("==================");
            System.out.println("(1) Uniform Top");
            System.out.println("(2) Uniform Bottom");
            System.out.println("(3) Back");
            System.out.print("Enter Choice: ");
            int unifChoice = miscInput.nextInt();
            switch(unifChoice) {
            case 1:
            	System.out.println("Uniform Top List");
            	System.out.println("(1) Uniform Polo(Boys)");
            	System.out.println("(2) Uniform Blouse(Girls)");
            	System.out.println("(3) PE Top");
            	System.out.print("Select choice: ");
            	int unifTop = miscInput.nextInt();
            	switch(unifTop) {
            	case 1:
            		System.out.println("Uniform Polo for Boys");
            		System.out.println("Price: 515");
            		System.out.println("S, M, X, XL)");
            		System.out.print("Input size: ");
            		String sizeB = miscInput.next();
            		unifProcess(currentAccount, "Polo for Boys", sizeB);
            		break;
            	case 2:
            		System.out.println("Uniform Blouse for Girls");
            		System.out.println("Price: 525");
            		System.out.println("S, M, X, XL)");
            		System.out.print("Input size: ");
            		String sizeG = miscInput.next();
            		unifProcess(currentAccount, "Blouse for Girls", sizeG);
            		break;
            	case 3:
            		System.out.println("PE Uniform Top");
            		System.out.println("Price: 355");
            		System.out.println("S, M, X, XL)");
            		System.out.print("Input size: ");
            		String sizePeT = miscInput.next();
            		unifProcess(currentAccount, "PE Top", sizePeT);
            		break;
            	default: 
                    System.out.println("Invalid choice. Please try again.");
            	}//unif top
            	break;
            case 2:
   
            	System.out.println("Uniform Pants List");
            	System.out.println("(1) Uniform Pants(Boys)");
            	System.out.println("(2) Uniform Skirt(Girls)");
            	System.out.println("(3) PE Pants");
            	System.out.print("Select choice: ");
            	int unifPants = miscInput.nextInt();
            	switch(unifPants) {
            	case 1:
            		System.out.println("Uniform Pants for Boys");
            		System.out.println("Price: 530");
            		System.out.println("S, M, X, XL)");
            		System.out.print("Input size: ");
            		String sizeB = miscInput.next();
            		unifProcess(currentAccount, "Pants for Boys", sizeB);
            		break;
            	case 2:
            		System.out.println("Uniform Skirt for Girls");
            		System.out.println("Price: 425");
            		System.out.println("S, M, X, XL)");
            		System.out.print("Input size: ");
            		String sizeG = miscInput.next();
            		unifProcess(currentAccount, "Skirt for Girls", sizeG);
            		break;
            	case 3:
            		System.out.println("PE Uniform Top");
            		System.out.println("Price: 450");
            		System.out.println("S, M, X, XL)");
            		System.out.print("Input size: ");
            		String sizePeT = miscInput.next();
            		unifProcess(currentAccount, "PE Pants", sizePeT);
            		break;
            	default: 
                    System.out.println("Invalid choice. Please try again.");
            	}//unif bottom
            	break;
            case 3:
            	return;
            default: 
                System.out.println("Invalid choice. Please try again.");
                
            }//unif choice
            
        }
    }
    private static void unifProcess(Account currentAccount, String Uniform, String size) {
        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to make a receipt? (Y/N): ");
        char yn = input.next().charAt(0);
        if (yn == 'Y' || yn == 'y') {
            String receipt =  "=======================\n" +
            				 "RECEIPT\n" +
                             "Name: " + currentAccount.name + "\n" +
                             "Student No: " + currentAccount.studNo + "\n" +
                             "Course: " + currentAccount.course + "\n" +
                             "Uniform: " + Uniform + "\n" + 
                             "Size: " + size +
                             "\n=======================\n";
            System.out.println(receipt);
            currentAccount.purchaseHistory.add(receipt); 
        } else {
            System.out.println("No receipt generated.");
        }
    }
    private static void books(Scanner miscInput, Account currentAccount) {
        while (true) {
            System.out.println("==================");
            System.out.println("List of Books");
            System.out.println("==================");
            System.out.println("Book Code: 6479 - AE5: International Business and Trade - Book Price: 500");
            System.out.println("Book Code: 6480 - AKAD-KOLAB: Tuon sa Kolaboratibong Pagsulat sa Filipino - Book Price: 275");
            System.out.println("Book Code: 6609 - BA-ECO101: Basics of Microeconomics - Book Price: 300");
            System.out.println("Book Code: 6612 - BA-MGT101: Introduction to Human Resource Management Revised Edition - Book Price: 350");
            System.out.println("Book Code: 6486 - A-MGT103: Total Quality Management: An Introduction - Book Price: 375");
            System.out.println("Book Code: 6494 - Cal01: Differential Calculus - Book Price: 500");
            System.out.println("Book Code: 6620 - CPE102: Programming Logic & Design Using Dev C++ - Book Price: 450");
            System.out.println("Book Code: 6499 - DE: Engineering Differential Equations - Book Price: 500");
            System.out.println("Book Code: 6625 - ELSCIENCES: Earth and Life Science - Book Price: 495");
            System.out.println("Book Code: 6508 - EMF: Engineering Mathematics Formula - Book Price: 150");
            System.out.println("(11) Back to Main Menu");
            System.out.println("==================");
            System.out.print("Enter Choice: ");

            int bookChoice = miscInput.nextInt();
            switch (bookChoice) {
                case 6479:
                    processPurchase(currentAccount, "AE5: International Business and Trade", 6479, 500);
                    break;
                case 6480:
                    processPurchase(currentAccount, "AKAD-KOLAB: Tuon sa Kolaboratibong Pagsulat sa Filipino", 6480, 275);
                    break;
                case 6609:
                    processPurchase(currentAccount, "BA-ECO101: Basics of Microeconomics", 6609,300);
                    break;
                case 6612:
                    processPurchase(currentAccount, "BA-MGT101: Introduction to Human Resource Management Revised Edition", 6612, 350);
                    break;
                case 6486:
                    processPurchase(currentAccount, "BA-MGT103: Total Quality Management: An Introduction", 6486, 375);
                    break;
                case 6494:
                    processPurchase(currentAccount, "Cal01: Differential Calculus", 6494, 500);
                    break;
                case 6620:
                    processPurchase(currentAccount, "CPE102: Programming Logic & Design Using Dev C++", 6620, 450);
                    break;
                case 6499:
                    processPurchase(currentAccount, "DE: Engineering Differential Equations", 6499, 500);
                    break;
                case 6625:
                    processPurchase(currentAccount, "ELSCIENCES: Earth and Life Science", 6625, 495);
                    break;
                case 6508:
                    processPurchase(currentAccount, "EMF: Engineering Mathematics Formula", 6508, 150);
                    break;
                case 11:
                	return;
                default:
                	System.out.println("Invalid input...");
            }
        }
    }

    private static void processPurchase(Account currentAccount, String bookName, int bookNumber, int bookPrice) {
        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to make a receipt? (Y/N): ");
        char yn = input.next().charAt(0);
        if (yn == 'Y' || yn == 'y') {
            String receipt = "=======================\n" +
            				 "RECEIPT\n" +
                             "Name: " + currentAccount.name + "\n" +
                             "Student No: " + currentAccount.studNo + "\n" +
                             "Course: " + currentAccount.course + "\n" +
                             "Book: " + bookName + "\n" + 
                             "Book No: " + bookNumber +
                             "Book Price" + bookPrice +
                             "\n=======================\n";
            System.out.println(receipt);
            currentAccount.purchaseHistory.add(receipt); 
        } else {
            System.out.println("No receipt generated.");
        }
    }

    private static void displayPurchaseHistory(Account currentAccount) {
        System.out.println("==================");
        System.out.println("Purchase History");
        System.out.println("==================");
        if (currentAccount.purchaseHistory.isEmpty()) {
            System.out.println("No purchases made yet.");
        } else {
            for (String receipt : currentAccount.purchaseHistory) {
                System.out.println(receipt);
                System.out.println("------------------");
            }
        }
    }
}

class Account {
    String username;
    String name;
    String studNo;
    String course;
    String email;
    String phone;
    String password;
    List<String> purchaseHistory; 

    Account(String username, String name, String studNo, String course, String email, String phone, String password) {
        this.username = username;
        this.name = name;
        this.studNo = studNo;
        this.course = course;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.purchaseHistory = new ArrayList<>(); 
    }
}
