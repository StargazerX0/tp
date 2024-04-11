package file;

import company.Company;
import exception.LoadProfileException;
import minigame.bondgame.*;
import minigame.cryptocurrency.*;
import minigame.stockgame.*;
import org.json.JSONArray;
import org.json.JSONObject;
import player.Asset;
import player.PlayerProfile;

import java.util.ArrayList;
import java.util.List;

public class Decoder {

    public static PlayerProfile decodePlayerProfile(String json) throws LoadProfileException {
        try {
            JSONObject jsonObj = new JSONObject(json);

            String name = jsonObj.getString("name");
            String occupation = jsonObj.getString("occupation");
            int health = jsonObj.getInt("health");
            int currentRound = jsonObj.getInt("currentRound");
            boolean isAdvancedPlayer = jsonObj.getBoolean("isAdvancedPlayer");

            Asset asset = decodeAsset(jsonObj.getJSONObject("asset").toString());
            Company company = decodeCompany(jsonObj.getJSONObject("company").toString());

            return new PlayerProfile(name, occupation, health, asset, currentRound, isAdvancedPlayer, company);
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding player profile.\n");
        }
    }

    private static Asset decodeAsset(String json) throws LoadProfileException {
        try {
            JSONObject jsonObj = new JSONObject(json);
            int totalAsset = jsonObj.getInt("money");

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

    private static List<Stock> decodeStocks(String json, List<Integer> stockCount) throws LoadProfileException {
        List<Stock> stocks = new ArrayList<>();
        try {
            JSONArray stockArray = new JSONArray(json);
            for (int i = 0; i < stockArray.length(); i++) {
                JSONObject stockObject = stockArray.getJSONObject(i);
                String name = stockObject.getString("name");
                int count = stockObject.getInt("count");

                Stock stock = createStockFromName(name);
                stocks.add(stock);
                stockCount.add(count);
            }
            return stocks;
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding stocks.\n");
        }
    }

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

    private static List<Bond> decodeBonds(String json, List<Integer> bondCount) throws LoadProfileException {
        List<Bond> bonds = new ArrayList<>();
        try {
            JSONArray bondArray = new JSONArray(json);
            for (int i = 0; i < bondArray.length(); i++) {
                JSONObject bondObject = bondArray.getJSONObject(i);
                String name = bondObject.getString("name");
                int count = bondObject.getInt("count");

                Bond bond = createBondFromName(name);
                bonds.add(bond);
                bondCount.add(count);
            }
            return bonds;
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding bonds.\n");
        }
    }
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

    private static List<CryptoCurrency> decodeCryptos(String json, List<Integer> cryptoCount) throws LoadProfileException {
        List<CryptoCurrency> cryptos = new ArrayList<>();
        try {
            JSONArray cryptoArray = new JSONArray(json);
            for (int i = 0; i < cryptoArray.length(); i++) {
                JSONObject cryptoObject = cryptoArray.getJSONObject(i);
                String name = cryptoObject.getString("name");
                int count = cryptoObject.getInt("count");

                CryptoCurrency crypto = createCryptoFromName(name);
                cryptos.add(crypto);
                cryptoCount.add(count);
            }
            return cryptos;
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding cryptocurrencies.\n");
        }
    }

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

    private static Company decodeCompany(String json) throws LoadProfileException {
        try {
            JSONObject jsonObj = new JSONObject(json);

            String name = jsonObj.getString("name");
            int numberOfEmployees = jsonObj.getInt("numberOfEmployees");
            int employeeSalary = jsonObj.getInt("employeeSalary");
            int revenuePerEmployee = jsonObj.getInt("revenuePerEmployee");

            return new Company(name, numberOfEmployees, employeeSalary, revenuePerEmployee);
        } catch (Exception e) {
            throw new LoadProfileException("Error decoding company data.\n");
        }
    }
}
