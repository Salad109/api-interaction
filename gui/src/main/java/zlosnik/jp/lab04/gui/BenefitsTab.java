package zlosnik.jp.lab04.gui;

import zlosnik.jp.lab04.client.ApiResponse;
import zlosnik.jp.lab04.client.ApiService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BenefitsTab extends JPanel {
    private DefaultListModel<String> listModel;

    public BenefitsTab(InformationStorage storage, ApiService apiService) {
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        updateList(storage.swiadczenia);
        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();

        JLabel benefitLabel = new JLabel("Nazwa świadczenia:");
        JTextField benefitField = new JTextField(20);
        JButton button = new JButton("Szukaj");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String benefitInput = benefitField.getText();
                // Process the input text
                System.out.println("Wyszukiwanie świadczenia. Świadczenie: " + benefitInput);
                String apiUrl = getBenefitsUrl(benefitInput);
                System.out.println(apiUrl);
                ApiResponse response = apiService.makeHttpRequest(apiUrl);
                if (response == null) {
                    System.out.println("Error while making request");
                    return;
                }
                storage.swiadczenia = response.getData();
                if (storage.swiadczenia.isEmpty()) storage.swiadczenia.add("Brak wyników");
                updateList(storage.swiadczenia);
            }
        });


        inputPanel.add(benefitLabel);
        inputPanel.add(benefitField);
        inputPanel.add(button);
        add(inputPanel, BorderLayout.SOUTH);

    }

    private void updateList(java.util.List<String> data) {
        listModel.clear();
        for (String item : data) {
            listModel.addElement(item);
        }
    }

    String getBenefitsUrl(String name) {
        return "https://api.nfz.gov.pl/app-itl-api/benefits?page=1&limit=25&format=json&name=" + name + "&api-version=1.3";
    }
}