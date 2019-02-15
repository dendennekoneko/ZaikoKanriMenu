package 在庫管理システム;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Zaiko extends Mado{

	static Statement st;										//SQL実行用インスタンス
	static ResultSet rset;                                      //SQL結果格納用インスタンス
	static ResultSetMetaData rsmd;
	
	static String selectSql;
	public static int get_shocd = 0;
	public static String get_shoname = "";
	
	static boolean string_check_ok;
	
    public static void main(String[] args) throws SQLException {
    	
    	
        // (1) 接続用のURIを用意する(必要に応じて認証指示user/passwordを付ける)
        String uri = "jdbc:postgresql://localhost:5432/sample";
        String user = "postgres";
        String password = "1818";
        
        // (2) DriverManagerクラスのメソッドで接続する
        Connection conn = DriverManager.getConnection(uri,user,password);
        
        // (3) SQL送信用インスタンスの作成
       st = conn.createStatement();
       
       Zaiko z = new Zaiko();
        
        //updateSql(a);															//SQL書き換え

    }
    
    public static void getSql() throws SQLException {									//SQL結果表示用メソッド 	
    	
        selectSql = "SELECT * FROM hatzaiko ORDER BY shocd ASC";
        l_error.setText("");
        string_check_ok = false;
        
    	rset = st.executeQuery(selectSql);
    	
        //まず無効な文字列が入っていないか確認する！！
    	if(!get_shoname.equals("")) {
            stringCheck();
            if(!string_check_ok) {
            	l_error.setText("存在しない商品名が入力されています");
            }
    	}else {
    		string_check_ok = true;
    	}

        //存在しない文字列エラーが出ていなければ
    	if(string_check_ok) {
    		System.out.println("check院！");
            search();
    	}
        
        //諸々のエラーが出ていなければ
    	if(l_error.getText().equals("")) {
    		
    		rset = st.executeQuery(selectSql);
    		
            //カラム名を取得
            rsmd = rset.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i)+"　：　");
             }
            System.out.println("");
            
            //レコードを取得
            while(rset.next()) {
            	System.out.print(rset.getInt("shocd") + "　　　　：　");
            	System.out.print(rset.getString("shoname").trim() +"　　　：　");
            	System.out.print(rset.getInt("zaisu"));       	
            	System.out.println();
            }
    	}else {
    		System.out.println("エラーで終わりました");
    	}
    }
    
     //存在しない商品名が入力されていないか監視
    public static void stringCheck() throws SQLException {									//SQL結果表示用メソッド 	

    	if(!get_shoname.equals("")) {
            while(rset.next()) {
            	if(rset.getString("shoname").trim().equals(get_shoname)) {
            		string_check_ok = true;
            		break;
            	}
            }
    	}
    }        
    
    //入力された商品名が一致しているかチェック
    public static void search() throws SQLException {
    	
    	//商品ＣＤが入力されている
    	if(get_shocd != 0) {
    		selectSql = "SELECT * FROM hatzaiko WHERE shocd="+get_shocd+"";
            rset = st.executeQuery(selectSql);
            //商品名が入力されている
            if(!get_shoname.trim().equals("")) {
                while(rset.next()) {
                	//入力された商品名がと、入力された商品ＣＤに対応する商品名が正しく一致している
                	if(rset.getString("shoname").trim().equals(get_shoname)) {
                		break;
                	}else {
                    	l_error.setText("商品ＣＤと商品名が一致しません");
                    	break;
                	}
                }
            }
         //商品ＣＤが入力され、商品名が入力されていない。
    	}else if(!get_shoname.trim().equals("")){
            rset = st.executeQuery(selectSql);
    		while(rset.next()) {
        		//入力された商品名がDB上の商品名と一致している
            	if(rset.getString("shoname").trim().equals(get_shoname.trim())) {
            		System.out.println("商品名一致？");
            		selectSql = "SELECT * FROM hatzaiko WHERE shoname like '%"+get_shoname+"%'";
            		break;
            	}
    		}
    	}
    }
    
    
   /* public static void updateSql(int a) throws SQLException {								//SQL書き換え用メソッド
        String updateAll = "update hatzaiko SET zaisu="+a+"";
        st.executeUpdate(updateAll);
    }*/
    
}