package �݌ɊǗ��V�X�e��;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

//����SQL�����p���C���N���X
public class In_SQL{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
    Connection conn;										//SQL�ڑ��C���X�^���X
	Statement st;												//SQL���s�p�C���X�^���X

	ResultSet rset;                                   			 //SQL���ʊi�[�p�C���X�^���X
	ResultSetMetaData rsmd;							//SQL�J�������擾�p�C���X�^���X	
	
	String selectSql;											//SQL�����i�[����
	public static int get_shocd = 0;					//���͂��ꂽ���i�b�c
	public static String get_shoname = "";		//���͂��ꂽ���i��
	public static int get_nyusu = 0;					//���͂��ꂽ�o�א�
	
	boolean string_check_ok;					//DB���ɑ��݂��Ȃ������񂪓��͂���Ă��Ȃ����`�F�b�N�p
	boolean int_check_ok;						//DB���ɑ��݂��Ȃ����i�b�c�����͂���Ă��Ȃ����`�F�b�N�p
	
    In_SQL() throws SQLException{
        // (1) �ڑ��p��URI��p�ӂ���(�K�v�ɉ����ĔF�؎w��user/password��t����)
    	String uri = "jdbc:postgresql://localhost:5432/sample";
        String user = "postgres";
        String password = "1818";
        
        // (2) DriverManager�N���X�̃��\�b�h�Őڑ�����
        conn = DriverManager.getConnection(uri,user,password);
        
      //SQL���s�p�C���X�^���X
        st = conn.createStatement();
    }
    
    //SQL���ʈꗗ�\���p���\�b�h 	********************************************************************
    public void getSql() throws SQLException {
        selectSql = "SELECT * FROM hatzaiko ORDER BY shocd ASC";
    	
    	intCheck();
    	if(int_check_ok) {
    		stringCheck();
    	}

        //���݂��Ȃ�������G���[���o�Ă��Ȃ����
    	if(string_check_ok && int_check_ok) {
            search();
    	}
        
        //���X�̃G���[���o�Ă��Ȃ����
    	if(Zaiko_Nyuuka.l_error.getText().equals("") && int_check_ok && string_check_ok) {
        	//�ꗗ�\�쐬
        	nyuuka();
    		conn.close();
    	}
    }
	//*****************************************************************************************
    //�ꗗ�\�쐬���\�b�h
	public void nyuuka() throws SQLException {
		st.executeUpdate(selectSql);
		Zaiko_Nyuuka.l_result.setText(get_nyusu+"�A���ׂ���܂���");
	}
    
    //���݂��Ȃ����i�b�c�����͂���Ă��Ȃ����`�F�b�N
    public void intCheck() throws SQLException {
    	//���݂��Ȃ����iCD�����͂���Ă��Ȃ����`�F�b�N
    	if(get_shocd!=0) {
        	rset = st.executeQuery(selectSql);
            while(rset.next()) {
            	//���͂��ꂽ���iCD�ƁASQL���ʂ̏��iCD����v����
            	if(rset.getString("shocd").equals(String.valueOf(get_shocd))) {
            		int_check_ok = true;
            		break;
            	}
            }
            if(!int_check_ok) {
            	Zaiko_Nyuuka.l_error.setText("���݂��Ȃ����i�b�c�����͂���Ă��܂��B");
            }
    	}else {
    		int_check_ok = true;
    	}
	}
    
     //���݂��Ȃ����i�������͂���Ă��Ȃ����`�F�b�N
    public void stringCheck() throws SQLException {
        //�����ȏ��i���������Ă��Ȃ����m�F����I�I
    	if(!get_shoname.equals("")) {
        	rset = st.executeQuery(selectSql);
            while(rset.next()) {
            	//���͂��ꂽ���i���ƁASQL���ʂ̏��i������v����
            	if(rset.getString("shoname").trim().equals(get_shoname.trim())) {
            		string_check_ok = true;
            		break;
            	}
            }
            if(!string_check_ok) {
            	Zaiko_Nyuuka.l_error.setText("���݂��Ȃ����i�������͂���Ă��܂��B");
            }
    	}else {
    		string_check_ok = true;
    	}
    }        
    
    //���͂��ꂽ���i������v���Ă��邩�`�F�b�N
    public void search() throws SQLException {
    	//���i�b�c�����͂���Ă���
    	if(get_shocd != 0) {
    		selectSql = "SELECT * FROM hatzaiko WHERE shocd="+get_shocd+"";
            //���i�������͂���Ă���
            if(!get_shoname.trim().equals("")) {
                rset = st.executeQuery(selectSql);
                while(rset.next()) {
                	//���͂��ꂽ���i�����ƁA���͂��ꂽ���i�b�c�ɑΉ����鏤�i������������v���Ă���
                	if(rset.getString("shoname").trim().equals(get_shoname)) {
                		selectSql = "UPDATE hatzaiko SET zaisu = zaisu + "+get_nyusu+" WHERE shocd="+get_shocd+"";
                		break;
                	}else {
                    	Zaiko_Nyuuka.l_error.setText("���i�b�c�Ə��i������v���܂���B");
                    	break;
                	}
                }
            }else {
            	selectSql = "UPDATE hatzaiko SET zaisu = zaisu + "+get_nyusu+" WHERE shocd="+get_shocd+"";
            }
         //���i���̂ݓ��͂���Ă���B
    	}else {
    		if(!get_shoname.trim().equals("")){
                rset = st.executeQuery(selectSql);
        		while(rset.next()) {
            		//���͂��ꂽ���i����DB��̏��i���ƈ�v���Ă���
                	if(rset.getString("shoname").trim().equals(get_shoname)) {
                		selectSql = "UPDATE hatzaiko SET zaisu = zaisu + "+get_nyusu+" WHERE shoname LIKE '%"+get_shoname+"%'";
                		break;
                	}
        		}
    		}else {
    			selectSql = "UPDATE hatzaiko SET zaisu = zaisu + "+get_nyusu+"";
    		}
    	}
    }
}