// Desenvolvido por Daniella Silva - Ciência da Computação - PUC Minas - 2025

package dao;

import model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends DAO {    
    public StudentDAO() {
        super();
        abrirConexao();
    }
    
    public void finalize() {
        fecharConexao();
    }
    
    public boolean inserir(Student estudante) {
        boolean status = false;
        
        try {
            String sql = "INSERT INTO students (name, email, phone_number, enrollment_date, course) "
                       + "VALUES (?, ?, ?, ?, ?);";
            
            PreparedStatement st = conexao.prepareStatement(sql);
            
            st.setString(1, estudante.getNome());
            st.setString(2, estudante.getEmail());
            st.setString(3, estudante.getTelefone());
            st.setDate(4, Date.valueOf(estudante.getDataMatricula()));
            st.setString(5, estudante.getCurso());
            st.executeUpdate();
            st.close();
            
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        
        return status;
    }

    public Student obter(int id) {
        Student estudante = null;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            String sql = "SELECT * FROM students WHERE id=" + id;
            
            ResultSet rs = st.executeQuery(sql);    
            
            if (rs.next()) {            
                estudante = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getDate("enrollment_date").toLocalDate(),
                    rs.getString("course")
                );
            }
            
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return estudante;
    }
    
    public List<Student> obter() {
        return obter("");
    }

    public List<Student> obterOrdenadoPorID() {
        return obter("id");        
    }

    public List<Student> obterOrdenadoPorNome() {
        return obter("name");        
    }
    
    private List<Student> obter(String ordenarPor) {
        List<Student> estudantes = new ArrayList<Student>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            String sql = "SELECT * FROM students" + ((ordenarPor.trim().length() == 0) ? "" : (" ORDER BY " + ordenarPor));
            
            ResultSet rs = st.executeQuery(sql);   
            
            while (rs.next()) {                    
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getDate("enrollment_date").toLocalDate(),
                    rs.getString("course")
                );
                
                estudantes.add(s);
            }
            
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return estudantes;
    }
    
    public List<Student> obterOrdenadoPorCurso() {
        List<Student> estudantes = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY course";

        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student estudante = new Student();
                estudante.setId(rs.getInt("id"));
                estudante.setNome(rs.getString("name"));
                estudante.setCurso(rs.getString("course"));
                estudante.setDataMatricula(rs.getDate("enrollment_date").toLocalDate());
                estudantes.add(estudante);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudantes;
    }
    
    public boolean atualizar(Student estudante) {
        boolean status = false;
        
        try {  
            String sql = "UPDATE students SET name = ?, email = ?, phone_number = ?, "
                       + "enrollment_date = ?, course = ? WHERE id = ?";
            
            PreparedStatement st = conexao.prepareStatement(sql);
            
            st.setString(1, estudante.getNome());
            st.setString(2, estudante.getEmail());
            st.setString(3, estudante.getTelefone());
            st.setDate(4, Date.valueOf(estudante.getDataMatricula()));
            st.setString(5, estudante.getCurso());
            st.setInt(6, estudante.getId());
            st.executeUpdate();
            
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        
        return status;
    }
    
    public boolean deletar(int id) {
        boolean status = false;
        
        try {  
            Statement st = conexao.createStatement();
            
            st.executeUpdate("DELETE FROM students WHERE id = " + id);
            
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        
        return status;
    }
}