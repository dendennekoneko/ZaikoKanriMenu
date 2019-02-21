package 在庫管理システム;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	
	static String selectSql;									//SQL文を格納する
	public static int get_shocd = 0;					//入力された商品ＣＤ
	public static String get_shoname = "";		//入力された商品名
	
	boolean string_check_ok;					//DB内に存在しない文字列が入力されていないかチェック用
	boolean int_check_ok;						//DB内に存在しない商品ＣＤが入力されていないかチェック用
	
	public static List<ArrayList<String>> result_Column = new ArrayList<>();			//二次元配列(DB結果を格納する表)
	
	//コンストラクタ、在庫検索が入力されたタイミングでＤＢ接続
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
    
    //SQL結果一覧表示用メソッド
    public void getSql() throws SQLException {
        selectSql = "SELECT * FROM hatzaiko ORDER BY shocd ASC";
    	
        //商品ＣＤが正しく入力されているかチェック
    	intCheck();
    	//商品ＣＤが問題なければ
    	if(int_check_ok) {
    		//商品名が正しく入力されているかチェック
    		stringCheck();
    	}

        //存在しない文字列エラーが出ていなければ
    	if(string_check_ok && int_check_ok) {
    		//入力された検索条件に従い、SQL実行メソッドlistGo()で必要なSQL文を確定する。
            search();
    	}
        
        //諸々のエラーが出ていなければ
    	if(Zaiko_Search.l_error_syu.getText().equals("") && int_check_ok && string_check_ok) {
        	//SQL実行＆一覧表作成
        	listGo();
    	}
    }
    
    //一覧表作成メソッド
	public void listGo() throws SQLException {
		
		//選択SQL実行
		rset = st.executeQuery(selectSql);
		
        //レコードを取得
		while(rset.next()) {
			//なぎせさん(ArrayList生成)
			ArrayList<String> tempArray = new ArrayList<>();
			
			tempArray.add(String.valueOf(rset.getInt("shocd")));
			tempArray.add(rset.getString("shoname"));
			tempArray.add(String.valueOf(rset.getInt("zaisu")));

			//二次元ListにtempArrayを追加
			result_Column.add(tempArray);
		}
		
		//在庫一覧表が開いていない
		if(!Zaiko_List.paint_now) {
			//在庫一覧表インスタンス生成
			new Zaiko_List();
			//在庫一覧表のJTable配列に代入できたため、二次元配列をクリアする
			Search_SQL.result_Column.clear();
			conn.close();
		}
	}
    
    //存在しない商品ＣＤが入力されていないかチェック
    public void intCheck() throws SQLException {
    	//商品ＣＤが入力されている
    	if(get_shocd!=0) {
        	rset = st.executeQuery(selectSql);
            while(rset.next()) {
            	//入力された商品CDと、SQL結果の商品CDが一致する
            	if(rset.getString("shocd").equals(String.valueOf(get_shocd))) {
            		int_check_ok = true;
            		break;
            	}
            }
            //入力された商品ＣＤが不正
            if(!int_check_ok) {
            	Zaiko_Search.l_error_syu.setText("存在しない商品ＣＤが入力されています。");
            }
        //そもそも商品ＣＤが入力されていなかった、もしくは０なら
    	}else {
    		int_check_ok = true;
    	}
	}
    
     //存在しない商品名が入力されていないかチェック
    public void stringCheck() throws SQLException {
        //商品名が入力されている
    	if(!get_shoname.equals("")) {
        	rset = st.executeQuery(selectSql);
            while(rset.next()) {
            	//入力された商品名と、SQL結果の商品名が一致する
            	if(rset.getString("shoname").trim().equals(get_shoname.trim())) {
            		string_check_ok = true;
            		break;
            	}
            }
            //入力された商品名が不正
            if(!string_check_ok) {
            	Zaiko_Search.l_error_syu.setText("存在しない商品名が入力されています。");
            }
        //そもそも商品名が入力されていなかったら
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
                    	Zaiko_Search.l_error_syu.setText("商品ＣＤと商品名が一致しません。");
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
    
	//ウィンドウが閉じられた時の処理、オーバーライド←そもそも親クラスにこのメソッド必要ない・・・
	class WinAda extends WindowAdapter{
	    public void windowClosing(WindowEvent e) {
			try {
				//データベース接続を切断
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	    }
	}
}