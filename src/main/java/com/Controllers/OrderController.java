package com.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.models.Pizza;
import com.models.Order;
import com.models.Customer;
import com.Utils.LoyaltyProgram;

import java.util.Arrays;

public class OrderController {

    @FXML
    private ComboBox<String> crustComboBox;

    @FXML
    private ComboBox<String> sauceComboBox;

    @FXML
    private CheckBox mushroomsCheckBox, pepperoniCheckBox, onionsCheckBox;

    @FXML
    private TextField nameTextField, addressTextField;

    @FXML
    private TextArea feedbackTextArea;

    @FXML
    private Slider ratingSlider;

    @FXML
    private Button submitFeedbackButton, cardPaymentButton, cashPaymentButton;

    @FXML
    private Label paymentStatusLabel, priceLabel;

    private Customer currentCustomer;
    // Base price for the pizza
    private double basePrice = 1500.00;
    private double total = basePrice;

    @FXML
    public void initialize() {
        crustComboBox.getItems().addAll("Thin", "Thick");
        sauceComboBox.getItems().addAll("Tomato", "Barbecue");

        // Add listeners to update price dynamically
        crustComboBox.setOnAction(event -> updatePrice());
        sauceComboBox.setOnAction(event -> updatePrice());
        mushroomsCheckBox.setOnAction(event -> updatePrice());
        pepperoniCheckBox.setOnAction(event -> updatePrice());
        onionsCheckBox.setOnAction(event -> updatePrice());
    }

    private void updatePrice() {
        double price = basePrice;

        // Add cost for crust
        String crust = crustComboBox.getValue();
        if ("Thin".equals(crust)) {
            price += 200.00;
        } else if ("Thick".equals(crust)) {
            price += 300.00;
        }


        // Add cost for sauce
        String sauce = sauceComboBox.getValue();
        if ("Tomato".equals(sauce)) {
            price += 150.00;
        } else if ("Barbecue".equals(sauce)) {
            price += 200.00;
        }

        // Add cost for toppings
        if (mushroomsCheckBox.isSelected()) price += 350.00;
        if (pepperoniCheckBox.isSelected()) price += 250.00;
        if (onionsCheckBox.isSelected()) price += 200.00;

        total = price;
        // Update the price label
        priceLabel.setText(String.format("Rs. %.2f", price));
    }

    @FXML
    public void placeOrder() {
        if (currentCustomer == null) {
            String customerName = nameTextField.getText();
            String customerAddress = addressTextField.getText();

            if (customerName.isEmpty() || customerAddress.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Missing Information");
                alert.setHeaderText("Please provide your name and address.");
                alert.show();
                return;
            }

            currentCustomer = new Customer(customerName, customerAddress);
        }

        String crust = crustComboBox.getValue();
        String sauce = sauceComboBox.getValue();

        String[] toppings = {mushroomsCheckBox.isSelected() ? "Mushrooms" : null, pepperoniCheckBox.isSelected() ? "Pepperoni" : null, onionsCheckBox.isSelected() ? "Onions" : null,};

        // Remove null toppings
        toppings = Arrays.stream(toppings).filter(topping -> topping != null).toArray(String[]::new);

        Pizza pizza = new Pizza(crust, sauce, toppings);
        Order order = new Order(pizza, currentCustomer.getName(), currentCustomer.getAddress(), total);

        currentCustomer.setCrustPreference(crust);
        currentCustomer.setSaucePreference(sauce);
        currentCustomer.setToppingsPreference(toppings);
        currentCustomer.savePreferences("userPreferences.json");

        LoyaltyProgram.addPoints(currentCustomer, 10);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText("Your Order:");
        alert.setContentText(order.getOrderDetails());
        alert.show();

        int currentPoints = LoyaltyProgram.getPoints(currentCustomer);
        Alert loyaltyAlert = new Alert(Alert.AlertType.INFORMATION);
        loyaltyAlert.setTitle("Loyalty Points");
        loyaltyAlert.setHeaderText("Loyalty Points:");
        loyaltyAlert.setContentText("You currently have " + currentPoints + " loyalty points.");
        loyaltyAlert.show();

        // Enable feedback and payment options
        feedbackTextArea.setDisable(false);
        ratingSlider.setDisable(false);
        submitFeedbackButton.setDisable(false);
        cardPaymentButton.setDisable(false);
        cashPaymentButton.setDisable(false);
    }

    @FXML
    public void handleCardPayment() {
        paymentStatusLabel.setText("Processing card payment...");
        processPayment("Card");
    }

    @FXML
    public void handleCashPayment() {
        paymentStatusLabel.setText("Processing cash payment...");
        processPayment("Cash");
    }

    private void processPayment(String paymentMethod) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ("Card".equals(paymentMethod)) {
            paymentStatusLabel.setText("Payment successful via Card.");
        } else if ("Cash".equals(paymentMethod)) {
            paymentStatusLabel.setText("Payment successful via Cash.");
        }
    }

    @FXML
    public void submitFeedback() {
        String feedback = feedbackTextArea.getText();
        int rating = (int) ratingSlider.getValue();

        if (feedback.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feedback Error");
            alert.setHeaderText("Feedback cannot be empty.");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feedback Submitted");
        alert.setHeaderText("Thank you for your feedback!");
        alert.setContentText("You rated us " + rating + " out of 5.\nYour feedback: " + feedback);
        alert.show();

        feedbackTextArea.setDisable(true);
        ratingSlider.setDisable(true);
        submitFeedbackButton.setDisable(true);
    }
}
