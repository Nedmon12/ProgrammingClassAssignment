/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.sql.DataSource;

/**
 *
 * @author Hp
 */
public class Main extends Application {

    public Stage primaryStage = new Stage();
    public Datasource dataSource ;
    private Connection conn;
    public static final String CONNECTION_STRING = "jdbc:sqlite:E:\\Databases\\Library.db";

    @Override
    public void start(Stage primaryStage) {
       // dataSource.open();
        homepage();



    }


    public void homepage(){
        // Stage primaryStage=new Stage();
        GridPane pane=new GridPane();
        pane.setPadding(new Insets(11,12,13,14));
        pane.setVgap(15);
        pane.setHgap(5);



        Button btnreg = new Button("Sign-Up");
        btnreg.setPrefWidth(120);
        btnreg.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){
                //primaryStage.close();
                regScene();
            }
        });

        Label unlbl=new Label("User-Name: ");
        Label pwlbl=new Label("Password: ");
        Label lblq=new Label("You Are What You Read");
        TextField untxtfld=new TextField();
        untxtfld.setPromptText("Enter User Name Here");
        TextField pwtxtfld=new TextField();
        pwtxtfld.setPromptText("Enter Password Here");
        Button btnlog=new Button("Login");
        String username = untxtfld.getText();
        String password = pwtxtfld.getText();
        btnlog.setPrefWidth(120);
        btnlog.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event) {
                   // boolean flag = dataSource.open();
                    String userNamecheck = dataSource.queryUserPassword(username);
                    if (userNamecheck != null) {
                        if (userNamecheck == password) librarianscene();
                        else {
                            //TODO ALRET PASSWORD DOESN'T MATCH;
                            System.out.println("Password mismatch");
                        }
                    } else {
                        //TODO ALERT USERNAME NOT FOUND..
                        System.out.println("Username mismatch");
                    }
                }
        });
        Button btnfp=new Button("Forgot Password");
        btnfp.setPrefWidth(120);

        HBox hb=new HBox(10);
        HBox hb1=new HBox();
        HBox hb2=new HBox(5);
        hb.setPadding(new Insets(11,12,13,14));
        hb2.setPadding(new Insets(11,12,13,14));
        hb2.setAlignment(Pos.CENTER_RIGHT);
        hb.getChildren().addAll(btnreg);
        hb2.getChildren().addAll(btnlog,btnfp);
        hb.setAlignment(Pos.TOP_RIGHT);
        hb1.setPadding(new Insets(11,12,13,14));
        hb1.getChildren().addAll(lblq);
        hb1.setAlignment(Pos.BOTTOM_CENTER);
        VBox vb=new VBox(200);

        vb.setPadding(new Insets(11,12,13,14));
        btnreg.getStyleClass().add("btnreg");
        btnlog.getStyleClass().add("btnlog");
        btnfp.getStyleClass().add("btnfp");
        unlbl.getStyleClass().add("unlbl");
        pwlbl.getStyleClass().add("pwlbl");
        lblq.getStyleClass().add("lblq");

        pane.add(unlbl,0,0);
        pane.add(untxtfld,1,0);
        pane.add(pwlbl,0,1);
        pane.add(pwtxtfld,1,1);
        pane.add(hb2,1,2);
        GridPane.setHalignment(unlbl, HPos.CENTER);
        GridPane.setHalignment(untxtfld, HPos.CENTER);
        GridPane.setHalignment(pwlbl, HPos.CENTER);
        GridPane.setHalignment(pwtxtfld, HPos.CENTER);
        GridPane.setHalignment(btnlog, HPos.CENTER);
        GridPane.setHalignment(btnfp, HPos.CENTER);

        pane.setAlignment(Pos.CENTER);
        pane.getStyleClass().add("pane1");
        vb.getStyleClass().add("vbox");

        vb.getChildren().addAll(hb,pane,hb1);
        Scene scene = new Scene(vb,1365,700);
        scene.getStylesheets().add("/hellofx/example.css");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Library Management System");
        primaryStage.show();




    }

    //========================Registration window scene=====================================

    private void regScene(){
        Stage stage=new Stage();
        HBox homebox=new HBox(10);
        homebox.setPadding(new Insets(11,12,13,14));
        Button btnlogout=new Button("Home");
        homebox.setAlignment(Pos.TOP_RIGHT);
        homebox.getChildren().addAll(btnlogout);

        btnlogout.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                homepage();
                stage.close();
            }
        });
        GridPane pane=new GridPane();
        DatePicker dp=new DatePicker();

        DatePicker dp1=new DatePicker();
        HBox hb1=new HBox(5);
        HBox hb2=new HBox(5);
        HBox hb3=new HBox(5);
        VBox vb1=new VBox(5);

        Label regnolbl=new Label("Registration No: ");
        TextField regtxtfld=new TextField();
        Label fulnmlbl=new Label("Full Name: ");
        TextField fulnmtxtfld=new TextField();
        Label unmlbl=new Label("User Name: ");
        TextField unmtxtfld=new TextField();
        Label fathnmlbl=new Label("Father's Name: ");
        TextField fathnmtxtfld=new TextField();
        Label mothnmlbl=new Label("Mother's Name: ");
        TextField mothnmtxtfld=new TextField();
        Label doblbl=new Label("Date Of Birth: ");

        Label emaillbl=new Label("Email: ");
        TextField emailtxtfld=new TextField();
        Label pnolbl=new Label("Phone Number: ");
        TextField pnotxtfld=new TextField();
        Label genlbl=new Label("Gender: ");
        ToggleGroup grp=new ToggleGroup();
        RadioButton genbtn=new RadioButton("Male");
        RadioButton genbtn1=new RadioButton("Female");

        genbtn.setToggleGroup(grp);
        genbtn1.setToggleGroup(grp);

        genbtn.setId("gen");
        genbtn1.setId("gen");

        Label regdlbl=new Label("Registration Date: ");

        Button savebtn=new Button("Save ");
        Button cancelbtn=new Button("Cancel");
        cancelbtn.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){
                homepage();
                stage.close();
            }
        });

        pane.setPadding(new Insets(130,12,13,14));
        pane.setAlignment(Pos.BASELINE_CENTER);
        pane.setHgap(10);
        pane.setVgap(5);
        pane.addRow(0,regnolbl);
        pane.addRow(0, regtxtfld);
        pane.addRow(1, fulnmlbl);
        pane.addRow(1, fulnmtxtfld);
        pane.addRow(2,unmlbl);
        pane.addRow(2,unmtxtfld);
        pane.addRow(3, mothnmlbl);
        pane.addRow(3, mothnmtxtfld);
        pane.addRow(4, doblbl);
        pane.addRow(4,dp);
        pane.addRow(5, emaillbl);
        pane.addRow(5, emailtxtfld);
        pane.addRow(6, pnolbl);
        pane.addRow(6, pnotxtfld);
        regnolbl.setId("reglbl");
        fulnmlbl.setId("reglbl");
        fathnmlbl.setId("reglbl");
        mothnmlbl.setId("reglbl");
        unmlbl.setId("reglbl");
        doblbl.setId("reglbl");
        emaillbl.setId("reglbl");
        pnolbl.setId("reglbl");
        genlbl.setId("reglbl");
        regdlbl.setId("reglbl");
        regtxtfld.setId("regtxtfld");
        fulnmtxtfld.setId("regtxtfld");
        fathnmtxtfld.setId("regtxtfld");
        mothnmtxtfld.setId("regtxtfld");
        pnotxtfld.setId("regtxtfld");
        emailtxtfld.setId("regtxtfld");
        unmtxtfld.setId("regtxtfld");
        regtxtfld.setId("regtxtfld");
        regtxtfld.setId("regtxtfld");
        regtxtfld.setId("regtxtfld");
        btnlogout.setId("btns");
        savebtn.setId("btns");
        savebtn.setPrefWidth(120);
        cancelbtn.setPrefWidth(120);
        cancelbtn.setId("btns");


        hb1.setPadding(new Insets(11,12,13,14));
        hb1.setAlignment(Pos.BASELINE_CENTER);
        hb2.setPadding(new Insets(11,12,13,14));
        hb2.setAlignment(Pos.BASELINE_CENTER);
        vb1.setPadding(new Insets(11,12,13,14));
        hb1.getChildren().addAll(genlbl,genbtn,genbtn1);
        hb2.getChildren().addAll(regdlbl,dp1);
        hb3.getChildren().addAll(savebtn,cancelbtn);
        hb3.setAlignment(Pos.BASELINE_CENTER);
        vb1.getChildren().addAll(homebox,pane,hb1,hb2,hb3);
        vb1.getStyleClass().add("vb1");

        Scene scene1=new Scene(vb1,1365,700);
        scene1.getStylesheets().add("/sample/example.css");
        stage.setTitle("Registration");
        stage.setScene(scene1);

        stage.show();
    }

//=========================================Librarian login scene==============================================



    private void librarianscene() {
        // Stage stage=new Stage();
        HBox homebox=new HBox(10);
        homebox.setPadding(new Insets(11,12,13,14));
        VBox container=new VBox(10);
        Button btnlogout=new Button("Log Out");
        homebox.setAlignment(Pos.TOP_RIGHT);
        homebox.getChildren().addAll(btnlogout);

        btnlogout.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){
                homepage();
                //stage.close();
            }
        });
        Button btnadd=new Button("Add Book Details");
        btnadd.setPrefWidth(250);
        btnadd.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                addbook();
                //stage.close();
            }
        });
        btnadd.setPrefHeight(50);
        Button btnmodndel=new Button("Modify & Delete Book");
        btnmodndel.setPrefWidth(250);
        btnmodndel.setPrefHeight(50);

        Button btnisu=new Button("Issue Book");
        btnisu.setPrefWidth(250);
        btnisu.setPrefHeight(50);
        btnisu.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                issuebook();
                //stage.close();
            }
        });
        Button btnret=new Button("Return Book");
        btnret.setPrefWidth(250);
        btnret.setPrefHeight(50);
        btnret.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                returnbook();
                //stage.close();
            }
        });
        Button btnview=new Button("View Book List");
        btnview.setPrefWidth(250);
        btnview.setPrefHeight(50);
        btnview.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listBooks();
            }
        });
        Button btnviewisubooks=new Button("View Issued Books");
        btnviewisubooks.setPrefWidth(250);
        btnviewisubooks.setPrefHeight(50);
        Button btnviewrequbooks=new Button("View Requested Books");
        btnviewrequbooks.setPrefWidth(250);
        btnviewrequbooks.setPrefHeight(50);
        btnadd.setId("bookbtn");
        btnmodndel.setId("bookbtn");

        btnisu.setId("bookbtn");
        btnret.setId("bookbtn");
        btnview.setId("bookbtn");
        btnlogout.setId("btns");
        btnviewisubooks.setId("bookbtn");
        btnviewrequbooks.setId("bookbtn");
        GridPane pane=new GridPane();
        pane.setPadding(new Insets(190,12,13,14));
        pane.addRow(0,btnadd);
        pane.addRow(1,btnmodndel);
        pane.addRow(2,btnview);
        pane.addRow(3,btnviewisubooks);
        pane.addRow(4, btnviewrequbooks);
        pane.addRow(5,btnisu);
        pane.addRow(6,btnret);
        container.getStyleClass().add("librscene");
        pane.setAlignment(Pos.BASELINE_CENTER);
        container.getChildren().addAll(homebox,pane);
        Scene scene2=new Scene(container,1365,700);
        scene2.getStylesheets().add("/sample/example.css");

        primaryStage.setScene(scene2);
        primaryStage.setTitle("Librarian");
        //stage.show();


    }

    private void addbook(){

        //Stage addstage=new Stage();
        GridPane gp=new  GridPane();
        Label tx=new Label("Please enter the information about the book");
        tx.getStyleClass().add("lblq");
        HBox homebox=new HBox(500);
        homebox.setPadding(new Insets(11,12,13,14));
        Button btnlogout=new Button("Back");
        Button bt=new Button("Add book");
        bt.setPrefWidth(120);
        homebox.setAlignment(Pos.TOP_RIGHT);
        homebox.getChildren().addAll(tx,btnlogout);

        btnlogout.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){
                librarianscene();
                //addstage.close();
            }
        });
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(5);
        gp.setHgap(5);
        Label lb1=new Label("Book ID:");
        Label lb2=new Label("Book title:");
        Label lb3=new Label(" Published Year:");
        Label lb4=new Label(" Author:");
        Label lb5=new Label(" Price:");
        Label lb6=new Label("ISBN no:");
        Label lb7=new Label("Purchase Date:");
        Label lb8=new Label(" Book Category:");
        Label lb9=new Label("Key Word:");
        Label lb10=new Label("Book Edition:");
        Label tarlbl=new Label(" Description:");
        TextArea tar=new TextArea();
        tar.setPrefWidth(400);
        tar.setPrefHeight(150);
        lb1.setId("reglbl");
        lb2.setId("reglbl");
        lb3.setId("reglbl");
        lb4.setId("reglbl");
        lb5.setId("reglbl");
        lb6.setId("reglbl");
        lb7.setId("reglbl");
        lb8.setId("reglbl");
        lb9.setId("reglbl");
        lb10.setId("reglbl");
        tarlbl.setId("reglbl");
        btnlogout.setId("btns");
        bt.setId("btns");
        TextField t1=new TextField();
        TextField t2=new TextField();
        TextField t3=new TextField();
        TextField t4=new TextField();
        TextField t5=new TextField();
        TextField t6=new TextField();
        DatePicker t7 = new DatePicker();
        TextField t8=new TextField();
        TextField t9=new TextField();
        TextField t10=new TextField();



        gp.setPadding(new Insets(110,11,12,13));
        gp.add(lb1,0,0);
        gp.add(t1,1,0);
        gp.add(lb2,2,0);
        gp.add(t2,3,0);
        gp.add(lb3,0,1);
        gp.add(t3,1,1);
        gp.add(lb10,2,1);
        gp.add(t10,3,1);
        gp.add(lb4,0,2);
        gp.add(t4,1,2);
        gp.add(lb5,2,2);
        gp.add(t5,3,2);
        gp.add(lb6,0,3);
        gp.add(t6,1,3);
        gp.add(lb7,2,3);
        gp.add(t7,3,3);
        gp.add(lb8,0,4);
        gp.add(t8,1,4);
        gp.add(lb9,2,4);
        gp.add(t9,3,4);
        HBox desbx=new HBox(10);
        desbx.setAlignment(Pos.CENTER);
        desbx.setPadding(new Insets(11,12,13,14));
        desbx.getChildren().addAll(tarlbl,tar);
        GridPane.setHalignment(bt,HPos.CENTER);


        VBox vb=new VBox(10);
        vb.setPadding(new Insets(5,5,5,5));
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(homebox,gp,desbx,bt);
        vb.getStyleClass().add("addscene");
        Scene scene = new Scene(vb,1365,700);
        scene.getStylesheets().add("/sample/example.css");
        primaryStage.setTitle("Add Books");
        primaryStage.setScene(scene);
        //primaryStage.show();
    }

    private void viewbooklibr(){
    }
    private void modifybook(){
    }
    private void issuebook(){
        Stage isustage=new Stage();
        HBox homebox=new HBox(10);
        VBox container=new VBox(50);

        homebox.setPadding(new Insets(11,12,13,14));
        Button btnlogout=new Button("Back");
        homebox.setAlignment(Pos.TOP_RIGHT);
        homebox.getChildren().addAll(btnlogout);
        btnlogout.setId("btns");
        btnlogout.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){
                librarianscene();
                isustage.close();
            }
        });

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);   //place the nodes in the center of the pane
        pane.setPadding(new Insets(190, 12.5, 13.5, 14.5));
        pane.setHgap(25);
        pane.setVgap(25);
        // Place nodes in the pane
        TextField memberField = new TextField();
        Label memberLabel = new Label("Registration No:");
        memberLabel.setId("reglbl");
        pane.add(memberLabel, 0, 0);
        pane.add(memberField, 1, 0);


        TextField bookCodeField = new TextField();
        Label bookCodeLabel = new Label("Book Id:");
        bookCodeLabel.setId("reglbl");
        pane.add(bookCodeLabel, 2, 0);
        pane.add(bookCodeField, 3, 0);

        DatePicker dateofissueField = new DatePicker();
        Label dateofissueLabel = new Label("Date of Expriy:");
        dateofissueLabel.setId("reglbl");
        pane.add(dateofissueLabel, 0, 1);
        pane.add(dateofissueField, 1, 1);

        DatePicker dateofexpriyField = new DatePicker();
        Label dateofexpriyLabel = new Label("Date of Issue:");
        dateofexpriyLabel.setId("reglbl");
        pane.add(dateofexpriyLabel, 2, 1);
        pane.add(dateofexpriyField, 3, 1);


        Button btisu = new Button("Issue");
        btisu.setId("btns");
        btisu.setPrefWidth(120);
        pane.add(btisu, 1, 3);
        pane.setAlignment(Pos.BASELINE_CENTER);
        //Set the horizontal alignment for the node
        GridPane.setHalignment(btisu, HPos.RIGHT);

        container.getChildren().addAll(homebox,pane);
        container.getStyleClass().add("isuscene");
        // Create a scene and place it in the stage
        Scene scene = new Scene(container,1365,700);

        scene.getStylesheets().add("/hellofx/example.css");
        isustage.setTitle("Issue Books");
        isustage.setScene(scene);
        isustage.show();
    }






    private void returnbook(){
        Stage retstage=new Stage();
        GridPane pane = new GridPane();
        Button btret = new Button("Return Book ");
        HBox homebox=new HBox(10);
        VBox container=new VBox(50);

        homebox.setPadding(new Insets(11,12,13,14));
        Button btnlogout=new Button("Back");
        homebox.setAlignment(Pos.TOP_RIGHT);
        homebox.getChildren().addAll(btnlogout);
        btnlogout.setId("btns");
        btnlogout.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){
                librarianscene();
                retstage.close();
            }
        });
        pane.setAlignment(Pos.CENTER);   //place the nodes in the center of the pane

        pane.setHgap(20);
        pane.setVgap(20);
        // Place nodes in the pane

        TextField bookIdField = new TextField();
        Label bookIdLabel = new Label("Book ID:");

        pane.addRow(0,bookIdLabel);
        pane.addRow(0,bookIdField);

        DatePicker issuedateField = new DatePicker();
        Label issuedateLabel = new Label("Issue Date :");

        pane.addRow(1,issuedateLabel);
        pane.addRow(1,issuedateField);

        DatePicker returnedonField = new DatePicker();
        Label returnedonLabel = new Label("Returned On :");

        pane.addRow(2,returnedonLabel);
        pane.addRow(2,returnedonField);

        TextField regnotxtfld = new TextField();
        Label regnolbl = new Label("Registration No:");

        pane.addRow(3,regnolbl);
        pane.addRow(3,regnotxtfld);


        DatePicker duedateField = new DatePicker();
        Label duedateLabel = new Label("Due Date:");

        pane.addRow(4,duedateLabel);
        pane.addRow(4,duedateField);

        TextField fineField = new TextField();
        fineField.setMinWidth(45);
        fineField.setMinHeight(45);
        Label finelbl = new Label("Fine:");

        pane.addRow(5,finelbl);
        pane.addRow(5,fineField);
        bookIdLabel.setId("reglbl");

        issuedateLabel.setId("reglbl");
        returnedonLabel.setId("reglbl");
        regnolbl.setId("reglbl");
        duedateLabel.setId("reglbl");
        finelbl.setId("reglbl");
        HBox btretbx=new HBox(10);
        btretbx.setAlignment(Pos.CENTER);
        btretbx.getChildren().addAll(btret);
        btret.setId("btns");
        btret.setPrefWidth(120);

        container.getStyleClass().add("isuscene");
        pane.setPadding(new Insets(150,12,13,14));

        container.getChildren().addAll(homebox,pane,btretbx);
        Scene scene = new Scene(container, 1365,700);
        scene.getStylesheets().add("/hellofx/example.css");
        retstage.setTitle("Return Books");
        retstage.setScene(scene);
        retstage.show();

    }

//==============================user ui===========================

    private void userscene(){
        Stage userstage=new Stage();
        GridPane pane=new GridPane();
        HBox homebox=new HBox(10);

        homebox.setPadding(new Insets(11,12,13,14));
        VBox container=new VBox(10);
        Button btnlogout=new Button("Log Out");
        btnlogout.setId("btns");
        btnlogout.setPrefWidth(120);
        btnlogout.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){
                homepage();
                userstage.close();
            }
        });
        homebox.setAlignment(Pos.TOP_RIGHT);
        homebox.getChildren().addAll(btnlogout);
        pane.setPadding(new Insets(190,12,13,14));
        Button reqbtn=new Button("Request Book");
        reqbtn.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){
                requestbook();
                userstage.close();
            }
        });
        Button viewbkbtn=new Button("View Book");
        reqbtn.setId("bookbtn");
        viewbkbtn.setId("bookbtn");
        reqbtn.setPrefWidth(250);
        reqbtn.setPrefHeight(50);
        viewbkbtn.setPrefWidth(250);
        viewbkbtn.setPrefHeight(50);
        pane.addRow(0, reqbtn);
        pane.addRow(1,viewbkbtn);
        pane.setAlignment(Pos.CENTER);
        container.getChildren().addAll(homebox,pane);
        container.getStyleClass().add("userscene");
        Scene scene=new Scene(container,1365,700);
        scene.getStylesheets().add("/hellofx/example.css");
        userstage.setScene(scene);
        userstage.setTitle("User");
        userstage.show();
    }

    private void requestbook(){
        Stage stage=new Stage();
        HBox homebox=new HBox(10);
        VBox container=new VBox(50);

        homebox.setPadding(new Insets(11,12,13,14));
        Button btnlogout=new Button("Back");
        homebox.setAlignment(Pos.TOP_RIGHT);
        homebox.getChildren().addAll(btnlogout);
        btnlogout.setId("btns");
        btnlogout.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){
                userscene();
                stage.close();
            }
        });
        GridPane pane=new GridPane();
        pane.setVgap(5);
        pane.setHgap(5);
        Button requbtn=new Button("Request Book");
        requbtn.setPrefWidth(120);
        Label bktitle=new Label("Book Title:");
        TextField bktitletxtfld=new TextField();
        Label bkisbn=new Label("Book Isbn:");
        TextField bkisbntxtfld=new TextField();
        Label bkauthor=new Label("Book Author:");
        TextField bkauthortxtfld=new TextField();
        Label bkgenre=new Label("Book Genre:");
        TextField bkgenretxtfld=new TextField();
        bktitle.setId("reglbl");
        bkisbn.setId("reglbl");
        bkauthor.setId("reglbl");
        bkgenre.setId("reglbl");
        requbtn.setId("btns");
        pane.addRow(0,bktitle);
        pane.addRow(0,bktitletxtfld);
        pane.addRow(1,bkisbn);
        pane.addRow(1,bkisbntxtfld);
        pane.addRow(2,bkauthor);
        pane.addRow(2,bkauthortxtfld);
        pane.addRow(3,bkgenre);
        pane.addRow(3,bkgenretxtfld);
        pane.add(requbtn,1,4);

        pane.setPadding(new Insets(100,12,13,14));
        pane.setAlignment(Pos.CENTER);
        container.getChildren().addAll(homebox,pane);
        container.getStyleClass().add("userscene");
        Scene requscene=new Scene(container,1365,700);
        requscene.getStylesheets().add("/hellofx/example.css");
        stage.setScene(requscene);
        stage.setTitle("Request Books");
        stage.show();
    }
    private void viewbookuser(){
    }





    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void listBooks () {
        TableView<Books> bookTable = new TableView<>();
        dataSource = new Datasource();
        boolean flag = dataSource.open();
        try {
        conn = DriverManager.getConnection(CONNECTION_STRING);
        //TableColumn<Books, String> bookNameColumn = new TableColumn<>("Book Title");
        //bookNameColumn.setCellValueFactory((new PropertyValueFactory<>("BookTitle")));
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Books ORDER BY BookTitle COLLATE NOCASE ASC");
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                TableColumn column = new TableColumn<>();
                switch (resultSet.getMetaData().getColumnName(i + 1)) {
                    case "BookId":
                        column.setText("Book Id");
                        break;
                    case "BookTitle":
                        column.setText("Title");
                        break;
                    case "Author Id":
                        column.setText("AuthorId");
                        break;

                    default:
                        column.setText(resultSet.getMetaData().getColumnName(i + 1)); //if column name in SQL Database is not found, then TableView column receive SQL Database current column name (not readable)
                        break;
                }
                column.setCellValueFactory(new PropertyValueFactory<>(resultSet.getMetaData().getColumnName(i + 1))); //Setting cell property value to correct variable from Book class.
                bookTable.getColumns().add(column);
            }
        }
        catch (SQLException e) {
            System.out.println("Connection to databse failed : "+e.getMessage());
        }
        //Task<ObservableList<Books>> task = new GetAllBooks();
        bookTable.setItems(FXCollections.observableArrayList(dataSource.queryBooks(1)));
        Scene tableScene = new Scene(bookTable,1365,700);
        tableScene.getStylesheets().add("example.css");
        primaryStage.setResizable(false);
        primaryStage.setScene(tableScene);
        primaryStage.setTitle("Library Management System");
        primaryStage.show();
    }

}
class GetAllBooks extends Task {
    public Datasource datasource = new Datasource();
    public boolean flag = datasource.open();
    @Override
    public ObservableList<Books> call () {
        return FXCollections.observableArrayList(datasource.queryBooks(2));
    }
}
