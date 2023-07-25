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
    private final List<Coin> coinDenominations;
    private List<Coin> coinPool;

    public List<Coin> getCoinPool() {
        return coinPool;
    }

    public VendingMachineChangeTracker(List<Coin> coinDenominations) {
        LOGGER.info("VendingMachineChangeTracker - VendingMachineChangeTracker Constructor called");
        this.coinDenominations = coinDenominations;
        this.coinPool = new ArrayList<>();
        log(coinDenominations, coinPool);
    }

    public void registerCoins(List<Coin> coins) {
        LOGGER.info("VendingMachineChangeTracker - registerCoins method invoked");
        this.coinPool = coins;
        LOGGER.info("Coin Pool "+coinPool);
    }

    public List<Coin> getChange(int orderValue) throws ExactChangeNotAvailableException {
        LOGGER.info("VendingMachineChangeTracker - getChange method invoked");
        LOGGER.info("Order Value is "+orderValue);
        log(coinDenominations, coinPool);
        int[] coins = coinPool.stream().mapToInt(i -> i.getValue()).toArray();
        List<Integer> coinsMakingUpOrderValue = getMinimumCoinsList(coins, orderValue);
        LOGGER.info("Coins Making-Up the Order Value "+coinsMakingUpOrderValue);
        // update the coin pool to remove the served order
        List<Integer> difference = coinPool.stream().mapToInt(i -> i.getValue()).boxed().collect(Collectors.toList());

        for (Integer coinValue : coinsMakingUpOrderValue) {
            difference.remove(coinValue);
        }
        coinPool = parseCoinValues(difference);
        log(coinDenominations, coinPool);
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
        List<Coin> coins = coinValues.stream().map(Coin::new).collect(Collectors.toList());
        return coins;
    }

    private static void log(List<Coin> coinDenominations, List<Coin> coinPool) {
        LOGGER.info("Coin Denominations "+coinDenominations);
        LOGGER.info("Coin Pool "+coinPool);
    }
}
