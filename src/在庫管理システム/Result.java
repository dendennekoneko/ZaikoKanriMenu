package �݌ɊǗ��V�X�e��;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//�E�B���h�E�̂ЂȌ^
public class Result extends Result_Window {
	
	private static final long serialVersionUID = 1L;
	
	Label l_1,l_2;
	
	List<String>  columns = new ArrayList<String>(Arrays.asList("Apple", "Orange", "Melon"));
	private String[] column = {"COUNTRY", "WIN", "LOST"};

	
	//�R���X�g���N�^(��ʍ\�����`)
	Result(){
		setTitle("�₢���킹�݌� �ꗗ�\��");
		setBounds(400,300,500,370);										//setBounds�ŕ\���ʒu�Ƒ傫���w��
	    setBackground(new Color(140,236,235));					//�w�i�F�w��
		setVisible(true);															//�t���[���L����(��^���Ƃ��Ċo����)
		partsInitialize();																//�������@
		partsSet();																		//�������A
		partsLayout();																	//�������B
		addWindowListener(new WinAda());	
		
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		JTable table = new JTable(tableModel);
		tableModel.addColumn(Zaiko.result_Column);

	    add(table); 
		
	}
	
	
	//�p�[�c�𐶐��A�����l��ݒ肷��
	public void partsInitialize() {												

	}
	
	public void partsSet() {														//�p�[�c�����ԂɃt���[���ɔz�u����
		getContentPane().add(l_1);
		getContentPane().add(l_2);
	}
	
	public void partsLayout() {												//�p�[�c�̔z�u���蓮�ݒ�
		l_1.setBounds(50,30,50,20);
		l_2.setBounds(50,70,50,20);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}
	
}
