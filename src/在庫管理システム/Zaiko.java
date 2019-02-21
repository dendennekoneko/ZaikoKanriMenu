package 在庫管理システム;

import java.sql.SQLException;

//SQL処理用メインクラス
public class Zaiko{

    public static void main(String[] args) throws SQLException {
       
       //画面生成クラス(Superクラスのコンストラクタ)へ
       new Zaiko_Menu();

    }
}