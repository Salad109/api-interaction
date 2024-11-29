package zlosnik.jp.lab04.gui;

import zlosnik.jp.lab04.client.HttpService;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(InformationStorage storage, HttpService httpService) {
        setTitle("Aplikacja NFZ");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel tab1 = new LocalitiesTab(storage, httpService);
        tabbedPane.addTab("Miejscowości udzielania świadczeń", tab1);

        JPanel tab2 = new PlacesTab(storage, httpService);
        tabbedPane.addTab("Miejsca udzielania świadczeń ", tab2);

        add(tabbedPane);
        setVisible(true);
    }
}