package me.internalizable.jarvis.query;

import javafx.application.Platform;
import me.internalizable.jarvis.Jarvis;
import me.internalizable.jarvis.gui.App;
import me.internalizable.jarvis.internal.Accessory;
import me.internalizable.jarvis.internal.Operation;
import me.internalizable.jarvis.internal.users.User;
import me.internalizable.jarvis.internal.users.UserType;
import me.internalizable.jarvis.query.filters.utils.FilterUtils;
import me.internalizable.jarvis.utils.FormatMarkdown;
import me.internalizable.jarvis.utils.JarvisLists;
import me.internalizable.jarvis.utils.StaticUtils;

import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

public class CLISearch {

    private final Scanner scanner;

    private List<IFilter> appliedFilters;
    private List<Predicate<Operation>> appliedPredicates;

    public CLISearch() {
        scanner = new Scanner(System.in);

        appliedFilters = new ArrayList<>();
        appliedPredicates = new ArrayList<>();

        FilterUtils filterUtils = new FilterUtils();
        filterUtils.loadFilters();
    }

    public void start() {
        System.out.println(StaticUtils.getDivider());
        System.out.println("Welcome to Jarvis' Smart Home technology CLI.");
        System.out.println(StaticUtils.getDivider());

        openLoginMenu();
    }

    private void openLoginMenu() {
        String username, password;

        do {
            System.out.print(StaticUtils.getFormattedText("Username", FormatMarkdown.UNDERLINED) + ": ");
            username = scanner.next();

            System.out.print(StaticUtils.getFormattedText("Password", FormatMarkdown.UNDERLINED) + ": ");
            password = scanner.next();

            if(JarvisLists.hasLogin(username, password))
                break;
            else {
                System.out.println("\n" + StaticUtils.getFormattedText("Wrong login details, try again!", FormatMarkdown.INVERTED));
                System.out.println(StaticUtils.getDivider());
            }


        } while(true);

        openMainMenu();
    }

    private void openRegisterMenu() {
        String username, password, privilegeType;

        do {
            System.out.println();
            System.out.println(StaticUtils.getDivider());
            System.out.println("               Register Menu                   ");
            System.out.println(StaticUtils.getDivider());

            System.out.print(StaticUtils.getFormattedText("Username", FormatMarkdown.UNDERLINED) + ": ");
            username = scanner.next();

            System.out.print(StaticUtils.getFormattedText("Password", FormatMarkdown.UNDERLINED) + ": ");
            password = scanner.next();

            System.out.print(StaticUtils.getFormattedText("Privileges", FormatMarkdown.UNDERLINED) + ": ");
            privilegeType = scanner.next();

            if(JarvisLists.hasLogin(username, password))
                System.out.println("\n" + StaticUtils.getFormattedText("A user with that login already exists!", FormatMarkdown.INVERTED));
            else {
                try {
                    UserType userType = UserType.valueOf(privilegeType);
                    JarvisLists.registerUser(username, password, userType);
                    break;
                } catch (IllegalArgumentException argumentException) {
                    System.out.println("\n" + StaticUtils.getFormattedText("No privilege type was found, use either [VISITOR/MEMBER/ADMINISTRATOR]", FormatMarkdown.INVERTED));
                }

            }
        } while(true);

        openMainMenu();
    }

    private void openMainMenu() {
        System.out.println();
        System.out.println(StaticUtils.getDivider());
        System.out.println("                 Main Menu                   ");
        System.out.println(StaticUtils.getDivider());
        System.out.println("1- Display particular search menu");
        System.out.println("2- Display filtering menu");
        System.out.println("3- Register new user.");
        System.out.println("4- Exit menu");

        int choice = 0;

        do {
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> openDisplayMenu();
                case 2 -> openFilterMenu();
                case 3 -> openRegisterMenu();
                case 4 -> System.out.println("Exiting application...");

                default -> System.out.println("Unknown data point entry, try again");
            }

        } while(choice < 1 || choice > 4);
    }

    private void openFilterMenu() {
        int choice;

        do {
            System.out.println();
            System.out.println(StaticUtils.getDivider());
            System.out.println("               Filter Menu                   ");
            System.out.println(StaticUtils.getDivider());

            FilterUtils.getAvailableFilters().forEach(filter -> {
                String nonFormattedString = filter.getFilterID() + "- " + filter.getFilterName();

                if(appliedFilters.contains(filter))
                    System.out.println(StaticUtils.getFormattedText(nonFormattedString, FormatMarkdown.STRIKETHROUGH));
                else
                    System.out.println(nonFormattedString);
            });

            System.out.println((FilterUtils.getAvailableFilters().size() + 1) + "- Exit to main menu.\n");

            System.out.print("Applied filters: ");

            if(appliedFilters.size() == 0)
                System.out.print(StaticUtils.getFormattedText("There are currently no applied filters", FormatMarkdown.ITALIC));

            else appliedFilters.forEach(filter -> System.out.print(StaticUtils.getFormattedText(filter.getClass().getSimpleName(), FormatMarkdown.ITALIC) + " "));

            System.out.println("\n");
            System.out.println(StaticUtils.getFormattedText("You may enter `-1` at any moment to clear current filters", FormatMarkdown.ITALIC));


            choice = scanner.nextInt();

            if(choice == -1) {
                clearFilters();
                System.out.print("The filters have been cleaned, reopening menu");

                try {
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);

                    openFilterMenu();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                return;
            }

            if(choice == FilterUtils.getAvailableFilters().size() + 1) {
                appliedFilters.clear();
                openMainMenu();
                return;
            }

            int finalChoice = choice;

            IFilter chosenFilter = FilterUtils.getAvailableFilters().stream().filter(filter -> filter.getFilterID() == finalChoice).findFirst().orElse(null);

            if(chosenFilter != null) {
                if(!appliedFilters.contains(chosenFilter)) {
                    System.out.print("Enter your query: ");

                    scanner.nextLine();
                    String inputString = scanner.nextLine();

                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

                    if(finalChoice ==  12)
                        dateFormatter = new SimpleDateFormat("MM/yyyy");
                    else if(finalChoice == 13)
                        dateFormatter = new SimpleDateFormat("yyyy");

                    switch(finalChoice) {
                        case 1,4,8 -> {
                            try {
                                Long toBeSent = Long.parseLong(inputString);

                                appliedPredicates.add(chosenFilter.getPredicate(toBeSent));
                                appliedFilters.add(chosenFilter);

                                showFilteredResult();
                            } catch(NumberFormatException exception) {
                                System.out.println("ERROR: You cannot input a non long value for the ID");
                            }
                        }

                        case 2,3,5,6,7,9,10 -> {
                            appliedPredicates.add(chosenFilter.getPredicate(inputString));
                            appliedFilters.add(chosenFilter);

                            showFilteredResult();
                        }

                        case 11,12,13 -> {
                            try {
                                Date chosenDate = dateFormatter.parse(inputString);

                                appliedPredicates.add(chosenFilter.getPredicate(chosenDate));
                                appliedFilters.add(chosenFilter);

                                showFilteredResult();
                            } catch (ParseException e) {
                                System.out.println("Unable to parse date");
                            }
                        }
                    }
                } else
                    System.out.println("You've already filtered this field, please clean your filters before proceeding.");
            } else
                System.out.println("No filter with that ID was found. Please try again");

        } while(choice != FilterUtils.getAvailableFilters().size() + 1);

    }

    private void showFilteredResult() {
        Predicate<Operation> pred = appliedPredicates.stream().reduce(Predicate::and).orElse(x->true);

        if(JarvisLists.getOperationList().stream().noneMatch(pred))
            System.out.println("The stream returned with no result.");
        else
            JarvisLists.getOperationList().stream().filter(pred).forEach(Operation::printSummaryOperation);
    }

    private void clearFilters() {
        appliedFilters.clear();
        appliedPredicates.clear();
    }

    private void openDisplayMenu() {
        int choice = 0;

        do {
            System.out.println();
            System.out.println(StaticUtils.getDivider());
            System.out.println("              Display Menu                   ");
            System.out.println(StaticUtils.getDivider());
            System.out.println("1- Display all operations.");
            System.out.println("2- Display a summary of the last n operations.");
            System.out.println("3- Display all operations between 2 dates.");
            System.out.println("4- Display the before and after of a specific operation.");
            System.out.println("5- Display all accessories.");
            System.out.println("6- Display all users.");
            System.out.println("7- Exit to main menu.");

            choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 7) {
                openMainMenu();
                return;
            }

            switch (choice) {
                case 1 -> JarvisLists.getOperationList().forEach(Operation::printOperation);
                case 2 -> {
                    System.out.println("Enter the amount of the last accessories you'd like to check: ");
                    int n = scanner.nextInt();

                    while(n <= 0) {
                        System.out.print("The size cannot be negative: ");
                        n = scanner.nextInt();
                    }

                    JarvisLists.printLastOperations(n);
                }
                case 3 -> {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                    System.out.println("Enter the first date following this format: 31/12/1998 23:37:50");
                    String firstDateString = scanner.nextLine();

                    try {
                        Date firstDate = dateFormatter.parse(firstDateString);

                        System.out.println("Enter the second date following this format: 31/12/1998 23:37:50");
                        String secondDateString = scanner.nextLine();

                        Date secondDate = dateFormatter.parse(secondDateString);

                        Date lowestDate = firstDate.before(secondDate) ? firstDate : secondDate;
                        Date highestDate = firstDate.after(secondDate) ? firstDate : secondDate;

                        JarvisLists.showBetween(lowestDate, highestDate);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                case 4 -> {
                    System.out.println("Enter the ID of the operation you'd like to check: ");
                    int id = scanner.nextInt();

                    JarvisLists.showChangeOfStates(id);
                }

                case 5 -> JarvisLists.getAccessoryList().forEach(Accessory::printAccessory);
                case 6 -> JarvisLists.getUsersList().forEach(User::printUser);
                default -> System.out.println("Unknown data point entry, try again");
            }

        } while(true);

    }
}
