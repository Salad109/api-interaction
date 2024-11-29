package zlosnik.jp.lab04.gui;

import zlosnik.jp.lab04.client.HttpService;
import zlosnik.jp.lab04.client.HttpServiceClient;
import zlosnik.jp.lab04.client.ApiResponse;

public class Main {

    public static void main(String[] args) {
        var storage = new InformationStorage();
        HttpService httpService = new HttpServiceClient();
        String apiUrl = "https://api.nfz.gov.pl/app-itl-api/localities?page=1&limit=10&format=json&name=Wroc%C5%82aw&province=01&api-version=1.3";
        ApiResponse response = httpService.makeHttpRequest(apiUrl);

        if (response == null) {
            System.out.println("Error while making request");
            return;
        }

        storage.swiadczeniaZdrowotne = response.getData();
        Frame frame = new Frame(storage);
        frame.setVisible(true);
    }
}
