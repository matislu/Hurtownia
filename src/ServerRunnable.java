import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ServerRunnable implements Runnable {

    private Socket socket;
    private JDBC dataBase;
    private String idKlienta;

    public ServerRunnable(Socket socket, JDBC dataBase) {
        this.socket = socket;
        this.dataBase = dataBase;
        // Generate id for new client
        idKlienta = String.valueOf(UUID.randomUUID());
        System.out.println("Nowy klient o ID: " + idKlienta);
    }
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            
            
            String answer = in.readLine();
            ResultSet rs;
            int numRows;
            int idProduktu;
            int temp;
            String answer1;
            String answer2;
            
            while(!answer.equals("exit")) {
     
            	if(answer.equals("cennik")) {
            		rs = dataBase.getCennik();
                    rs.last();
                    // Count number of questions
                    numRows = rs.getRow();
                    idProduktu = 0;

                    // Send to client all questions
                    for (int i = 1; i <= numRows; i++) {
                        rs = dataBase.getProdukt(i);
                        while (rs.next()) {
                            idProduktu = rs.getInt("idProduktu");
                            out.println(rs.getString("nazwa"));
                            out.println(rs.getInt("cena"));
                        }
                        
                    }
                    answer1 = in.readLine();
                    if(!answer1.equals("exit") && !answer1.equals("back")) {
                    	System.out.println(answer1);
                    	answer2 = in.readLine();
                    	System.out.println(answer2);
                    	dataBase.updateCena(Integer.parseInt(answer1), Integer.parseInt(answer2));
                    	answer = "stop";
                    }
                    answer = "stop";
                    
            	} else if(answer.equals("magazyn")) {
            		rs = dataBase.getMagazyn();
                    rs.last();
                    numRows = rs.getRow();
                    idProduktu = 0;
                    
                    for (int i = 1; i <= numRows; i++) {
                        rs = dataBase.getProdukt2(i);
                        while (rs.next()) {
                            idProduktu = rs.getInt("idProduktu");
                            out.println(rs.getString("nazwa"));
                            out.println(rs.getInt("ilosc"));
                        }
                        
                    }
                    answer = "stop";
                    
                 } else if(answer.equals("dodaj")) {
            		rs = dataBase.getMagazyn();
                    rs.last();
                    numRows = rs.getRow();
                    idProduktu = 0;
                    
                    for (int i = 1; i <= numRows; i++) {
                        rs = dataBase.getProdukt2(i);
                        while (rs.next()) {
                            idProduktu = rs.getInt("idProduktu");
                            out.println(rs.getString("nazwa"));
                            temp = rs.getInt("ilosc");
                        }
                        
                    }
                    answer1 = in.readLine();
                    if(!answer1.equals("exit") && !answer1.equals("back")) {
                    	System.out.println(answer1);
                    	answer2 = in.readLine();
                    	System.out.println(answer2);
                    	dataBase.updateStanMagazynu(Integer.parseInt(answer1), Integer.parseInt(answer2));
                    	answer = "stop";
                    }
                    answer = "stop";
                    
                 } else if(answer.equals("zamow")) {
            		rs = dataBase.getDostawca();
                    rs.last();
                    numRows = rs.getRow();
                    idProduktu = 0;
                    
                    for (int i = 1; i <= numRows; i++) {
                        rs = dataBase.getProdukt3(i);
                        while (rs.next()) {
                            idProduktu = rs.getInt("idProduktu");
                            out.println(rs.getString("nazwa"));
                            out.println(rs.getInt("cena"));
                        }
                        
                    }
                    answer1 = in.readLine();
                    if(!answer1.equals("exit") && !answer1.equals("back")) {
                    	System.out.println(answer1);
                    	answer2 = in.readLine();
                    	System.out.println(answer2);
                    	dataBase.updateStanMagazynu(Integer.parseInt(answer1), Integer.parseInt(answer2));
                    	answer = "stop";
                    }
                    answer = "stop";
                    
                 } else if(answer.equals("zamow2")) {
            		rs = dataBase.getCennik();
                    rs.last();
                    numRows = rs.getRow();
                    idProduktu = 0;
                    
                    for (int i = 1; i <= numRows; i++) {
                        rs = dataBase.getProdukt(i);
                        while (rs.next()) {
                            idProduktu = rs.getInt("idProduktu");
                            out.println(rs.getString("nazwa"));
                            out.println(rs.getInt("cena"));
                        }
                        
                    }
                    answer1 = in.readLine();
                    if(!answer1.equals("exit") && !answer1.equals("back")) {
                    	System.out.println(answer1);
                    	answer2 = in.readLine();
                    	System.out.println(answer2);
                    	dataBase.updateStanMagazynu2(Integer.parseInt(answer1), Integer.parseInt(answer2));
                    	answer = "stop";
                    }
                    answer = "stop";
                    
                 } else if(answer.equals("cennik2")) {
            		rs = dataBase.getCennik();
                    rs.last();
                    // Count number of questions
                    numRows = rs.getRow();
                    idProduktu = 0;

                    // Send to client all questions
                    for (int i = 1; i <= numRows; i++) {
                        rs = dataBase.getProdukt(i);
                        while (rs.next()) {
                            idProduktu = rs.getInt("idProduktu");
                            out.println(rs.getString("nazwa"));
                            out.println(rs.getInt("cena"));
                        }
                        
                    }
                    answer = "stop";
                    
            	}
            	
            	answer = in.readLine();
            	}
            
            
            out.println("exit");
            out.close();
            in.close();
            System.out.println("Zakonczono polaczenie z klientem o ID: " + idKlienta);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
