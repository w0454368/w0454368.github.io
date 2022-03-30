package portMan;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    private static String fileToDelete;

    private String fileNameToAdd;

    // Absolute path string for project directory, change here as needed
    private String filePath = "C:\\Git\\myRepos\\w0454368\\APPD1001\\Module_09\\src\\portEntries";

    @FXML
    public TextArea welcomeText;

    @FXML
    private ListView fileList;

    @FXML
    private TextField fileField;

    @FXML
    private TextField listNameField;

    @FXML
    private ChoiceBox categoryChoice;

    @FXML
    private Button promptDel;

    @FXML
    private Button promptCancel;

    @FXML
    private Button promptOK;

    public void changeScene(Stage stage,String sceneName) throws Exception {
        try {

            FXMLLoader newScene = new FXMLLoader(getClass().getResource(sceneName + ".fxml"));
            Parent root = newScene.load();
            Controller controller = newScene.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            if (sceneName.equals("startScene")) {
                setWelcomeText(controller.welcomeText);
            }

            if (sceneName.equals("listAll") || (sceneName.equals("deleteEntry"))) {
                searchAll(controller.fileList);
            }
            if (sceneName.equals("listCat") || (sceneName.equals("moveEntry"))) {
                populateChoices(controller.categoryChoice);
                controller.fileList.getItems().add("No results found.");
            }
            if (sceneName.equals("listName")) {
                controller.fileList.getItems().add("No results found");
            }
            if (sceneName.equals("addEntry")) {
                populateChoices(controller.categoryChoice);
            }

            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setWelcomeText(TextArea textArea) {
        textArea.setText("Welcome to Portfolio Manager!\n" +
                "Here you can view, add, move, or delete PDF-formatted portfolio entries.\n" +
                "To find an entry to view, click 'View Entries'.\n" +
                "To add a new entry or move/delete an existing one, click 'Manage Entries'.\n" +
                "If you're all done and ready to leave, click 'Exit' to close the program.");
    }

    public void deleteFile() throws Exception {
        String fileName = fileList.getSelectionModel().getSelectedItem().toString();
        try (Stream<Path> files = Files.walk(Paths.get(filePath))) {
            List<File> result = files.filter(Files::isRegularFile).map(x -> x.toFile()).collect(Collectors.toList());

            if (fileName != null) {
                for (File file : result) {
                    if (file.getAbsolutePath().contains(fileName)) {
                            File targetFile = file;
                            Controller.fileToDelete = targetFile.getAbsolutePath();
                            if (fileToDelete != null) {
                                openPrompt();
                            } else {
                                System.out.println("file is null");
                            }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openPrompt() throws Exception {
        try {
            Stage newStage = new Stage();
            Parent confirmBox = FXMLLoader.load(getClass().getResource("confirmDelete.fxml"));
            newStage.setTitle("Confirm Delete");
            newStage.setScene(new Scene(confirmBox));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelDel() {
        Stage stage = (Stage) promptCancel.getScene().getWindow();
        stage.close();
    }

    public void closeAddPrompt() {
        Stage stage = (Stage) promptOK.getScene().getWindow();
        stage.close();
        try {toManageEntries();}
        catch (Exception e) {e.printStackTrace();}
    }

    public void closeMovePrompt() {
        Stage stage = (Stage) promptOK.getScene().getWindow();
        stage.close();
        try {toMoveEntry();}
        catch (Exception e) {e.printStackTrace();}
    }

    public void closeDelPrompt() {
        Stage stage = (Stage) promptOK.getScene().getWindow();
        stage.close();
        try {toDeleteEntry();}
        catch (Exception e) {e.printStackTrace();}
    }

    public void confirmDel() {
        Stage stage = (Stage) promptDel.getScene().getWindow();
        File delFile = new File(Controller.fileToDelete);
        if (delFile.delete()) {
            try {
                Parent success = FXMLLoader.load(getClass().getResource("delSuccess.fxml"));
                stage.setScene(new Scene(success));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addEntry() {
        Stage stage = (Stage) fileField.getScene().getWindow();
        String targetPath = fileField.getText();
        String targetDir = categoryChoice.getSelectionModel().getSelectedItem().toString();
        File dest = new File(filePath + "\\"
                + targetDir + "\\" + fileNameToAdd);
        File input = new File(targetPath);
        try {
            Files.copy(input.toPath(), dest.toPath());
            Parent success = FXMLLoader.load(getClass().getResource("addSuccess.fxml"));
            stage.setScene(new Scene(success));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseFile() {
        JFileChooser pickFile = new JFileChooser();
        int returnVal = pickFile.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File targetFile = pickFile.getSelectedFile();
            fileNameToAdd = targetFile.getName();
            String filePath = targetFile.getAbsolutePath();
            fileField.setText(filePath);
        }
    }

    public void moveEntry() {
        String fileName = fileList.getSelectionModel().getSelectedItem().toString();
        String targetDir = categoryChoice.getSelectionModel().getSelectedItem().toString();
        File dest = new File(filePath + "\\"
                + targetDir + "\\" + fileName);
        String targetPath = null;
        try (Stream<Path> files = Files.walk(Paths.get(filePath))) {
            List<File> result = files.filter(Files::isRegularFile).map(x -> x.toFile()).collect(Collectors.toList());

            if (fileName != null) {
                for (File file : result) {
                    if (file.getName().contains(fileName)) {
                        targetPath = file.getAbsolutePath();
                    }
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        File input = new File(targetPath);
        try {
            Files.move(input.toPath(), dest.toPath());
            Stage stage = new Stage();
            Parent success = FXMLLoader.load(getClass().getResource("moveSuccess.fxml"));
            stage.setScene(new Scene(success));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchAll(ListView list) {
        try (Stream<Path> files = Files.walk(Paths.get(filePath))) {
            List<String> result = files.filter(Files::isRegularFile)
                    .map(x -> x.getFileName().toString()).collect(Collectors.toList());
            java.util.Collections.sort(result);

            for (String name : result) {
                list.getItems().add(name);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void searchCatWrap() {
        searchCat(fileList);
    }

    public void searchNameWrap() {
        searchName(fileList);
    }

    public void searchCat(ListView list) {
        list.getItems().clear();
        String category = categoryChoice.getSelectionModel().getSelectedItem().toString();
        try (Stream<Path> files = Files.walk(Paths.get(filePath + "\\" + category))) {
            List<String> result = files.filter(Files::isRegularFile)
                    .map(x -> x.getFileName().toString()).collect(Collectors.toList());
            java.util.Collections.sort(result);

            if (result != null) {
                for (String name : result) {
                    list.getItems().add(name);
                }
            } else {
                list.getItems().add("No results found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchName(ListView list) {
        list.getItems().clear();
        String fileName = listNameField.getText();
        try (Stream<Path> files = Files.walk(Paths.get(filePath))) {
            List<File> result = files.filter(Files::isRegularFile).map(x -> x.toFile()).collect(Collectors.toList());

            if (fileName != null) {
                for (File file : result) {
                    if (file.getName().contains(fileName)) {
                        String name = file.getName();
                        list.getItems().add(name);
                    }
                }
            }
            if (list.getItems().size() == 0) {
                list.getItems().add("No results found.");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void viewEntry() {
        String fileName = fileList.getSelectionModel().getSelectedItem().toString();
        try (Stream<Path> files = Files.walk(Paths.get(filePath))) {
            List<File> result = files.filter(Files::isRegularFile).map(x -> x.toFile()).collect(Collectors.toList());

            if (fileName != null) {
                for (File file : result) {
                    if (file.getAbsolutePath().contains(fileName)) {
                        try {
                            File targetFile = file;
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(targetFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                System.out.println("File not selected");
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public void populateChoices(ChoiceBox choice) {
        choice.getItems().add("PROG1400");
        choice.getItems().add("NETW1700");
        choice.getItems().add("OSYS1000");
        choice.getItems().add("APPD1001");
        choice.getItems().add("SAAD1001");
    }

    public void toStartScene() throws Exception {
        changeScene(Main.mainWindow,"startScene");
    }

    public void toViewEntries() throws Exception {
        changeScene(Main.mainWindow,"viewEntries");
    }

    public void toManageEntries() throws Exception {
        changeScene(Main.mainWindow,"manageEntries");
    }

    public void toListAll() throws Exception {
        changeScene(Main.mainWindow,"listAll");
    }

    public void toListCat() throws Exception {
        changeScene(Main.mainWindow,"listCat");
    }

    public void toListName() throws Exception {
        changeScene(Main.mainWindow,"listName");
    }

    public void toAddEntry() throws Exception {
        changeScene(Main.mainWindow,"addEntry");
    }

    public void toMoveEntry() throws Exception {
        changeScene(Main.mainWindow,"moveEntry");
    }

    public void toDeleteEntry() throws Exception {
        changeScene(Main.mainWindow,"deleteEntry");
    }

    public void exitProgram() {
        System.exit(0);
    }

}
