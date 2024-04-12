package file;

import minigame.bondgame.Bond;
import minigame.cryptocurrency.CryptoCurrency;
import minigame.stockgame.Stock;
import player.Asset;
import company.Company;
import player.PlayerProfile;

import java.util.List;

public class Serializer {

    public static String constructJson(PlayerProfile playerProfile) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n")
            .append(" \"name\": \"").append(playerProfile.getName()).append("\",\n")
            .append(" \"occupation\": \"").append(playerProfile.getOccupation()).append("\",\n")
            .append(" \"health\": ").append(playerProfile.getHealth()).append(",\n")
            .append(" \"currentRound\": ").append(playerProfile.getCurrentRound()).append(",\n")
            .append(" \"actionCount\": ").append(playerProfile.getActionCount()).append(",\n")
            .append(" \"isAdvancedPlayer\": ").append(playerProfile.isAdvancedPlayer()).append(",\n")
            .append(" \"company\": ").append(serializeCompany(playerProfile.getCompany())).append(",\n")
            .append(" \"asset\": ").append(serializeAssets(playerProfile.getAsset())).append("\n")
            .append("}");

        return jsonBuilder.toString();
    }

    private static String serializeCompany(Company company) {
        return String.format("{\n" +
                "   \"name\": \"%s\",\n" +
                "   \"numberOfEmployees\": %d,\n" +
                "   \"employeeSalary\": %d,\n" +
                "   \"revenuePerEmployee\": %d\n" +
                "}",
            company.getName(),
            company.getNumberOfEmployees(),
            company.getEmployeeSalary(),
            company.getRevenuePerEmployee()
        );
    }

    private static String serializeAssets(Asset asset) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n")
            .append(" \"money\": ").append(asset.outputMoney()).append(",\n")
            .append(" \"stocks\": ").append(serializeStocks(asset.getStockList(),
                asset.getStockCount())).append(",\n")
            .append(" \"bonds\": ").append(serializeBonds(asset.getBondList(),
                asset.getBondCount())).append(",\n")
            .append(" \"cryptos\": ").append(serializeCryptos(asset.getCryptoList(),
                asset.getCryptoCount())).append("\n")
            .append("}");

        return jsonBuilder.toString();
    }

    private static String serializeStocks(List<Stock> stocks, List<Integer> counts) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < stocks.size(); i++) {
            Stock stock = stocks.get(i);
            int count = counts.get(i);
            jsonBuilder.append("{")
                .append("\"name\": \"").append(stock.returnStockName()).append("\",")
                .append("\"count\": ").append(count)
                .append("}");
            if (i < stocks.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private static String serializeBonds(List<Bond> bonds, List<Integer> counts) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < bonds.size(); i++) {
            Bond bond = bonds.get(i);
            int count = counts.get(i);
            jsonBuilder.append("{")
                .append("\"name\": \"").append(bond.returnBondName()).append("\",")
                .append("\"count\": ").append(count)
                .append("}");
            if (i < bonds.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    private static String serializeCryptos(List<CryptoCurrency> cryptos, List<Integer> counts) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < cryptos.size(); i++) {
            CryptoCurrency crypto = cryptos.get(i);
            int count = counts.get(i);
            jsonBuilder.append("{")
                .append("\"name\": \"").append(crypto.returnCryptoName()).append("\",")
                .append("\"count\": ").append(count)
                .append("}");
            if (i < cryptos.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
}
