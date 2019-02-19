package 在庫管理システム;

import java.sql.SQLException;

//SQL処理用メインクラス
public class Zaiko{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
		
    //static Connection conn;								//SQL接続インスタンス

	//static Statement st;										//SQL実行用インスタンス
	//static ResultSet rset;                                    //SQL結果格納用インスタンス
	//static ResultSetMetaData rsmd;					//SQLカラム名取得用インスタンス	
	
	//static String selectSql;									//SQL文を格納する
	//public static int get_shocd = 0;					//入力された商品ＣＤ
	//public static String get_shoname = "";		//入力された商品名
	
	//static boolean string_check_ok;					//DB内に存在しない文字列が入力されていないかチェック用
	
	//public static List<ArrayList<String>> result_Column = new ArrayList<>();			//二次元配列(DB結果を格納する表)
	
	
	//******************************************************************************************
    public static void main(String[] args) throws SQLException {
    	
        // (1) 接続用のURIを用意する(必要に応じて認証指示user/passwordを付ける)
    	//String uri = "jdbc:postgresql://localhost:5432/sample";
        //String user = "postgres";
        //String password = "1818";
        
        // (2) DriverManagerクラスのメソッドで接続する
        //conn = DriverManager.getConnection(uri,user,password);
        
        // (3) SQL送信用インスタンスの作成
       //st = conn.createStatement();
       
       //画面生成クラス(Superクラスのコンストラクタ)へ
       new Zaiko_Menu();
       
        //updateSql(a);															//SQL書き換え

    }
  //******************************************************************************************
    
   /* public static void updateSql(int a) throws SQLException {								//SQL書き換え用メソッド
        String updateAll = "update hatzaiko SET zaisu="+a+"";
        st.executeUpdate(updateAll);
    }*/
    
}