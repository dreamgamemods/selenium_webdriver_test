package selenium.selenium.Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static Connection con = null;
	private static Statement stmt;
	public static String DB_URL = "jdbc:sqlserver://localhost/Testdata";
	public static String DB_USER = "user";
	public static String DB_PASSWORD = "password";
	
	public void connectToDB() throws Exception{
		
		String dbClass = "com.mysql.cj.jdbc.Driver";
		Class.forName(dbClass).newInstance();

		Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		stmt = con.createStatement();
		
		createTable(stmt);

        // data search
        String SQL = "SELECT TOP 10 * FROM Production.Product;";
        ResultSet rs = stmt.executeQuery(SQL);
        
        
     // delete the first
        rs.first();
        rs.deleteRow();
        
        // add
        rs.moveToInsertRow();
        rs.updateString("ProductID", "1");
        rs.updateString("Name", "SomeName");
        rs.insertRow();

        rs = stmt.executeQuery(SQL);
      
        while (rs.next()) {
            System.out.println(rs.getString("ProductID") + " : " + rs.getString("Name"));
            System.out.print(rs.getString(1) + rs.getString(2)); //alternative method to the one right above
        }
        
    }
	
	
	
	public void disconnectFromDB() throws SQLException{
		 if (con != null) {
	        	con.close();
	        	}
	}
	
	private static void createTable(Statement stmt) throws SQLException {
		
		//delete existing
        stmt.execute("if exists (select * from sys.objects where name = 'Product_JDBC_Sample')"
                + "drop table Product_JDBC_Sample");

        //create new table
        String sql = "CREATE TABLE [Product_JDBC_Sample](" + "[ProductID] [int] IDENTITY(1,1) NOT NULL,"
                + "[Name] [varchar](30) NOT NULL," + "[ProductNumber] [nvarchar](25) NOT NULL,"
                + "[MakeFlag] [bit] NOT NULL," + "[FinishedGoodsFlag] [bit] NOT NULL," + "[Color] [nvarchar](15) NULL,"
                + "[SafetyStockLevel] [smallint] NOT NULL," + "[ReorderPoint] [smallint] NOT NULL,"
                + "[StandardCost] [money] NOT NULL," + "[ListPrice] [money] NOT NULL," + "[Size] [nvarchar](5) NULL,"
                + "[SizeUnitMeasureCode] [nchar](3) NULL," + "[WeightUnitMeasureCode] [nchar](3) NULL,"
                + "[Weight] [decimal](8, 2) NULL," + "[DaysToManufacture] [int] NOT NULL,"
                + "[ProductLine] [nchar](2) NULL," + "[Class] [nchar](2) NULL," + "[Style] [nchar](2) NULL,"
                + "[ProductSubcategoryID] [int] NULL," + "[ProductModelID] [int] NULL,"
                + "[SellStartDate] [datetime] NOT NULL," + "[SellEndDate] [datetime] NULL,"
                + "[DiscontinuedDate] [datetime] NULL," + "[rowguid] [uniqueidentifier] ROWGUIDCOL  NOT NULL,"
                + "[ModifiedDate] [datetime] NOT NULL,)";

        stmt.execute(sql);

        //add data
        sql = "INSERT Product_JDBC_Sample VALUES ('Adjustable Time','AR-5381','0','0',NULL,'1000','750','0.00','0.00',NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'2008-04-30 00:00:00.000',NULL,NULL,'694215B7-08F7-4C0D-ACB1-D734BA44C0C8','2014-02-08 10:01:36.827') ";
        stmt.execute(sql);

        sql = "INSERT Product_JDBC_Sample VALUES ('ML Bottom Bracket','BB-8107','0','0',NULL,'1000','750','0.00','0.00',NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'2008-04-30 00:00:00.000',NULL,NULL,'694215B7-08F7-4C0D-ACB1-D734BA44C0C8','2014-02-08 10:01:36.827') ";
        stmt.execute(sql);

        sql = "INSERT Product_JDBC_Sample VALUES ('Mountain-500 Black, 44','BK-M18B-44','0','0',NULL,'1000','750','0.00','0.00',NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'2008-04-30 00:00:00.000',NULL,NULL,'694215B7-08F7-4C0D-ACB1-D734BA44C0C8','2014-02-08 10:01:36.827') ";
        stmt.execute(sql);
    }
	
}
