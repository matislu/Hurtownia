import java.sql.*;
import java.util.Properties;

public class JDBC {

    private Statement st;
    private Connection connection;

    public JDBC() {
    }

    public void connect() {
        if (checkDriver("com.mysql.jdbc.Driver"))
            System.out.println("... OK");
        else
            System.exit(1);

        connection = getConnection("jdbc:mysql://", "localhost", 3306, "root", "");
        st = createStatement();
        if (executeUpdate("USE Hurtownia3;") == 0)
            System.out.println("Baza wybrana");
        else {
            System.out.println("Baza nie istnieje! Tworzymy baze: ");
            if (executeUpdate("create Database Hurtownia3;") == 1)
                System.out.println("Baza utworzona");
            else
                System.out.println("Baza nieutworzona!");
            if (executeUpdate("USE Hurtownia3;") == 0)
                System.out.println("Baza wybrana");
            else
                System.out.println("Baza niewybrana!");
            createTables();
        }
    }

    private boolean checkDriver(String driver) {
        System.out.print("Sprawdzanie sterownika bazy danych ");
        try {
            Class.forName(driver).newInstance();
            return true;
        } catch (Exception e) {
            System.out.println("Blad przy ladowaniu sterownika bazy!");
            return false;
        }
    }

    private Connection getConnection(String kindOfDatabase, String adres, int port, String userName, String password) {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        try {
            conn = DriverManager.getConnection(kindOfDatabase + adres + ":" + port + "/",
                    connectionProps);
        } catch (SQLException e) {
            System.out.println("Blad poloczenia z baza danych danych! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(2);
        }
        System.out.println("Poloczenie z baza danych: ... OK");
        return conn;
    }

    private Statement createStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Blad createStatement! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(3);
        }
        return null;
    }

    private int executeUpdate(String sql) {
        try {
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            return -1;
        }
    }

    private void createTables() {
        if (executeUpdate("CREATE TABLE cennik (idProduktu int(10) NOT NULL, nazwa varchar(100) NOT NULL, cena int(10) NOT NULL);") == 0) {
            executeUpdate("ALTER TABLE cennik ADD PRIMARY KEY (idProduktu);");
            System.out.println("Tabela cennik utworzona");
            String sql = "INSERT INTO cennik VALUES" +
                    "(1, 'Ziemniak', '1'), " +
                    "(2, 'Pomidor', '5'), " +
                    "(3, 'Papryka', '8'), " +
                    "(4, 'Salata', '4'), " +
                    "(5, 'Jablko', '3'), " +
                    "(6, 'Banan', '5'), " +
                    "(7, 'Ogorek', '6'), " +
                    "(8, 'Truskawka', '12'), " +
                    "(9, 'Malina', '10'), " +
                    "(10, 'Marchewka', '2');";
            executeUpdate(sql);
            System.out.println("Tabela cennik wypelniona danymi");
        } else
            System.out.println("Tabela cennik juz istnieje!");
        
        if (executeUpdate("CREATE TABLE magazyn (idProduktu int(10) NOT NULL, nazwa varchar(100) NOT NULL, ilosc int(10) NOT NULL);") == 0) {
            executeUpdate("ALTER TABLE magazyn ADD PRIMARY KEY (idProduktu);");
            System.out.println("Tabela magazyn utworzona");
            String sql = "INSERT INTO magazyn VALUES" +
                    "(1, 'Ziemniak', '1000'), " +
                    "(2, 'Pomidor', '500'), " +
                    "(3, 'Papryka', '800'), " +
                    "(4, 'Salata', '400'), " +
                    "(5, 'Jablko', '300'), " +
                    "(6, 'Banan', '500'), " +
                    "(7, 'Ogorek', '600'), " +
                    "(8, 'Truskawka', '120'), " +
                    "(9, 'Malina', '100'), " +
                    "(10, 'Marchewka', '20');";
            executeUpdate(sql);
            System.out.println("Tabela magazyn wypelniona danymi");
        } else
            System.out.println("Tabela magazyn juz istnieje!");
        
        if (executeUpdate("CREATE TABLE dostawca (idProduktu int(10) NOT NULL, nazwa varchar(100) NOT NULL, cena int(10) NOT NULL);") == 0) {
            executeUpdate("ALTER TABLE dostawca ADD PRIMARY KEY (idProduktu);");
            System.out.println("Tabela dostawca utworzona");
            String sql = "INSERT INTO dostawca VALUES" +
                    "(1, 'Ziemniak', '1'), " +
                    "(2, 'Pomidor', '3'), " +
                    "(3, 'Papryka', '5'), " +
                    "(4, 'Salata', '2'), " +
                    "(5, 'Jablko', '2'), " +
                    "(6, 'Banan', '3'), " +
                    "(7, 'Ogorek', '3'), " +
                    "(8, 'Truskawka', '8'), " +
                    "(9, 'Malina', '7'), " +
                    "(10, 'Marchewka', '1');";
            executeUpdate(sql);
            System.out.println("Tabela dostawca wypelniona danymi");
        } else
            System.out.println("Tabela dostawca juz istnieje!");
    }

    public ResultSet getCennik() {
        String sql = "Select * from cennik;";
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet getMagazyn() {
        String sql = "Select * from magazyn;";
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet getDostawca() {
        String sql = "Select * from dostawca;";
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet getProdukt(int id) {
        String sql = "Select * from cennik where idProduktu = " + id;
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet getProdukt2(int id) {
        String sql = "Select * from magazyn where idProduktu = " + id;
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet getProdukt3(int id) {
        String sql = "Select * from dostawca where idProduktu = " + id;
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    

    public void updateStanMagazynu(int idProduktu, int ilosc) {
        String sql = "Select ilosc from magazyn where idProduktu =" + idProduktu;
        int counter = ilosc;
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                counter += rs.getInt("ilosc");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        sql = "UPDATE magazyn set ilosc = " + counter + " where idProduktu =" + idProduktu;
        executeUpdate(sql);
    }
    
    public void updateStanMagazynu2(int idProduktu, int ilosc) {
        String sql = "Select ilosc from magazyn where idProduktu =" + idProduktu;
        int counter = ilosc;
        int ab = 0;
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ab = rs.getInt("ilosc") - counter;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        sql = "UPDATE magazyn set ilosc = " + ab + " where idProduktu =" + idProduktu;
        executeUpdate(sql);
    }
    
    public void updateCena(int idProduktu, int cena) {
    	
        String sql = "UPDATE cennik set cena = " + cena + " where idProduktu =" + idProduktu;
        executeUpdate(sql);
    }
    
    public void closeConnection() {
        System.out.print("\nZamykanie polaczenia z baza ...");
        try {
            st.close();
            connection.close();
        } catch (SQLException e) {
            System.out
                    .println("Blad przy zamykaniu polaczenia z baza! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(4);
        }
        System.out.print(" zamkniecie OK\n");
    }

}

