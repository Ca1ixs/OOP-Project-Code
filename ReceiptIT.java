import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Accounts {
    String username;
    String name;
    String studNo;
    String course;
    String email;
    String phone;
    String password;
    List<String> purchaseHistory;

    Accounts(String username, String name, String studNo, String course, String email, String phone, String password) {
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

public class ReceiptIT {

    public static void main(String[] args) {
        LinkedList<Accounts> accounts = new LinkedList<>();
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            try {
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
                if (choice != 1 && choice != 2 && choice != 3) {
                    throw new InputMismatchException("Invalid option. Please choose 1, 2, or 3.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number (1, 2, or 3).");
                continue;  
            }
            switch (choice) {
                case 1:
                    signIn(accounts, input);
                    break;
                case 2:
                    signUp(accounts, input);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid input, try again.");
            }
        } while (choice != 3);
        input.close();

       
        switch(choice) {
        case 1:
            signIn(accounts, input);
        	break;
        case 2:
            signUp(accounts, input);
        	break;
        case 3:
        	System.out.println("Exiting...");
        	break;
        	default:
        		System.out.println("Invalid input, try again.");
        }

        input.close();
    }
    
    private static void signIn(LinkedList<Accounts> accounts, Scanner input) {
        boolean signedIn = false;
        int signInAttempts = 0;
        Accounts currentAccount = null;

        while (signInAttempts < 3) {
            System.out.print("Enter Username: ");
            String userInput = input.nextLine();
            System.out.print("Enter Password: ");
            String passwordInput = input.nextLine();

            for (Accounts account : accounts) {
                if (account.username.equals(userInput) && account.password.equals(passwordInput)) {
                    System.out.println("+=================+");
                    System.out.println("Sign-In successful! Welcome, " + account.name + "!");
                    signedIn = true;
                    currentAccount = account;
                    break;
                }
            }

            if (signedIn) {
                signedInMenu(currentAccount, input);
                break;
            } else {
                System.out.println("Invalid username or password. Try again.");
                signInAttempts++;
            }
        }

        if (!signedIn) {
            System.out.println("------------------");
            System.out.println("Too many attempts. Returning to main menu.");
        }
    }

    private static void signUp(LinkedList<Accounts> accounts, Scanner input) {
        boolean signupSuccessful = false;

        while (!signupSuccessful) {
            System.out.println("------------------");
            System.out.print("Enter Name: ");
            String name = input.nextLine();
            System.out.print("Student No.: ");
            String studNo = input.nextLine();
            System.out.print("Student Course and Block: ");
            String course = input.nextLine();
            System.out.print("Enter email: ");
            String email = input.nextLine();
            System.out.print("Enter phone no.: ");
            String phone = input.nextLine();
            System.out.print("Enter Username: ");
            String user = input.nextLine();
            System.out.print("Enter the password: ");
            String pass = input.nextLine();
            int counter = 0;
            boolean passwordMatched = false;

            while (counter < 3) {
                System.out.print("Re-Enter password: ");
                String rePass = input.nextLine();
                if (rePass.equals(pass)) {
                    passwordMatched = true;
                    System.out.println("Signup successful!");
                    accounts.add(new Accounts(user, name, studNo, course, email, phone, pass));
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
        }
        return;
    }

    private static void signedInMenu(Accounts currentAccount, Scanner input) {
        boolean signedIn = true;

        while (signedIn) {
            System.out.println("+=================+");
            System.out.println("Welcome, " + currentAccount.name + "!");
            System.out.println("(1) Misc");
            System.out.println("(2) History");
            System.out.println("(3) Sign-Out");
            System.out.println("+=================+");
            System.out.print("Enter Choice: ");
            int menu = input.nextInt();
            input.nextLine(); 
            switch (menu) {
                case 1:
                    handleMiscellaneousMenu(input, currentAccount);
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
        }
    }

    private static void handleMiscellaneousMenu(Scanner input, Accounts currentAccount) {
    	while (true) {
            System.out.println("==================");
            System.out.println("Welcome to Misc");
            System.out.println("==================");
            System.out.println("(1) Books");
            System.out.println("(2) Uniform");
            System.out.println("(3) Other Items");
            System.out.println("(4) Back to Main Menu");
            System.out.print("Enter Choice: ");
            int misc = input.nextInt();
            switch (misc) {
                case 1:
                    books(input, currentAccount);
                    break;
                case 2:
                	uniform(input, currentAccount);           
                	break;
                case 3:
                	 otherItems(input, currentAccount);
                    break;
                case 4:
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void books(Scanner miscInput, Accounts currentAccount) {

        while (true) {
            System.out.println("==================");
            System.out.println("List of Books 1");
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
            System.out.println("(1) Next");
            System.out.println("(2) Main Menu");
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
                case 1:
                	books2(miscInput, currentAccount);
                	break;
                case 2:
                	return;
                default:
                	System.out.println("Invalid input...");
            }
        }
    }
    private static void books2(Scanner miscInput, Accounts currentAccount) {

        while (true) {
            System.out.println("==================");
            System.out.println("List of Books 2");
            System.out.println("==================");
            System.out.println("Book Code: 6617 - FM-ELEC103: Treasury Management - Book Price: 350");
            System.out.println("Book Code: 6615 - FM101:Fundamentals of Financial Management - Book Price: 375");
            System.out.println("Book Code: 6515 - FM106:Credit and Collection - Book Price: 520");
            System.out.println("Book Code: 6607 - GE-ELEC6:Environmental Science(A Simplified Approach for College Students) - Book Price: 610");
            System.out.println("Book Code: 6523 - GE01:Understanding the Self - Book Price: 470");
            System.out.println("Book Code: 6524 - GE02 Readings in Philippine History - Book Price: 325");
            System.out.println("Book Code: 6525 - GE03:The Contemporary World - Book Price: 375");
            System.out.println("Book Code: 6526 - GE04:Mathematics in the Modern World (Revised edition) - Book Price: 410");
            System.out.println("Book Code: 6527 - GE05:Communicate & Connect Purposive Communication - Book Price: 550");
            System.out.println("Book Code: 6528 - GE06:An Eye for Art Appreciation: Perception and Expression - Book Price: 450");
            System.out.println("(1) Next");
            System.out.println("(2) Back");
            System.out.println("==================");
            System.out.print("Enter Choice: ");

            int bookChoice = miscInput.nextInt();
            switch (bookChoice) {
            case 6617:
                processPurchase(currentAccount, "FM-ELEC103: Treasury Management", 6617, 350);
                break;
            case 6615:
                processPurchase(currentAccount, "FM101: Fundamentals of Financial Management", 6615, 375);
                break;
            case 6515:
                processPurchase(currentAccount, "FM106: Credit and Collection", 6515, 520);
                break;
            case 6607:
                processPurchase(currentAccount, "GE-ELEC6: Environmental Science(A Simplified Approach for College Students)", 6607, 610);
                break;
            case 6523:
                processPurchase(currentAccount, "GE01: Understanding the Self", 6523, 470);
                break;
            case 6524:
                processPurchase(currentAccount, "GE02: Readings in Philippine History", 6524, 325);
                break;
            case 6525:
                processPurchase(currentAccount, "GE03: The Contemporary World", 6525, 375);
                break;
            case 6526:
                processPurchase(currentAccount, "GE04: Mathematics in the Modern World (Revised edition)", 6526, 410);
                break;
            case 6527:
                processPurchase(currentAccount, "GE05: Communicate & Connect Purposive Communication", 6527, 550);
                break;
            case 6528:
                processPurchase(currentAccount, "GE06: An Eye for Art Appreciation: Perception and Expression", 6528, 450);
                break;
                case 1:
                	books3(miscInput, currentAccount);;
                case 2:
                	return;
                default:
                	System.out.println("Invalid input...");
            }
        }
    }
    private static void books3(Scanner miscInput, Accounts currentAccount) {

        while (true) {
            System.out.println("==================");
            System.out.println("List of Books 3");
            System.out.println("==================");
            System.out.println("Book Code: 6529 - GE07 Science Technology and Society - Book Price: 630");
            System.out.println("Book Code: 6530 - GE08:Introduction to Ethics - Book Price: 350");
            System.out.println("Book Code: 6531 - GE09 Jose Rizal: A Review on the Life and Works of the First Filipino - Book Price: 375");
            System.out.println("Book Code: 6614 - MM101:Consumer Behavior Dynamics, Dimensions & Models - Book Price: 520");
            System.out.println("Book Code: 6618 - MM103:Professional Salesmanship Second Edition - Book Price: 490");
            System.out.println("Book Code: 6611 - MM105: Pricing Strategy - Book Price: 520");
            System.out.println("Book Code: 6557 - NSTP: National Service Training Program 1 (NSTP-1) 2023 Edition - Book Price: 400");
            System.out.println("Book Code: 6616 - OM106:Productivity & Quality Tools - Book Price: 495");
            System.out.println("Book Code: 6608 - OM107:Facilities Planning and Management Second Edition - Book Price: 475");
            System.out.println("Book Code: 6613 - OM108:Fundamentals of Information Management - Book Price: 275");
            System.out.println("Book Code: 6568 - PHILWORLD:21st Century Literature from the Philippines and the World [K-12] - Book Price: 510");
            System.out.println("Book Code: 6581 - PROFEDB:Assessment in Learning 1 (A Modular Approach) - Book Price: 480");
            System.out.println("(1) Back");
            System.out.println("==================");
            System.out.print("Enter Choice: ");

            int bookChoice = miscInput.nextInt();
            switch (bookChoice) {

            case 6529:
                processPurchase(currentAccount, "GE07: Science Technology and Society", 6529, 630);
                break;
            case 6530:
                processPurchase(currentAccount, "GE08: Introduction to Ethics", 6530, 350);
                break;
            case 6531:
                processPurchase(currentAccount, "GE09: Jose Rizal: A Review on the Life and Works of the First Filipino", 6531, 375);
                break;
            case 6614:
                processPurchase(currentAccount, "MM101: Consumer Behavior Dynamics, Dimensions & Models", 6614, 520);
                break;
            case 6618:
                processPurchase(currentAccount, "MM103: Professional Salesmanship Second Edition", 6618, 490);
                break;
            case 6611:
                processPurchase(currentAccount, "MM105: Pricing Strategy", 6611, 520);
                break;
            case 6557:
                processPurchase(currentAccount, "NSTP: National Service Training Program 1 (NSTP-1) 2023 Edition", 6557, 400);
                break;
            case 6616:
                processPurchase(currentAccount, "OM106: Productivity & Quality Tools", 6616, 495);
                break;
            case 6608:
                processPurchase(currentAccount, "OM107: Facilities Planning and Management Second Edition", 6608, 475);
                break;
            case 6613:
                processPurchase(currentAccount, "OM108: Fundamentals of Information Management", 6613, 275);
                break;
            case 6568:
                processPurchase(currentAccount, "PHILWORLD: 21st Century Literature from the Philippines and the World [K-12]", 6568, 510);
                break;
            case 6581:
                processPurchase(currentAccount, "PROFEDB: Assessment in Learning 1 (A Modular Approach)", 6581, 480);
                break;
                case 1:
                	return;
                default:
                	System.out.println("Invalid input...");
            }
        }
    }
    private static void processPurchase(Accounts currentAccount, String bookName, int bookNumber, int bookPrice) {

        Scanner input = new Scanner(System.in);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String DateTime = currentDateTime.format(formatter);
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
                             "\nBook Price: " + bookPrice +
                             "\nDate and time: " + DateTime +
                             "\n=======================\n";
            System.out.println(receipt);
            currentAccount.purchaseHistory.add(receipt); 
        } else {
            System.out.println("No receipt generated.");
        }
    }
    public static void otherItems(Scanner miscInput, Accounts currentAccount) {
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
    private static void otherItemsProcess(Accounts currentAccount, String Items, int Price) {
        Scanner input = new Scanner(System.in);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String DateTime = currentDateTime.format(formatter);
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
                             "\nDate and time: " + DateTime +
                             "\n=======================\n";
            System.out.println(receipt);
            currentAccount.purchaseHistory.add(receipt); 
        } else {
            System.out.println("No receipt generated.");
        }
    }
    public static void uniform(Scanner miscInput, Accounts currentAccount) {
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
    private static void unifProcess(Accounts currentAccount, String Uniform, String size) {
        Scanner input = new Scanner(System.in);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String DateTime = currentDateTime.format(formatter);
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
                             "\nDate and time: " + DateTime +
                             "\n=======================\n";
            System.out.println(receipt);
            currentAccount.purchaseHistory.add(receipt); 
        } else {
            System.out.println("No receipt generated.");
        }
    }
    private static void displayPurchaseHistory(Accounts currentAccount) {
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
