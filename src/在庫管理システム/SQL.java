package �݌ɊǗ��V�X�e��;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//SQL�����p���C���N���X
public class SQL{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
    Connection conn;										//SQL�ڑ��C���X�^���X
	Statement st;												//SQL���s�p�C���X�^���X

	ResultSet rset;                                   			 //SQL���ʊi�[�p�C���X�^���X
	ResultSetMetaData rsmd;							//SQL�J�������擾�p�C���X�^���X	
	
    SQL() throws SQLException{
        // (1) �ڑ��p��URI��p�ӂ���(�K�v�ɉ����ĔF�؎w��user/password��t����)
    	String uri = "jdbc:postgresql://localhost:5432/sample";
        String user = "postgres";
        String password = "1818";
        
        // (2) DriverManager�N���X�̃��\�b�h�Őڑ�����
        conn = DriverManager.getConnection(uri,user,password);
        
      //SQL���s�p�C���X�^���X
        st = conn.createStatement();
    }
}