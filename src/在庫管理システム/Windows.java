package 在庫管理システム;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


//ウィンドウのひな型
public abstract class Windows extends JFrame {
	
	private static final long serialVersionUID = 1L;

	//コンストラクタ(画面構成を定義)
	Windows(){
		//setVisible(true);															//フレーム有効化(定型文として覚える)　※
		addWindowListener(new WinAda());
	}

	//ウィンドウが閉じられた時の処理(クラス名以外、定型文)いらない・・・・
	class WinAda extends WindowAdapter {
	    public void windowActivated(WindowEvent e) {
	    }
	}
}
