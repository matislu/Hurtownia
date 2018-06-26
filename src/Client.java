import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.regex.Pattern;

public class Client extends Application {

    private Socket socket;
    private BufferedReader in;
    private PrintStream out;

    @Override
    public void start(Stage primaryStage) {

        // Panel wyboru portu i serwera
    	//***********************************************************************************
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        final Text sceneTitle = new Text("Panel Klienta");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        final Label serverPort = new Label("Podaj port: ");
        grid.add(serverPort, 0, 2);

        TextField portField = new TextField();
        grid.add(portField, 1, 2);

        final Label serverHost = new Label("Podaj adres serwera: ");
        grid.add(serverHost, 0, 3);

        TextField hostField = new TextField();
        grid.add(hostField, 1, 3);

        final Label errorMessage = new Label();
        grid.add(errorMessage, 1, 7);

        Button turnOnButton = new Button("Po³acz");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(turnOnButton);
        grid.add(hBox, 1, 5);
        
        //****************
        //*Wyglad ekranów*
        //****************
        
        //Przyciski do opuszczenia programu
        //**************************************************************************
        Button exitt = new Button("WyjdŸ");
        Button exitt2 = new Button("WyjdŸ");
        Button exitt3 = new Button("WyjdŸ");
        Button exitt4 = new Button("WyjdŸ");
        Button exitt5 = new Button("WyjdŸ");
        Button exitt6 = new Button("WyjdŸ");
        Button exitt7 = new Button("WyjdŸ");
        Button exitt8 = new Button("WyjdŸ");
        Button exitt9 = new Button("WyjdŸ");
        Button exitt10 = new Button("WyjdŸ");
        
        //Przycisko do powrotu
        //***************************************************************************
        Button back = new Button("Powrót");
        Button back2 = new Button("Powrót");
        Button back3 = new Button("Powrót");
        Button back4 = new Button("Powrót");
        Button back5 = new Button("Powrót");
        Button back6 = new Button("Powrót");
        Button back7 = new Button("Powrót");
        Button back8 = new Button("Powrót");
        Button back9 = new Button("Powrót");
        
        //Wybór trybu u¿ytkownika
        //*****************************************************************************
        VBox logowanie = new VBox(20);
        Label kto = new Label();
        RadioButton hurt = new RadioButton();
        RadioButton klient = new RadioButton();
        Button button2 = new Button("Wybierz");
        
        kto.setFont(Font.font(null, FontWeight.BOLD, 20));
        hurt.setFont(new Font(16));
        klient.setFont(new Font(16));
        
        ToggleGroup abc = new ToggleGroup();
        hurt.setToggleGroup(abc);
        klient.setToggleGroup(abc);
        
        button2.setDisable(true);
        hurt.setOnAction(e -> button2.setDisable(false));
        klient.setOnAction(e -> button2.setDisable(false));
        button2.setDisable(true);
        exitt3.setDisable(false);
        
        logowanie.getChildren().addAll(kto, hurt, klient, button2, exitt3);
        logowanie.setAlignment(Pos.CENTER_LEFT);
        
        //Panel hurtownii
        //***************************************************************************
        VBox hurtownia = new VBox(20);
        Label akcja = new Label();
        RadioButton cennik = new RadioButton();
        RadioButton magazyn = new RadioButton();
        RadioButton zamawianie = new RadioButton();
        Button button3 = new Button("Wybierz");
        
        akcja.setFont(Font.font(null, FontWeight.BOLD, 20));
        cennik.setFont(new Font(16));
        magazyn.setFont(new Font(16));
        zamawianie.setFont(new Font(16));
        
        ToggleGroup abcd = new ToggleGroup();
        cennik.setToggleGroup(abcd);
        magazyn.setToggleGroup(abcd);
        zamawianie.setToggleGroup(abcd);
        
        button3.setDisable(true);
        cennik.setOnAction(e -> button3.setDisable(false));
        magazyn.setOnAction(e -> button3.setDisable(false));
        zamawianie.setOnAction(e -> button3.setDisable(false));
        button3.setDisable(true);
        back.setDisable(false);
        exitt4.setDisable(false);
        
        
        hurtownia.getChildren().addAll(akcja, cennik, magazyn, zamawianie, button3, back, exitt4);
        hurtownia.setAlignment(Pos.CENTER_LEFT);
        
        //Cennik
        //******************************************************************
        VBox layout = new VBox(20);
        Label tytul = new Label();
        RadioButton[] produkt = new RadioButton[10];
        for (int i = 0; i < 10; i++) {
        	produkt[i] = new RadioButton();
        }
        Label wybor5 = new Label();
        TextField cena5 = new TextField();
        Button button10 = new Button("Aktualizuj");
        
        tytul.setFont(Font.font(null, FontWeight.BOLD, 20));
        for (int i = 0; i < 10; i++) {
        	produkt[i].setFont(new Font(14));
        }
        
        ToggleGroup zzz = new ToggleGroup();
        for (int i = 0; i < 10; i++) {
        	produkt[i].setToggleGroup(zzz);
        }
        button10.setDisable(true);
        for (int i = 0; i < 10; i++) {
        	produkt[i].setOnAction(e -> button10.setDisable(false));
        }
        button10.setDisable(true);
        back2.setDisable(false);
        exitt2.setDisable(false);
       
        
        layout.getChildren().addAll(tytul);
        for (int i = 0; i < 10; i++) {
        	layout.getChildren().addAll(produkt[i]);
        }
        layout.getChildren().addAll(wybor5, cena5, button10, back2, exitt2);
        
        layout.setAlignment(Pos.CENTER_LEFT);
        
        //Magazyn
        //******************************************************************************
        VBox magazyn2 = new VBox(20);
        Label lab = new Label();
        RadioButton stan = new RadioButton();
        RadioButton dodaj = new RadioButton();
        Button button4 = new Button("Wybierz");
        
        lab.setFont(Font.font(null, FontWeight.BOLD, 20));
        stan.setFont(new Font(16));
        dodaj.setFont(new Font(16));
        
        ToggleGroup abcde = new ToggleGroup();
        stan.setToggleGroup(abcde);
        dodaj.setToggleGroup(abcde);
        
        button4.setDisable(true);
        stan.setOnAction(e -> button4.setDisable(false));
        dodaj.setOnAction(e -> button4.setDisable(false));
        button4.setDisable(true);
        back3.setDisable(false);
        exitt5.setDisable(false);
        
        magazyn2.getChildren().addAll(lab, stan, dodaj, button4, back3, exitt5);
        magazyn2.setAlignment(Pos.CENTER_LEFT);
        
        //Stan magazynu
        //******************************************************************************************
        VBox stanMag = new VBox(20);
        Label tytul2 = new Label();
        Label[] produkt2 = new Label[10];
        for (int i = 0; i < 10; i++) {
        	produkt2[i] = new Label();
        }
        
        tytul2.setFont(Font.font(null, FontWeight.BOLD, 20));
        for (int i = 0; i < 10; i++) {
        	produkt2[i].setFont(Font.font(null, FontWeight.BOLD, 14));
        }
        
        back4.setDisable(false);
        exitt.setDisable(false);
        
        stanMag.getChildren().addAll(tytul2);
        for (int i = 0; i < 10; i++) {
        	stanMag.getChildren().addAll(produkt2[i]);
        }
        stanMag.getChildren().addAll(back4, exitt);
        
        stanMag.setAlignment(Pos.CENTER_LEFT);
        
        //Dodawanie produktu do magazynu
        //******************************************************************************************
        VBox dodPro = new VBox(20);
        Label tytul3 = new Label();
        RadioButton[] produkt3 = new RadioButton[10];
        for (int i = 0; i < 10; i++) {
        	produkt3[i] = new RadioButton();
        }
        Label ilosc = new Label();
        TextField ilosc2 = new TextField();
        Button button5 = new Button("Dodaj");
        
        tytul3.setFont(Font.font(null, FontWeight.BOLD, 20));
        for (int i = 0; i < 10; i++) {
        	produkt3[i].setFont(new Font(14));
        }
        ilosc.setFont(Font.font(null, FontWeight.BOLD, 14));
        
        ToggleGroup cda = new ToggleGroup();
        for (int i = 0; i < 10; i++) {
        	produkt3[i].setToggleGroup(cda);
        }
        
        button5.setDisable(true);
        for (int i = 0; i < 10; i++) {
        	produkt3[i].setOnAction(e -> button5.setDisable(false));
        }
        button5.setDisable(true);
        back5.setDisable(false);
        exitt6.setDisable(false);

        dodPro.getChildren().addAll(tytul3);
        for (int i = 0; i < 10; i++) {
        	dodPro.getChildren().addAll(produkt3[i]);
        }
        dodPro.getChildren().addAll(ilosc, ilosc2, button5, back5, exitt6);
        
        dodPro.setAlignment(Pos.CENTER_LEFT);
        
        //Zamawianie towarów przez hurtownie
        //***************************************************************
        
        VBox zamow = new VBox(20);
        Label tytul4 = new Label();
        RadioButton[] produkt4 = new RadioButton[10];
        for (int i = 0; i < 10; i++) {
        	produkt4[i] = new RadioButton();
        }
        Label ilosc3 = new Label();
        TextField ilosc4 = new TextField();
        Button button6 = new Button("Zamów");
        
        tytul4.setFont(Font.font(null, FontWeight.BOLD, 20));
        for (int i = 0; i < 10; i++) {
        	produkt4[i].setFont(new Font(14));
        }
        ilosc3.setFont(Font.font(null, FontWeight.BOLD, 14));
        
        ToggleGroup cdac = new ToggleGroup();
        for (int i = 0; i < 10; i++) {
        	produkt4[i].setToggleGroup(cdac);
        }
        
        button6.setDisable(true);
        for (int i = 0; i < 10; i++) {
        	produkt4[i].setOnAction(e -> button6.setDisable(false));
        }
        button6.setDisable(true);
        back6.setDisable(false);
        exitt7.setDisable(false);

        zamow.getChildren().addAll(tytul4);
        for (int i = 0; i < 10; i++) {
        	zamow.getChildren().addAll(produkt4[i]);
        }
        zamow.getChildren().addAll(ilosc3, ilosc4, button6, back6, exitt7);
        
        zamow.setAlignment(Pos.CENTER_LEFT);
        
        
        //Panel klienta
        //**************************************************************************
        VBox klient2 = new VBox(20);
        Label akcja2 = new Label();
        RadioButton cennik2 = new RadioButton();
        RadioButton zamawianie2 = new RadioButton();
        Button button7 = new Button("Wybierz");
        
        akcja2.setFont(Font.font(null, FontWeight.BOLD, 20));
        cennik2.setFont(new Font(16));
        zamawianie2.setFont(new Font(16));
        
        ToggleGroup q = new ToggleGroup();
        cennik2.setToggleGroup(q);
        zamawianie2.setToggleGroup(q);
        
        button7.setDisable(true);
        cennik2.setOnAction(e -> button7.setDisable(false));
        zamawianie2.setOnAction(e -> button7.setDisable(false));
        button7.setDisable(true);
        back7.setDisable(false);
        exitt8.setDisable(false);
        
        klient2.getChildren().addAll(akcja2, cennik2, zamawianie2, button7, back7, exitt8);
        klient2.setAlignment(Pos.CENTER_LEFT);
        
        //Cennik u klienta
        //****************************************************************************8
        
        VBox layout8 = new VBox(20);
        Label tytul8 = new Label();
        Label[] produkt8 = new Label[10];
        for (int i = 0; i < 10; i++) {
        	produkt8[i] = new Label();
        }
        
        
        tytul8.setFont(Font.font(null, FontWeight.BOLD, 20));
        for (int i = 0; i < 10; i++) {
        	produkt8[i].setFont(Font.font(null, FontWeight.BOLD, 14));
        }
        
        back8.setDisable(false);
        exitt9.setDisable(false);
        
        layout8.getChildren().addAll(tytul8);
        for (int i = 0; i < 10; i++) {
        	layout8.getChildren().addAll(produkt8[i]);
        }
        layout8.getChildren().addAll(back8, exitt9);
        
        layout8.setAlignment(Pos.CENTER_LEFT);
        
        //Zamawianie towarów przez klienta
        //***************************************************************
        
        VBox zamow9 = new VBox(20);
        Label tytul9 = new Label();
        RadioButton[] produkt9 = new RadioButton[10];
        for (int i = 0; i < 10; i++) {
        	produkt9[i] = new RadioButton();
        }
        Label ilosc9 = new Label();
        TextField ilosc10 = new TextField();
        Button button9 = new Button("Zamów");
        
        tytul9.setFont(Font.font(null, FontWeight.BOLD, 20));
        for (int i = 0; i < 10; i++) {
        	produkt9[i].setFont(new Font(14));
        }
        ilosc9.setFont(Font.font(null, FontWeight.BOLD, 14));
        
        ToggleGroup qw = new ToggleGroup();
        for (int i = 0; i < 10; i++) {
        	produkt9[i].setToggleGroup(qw);
        }
        
        button9.setDisable(true);
        for (int i = 0; i < 10; i++) {
        	produkt9[i].setOnAction(e -> button9.setDisable(false));
        }
        button9.setDisable(true);
        back9.setDisable(false);
        exitt10.setDisable(false);

        zamow9.getChildren().addAll(tytul9);
        for (int i = 0; i < 10; i++) {
        	zamow9.getChildren().addAll(produkt9[i]);
        }
        zamow9.getChildren().addAll(ilosc9, ilosc10, button9, back9, exitt10);
        
        zamow9.setAlignment(Pos.CENTER_LEFT);
        
        
        
        //********************
        //*Dzia³anie buttonów*
        //********************
        
        turnOnButton.setOnAction(e -> { //zatwierdzanie wyboru portu i serwera
            String portRegex = "[0-9]+";
            String hostRegex = "([0-9]{1,3}.){3}[0-9]{1,3}";
            if (Pattern.matches(portRegex, portField.getText().toString()) && Pattern.matches(hostRegex, hostField.getText().toString())) {
                try {
                    socket = new Socket(hostField.getText().toString(), Integer.parseInt(portField.getText().toString()));
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintStream(socket.getOutputStream());
                    hBox.getChildren().remove(turnOnButton);
                    grid.getChildren().removeAll(sceneTitle, serverPort, portField, serverHost, hostField, hBox, errorMessage);

                    kto.setText("Jako kto chcesz sie zalogowaæ?");
                    hurt.setText("Hurtowania");
                    klient.setText("Klient");
                    
                    grid.getChildren().setAll(logowanie);
                    
                } catch (IOException e1) {
                    errorMessage.setText("Nie uda³o sie po³¹czyc z serwerem!");
                    errorMessage.setTextFill(Color.RED);
                }
            } else {
                errorMessage.setText("Podano b³êdne dane!");
                errorMessage.setTextFill(Color.RED);
            }
        });
        
        button2.setOnAction(e -> { //zatwierdzenie wyboru hurtowni/klienta
            		
        			int a=0;
        			if (hurt.isSelected()) {
        				a=1;
        			} else if(klient.isSelected()) {
        				a=2;
        			}
        			
                   
                    grid.getChildren().removeAll(kto, hurt, klient, logowanie);
                    
                    if (a==1) { //panel hurtownii
                    	akcja.setText("Co chcesz zrobiæ?");
                    	cennik.setText("Otwórz cennik");
                    	magazyn.setText("Magazyn");
                    	zamawianie.setText("Zamów towary");
                    
                    grid.getChildren().setAll(hurtownia);
                    } else if (a==2) { //panel klienta
                    	akcja2.setText("Co chcesz zrobiæ?");
                        cennik2.setText("Otwórz cennik hurtowni");
                        zamawianie2.setText("Zamów towary z hurtowni");
                        
                        grid.getChildren().setAll(klient2);
                    }
        });
        
        button3.setOnAction(e -> { //zatwierdzenie wyboru cennika/magazynu/zamawiania
            
            int i=0;
            try {
            	
            	if (cennik.isSelected()) {
            		i=1;
            	} else if (magazyn.isSelected()) {
            		i=2;
            	} else if (zamawianie.isSelected()) {
            		i=3;
            	}
            		
             
                grid.getChildren().removeAll(akcja, cennik, magazyn, zamawianie, hurtownia);
                
                if (i==1) { //cennik
                	
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                    }
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                }  else if (i==2) { //magazyn
                	
                	lab.setText("Co chcesz zrobiæ?");
                    stan.setText("SprawdŸ stan magazynu");
                    dodaj.setText("Dodaj produkt");
                    
                    grid.getChildren().setAll(magazyn2);
                    
                }	else if (i==3) { //zamawianie 
                	
                	out.println("zamow");
                	
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "z³/kg");
                    }
                	
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    
                    
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                }
             
            } catch (IOException e1) {
                errorMessage.setText("Nie uda³o sie po³¹czyc z serwerem!");
                errorMessage.setTextFill(Color.RED);
            }
        
        });
        
        button4.setOnAction(e -> { // zatwierdzenie wyboru stanMagazynu/dodawanieProduktów
            int i=0;
            try {
            	
            	if (stan.isSelected()) {
            		i=1;
            	} else if (dodaj.isSelected()) {
            		i=2;
            	}
          
                grid.getChildren().removeAll(lab, stan, dodaj, magazyn2);
                if (i==1) { //stan magazynu
                	
                	out.println("magazyn");
                	tytul2.setText("Stan magazynu");
                	for (int j = 0; j < 10; j++) {
                		produkt2[j].setText(in.readLine() + " - " + in.readLine() + "kg");
                		
                    }
                	
                	grid.getChildren().setAll(stanMag);
                	
                }  else if (i==2) { //dodawanie produktow
                	
                	out.println("dodaj");
                	
                	tytul3.setText("Wybierz produkt z listy");
                	
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                	
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                }
                
            } catch (IOException e1) {
            	errorMessage.setText("Nie uda³o sie po³¹czyc z serwerem!");
                errorMessage.setTextFill(Color.RED);
            }
        
        });
        
        
        button5.setOnAction(e -> { //zatwierdzenie dodania produktu
    		
        	if (produkt3[0].isSelected()) {
        		
                out.println("1");
                out.println(ilosc2.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(dodPro);
                
                	out.println("dodaj");
                	tytul3.setText("Wybierz produkt z listy");
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie uda³o siê po³¹czyc z serwerem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt3[1].isSelected()) {
               
                out.println("2");
                out.println(ilosc2.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(dodPro);
                
                	out.println("dodaj");
                	tytul3.setText("Wybierz produkt z listy");
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie uda³o siê po³¹czyc z serwerem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt3[2].isSelected()) {
               
                out.println("3");
                out.println(ilosc2.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(dodPro);
                
                	out.println("dodaj");
                	tytul3.setText("Wybierz produkt z listy");
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie uda³o siê po³¹czyc z serwerem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt3[3].isSelected()) {
               
                out.println("4");
                out.println(ilosc2.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(dodPro);
                
                	out.println("dodaj");
                	tytul3.setText("Wybierz produkt z listy");
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt3[4].isSelected()) {
               
                out.println("5");
                out.println(ilosc2.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(dodPro);
                
                	out.println("dodaj");
                	tytul3.setText("Wybierz produkt z listy");
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt3[5].isSelected()) {
               
                out.println("6");
                out.println(ilosc2.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(dodPro);
                
                	out.println("dodaj");
                	tytul3.setText("Wybierz produkt z listy");
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt3[6].isSelected()) {
               
                out.println("7");
                out.println(ilosc2.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(dodPro);
                
                	out.println("dodaj");
                	tytul3.setText("Wybierz produkt z listy");
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt3[7].isSelected()) {
             
                out.println("8");
                out.println(ilosc2.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(dodPro);
                
                	out.println("dodaj");
                	tytul3.setText("Wybierz produkt z listy");
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt3[8].isSelected()) {
              
                out.println("9");
                out.println(ilosc2.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(dodPro);
                
                	out.println("dodaj");
                	tytul3.setText("Wybierz produkt z listy");
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt3[9].isSelected()) {
             
                out.println("10");
                out.println(ilosc2.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(dodPro);
                
                	out.println("dodaj");
                	tytul3.setText("Wybierz produkt z listy");
                	for (int j = 0; j < 10; j++) {
                		produkt3[j].setText(in.readLine());
                    }
                    ilosc.setText("Podaj iloœæ produktu(w kg)");
                    
                    grid.getChildren().setAll(dodPro);
                    ilosc2.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
            } 
			
        });
        
        button6.setOnAction(e -> { //zatwierdzenie zakupu produktu
    		
        	if (produkt4[0].isSelected()) {
        		
                out.println("1");
                out.println(ilosc4.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow);
                
                	out.println("zamow");
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt4[1].isSelected()) {
               
                out.println("2");
                out.println(ilosc4.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow);
                
                	out.println("zamow");
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt4[2].isSelected()) {
               
                out.println("3");
                out.println(ilosc4.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow);
                
                	out.println("zamow");
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt4[3].isSelected()) {
               
                out.println("4");
                out.println(ilosc4.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow);
                
                	out.println("zamow");
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt4[4].isSelected()) {
               
                out.println("5");
                out.println(ilosc4.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow);
                
                	out.println("zamow");
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt4[5].isSelected()) {
               
                out.println("6");
                out.println(ilosc4.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow);
                
                	out.println("zamow");
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt4[6].isSelected()) {
               
                out.println("7");
                out.println(ilosc4.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow);
                
                	out.println("zamow");
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt4[7].isSelected()) {
             
                out.println("8");
                out.println(ilosc4.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow);
                
                	out.println("zamow");
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt4[8].isSelected()) {
              
                out.println("9");
                out.println(ilosc4.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow);
                
                	out.println("zamow");
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt4[9].isSelected()) {
             
                out.println("10");
                out.println(ilosc4.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow);
                
                	out.println("zamow");
                	tytul4.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt4[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc3.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow);
                    ilosc4.getText();
                    
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
            } 
			
        });
        
       	button7.setOnAction(e -> {
    		
       		int b=0;
            try {
            	
            	if (cennik2.isSelected()) {
            		b=1;
            	} else if (zamawianie2.isSelected()) {
            		b=2;
            	}
            		
             
                grid.getChildren().removeAll(akcja2, cennik2, zamawianie2, klient2);
                
                if (b==1) { //cennik klienta
                	
                	out.println("cennik2");
                	tytul8.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt8[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                    }
                	grid.getChildren().setAll(layout8);
                	 
                }	else if (b==2) { //zamawianie przez klienta
                	
                	out.println("zamow2");
                	
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                	
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    
                    
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                }
                
            } catch (IOException e1) {
                errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                errorMessage.setTextFill(Color.RED);
            }
        	
        });
       	
       	button9.setOnAction(e -> { //zatwierdzenie zakupu produktu przez klienta
    		
        	if (produkt9[0].isSelected()) {
                out.println("1");
                out.println(ilosc10.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow9);
                
                	out.println("zamow2");
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt9[1].isSelected()) {
               
                out.println("2");
                out.println(ilosc10.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow9);
                
                	out.println("zamow2");
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt9[2].isSelected()) {
               
                out.println("3");
                out.println(ilosc10.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow9);
                
                	out.println("zamow2");
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
            } else if (produkt9[3].isSelected()) {
               
                out.println("4");
                out.println(ilosc10.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow9);
                
                	out.println("zamow2");
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
            } else if (produkt9[4].isSelected()) {
               
                out.println("5");
                out.println(ilosc10.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow9);
                
                	out.println("zamow2");
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
            } else if (produkt9[5].isSelected()) {
               
                out.println("6");
                out.println(ilosc10.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow9);
                
                	out.println("zamow2");
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
            } else if (produkt9[6].isSelected()) {
               
                out.println("7");
                out.println(ilosc10.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow9);
                
                	out.println("zamow2");
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
            } else if (produkt9[7].isSelected()) {
             
                out.println("8");
                out.println(ilosc10.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow9);
                
                	out.println("zamow2");
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt9[8].isSelected()) {
              
                out.println("9");
                out.println(ilosc10.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow9);
                
                	out.println("zamow2");
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt9[9].isSelected()) {
             
                out.println("10");
                out.println(ilosc10.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(zamow9);
                
                	out.println("zamow2");
                	tytul9.setText("Wybierz produkt który chcesz zamówiæ");
                	for (int j = 0; j < 10; j++) {
                		produkt9[j].setText(in.readLine() + "  |  " + in.readLine() + "zl/kg");
                    }
                    ilosc9.setText("Podaj iloœæ produktu(w kg)");
                    grid.getChildren().setAll(zamow9);
                    ilosc10.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } 
			
        });
       	
       	button10.setOnAction(e -> { //zatwierdzenie zmiany ceny produktu w cenniku
    		
        	if (produkt[0].isSelected()) {
        		
                out.println("1");
                out.println(cena5.getText().toString());
                
                try {    
                
                	grid.getChildren().removeAll(layout);
                
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                	}
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt[1].isSelected()) {
               
                out.println("2");
                out.println(cena5.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(layout);
                
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                	}
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
                
            } else if (produkt[2].isSelected()) {
               
                out.println("3");
                out.println(cena5.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(layout);
                
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                	}
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt[3].isSelected()) {
               
                out.println("4");
                out.println(cena5.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(layout);
                
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                	}
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt[4].isSelected()) {
               
                out.println("5");
                out.println(cena5.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(layout);
                
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                	}
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt[5].isSelected()) {
               
                out.println("6");
                out.println(cena5.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(layout);
                
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                	}
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt[6].isSelected()) {
               
                out.println("7");
                out.println(cena5.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(layout);
                
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                	}
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt[7].isSelected()) {
             
                out.println("8");
                out.println(cena5.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(layout);
                
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                	}
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt[8].isSelected()) {
              
                out.println("9");
                out.println(cena5.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(layout);
                
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                	}
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } else if (produkt[9].isSelected()) {
             
                out.println("10");
                out.println(cena5.getText().toString());
                
                try {    
                    
                	grid.getChildren().removeAll(layout);
                
                	out.println("cennik");
                	tytul.setText("Cennik");
                	for (int j = 0; j < 10; j++) {
                		produkt[j].setText(in.readLine() + " - " + in.readLine() + "z³/kg");
                	}
                	wybor5.setText("Na jak¹ cenê chcesz zmieniæ?");
                	grid.getChildren().setAll(layout);
                	cena5.getText();
                } catch (IOException e1) {
                	errorMessage.setText("Nie udalo sie polaczyc z serverem!");
                	errorMessage.setTextFill(Color.RED);
                }
                
            } 
			
        });

       	back.setOnAction(e -> {
    		
       		out.println("back");
            grid.getChildren().removeAll(akcja, cennik, magazyn, zamawianie, hurtownia);
       		
            kto.setText("Jako kto chcesz sie zalogowaæ?");
            hurt.setText("Hurtowania");
            klient.setText("Klient");
            
            grid.getChildren().setAll(logowanie);
        	
        });
        back2.setOnAction(e -> {
    		
        	out.println("back");
            grid.getChildren().removeAll(layout);
            
        	akcja.setText("Co chcesz zrobiæ?");
        	cennik.setText("Otwórz cennik");
        	magazyn.setText("Magazyn");
        	zamawianie.setText("Zamów towary");
        
        	grid.getChildren().setAll(hurtownia);
        	
        });
        
        back3.setOnAction(e -> {
    		
        	out.println("back");
        	grid.getChildren().removeAll(magazyn2);
        	
           	akcja.setText("Co chcesz zrobiæ?");
        	cennik.setText("Otwórz cennik");
        	magazyn.setText("Magazyn");
        	zamawianie.setText("Zamów towary");
        
        	grid.getChildren().setAll(hurtownia);
        	
        });
        
        back4.setOnAction(e -> {
    		
        	out.println("back");
        	grid.getChildren().removeAll(stanMag);
        	
        	lab.setText("Co chcesz zrobiæ?");
            stan.setText("SprawdŸ stan magazynu");
            dodaj.setText("Dodaj produkt");
            
            grid.getChildren().setAll(magazyn2);
        });

        back5.setOnAction(e -> {
	
        	out.println("back");
        	grid.getChildren().removeAll(dodPro);
	
        	lab.setText("Co chcesz zrobiæ?");
            stan.setText("SprawdŸ stan magazynu");
            dodaj.setText("Dodaj produkt");
            
            grid.getChildren().setAll(magazyn2);
            
        });
        
        back6.setOnAction(e -> {
        	
        	out.println("back");
        	grid.getChildren().removeAll(zamow);
        	
           	akcja.setText("Co chcesz zrobiæ?");
        	cennik.setText("Otwórz cennik");
        	magazyn.setText("Magazyn");
        	zamawianie.setText("Zamów towary");
        
        	grid.getChildren().setAll(hurtownia);
	
        });
        
        back7.setOnAction(e -> {
        	
        	out.println("back");
            grid.getChildren().removeAll(akcja2, cennik2, zamawianie2, klient2);
            
            kto.setText("Jako kto chcesz sie zalogowaæ?");
            hurt.setText("Hurtowania");
            klient.setText("Klient");
            
            grid.getChildren().setAll(logowanie);
	
        });
        
        back8.setOnAction(e -> {
        	
        	out.println("back");
        	grid.getChildren().removeAll(layout8);
	
        	akcja2.setText("Co chcesz zrobiæ?");
            cennik2.setText("Otwórz cennik hurtowni");
            zamawianie2.setText("Zamów towary z hurtowni");
            
            grid.getChildren().setAll(klient2);
        });
        
        back9.setOnAction(e -> {
        	
        	out.println("back");
        	grid.getChildren().removeAll(zamow9);
        	
        	akcja2.setText("Co chcesz zrobiæ?");
            cennik2.setText("Otwórz cennik hurtowni");
            zamawianie2.setText("Zamów towary z hurtowni");
            
            grid.getChildren().setAll(klient2);
            
        });

        exitt.setOnAction(e -> {
    		
        	out.println("exit");
        	out.println("exit");
        	System.exit(0);
        	
        });
        exitt2.setOnAction(e -> {
    		
        	out.println("exit");
        	out.println("exit");
        	System.exit(0);
        	
        });
        
        exitt3.setOnAction(e -> {
    		
        	out.println("exit");
        	out.println("exit");
        	System.exit(0);
        	
        });
        
        exitt4.setOnAction(e -> {
    		
        	out.println("exit");
        	out.println("exit");
        	System.exit(0);
        	
        });

        exitt5.setOnAction(e -> {
	
        	out.println("exit");
        	out.println("exit");
        	System.exit(0);
	
        });
        
        exitt6.setOnAction(e -> {
        	
        	out.println("exit");
        	out.println("exit");
        	System.exit(0);
	
        });
        
        exitt7.setOnAction(e -> {
        	
        	out.println("exit");
        	out.println("exit");
        	System.exit(0);
	
        });
        
        exitt8.setOnAction(e -> {
        	
        	out.println("exit");
        	out.println("exit");
        	System.exit(0);
	
        });
        
        exitt9.setOnAction(e -> {
        	
        	out.println("exit");
        	out.println("exit");
        	System.exit(0);
	
        });
        
        exitt10.setOnAction(e -> {
        	
        	out.println("exit");
        	out.println("exit");
        	System.exit(0);
	
        });
        
        Scene scene = new Scene(grid, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hurtownia");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        out.close();
        in.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

}
