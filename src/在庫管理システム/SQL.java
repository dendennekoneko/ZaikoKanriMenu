package 在庫管理システム;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//SQL処理用メインクラス
public class SQL{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
    Connection conn;										//SQL接続インスタンス
	Statement st;												//SQL実行用インスタンス

	ResultSet rset;                                   			 //SQL結果格納用インスタンス
	ResultSetMetaData rsmd;							//SQLカラム名取得用インスタンス	
	
    SQL() throws SQLException{
        // (1) 接続用のURIを用意する(必要に応じて認証指示user/passwordを付ける)
    	String uri = "jdbc:postgresql://localhost:5432/sample";
        String user = "postgres";
        String password = "1818";
        
        // (2) DriverManagerクラスのメソッドで接続する
        conn = DriverManager.getConnection(uri,user,password);
        
      //SQL実行用インスタンス
        st = conn.createStatement();
    }
}