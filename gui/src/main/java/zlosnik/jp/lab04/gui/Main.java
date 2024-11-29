package zlosnik.jp.lab04.gui;

import zlosnik.jp.lab04.client.HttpService;
import zlosnik.jp.lab04.client.HttpServiceClient;

public class Main {

    public static void main(String[] args) {
        var storage = new InformationStorage();
        HttpService httpService = new HttpServiceClient();

        Frame frame = new Frame(storage, httpService);
    }
}
