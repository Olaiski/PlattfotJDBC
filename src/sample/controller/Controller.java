package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import sample.datasource.Datasource;
import sample.model.Medlem;
import sample.model.SortOrder;

import java.io.IOException;
import java.util.Optional;


public class Controller {

    @FXML
    private TableView medlemTable;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Text epostTxt;
    @FXML
    private Text navnTxt;
    @FXML
    private Text adrTxt;
    @FXML
    private Text tlfTxt;
    @FXML
    private Text medlemTxt;
    @FXML
    private Text statusTxt;





    @FXML
    public void listMedlemmer() {
        Task<ObservableList<Medlem>> task = new GetAlleMedlemmer();
        medlemTable.itemsProperty().bind(task.valueProperty());

        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);

        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));


        new Thread(task).start();
    }

    @FXML
    public void hentMedlemInfo() {
        final Medlem medlem = (Medlem) medlemTable.getSelectionModel().getSelectedItem();
        if (medlem == null) {
            System.out.println("Ingen medlem valgt..");
            return;
        }

        medlemTxt.setVisible(true);
        epostTxt.setText(medlem.getEpost());
        navnTxt.setText(medlem.getName());
        adrTxt.setText(medlem.getAdresse());
        tlfTxt.setText(medlem.getTelefon());
    }

    @FXML
    public void sokMedlem() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("queryNavn.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


        Optional<ButtonType> result = dialog.showAndWait();
        QueryNavnController controller = fxmlLoader.getController();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Task<ObservableList<Medlem>> task = new Task<>() {
                @Override
                protected ObservableList<Medlem> call() throws Exception {
                    return FXCollections.observableArrayList(
                            Datasource.getInstance().queryMedlemNavn(controller.getSokNavn()));
                }
            };
            medlemTable.itemsProperty().bind(task.valueProperty());

            new Thread(task).start();
        } else  {
            System.out.println("Cancel..");
        }


    }

    @FXML
    public void slettMedlem() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("slettDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


        final Medlem medlem = (Medlem) medlemTable.getSelectionModel().getSelectedItem();
        if (medlem == null) {
            System.out.println("Ingen medlem valgt..");
            return;
        }

        SlettController controller = fxmlLoader.getController();
        controller.setNavn(medlem.getName());

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return Datasource.getInstance().deleteMedlem(medlem.getEpost());
                }
            };

            task.setOnSucceeded(e -> {
                if (task.valueProperty().get()) {
                    statusTxt.setVisible(true);
                    statusTxt.setOnMouseClicked(ex -> {
                        statusTxt.setVisible(false);
                    });
                    statusTxt.setText("Medlem slettet! [X]");
                    medlemTable.refresh();
                    listMedlemmer();
                }
            });


            new Thread(task).start();

        } else  {
            System.out.println("Cancel..");
        }
    }

    @FXML
    public void regKontigentDialog() {

    }

    @FXML
    public void visRegistrerDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        // Når den vises kan man bare samhandle med dialogboksen
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("registrerDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            System.out.println("Kan ikke laste dialog (registrer)");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            controller.processResults();
            listMedlemmer();
        } else {
            System.out.println("Cancel..");
        }
    }

    @FXML
    public void visEndreDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("updateDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Kan ikke laste dialog (update)");
            e.printStackTrace();
        }


        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        final Medlem medlem = (Medlem) medlemTable.getSelectionModel().getSelectedItem();
        if (medlem == null) {
            System.out.println("Ingen medlem valgt..");
            return;
        }


        UpdateController controller = fxmlLoader.getController();
        controller.setPrompts(medlem.getEpost(), medlem.getName(), medlem.getAdresse(), medlem.getTelefon());

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return Datasource.getInstance().updateMedlem(
                            medlem.getEpost(),
                            controller.getEpost(), controller.getNavn(), controller.getAdresse(), controller.getTelefon());
                }
            };

            task.setOnSucceeded(e -> {
                if (task.valueProperty().get()) {
                    statusTxt.setVisible(true);
                    statusTxt.setOnMouseClicked(ex -> {
                        statusTxt.setVisible(false);
                    });
                    statusTxt.setText("Medlem oppdatert! [X]");
                    listMedlemmer();
                }

            });

            new Thread(task).start();

        } else {
            System.out.println("Cancel..");
        }



    }


    @FXML
    public void avslutt() {
        System.exit(-1);
    }


}

class GetAlleMedlemmer extends Task {

    @Override
    public ObservableList<Medlem> call() {
        return FXCollections.observableArrayList
                (Datasource.getInstance().queryMedlem(SortOrder.ASC));
    }
}


