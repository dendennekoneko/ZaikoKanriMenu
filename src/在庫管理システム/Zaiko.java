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
public class Zaiko{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
		
    static Connection conn;								//SQL接続インスタンス

	static Statement st;										//SQL実行用インスタンス
	static ResultSet rset;                                    //SQL結果格納用インスタンス
	static ResultSetMetaData rsmd;					//SQLカラム名取得用インスタンス	
	
	static String selectSql;									//SQL文を格納する
	public static int get_shocd = 0;					//入力された商品ＣＤ
	public static String get_shoname = "";		//入力された商品名
	
	static boolean string_check_ok;					//DB内に存在しない文字列が入力されていないかチェック用
	
	public static List<ArrayList<String>> result_Column = new ArrayList<>();			//二次元配列(DB結果を格納する表)
	
	
	//******************************************************************************************
    public static void main(String[] args) throws SQLException {
    	
        // (1) 接続用のURIを用意する(必要に応じて認証指示user/passwordを付ける)
    	String uri = "jdbc:postgresql://localhost:5432/sample";
        String user = "postgres";
        String password = "1818";
        
        // (2) DriverManagerクラスのメソッドで接続する
        conn = DriverManager.getConnection(uri,user,password);
        
        // (3) SQL送信用インスタンスの作成
       st = conn.createStatement();
       
       //画面生成クラス(Superクラスのコンストラクタ)へ
       new Mado();
       
        //updateSql(a);															//SQL書き換え

    }
  //******************************************************************************************
    
    
    //SQL結果表示用メソッド 	
    public static void getSql() throws SQLException {
    	
        selectSql = "SELECT * FROM hatzaiko ORDER BY shocd ASC";
        Mado.l_error.setText("");
        string_check_ok = false;

    	rset = st.executeQuery(selectSql);
    	
        //まず無効な文字列が入っていないか確認する！！
    	if(!get_shoname.equals("")) {
            stringCheck();
            if(!string_check_ok) {
            	Mado.l_error.setText("存在しない商品名が入力されています。");
            }
    	}else {
    		string_check_ok = true;
    	}

        //存在しない文字列エラーが出ていなければ
    	if(string_check_ok) {
            search();
    	}
        
        //諸々のエラーが出ていなければ
    	if(Mado.l_error.getText().equals("")) {
    		
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
    		
    		new Result();
    		
            //カラム名を取得
            rsmd = rset.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i)+"　：　");
             }
            System.out.println("");

            //結果描画
        	for(ArrayList<String> a : result_Column) {
        		for(String b : a) {
        			System.out.print(b.trim() + "　：　");
        		}
        		System.out.println("");
        	}

        //エラーラベルに何らかのメッセージが入っている。
    	}else {
    		System.out.println("エラーで終わりました。");
    	}
    }
    
     //存在しない商品名が入力されていないかチェック
    public static void stringCheck() throws SQLException {
    	//商品名が入力されている
    	if(!get_shoname.equals("")) {
            while(rset.next()) {
            	//入力された商品名と、SQL結果の商品名が一致する
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
                    	Mado.l_error.setText("商品ＣＤと商品名が一致しません。");
                    	break;
                	}
                }
            }
         //商品名が入力されている。
    	}else if(!get_shoname.trim().equals("")){
            rset = st.executeQuery(selectSql);
    		while(rset.next()) {
        		//入力された商品名がDB上の商品名と一致している
            	if(rset.getString("shoname").trim().equals(get_shoname.trim())) {
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