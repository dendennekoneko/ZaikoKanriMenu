package �݌ɊǗ��V�X�e��;

import java.awt.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//�E�B���h�E�̂ЂȌ^
public class Result extends Windows {
	
	private static final long serialVersionUID = 1L;
	
	Label l_1,l_2;
	
	List<String>  columns = new ArrayList<String>(Arrays.asList("Apple", "Orange", "Melon"));
	private String[] column = {"COUNTRY", "WIN", "LOST"};

	//�R���X�g���N�^(��ʍ\�����`)
	Result(){
		setTitle("�₢���킹�݌� �ꗗ�\��");
		setBounds(400,300,500,370);										//setBounds�ŕ\���ʒu�Ƒ傫���w��
		partsInitialize();																//�������@
		partsSet();																		//�������A
		partsLayout();																	//�������B
		
		DefaultTableModel tableModel = new DefaultTableModel(column, 0);
		JTable table = new JTable(tableModel);
		tableModel.addColumn(Zaiko.result_Column);

	    add(table); 
		
	}
	
	
	//�p�[�c�𐶐��A�����l��ݒ肷��
	public void partsInitialize() {												

	}
	
	public void partsSet() {														//�p�[�c�����ԂɃt���[���ɔz�u����
		
	}
	
	public void partsLayout() {												//�p�[�c�̔z�u���蓮�ݒ�
		
	}


}
