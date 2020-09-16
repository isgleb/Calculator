import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.HashMap;

public class RatesHandler {
    private static HashMap<String, String> ratesValues = new HashMap<String, String>();


    public static String getRateValue(String currencyPair) {
        return ratesValues.get(currencyPair);
    }


    private static String rateParser(String url) throws Exception {
        Document document = Jsoup.connect(url).get();
        Elements value = document.getElementsByClass("news-stock-table__row_today");
        String text = value.text().split(" ")[1];
        String resultRate = text.replace(",", ".");
        return resultRate;
    }


    public static void refreshRates() throws Exception {

        String usdEur = rateParser("https://yandex.ru/news/quotes/30.html");
        ratesValues.put("EUR_USD", "* " + usdEur);
        ratesValues.put("USD_EUR", "/ " + usdEur);

        String rubUsd = rateParser("https://yandex.ru/news/quotes/2002.html");
        ratesValues.put("USD_RUB", "* " + rubUsd);
        ratesValues.put("RUB_USD", "/ " + rubUsd);

        String rubEur = rateParser("https://yandex.ru/news/quotes/2000.html");
        ratesValues.put("EUR_RUB", "* " + rubEur);
        ratesValues.put("RUB_EUR", "/ " + rubEur);

        ratesValues.put("RUB_RUB", "* 1");
        ratesValues.put("USD_USD", "* 1");
        ratesValues.put("EUR_EUR", "* 1");
    }
}
