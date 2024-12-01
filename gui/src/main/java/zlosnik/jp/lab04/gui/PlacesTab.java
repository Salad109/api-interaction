package zlosnik.jp.lab04.gui;

import zlosnik.jp.lab04.client.ApiResponse;
import zlosnik.jp.lab04.client.ApiService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlacesTab extends JPanel {
    String provinceList = "<html>01 – dolnośląskie<br>02 – kujawsko-pomorskie<br>03 – lubelskie<br>04 – lubuskie<br>05 - łódzkie<br>06 – małopolskie<br>07 – mazowieckie<br>08 – opolskie<br>09 – podkarpackie<br>10 – podlaskie<br>11 – pomorskie<br>12 – śląskie<br>13 – świętokrzyskie<br>14 – warmińsko-mazurskie<br>15 – wielkopolskie<br>16 – zachodniopomorskie</html>";
    private DefaultListModel<String> listModel;

    public PlacesTab(InformationStorage storage, ApiService apiService) {
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        updateList(storage.swiadczeniaZdrowotne);
        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel cityLabel = new JLabel("Miasto:");
        JTextField cityField = new JTextField(20);
        JLabel provinceLabel = new JLabel("Kod województwa:");
        JTextField provinceField = new JTextField(20);
        JButton button = new JButton("Szukaj");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cityInput = cityField.getText();
                String provinceInput = provinceField.getText();
                // Process the input text
                System.out.println("Wyszukiwanie miejsca. Miasto: " + cityInput + ", Kod województwa: " + provinceInput);
                String apiUrl = getPlacesUrl(cityInput, provinceInput);
                System.out.println(apiUrl);
                ApiResponse response = apiService.makeHttpRequest(apiUrl);
                if (response == null) {
                    System.out.println("Error while making request");
                    return;
                }
                storage.swiadczeniaZdrowotne = response.getData();
                if (storage.swiadczeniaZdrowotne.isEmpty()) storage.swiadczeniaZdrowotne.add("Brak wyników");
                updateList(storage.swiadczeniaZdrowotne);
            }
        });

        JLabel tooltipLabel = new JLabel("Kody województw (najedź na mnie)");
        tooltipLabel.setToolTipText(provinceList);

        inputPanel.add(cityLabel);
        inputPanel.add(cityField);
        inputPanel.add(provinceLabel);
        inputPanel.add(provinceField);
        inputPanel.add(tooltipLabel);
        inputPanel.add(button);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void updateList(java.util.List<String> data) {
        listModel.clear();
        for (String item : data) {
            listModel.addElement(item);
        }
    }

    String getPlacesUrl(String name, String province) {
        return "https://api.nfz.gov.pl/app-itl-api/places?page=1&limit=25&format=json&name=" + name + "&province=" + province + "&api-version=1.3";
    }
}