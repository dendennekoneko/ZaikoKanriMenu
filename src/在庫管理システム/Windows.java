package �݌ɊǗ��V�X�e��;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

//�E�B���h�E�̂ЂȌ^
public abstract class Windows extends JFrame {
	
	private static final long serialVersionUID = 1L;

	//�R���X�g���N�^(��ʍ\�����`)
	Windows(){
		setVisible(true);															//�t���[���L����(��^���Ƃ��Ċo����)�@��
	}
	
	//�p�[�c�𐶐��A�����l��ݒ肷��
	public abstract void partsInitialize();
	
	//�p�[�c�����ԂɃt���[���ɔz�u����
	public abstract void partsSet();
	
	//�p�[�c�̔z�u���蓮�ݒ�
	public abstract void partsLayout();

	//�E�B���h�E������ꂽ���̏���(�N���X���ȊO�A��^��)
	class WinAda extends WindowAdapter {
	    public void windowActivated(WindowEvent e) {
	        System.out.println("windowActivated");
	    }
	}
}
