package zlosnik.jp.lab04.gui;

import zlosnik.jp.lab04.client.ApiService;
import zlosnik.jp.lab04.client.ApiServiceClient;

public class Main {

    public static void main(String[] args) {
        var storage = new InformationStorage();
        ApiService apiService = new ApiServiceClient();

        Frame frame = new Frame(storage, apiService);
    }
}
