package �݌ɊǗ��V�X�e��;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//�݌Ƀ��j���[��ʁBWindows�N���X�̎q��
public class Zaiko_Menu extends Windows implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Button b_Search,b_Syukka,b_Nyuuka;															
	
	//�R���X�g���N�^(��ʍ\�����`)
	Zaiko_Menu(){
		setTitle("�݌Ƀ��j���[");
		setBounds(400,300,300,230);										//setBounds�ŕ\���ʒu�Ƒ傫���w��		
		getContentPane().setLayout(null);									//�p�[�c�z�u�ݒ�
		partsInitialize();																//�������@
		partsSet();																		//�������A
		partsLayout();																	//�������B
		listenerSet();																	//�������C
	}

	//�p�[�c�𐶐��A�����l��ݒ肷��
	public void partsInitialize() {												
		b_Search = new Button("�݌Ɍ���");
		b_Syukka = new Button("�o��");
		b_Nyuuka = new Button("����");
	}
	
	public void partsSet() {														//�p�[�c�����ԂɃt���[���ɔz�u����
		getContentPane().add(b_Search);
		getContentPane().add(b_Syukka);
		getContentPane().add(b_Nyuuka);
	}
	
	public void partsLayout() {												//�p�[�c�̔z�u���蓮�ݒ�
		b_Search.setBounds(65,30,150,20);
		b_Syukka.setBounds(65,70,150,20);
		b_Nyuuka.setBounds(65,110,150,20);
	}
	
	public void listenerSet() {													//���X�i�[�ݒ�p���\�b�h
		b_Search.addActionListener(this);
		b_Syukka.addActionListener(this);
		b_Nyuuka.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {					//�{�^���������ꂽ���̏���(ActionListener�̃I�o���C)
		if(e.getSource()==b_Search) {
			new Zaiko_Search();
		}else if(e.getSource()==b_Syukka) {
			new Zaiko_Syukka();
		}else if(e.getSource()==b_Nyuuka) {
			new Zaiko_Nyuuka();
		}
	}
}
