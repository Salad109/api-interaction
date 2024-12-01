package zlosnik.jp.lab04.gui;

import zlosnik.jp.lab04.client.ApiService;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(InformationStorage storage, ApiService apiService) {
        setTitle("Aplikacja NFZ");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel benefitsTab = new BenefitsTab(storage, apiService);
        JPanel localitiesTab = new LocalitiesTab(storage, apiService);
        JPanel placesTab = new PlacesTab(storage, apiService);

        tabbedPane.addTab("Świadczenia zdrowotne", benefitsTab);
        tabbedPane.addTab("Miejscowości udzielania świadczeń", localitiesTab);
        tabbedPane.addTab("Miejsca udzielania świadczeń ", placesTab);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        add(tabbedPane);
        setVisible(true);
    }
}