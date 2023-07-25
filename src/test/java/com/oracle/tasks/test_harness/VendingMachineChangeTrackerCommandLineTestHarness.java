package com.oracle.tasks.test_harness;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.tasks.model.Coin;
import org.junit.jupiter.api.Disabled;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Test Harness Class with REPL (Read-Eval-Print-Loop) for various options
 */
@Disabled
public class VendingMachineChangeTrackerCommandLineTestHarness
{
    private static final String BASE_URL = "http://localhost:8080/api/v1/vending-machine/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Vending Machine Test Runner!");
        System.out.println("Please verify whether the Vending Machine SpringBoot Application is up and running before choosing any of the following options.!");
        System.out.println("Enter '1' to initialize vending machine");
        System.out.println("Enter '2' to register coins");
        System.out.println("Enter '3' to get change");
        System.out.println("Enter '0' to exit");

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        while (true) {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter the coin denominations available separated by spaces: (eg:1 2 5)");
                    String coinDenominationsInput = scanner.nextLine();
                    List<String> coinDenominationsValues = Arrays.asList(coinDenominationsInput.split(" "));
                    List<Coin> coinDenominations = parseCoinValues(coinDenominationsValues);
                    ResponseEntity<String> initResponse = restTemplate.postForEntity(BASE_URL + "initialize", coinDenominations, String.class);
                    System.out.println(initResponse.getBody());
                    break;
                case 2:
                    System.out.println("Enter the coin values deposited by the user separated by spaces: (eg:2 2 2 5 5)");
                    String coinsInput = scanner.nextLine();
                    List<String> coinValues = Arrays.asList(coinsInput.split(" "));
                    List<Coin> coins = parseCoinValues(coinValues);
                    ResponseEntity<String> registerResponse = restTemplate.postForEntity(BASE_URL + "register-coins", coins, String.class);
                    System.out.println(registerResponse.getBody());
                    break;
                case 3:
                    System.out.print("Enter the order value: (eg:10)");
                    int orderValue = scanner.nextInt();
                    try {
                        ResponseEntity<String> getChangeResponse = restTemplate.postForEntity(BASE_URL + "get-change", orderValue, String.class);
                        System.out.println("Change coins: " + getChangeResponse.getBody());
                    } catch (Exception e) {
                        System.out.print("Exception " + e.getStackTrace());
                    }
                    break;
                case 0:
                    System.out.println("Exiting the Vending Machine Test Runner.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static List<Coin> parseCoinValues(List<String> coinValues) {
        List<Coin> coins = coinValues.stream().map(Integer::parseInt).map(Coin::new).collect(Collectors.toList());
        return coins;
    }
}
