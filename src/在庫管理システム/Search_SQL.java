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
public class Search_SQL{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
    Connection conn;										//SQL接続インスタンス
	Statement st;												//SQL実行用インスタンス

	ResultSet rset;                                   			 //SQL結果格納用インスタンス
	ResultSetMetaData rsmd;							//SQLカラム名取得用インスタンス	
	
	String selectSql;											//SQL文を格納する
	public static int get_shocd = 0;					//入力された商品ＣＤ
	public static String get_shoname = "";		//入力された商品名
	
	boolean string_check_ok;					//DB内に存在しない文字列が入力されていないかチェック用
	boolean int_check_ok;						//DB内に存在しない商品ＣＤが入力されていないかチェック用
	
	public static List<ArrayList<String>> result_Column = new ArrayList<>();			//二次元配列(DB結果を格納する表)
	
    Search_SQL() throws SQLException{
        // (1) 接続用のURIを用意する(必要に応じて認証指示user/passwordを付ける)
    	String uri = "jdbc:postgresql://localhost:5432/sample";
        String user = "postgres";
        String password = "1818";
        
        // (2) DriverManagerクラスのメソッドで接続する
        conn = DriverManager.getConnection(uri,user,password);
        
      //SQL実行用インスタンス
        st = conn.createStatement();
    }
    
    //SQL結果一覧表示用メソッド 	********************************************************************
    public void getSql() throws SQLException {
        selectSql = "SELECT * FROM hatzaiko ORDER BY shocd ASC";
    	
    	intCheck();
    	if(int_check_ok) {
    		stringCheck();
    	}

        //存在しない文字列エラーが出ていなければ
    	if(string_check_ok && int_check_ok) {
            search();
    	}
        
        //諸々のエラーが出ていなければ
    	if(Zaiko_Search.l_error.getText().equals("") && int_check_ok && string_check_ok) {
        	//一覧表作成
        	listGo();
    	}
    }
	//*****************************************************************************************
    //一覧表作成メソッド
	public void listGo() throws SQLException {
		rset = st.executeQuery(selectSql);
        //レコードを取得
		while(rset.next()) {
			//@SuppressWarnings("unchecked")					//コンパイル警告の無効化。←警告が出るため
			
			//なぎせさん(ArrayList生成)
			ArrayList<String> tempArray = new ArrayList<>();
			
			tempArray.add(String.valueOf(rset.getInt("shocd")));
			tempArray.add(rset.getString("shoname"));
			tempArray.add(String.valueOf(rset.getInt("zaisu")));

			//二次元ListにtempArrayを追加
			result_Column.add(tempArray);
		}
		
		new Zaiko_List();
		result_Column.clear();
	}
    
    //存在しない商品ＣＤが入力されていないかチェック
    public void intCheck() throws SQLException {
    	//存在しない商品CDが入力されていないかチェック
    	if(get_shocd!=0) {
        	rset = st.executeQuery(selectSql);
            while(rset.next()) {
            	//入力された商品CDと、SQL結果の商品CDが一致する
            	if(rset.getString("shocd").equals(String.valueOf(get_shocd))) {
            		int_check_ok = true;
            		break;
            	}
            }
            if(!int_check_ok) {
            	Zaiko_Search.l_error.setText("存在しない商品ＣＤが入力されています。");
            }
    	}else {
    		int_check_ok = true;
    	}
	}
    
     //存在しない商品名が入力されていないかチェック
    public void stringCheck() throws SQLException {
        //無効な商品名が入っていないか確認する！！
    	if(!get_shoname.equals("")) {
        	rset = st.executeQuery(selectSql);
            while(rset.next()) {
            	//入力された商品名と、SQL結果の商品名が一致する
            	if(rset.getString("shoname").trim().equals(get_shoname.trim())) {
            		string_check_ok = true;
            		break;
            	}
            }
            if(!string_check_ok) {
            	Zaiko_Search.l_error.setText("存在しない商品名が入力されています。");
            }
    	}else {
    		string_check_ok = true;
    	}
    }        
    
    //入力された商品名が一致しているかチェック
    public void search() throws SQLException {
    	//商品ＣＤが入力されている
    	if(get_shocd != 0) {
    		selectSql = "SELECT * FROM hatzaiko WHERE shocd="+get_shocd+"";
            //商品名が入力されている
            if(!get_shoname.trim().equals("")) {
                rset = st.executeQuery(selectSql);
                while(rset.next()) {
                	//入力された商品名がと、入力された商品ＣＤに対応する商品名が正しく一致している
                	if(rset.getString("shoname").trim().equals(get_shoname)) {
                		break;
                	}else {
                    	Zaiko_Search.l_error.setText("商品ＣＤと商品名が一致しません。");
                    	break;
                	}
                }
            }
         //商品名のみ入力されている。
    	}else {
    		if(!get_shoname.trim().equals("")){
                rset = st.executeQuery(selectSql);
        		while(rset.next()) {
            		//入力された商品名がDB上の商品名と一致している
                	if(rset.getString("shoname").trim().equals(get_shoname)) {
                		selectSql = "SELECT * FROM hatzaiko WHERE shoname like '%"+get_shoname+"%'";
                		break;
                	}
        		}
    		}
    	}
    }
}