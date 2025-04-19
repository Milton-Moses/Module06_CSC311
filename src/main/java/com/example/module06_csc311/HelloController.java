package com.example.module06_csc311;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for the registration form.
 * Handles all user input validation and form submission.
 *
 * @author YourName
 * @version 1.0
 * @since 2023-11-20
 */
public class HelloController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField dobField;
    @FXML
    private TextField zipCodeField;
    @FXML
    private Button addButton;
    @FXML
    private Label firstNameError;
    @FXML
    private Label lastNameError;
    @FXML
    private Label emailError;
    @FXML
    private Label dobError;
    @FXML
    private Label zipCodeError;

    /**
     * Initializes the controller class.
     * Sets up listeners for all text fields to validate input on focus change.
     */
    @FXML
    public void initialize() {
        // Set up focus change listeners for all fields
        firstNameField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) validateFirstName();
            checkAllValid();
        });

        lastNameField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) validateLastName();
            checkAllValid();
        });

        emailField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) validateEmail();
            checkAllValid();
        });

        dobField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) validateDOB();
            checkAllValid();
        });

        zipCodeField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) validateZipCode();
            checkAllValid();
        });

        // Initially disable the add button
        addButton.setDisable(true);
    }

    /**
     * Validates the first name field.
     * @return true if validation passes, false otherwise
     */
    private boolean validateFirstName() {
        String firstName = firstNameField.getText();
        if (firstName.matches("^[a-zA-Z]{2,25}$")) {
            firstNameError.setText("");
            return true;
        } else {
            firstNameError.setText("First name must be 2-25 letters");
            return false;
        }
    }

    /**
     * Validates the last name field.
     * @return true if validation passes, false otherwise
     */
    private boolean validateLastName() {
        String lastName = lastNameField.getText();
        if (lastName.matches("^[a-zA-Z]{2,25}$")) {
            lastNameError.setText("");
            return true;
        } else {
            lastNameError.setText("Last name must be 2-25 letters");
            return false;
        }
    }

    /**
     * Validates the email field based on the first and last name.
     * @return true if validation passes, false otherwise
     */
    private boolean validateEmail() {
        String email = emailField.getText();
        String firstName = firstNameField.getText().toLowerCase();
        String lastName = lastNameField.getText().toLowerCase();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            emailError.setText("Enter first and last name first");
            return false;
        }

        char firstLetter = lastName.charAt(0);
        String expectedPattern = lastName.substring(0,lastName.length()-1) + firstName.charAt(0) + "@farmingdale\\.edu";

        if (email.matches("^" + expectedPattern + "$")) {
            emailError.setText("");
            return true;
        } else {
            emailError.setText("Email must be: " + lastName.substring(0,lastName.length()-1) + firstName.charAt(0) + "@farmingdale.edu");
            return false;
        }
    }

    /**
     * Validates the date of birth field.
     * @return true if validation passes, false otherwise
     */
    private boolean validateDOB() {
        String dob = dobField.getText();
        if (dob.matches("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$")) {
            dobError.setText("");
            return true;
        } else {
            dobError.setText("Date must be in MM/DD/YYYY format");
            return false;
        }
    }

    /**
     * Validates the zip code field.
     * @return true if validation passes, false otherwise
     */
    private boolean validateZipCode() {
        String zipCode = zipCodeField.getText();
        if (zipCode.matches("^\\d{5}$")) {
            zipCodeError.setText("");
            return true;
        } else {
            zipCodeError.setText("Zip code must be 5 digits");
            return false;
        }
    }

    /**
     * Checks if all fields are valid and enables/disables the add button accordingly.
     */
    private void checkAllValid() {
        boolean allValid = validateFirstName() && validateLastName() && validateEmail()
                && validateDOB() && validateZipCode();
        addButton.setDisable(!allValid);
    }

    /**
     * Handles the add button click event.
     * Navigates to the completed registration screen if all validations pass.
     *
     * @throws Exception if the FXML file cannot be loaded
     */
    @FXML
    private void handleAddButton() throws Exception {
        if (validateFirstName() && validateLastName() && validateEmail()
                && validateDOB() && validateZipCode()) {

            Stage stage = (Stage) addButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("completed-registration.fxml"));
            stage.setScene(new Scene(root, 400, 450));
        }
    }
}