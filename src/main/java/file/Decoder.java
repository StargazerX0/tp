package file;

import company.Company;
import exception.LoadProfileException;

import exception.NameInputException;
import minigame.stockgame.Stock;
import minigame.stockgame.StockOne;
import minigame.stockgame.StockTwo;
import minigame.stockgame.StockThree;
import minigame.stockgame.StockFour;
import minigame.stockgame.StockFive;
import minigame.stockgame.StockSix;
import minigame.stockgame.StockSeven;
import minigame.stockgame.StockEight;
import minigame.stockgame.StockNine;
import minigame.stockgame.StockTen;

import minigame.bondgame.Bond;
import minigame.bondgame.CorporateGrowthBond;
import minigame.bondgame.GovernmentStabilityBond;
import minigame.bondgame.HighYieldBond;
import minigame.bondgame.InflationLinkedBond;

import minigame.cryptocurrency.CryptoCurrency;
import minigame.cryptocurrency.Bitcoin;
import minigame.cryptocurrency.Cardano;
import minigame.cryptocurrency.Ethereum;
import minigame.cryptocurrency.Litecoin;

import org.json.JSONArray;
import org.json.JSONObject;
import player.Asset;
import player.PlayerProfile;
import ui.Parser;

import java.util.ArrayList;
import java.util.List;


/**
 * Decodes JSON data into player profiles and associated objects.
 * This class translates JSON strings into Java objects like PlayerProfile, Asset, Stock, Bond, and CryptoCurrency.
 */
public class Decoder {

    /**
     * Decodes a JSON string into a PlayerProfile object, including related entities like assets and company.
     *
     * @param json The JSON string representing the player profile.
     * @return PlayerProfile The fully constructed player profile.
     * @throws LoadProfileException If any error occurs during decoding.
     */
    public static PlayerProfile decodePlayerProfile(String json) throws LoadProfileException {
        try {
            JSONObject jsonObj = new JSONObject(json);

            String name = jsonObj.getString("name");
            String occupation = jsonObj.getString("occupation");
            int health = jsonObj.getInt("health");
            int currentRound = jsonObj.getInt("currentRound");
            int actionCount = jsonObj.getInt("actionCount");
            boolean isAdvancedPlayer = jsonObj.getBoolean("isAdvancedPlayer");
            checkPlayerInfoValidity(name, occupation);
            checkPlayerDataValidity(health, currentRound, actionCount);
            
            Asset asset = decodeAsset(jsonObj.getJSONObject("asset").toString());
            Company company = decodeCompany(jsonObj.getJSONObject("company").toString());

            return new PlayerProfile(name, occupation, health, asset,
                currentRound, actionCount, isAdvancedPlayer, company);
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding player profile.\n");
        }
    }

    /**
     * Checks the validity of player's name and occupation.
     *
     * @param name The player's name.
     * @param occupation The player's occupation.
     */
    private static void checkPlayerInfoValidity(String name, String occupation) throws LoadProfileException {
        if (!occupation.equals("Robotics") &&
                !occupation.equals("Semi-conductor") &&
                !occupation.equals("Artificial intelligence")) {
            throw new LoadProfileException("Invalid player info.\n");
        }
        try {
            Parser.parseName(name);
        } catch (NameInputException e) {
            throw new LoadProfileException("Invalid player info.\n");
        }
    }

    /**
     * Checks the validity of player's numerical data.
     *
     * @param health The player's health.
     * @param currentRound The current round the player is on.
     * @param actionCount The number of actions the player has left.
     */
    private static void checkPlayerDataValidity(
            int health, int currentRound, int actionCount) throws LoadProfileException {
        if (health < 0 || health > 100 ||
                currentRound <= 0 || currentRound > 20 ||
                actionCount < 0 || actionCount > 2) {
            throw new LoadProfileException("Invalid player data.\n");
        }
    }

    /**
     * Decodes JSON string to an Asset object including stocks, bonds, and cryptocurrencies.
     *
     * @param json The JSON string of the asset details.
     * @return Asset The decoded asset object.
     * @throws LoadProfileException If decoding fails.
     */
    private static Asset decodeAsset(String json) throws LoadProfileException {
        try {
            JSONObject jsonObj = new JSONObject(json);
            int totalAsset = jsonObj.getInt("money");
            checkAssetValidity(totalAsset);

            Asset asset = new Asset(totalAsset);

            List<Integer> stockCount = new ArrayList<>();
            List<Stock> stocks = decodeStocks(jsonObj.getJSONArray("stocks").toString(), stockCount);
            asset.setStockList(stocks);
            asset.setStockCount(stockCount);

            // Assume similar methods for bonds and cryptos
            List<Integer> bondCount = new ArrayList<>();
            List<Bond> bonds = decodeBonds(jsonObj.getJSONArray("bonds").toString(), bondCount);
            asset.setBondList(bonds);
            asset.setBondCount(bondCount);

            List<Integer> cryptoCount = new ArrayList<>();
            List<CryptoCurrency> cryptos = decodeCryptos(jsonObj.getJSONArray("cryptos").toString(), cryptoCount);
            asset.setCryptoList(cryptos);
            asset.setCryptoCount(cryptoCount);

            return asset;
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding asset.\n");
        }
    }

    /**
     * Decodes a JSON array of stocks into a list of Stock objects and their counts.
     *
     * @param json The JSON string representing the array of stocks.
     * @param stockCount A list to store the counts of each stock.
     * @return {@code List<Stock>} A list of decoded stock objects.
     * @throws LoadProfileException If decoding fails.
     */
    private static List<Stock> decodeStocks(String json, List<Integer> stockCount) throws LoadProfileException {
        List<Stock> stocks = new ArrayList<>();
        try {
            JSONArray stockArray = new JSONArray(json);
            for (int i = 0; i < stockArray.length(); i++) {
                JSONObject stockObject = stockArray.getJSONObject(i);
                String name = stockObject.getString("name");
                int count = stockObject.getInt("count");
                checkAssetValidity(count);

                Stock stock = createStockFromName(name);
                stocks.add(stock);
                stockCount.add(count);
            }
            return stocks;
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding stocks.\n");
        }
    }

    /**
     * Creates a Stock object based on the stock name.
     *
     * @param name The name of the stock.
     * @return Stock The stock object.
     */
    private static Stock createStockFromName(String name) {
        switch (name) {
        case "WaveScan Technologies (Start ups) ":
            return new StockOne();
        case "Tesla (Large multi-national company) ":
            return new StockTwo();
        case "Nvidia (Multi-national company) ":
            return new StockThree();
        case "Groq (Start up company) ":
            return new StockFour();
        case "Atlas Tech ":
            return new StockFive();
        case "BYD (Vehicle cooperation) ":
            return new StockSix();
        case "NextGen (Semiconductor start-up) ":
            return new StockSeven();
        case "Microsoft (Multi-national cooperation) ":
            return new StockEight();
        case "eSim (software start-up) ":
            return new StockNine();
        case "Web 3.0 (Software start-up) ":
            return new StockTen();
        default:
            throw new IllegalArgumentException("Unknown stock name: " + name);
        }
    }

    /**
     * Decodes a JSON array of bonds into a list of Bond objects and their counts.
     *
     * @param json The JSON string representing the array of bonds.
     * @param bondCount A list to store the counts of each bond.
     * @return {@code List<Bond>} A list of decoded bond objects.
     * @throws LoadProfileException If decoding fails.
     */
    private static List<Bond> decodeBonds(String json, List<Integer> bondCount) throws LoadProfileException {
        List<Bond> bonds = new ArrayList<>();
        try {
            JSONArray bondArray = new JSONArray(json);
            for (int i = 0; i < bondArray.length(); i++) {
                JSONObject bondObject = bondArray.getJSONObject(i);
                String name = bondObject.getString("name");
                int count = bondObject.getInt("count");
                checkAssetValidity(count);

                Bond bond = createBondFromName(name);
                bonds.add(bond);
                bondCount.add(count);
            }
            return bonds;
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding bonds.\n");
        }
    }

    /**
     * Creates a Bond object based on the bond name.
     *
     * @param name The name of the bond.
     * @return Bond The bond object.
     */
    private static Bond createBondFromName(String name) {
        switch (name) {
        case "Corporate Growth Bond":
            return new CorporateGrowthBond();
        case "Government Stability Bond":
            return new GovernmentStabilityBond();
        case "High Yield Bond":
            return new HighYieldBond();
        case "Inflation Linked Bond":
            return new InflationLinkedBond();
        default:
            throw new IllegalArgumentException("Unknown bond name: " + name);
        }
    }

    /**
     * Decodes a JSON array of cryptocurrencies into a list of CryptoCurrency objects and their counts.
     *
     * @param json The JSON string representing the array of cryptocurrencies.
     * @param cryptoCount A list to store the counts of each cryptocurrency.
     * @return {@code List<CryptoCurrency>} A list of decoded cryptocurrency objects.
     * @throws LoadProfileException If decoding fails.
     */
    private static List<CryptoCurrency> decodeCryptos(String json, List<Integer> cryptoCount)
            throws LoadProfileException {
        List<CryptoCurrency> cryptos = new ArrayList<>();
        try {
            JSONArray cryptoArray = new JSONArray(json);
            for (int i = 0; i < cryptoArray.length(); i++) {
                JSONObject cryptoObject = cryptoArray.getJSONObject(i);
                String name = cryptoObject.getString("name");
                int count = cryptoObject.getInt("count");
                checkAssetValidity(count);

                CryptoCurrency crypto = createCryptoFromName(name);
                cryptos.add(crypto);
                cryptoCount.add(count);
            }
            return cryptos;
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding cryptocurrencies.\n");
        }
    }

    private static void checkAssetValidity(int number) throws LoadProfileException {
        if (number < 0) {
            throw new LoadProfileException("Invalid number.\n");
        }
    }

    /**
     * Creates a CryptoCurrency object based on the cryptocurrency name.
     *
     * @param name The name of the cryptocurrency.
     * @return CryptoCurrency The cryptocurrency object.
     */
    private static CryptoCurrency createCryptoFromName(String name) {
        switch (name) {
        case "Bitcoin":
            return new Bitcoin();
        case "Cardano":
            return new Cardano();
        case "Ethereum":
            return new Ethereum();
        case "Litecoin":
            return new Litecoin();
        default:
            throw new IllegalArgumentException("Unknown cryptocurrency name: " + name);
        }
    }

    /**
     * Decodes the company data from the JSON string into a Company object.
     *
     * @param json The JSON string containing the company data.
     * @return Company The decoded company object.
     * @throws LoadProfileException If an error occurs during decoding.
     */
    private static Company decodeCompany(String json) throws LoadProfileException {
        try {
            JSONObject jsonObj = new JSONObject(json);

            String name = jsonObj.getString("name");
            int numberOfEmployees = jsonObj.getInt("numberOfEmployees");
            int employeeSalary = jsonObj.getInt("employeeSalary");
            int revenuePerEmployee = jsonObj.getInt("revenuePerEmployee");
            checkCompanyDataValidity(numberOfEmployees, employeeSalary, revenuePerEmployee);
            
            return new Company(name, numberOfEmployees, employeeSalary, revenuePerEmployee);
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding company data.\n");
        }
    }

    private static void checkCompanyDataValidity(
            int numberOfEmployees, int employeeSalary, int revenuePerEmployee) throws LoadProfileException {
        if (numberOfEmployees < 0 || employeeSalary < 0 || revenuePerEmployee < 0) {
            throw new LoadProfileException("Invalid company data.\n");
        }
    }
}
