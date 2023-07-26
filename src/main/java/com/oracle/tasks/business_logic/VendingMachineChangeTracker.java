package com.oracle.tasks.business_logic;

import com.oracle.tasks.exception.ExactChangeNotAvailableException;
import com.oracle.tasks.model.Coin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Vending Machine Change Tracker class includes the business logic implementation
 */
public class VendingMachineChangeTracker {
    private static final Logger LOGGER = LoggerFactory.getLogger(VendingMachineChangeTracker.class);
    private List<Coin> coinPool;

    public List<Coin> getCoinPool() {
        return null == coinPool ? new ArrayList<>() : coinPool;
    }

    public VendingMachineChangeTracker(List<Coin> coinPool) {
        LOGGER.info("VendingMachineChangeTracker - VendingMachineChangeTracker Constructor called");
        this.coinPool = coinPool;
        LOGGER.info("Coin Pool After Initialization "+getCoinPool());
    }

    public void registerCoins(List<Coin> coins) {
        LOGGER.info("VendingMachineChangeTracker - registerCoins method invoked");
        // this.coinPool.addAll(coins); // throws Unsupported Operation Exception and hence converting the Coins into Integer & appending the newly registered coins with the Existing Coin Pool
        List<Integer> coinPoolValues = this.coinPool.stream().mapToInt(Coin::getValue).boxed().collect(Collectors.toList());
        List<Integer> registeredCoinValues = coins.stream().mapToInt(Coin::getValue).boxed().collect(Collectors.toList());
        coinPoolValues.addAll(registeredCoinValues);
        this.coinPool = parseCoinValues(coinPoolValues);
        LOGGER.info("Coin Pool after registration "+coinPool);
    }

    public List<Coin> getChange(int orderValue) throws ExactChangeNotAvailableException {
        LOGGER.info("VendingMachineChangeTracker - getChange method invoked");
        LOGGER.info("Order Value is "+orderValue);
        LOGGER.info("Coin Pool "+coinPool);
        int[] coins = coinPool.stream().mapToInt(Coin::getValue).toArray();
        List<Integer> coinsMakingUpOrderValue = getMinimumCoinsList(coins, orderValue);
        LOGGER.info("Coins Making-Up the Order Value "+coinsMakingUpOrderValue);
        // update the coin pool to remove the served order
        List<Integer> difference = coinPool.stream().mapToInt(Coin::getValue).boxed().collect(Collectors.toList());

        for (Integer coinValue : coinsMakingUpOrderValue) {
            difference.remove(coinValue);
        }
        coinPool = parseCoinValues(difference);
        LOGGER.info("Coin Pool "+coinPool);
        return parseCoinValues(coinsMakingUpOrderValue);
    }

    private static List<Integer> getMinimumCoinsList(int[] arr, int value) throws ExactChangeNotAvailableException {
        Arrays.sort(arr); // Sort the array in ascending order for better backtracking

        List<Integer> result = new ArrayList<>();
        if (dfs(arr, value, 0, result)) {
            return result;
        } else {
            throw new ExactChangeNotAvailableException("Exact Change Not Available");
        }
    }

    private static boolean dfs(int[] arr, int value, int index, List<Integer> result) {
        if (value == 0) {
            return true; // Base case: value is obtained
        }

        for (int i = index; i < arr.length; i++) {
            if (arr[i] <= value) {
                result.add(arr[i]);
                if (dfs(arr, value - arr[i], i + 1, result)) {
                    return true;
                }
                result.remove(result.size() - 1); // Backtrack
            }
        }

        return false; // Value cannot be obtained
    }

    private static List<Coin> parseCoinValues(List<Integer> coinValues) {
        return coinValues.stream().map(Coin::new).collect(Collectors.toList());
    }
}
