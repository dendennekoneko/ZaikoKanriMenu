package �݌ɊǗ��V�X�e��;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

//�E�B���h�E�̂ЂȌ^
public abstract class Result_Window extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Label l_1,l_2;
	static Label l_error;
	JTextField t_shocd;
	JTextField t_shoname;
	
	//�R���X�g���N�^(��ʍ\�����`)
	Result_Window(){
		setTitle("�₢���킹�݌� �ꗗ�\��");
		setBounds(400,300,500,370);										//setBounds�ŕ\���ʒu�Ƒ傫���w��
	    setBackground(new Color(140,236,235));					//�w�i�F�w��
		setVisible(true);															//�t���[���L����(��^���Ƃ��Ċo����)
		partsInitialize();																//�������@
		partsSet();																		//�������A
		partsLayout();																	//�������B
		addWindowListener(new WinAda());	
	}

	//�p�[�c�𐶐��A�����l��ݒ肷��
	public void partsInitialize() {												
		l_1 = new Label("���i�b�c");
		l_2 = new Label("���i��");

	}
	
	public void partsSet() {														//�p�[�c�����ԂɃt���[���ɔz�u����
		getContentPane().add(l_1);
		getContentPane().add(l_2);
	}
	
	public void partsLayout() {												//�p�[�c�̔z�u���蓮�ݒ�
		l_1.setBounds(50,30,50,20);
		l_2.setBounds(50,70,50,20);
	}
	
	
	
	
	//�E�B���h�E������ꂽ���̏���(�N���X���ȊO�A��^��)
	class WinAda extends WindowAdapter {
	    public void windowActivated(WindowEvent e) {
	        System.out.println("windowActivated");
	    }
	}
	
}
