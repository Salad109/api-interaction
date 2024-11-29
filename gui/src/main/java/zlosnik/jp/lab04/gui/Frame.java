package zlosnik.jp.lab04.gui;

import javax.swing.*;

public class Frame extends JFrame {

    public Frame(InformationStorage storage) {
        setTitle("Aplikacja NFZ");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel tab1 = new JPanel();

        JList<String> list = new JList<>(storage.swiadczeniaZdrowotne.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(list);
        tab1.add(scrollPane);

        tabbedPane.addTab("Tab 1", tab1);

        add(tabbedPane);
    }
}