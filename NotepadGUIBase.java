package notepad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NotepadGUIBase extends BorderPane {

    protected final MenuBar menuBar;
    protected final Menu menu_file;
    protected final MenuItem menu_new;
    protected final MenuItem menu_open;
    protected final MenuItem menu_save;
    protected final MenuItem menu_exit;
    protected final Menu menu_edit;
    protected final MenuItem menu_undo;
    protected final MenuItem menu_cut;
    protected final MenuItem menu_copy;
    protected final MenuItem menu_paste;
    protected final MenuItem menu_delete;
    protected final MenuItem menu_select;
    protected final Menu menu_help;
    protected final MenuItem menu_about;
    protected final TextArea my_text;

    public NotepadGUIBase(Stage prim) {

        menuBar = new MenuBar();
        menu_file = new Menu();
        menu_new = new MenuItem();
        menu_open = new MenuItem();
        menu_save = new MenuItem();
        menu_exit = new MenuItem();
        menu_edit = new Menu();
        menu_undo = new MenuItem();
        menu_cut = new MenuItem();
        menu_copy = new MenuItem();
        menu_paste = new MenuItem();
        menu_delete = new MenuItem();
        menu_select = new MenuItem();
        menu_help = new Menu();
        menu_about = new MenuItem();
        my_text = new TextArea();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        menu_file.setMnemonicParsing(false);
        menu_file.setText("File");

        menu_new.setMnemonicParsing(false);
        menu_new.setText("New");
        menu_new.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        menu_new.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                my_text.clear();
            }
        });
        menu_open.setMnemonicParsing(false);
        menu_open.setText("Open");
        menu_open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        menu_open.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                FileChooser fc=new FileChooser();
                File f= fc.showOpenDialog(prim);
                if(f!=null){
                    try{
                        FileInputStream fis=new FileInputStream(f);
                        long sz= f.length();
                        byte [] data= new byte[(int)sz];
                        fis.read(data);
                        my_text.setText(new String(data));
                    }
                    catch(IOException ex){
                        System.out.println(ex);
                    }
                }
            }
        });
        menu_save.setMnemonicParsing(false);
        menu_save.setText("Save");
        menu_save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        menu_save.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                FileChooser fc=new FileChooser();
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
                fc.setTitle("Save file");
                File f= fc.showSaveDialog(prim);
                try{
                    FileWriter fw=new FileWriter(f);
                    fw.write(my_text.getText());
                    fw.close();
                }
                catch(IOException ex){
                    System.out.println(ex);
                }
            }
        });
        menu_exit.setMnemonicParsing(false);
        menu_exit.setText("Exit");
        menu_exit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              prim.close();
            }
        });
        menu_edit.setMnemonicParsing(false);
        menu_edit.setText("Edit");
        
        menu_undo.setMnemonicParsing(false);
        menu_undo.setText("Undo");
        menu_undo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        menu_undo.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            my_text.undo();
            }
        });

        menu_cut.setMnemonicParsing(false);
        menu_cut.setText("Cut");
        menu_cut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        menu_cut.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              //String str=my_text.getSelectedText();
             // my_text.deleteText(my_text.getSelection());
              my_text.cut();
            }
        });
        menu_copy.setMnemonicParsing(false);
        menu_copy.setText("Copy");
        menu_copy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        menu_copy.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              my_text.copy();
            }
        });
    
        menu_paste.setMnemonicParsing(false);
        menu_paste.setText("Paste");
        menu_paste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
        menu_paste.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              my_text.paste();
            }
        });
        menu_delete.setMnemonicParsing(false);
        menu_delete.setText("Delete");
        menu_delete.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              my_text.deleteText(my_text.getSelection());
            }
        });


        menu_select.setMnemonicParsing(false);
        menu_select.setText("Select All");
        menu_select.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        menu_select.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              my_text.selectAll();
            }
        });

        menu_help.setMnemonicParsing(false);
        menu_help.setText("Help");

        menu_about.setMnemonicParsing(false);
        menu_about.setText("About");
        menu_about.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        menu_about.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("About");
                alert.setHeaderText(null);
                alert.setContentText("Implemented by May Esmail");
                alert.showAndWait();
            }
        });

        setTop(menuBar);

        BorderPane.setAlignment(my_text, javafx.geometry.Pos.CENTER);
        my_text.setPrefHeight(200.0);
        my_text.setPrefWidth(200.0);
        setCenter(my_text);

        menu_file.getItems().add(menu_new);
        menu_file.getItems().add(menu_open);
        menu_file.getItems().add(menu_save);
        menu_file.getItems().add(menu_exit);
        menuBar.getMenus().add(menu_file);
        menu_edit.getItems().add(menu_undo);
        menu_edit.getItems().add(menu_cut);
        menu_edit.getItems().add(menu_copy);
        menu_edit.getItems().add(menu_paste);
        menu_edit.getItems().add(menu_delete);
        menu_edit.getItems().add(menu_select);
        menuBar.getMenus().add(menu_edit);
        menu_help.getItems().add(menu_about);
        menuBar.getMenus().add(menu_help);

    }
}
