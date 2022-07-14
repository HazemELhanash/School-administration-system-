/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author RTL
 */
public class EXAM extends Application {
    private Connection conn=null;
    private ResultSet res=null;
    private PreparedStatement pst=null;
    private ObservableList <PRODUCT> data;
    private TableView<PRODUCT> r2;
    @Override
    public void start(Stage stage) throws SQLException {
        
       Text t=new Text("Add New Product");
       Label l1=new Label("ID");
       Label l2=new Label("Name");
       Label l3=new Label("Price ");
       Label l4=new Label("Quantity");
       Label l5=new Label("Department");
       Label l20=new Label("Quantity of all products");
       TextField t1=new TextField();
       TextField t2=new TextField();
       TextField t3=new TextField();
       TextField t4=new TextField();
       TextField t5=new TextField();
       Button b1=new Button("Add");
       Button b2=new Button("Clear");
       Button b20=new Button("Count");
       b1.setId("b1");
       b1.setOnAction(e -> {
          String id=t1.getText();
          String name=t2.getText();
          String price=t3.getText();
          String quantity=t4.getText();
          String dep=t5.getText();
          String sql="INSERT INTO PRODUCT(ID,name,price,quantity,department) VALUES (?,?,?,?,?)";
          conn=DB.DBConnection(); 
           try {
               pst=conn.prepareStatement(sql);
               pst.setString(1, id);
               pst.setString(2, name);
               pst.setString(3, price);
               pst.setString(4, quantity);
               pst.setString(5, dep);
               int i=pst.executeUpdate();
               if(i==1){
                   System.out.println("Data is inserted successfully");
                   pst.close();
                   conn.close();
                   show();
               }
           } catch (SQLException ex) {
               Logger.getLogger(EXAM.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       b2.getStyleClass().add("style");
       b2.setOnAction(e -> {
          t1.setText("");
          t2.setText("");
          t3.setText("");
          t4.setText("");
          t5.setText("");
       });
         GridPane g1=new GridPane();
       g1.add(t, 0, 0,2,1);
       g1.add(l1, 0, 1);
       g1.add(t1, 1, 1);
       g1.add(l2, 0, 2);
       g1.add(t2, 1, 2);
       g1.add(l3, 0, 3);
       g1.add(t3, 1, 3);
       g1.add(l4, 0, 4);
       g1.add(t4, 1, 4);
       g1.add(l5, 0, 5);
       g1.add(t5, 1, 5);
       g1.add(b1, 0, 6);
       g1.add(b2, 1, 6);
       g1.add(l20, 0, 9);
       g1.add(b20, 0, 12);
       g1.setVgap(5);
       g1.setHgap(5);
       g1.setPadding(new Insets(20,20,20,20));
       r2=new TableView<>();
       TableColumn <PRODUCT,Integer> c1=new TableColumn<>("ID");
       c1.setMinWidth(100);
       c1.setCellValueFactory(new PropertyValueFactory("ID"));
       TableColumn <PRODUCT,String> c2=new TableColumn<>("Name");
       c2.setMinWidth(100);
       c2.setCellValueFactory(new PropertyValueFactory("name"));
       TableColumn <PRODUCT,Double> c3=new TableColumn<>("Price");
       c3.setMinWidth(100);
       c3.setCellValueFactory(new PropertyValueFactory("price"));
       TableColumn <PRODUCT,Integer> c4=new TableColumn<>("Quantity");
       c4.setMinWidth(100);
       c4.setCellValueFactory(new PropertyValueFactory("quantity"));
       TableColumn <PRODUCT,Double> c5=new TableColumn<>("Department");
       c5.setMinWidth(100);
       c5.setCellValueFactory(new PropertyValueFactory("department"));
       r2.getColumns().addAll(c1,c2,c3,c4,c5);
       show();
       Label l6=new Label("Delete by ID");
       Label l7=new Label ("ID:  ");
       TextField t7 =new TextField();
       Button b3=new Button ("Delete");
       b3.setOnAction(e -> {
            String id=t7.getText();
            String sql="DELETE FROM PRODUCT WHERE ID=?";
            conn=DB.DBConnection();
           try {
               pst=conn.prepareStatement(sql);
               pst.setString(1, id);
               pst.executeUpdate();
               pst.close();
               conn.close();
               show();
           } catch (SQLException ex) {
               Logger.getLogger(EXAM.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       Label l8=new Label ("Update Quantity by ID");
       Label l9=new Label ("ID :  ");
       TextField t9 =new TextField();
       Label l10=new Label("New Quantity :  ");
       TextField t10 =new TextField();
       Button b4=new Button ("Update");
       b4.setOnAction(e -> {
           int id=Integer.parseInt(t9.getText());
           double gpa=Double.parseDouble(t10.getText());
           String sql="UPDATE PRODUCT SET quantity=? WHERE ID=?";
           conn=DB.DBConnection();
           try {
               pst=conn.prepareCall(sql);
               pst.setDouble(1, gpa);
               pst.setInt(2, id);
               pst.executeUpdate();
               pst.close();
               conn.close();
               show();
           } catch (SQLException ex) {
               Logger.getLogger(EXAM.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       GridPane g2=new GridPane();
       g2.add(l6, 0, 0, 2, 1);
       g2.add(l7, 0, 1);
       g2.add(t7, 1, 1);
       g2.add(b3, 0, 2,2,1);
       g2.add(l8, 0, 7,2,1);
       g2.add(l9, 0, 8);
       g2.add(t9, 1, 8);
       g2.add(l10, 0, 9);
       g2.add(t10, 1, 9);
       g2.add(b4, 0, 10,2,1);
       g2.setMinWidth(50);
       g2.setVgap(5);
       g2.setHgap(5);
       g2.setPadding(new Insets(20,20,20,20));
        FlowPane root = new FlowPane(g1,r2,g2);
        
        
        Scene scene = new Scene(root, 300, 250);
        scene.setFill(Color.BROWN);
        scene.getStylesheets().add(this.getClass().getResource("ex.css").toExternalForm());
        stage.setTitle("Exam");
        stage.setScene(scene);
        stage.show();
    }
     public void show() throws SQLException{
        data=FXCollections.observableArrayList();
        conn=DB.DBConnection();
        pst=conn.prepareStatement("SELECT * FROM PRODUCT");
        res=pst.executeQuery();
        while(res.next()){
            data.add(new PRODUCT(res.getInt(1),res.getString(2),res.getDouble(3),res.getInt(4),res.getString(5)));
        }
         pst.close();
         conn.close();
         r2.setItems(data);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
