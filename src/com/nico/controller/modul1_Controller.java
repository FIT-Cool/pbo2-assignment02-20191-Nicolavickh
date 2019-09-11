package com.nico.controller;

import com.nico.entity.category;
import com.nico.entity.item;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class modul1_Controller implements Initializable {

    private ObservableList<item> items;
    private ObservableList<category> categories;

    @FXML
    private MenuBar menuBar;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private ComboBox<category> comboBox;
    @FXML
    private TextField txtCategory;
    @FXML
    private TableColumn<item, String> colName;
    @FXML
    private TableColumn<item, Double> colPrice;
    @FXML
    private TableColumn<item, String> colCategory;
    @FXML
    private Button updateButton;
    @FXML
    private Button saveButton;
    @FXML
    private TableView<item> table;

    public void saveButton(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        if (txtName.getText().isEmpty() || txtPrice.getText().isEmpty() || comboBox.getValue().getName().isEmpty()) {
            a.setContentText("Please fill Name/Price/Category");
        } else {
            item i = new item();
            i.setName(txtName.getText().trim());
            i.setPrice(Double.parseDouble(txtPrice.getText().trim()));
            i.setCategory(comboBox.getValue());
            items.add(i);
        }

    }

    public void resetButton(ActionEvent actionEvent) {
        updateButton.setDisable(true);
        saveButton.setDisable(false);
        comboBox.setValue(null);
        txtCategory.setText(null);
        txtName.setText(null);
        txtPrice.setText(null);
    }

    public void updateButton(ActionEvent actionEvent) {
        item i = table.getSelectionModel().getSelectedItem();
        i.setCategory(comboBox.getValue());
        i.setName(txtName.getText());
        i.setPrice(Double.parseDouble(txtPrice.getText()));
        table.refresh();
    }

    public void saveCategoryButton(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        if (txtCategory.getText().isEmpty()) {
            a.setContentText("Please fill Category Name");
            a.showAndWait();
        } else {
            category c = new category();
            c.setName(txtCategory.getText().trim());
            boolean ada = false;
            for (category i : categories) {
                if (i.getName().equals(c.getName())) {
                    a.setContentText("Duplicate Category Name");
                    a.showAndWait();
                    ada = true;
                    break;
                }
            }
            if (ada == false) {
            if (ada == false) {
                categories.add(c);
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categories = FXCollections.observableArrayList();
        comboBox.setItems(categories);
        items = FXCollections.observableArrayList();
        table.setItems(items);
        colCategory.setCellValueFactory(data -> {
            item a = data.getValue();
            return new SimpleStringProperty(a.getCategory().getName());
        });
        colName.setCellValueFactory(data -> {
            item b = data.getValue();
            return new SimpleStringProperty(b.getName());

        });
        colPrice.setCellValueFactory(data -> {
            item c = data.getValue();
            return new SimpleDoubleProperty(c.getPrice()).asObject();
        });
    }

    @FXML
    private void tableClicked(MouseEvent mouseEvent) {
        item i = table.getSelectionModel().getSelectedItem();
        txtName.setText(i.getName());
        txtPrice.setText(String.valueOf(i.getPrice()));
        comboBox.setValue(i.getCategory());
        saveButton.setDisable(true);
        updateButton.setDisable(false);
    }
}
