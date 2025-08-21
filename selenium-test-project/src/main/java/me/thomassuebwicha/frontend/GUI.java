package me.thomassuebwicha.frontend;

import com.formdev.flatlaf.FlatLightLaf;
import me.thomassuebwicha.domain.property.Property;
import me.thomassuebwicha.service.RealEstate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GUI {

    JFrame frame;
    RealEstate re;
    JTable table;
    DefaultTableModel tableModel;
    JButton scrapeButton;
    JButton exportButton;
    JButton clearButton;

    public GUI(RealEstate re) {
        this.re = re;
    }

    public void init() {
        FlatLightLaf.setup();
        guiSetup();
        populateFrame();
    }

    private void populateFrame() {
        // Table setup
        String[] columnNames = {"Street","Suburb","Bedrooms", "House Type", "Selling Price/"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 30, 620, 200);
        frame.add(scrollPane);

        // Scrape button
        scrapeButton = new JButton("Start Scraping");
        scrapeButton.setBounds(100, 300, 130, 50);
        scrapeButton.addActionListener(this::onScrapeClicked);
        frame.add(scrapeButton);

        // Export button
        exportButton = new JButton("Export Data");
        exportButton.setBounds(270, 300, 130, 50);
        exportButton.addActionListener(this::onExportClicked);
        frame.add(exportButton);

        clearButton = new JButton("Clear Table");
        clearButton.setBounds(440, 300, 130, 50);
        clearButton.addActionListener(e -> clearTable());
        frame.add(clearButton);
    }

    private void onScrapeClicked(ActionEvent e) {
        List<Property> data = re.scrapeData();
//        tableModel.setRowCount(0); // Clear table
        for (Property row : data) {
            Object[] translation = {row.getStreet(),row.getSuburb(), row.getBedrooms(), row.getHouseType(), row.getSellingAll()};
            tableModel.addRow(translation);
        }
    }

    private void onExportClicked(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            try (FileWriter fw = new FileWriter(fileChooser.getSelectedFile())) {
                // Write headers
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    fw.write(tableModel.getColumnName(i));
                    if (i < tableModel.getColumnCount() - 1) fw.write(",");
                }
                fw.write("\n");
                // Write rows
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        fw.write(String.valueOf(tableModel.getValueAt(i, j)));
                        if (j < tableModel.getColumnCount() - 1) fw.write(",");
                    }
                    fw.write("\n");
                }
                JOptionPane.showMessageDialog(frame, "Data exported successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Export failed: " + ex.getMessage());
            }
        }
    }

    // Main driver method
    private void guiSetup(){
        // Creating instance of JFrame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 400 width and 500 height
        frame.setSize(700, 600);

        // using no layout managers
        frame.setLayout(null);
    }


    public void show(){
        frame.setVisible(true);
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }
}