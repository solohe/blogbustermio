package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.ausiasmarch.bean.PostBean;

public class PostDao {

    Connection oConnection = null;

    public PostDao(Connection oConnection) {
        this.oConnection = oConnection;
    }

    public PostBean get(int id) throws SQLException {
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        String strSQL = "SELECT * FROM post WHERE id=?";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oPreparedStatement.setInt(1, id);
        oResultSet = oPreparedStatement.executeQuery(strSQL);
        PostBean oPostBean;
        if (oResultSet.next()) {
            oPostBean = new PostBean();
            oPostBean.setId(oResultSet.getInt("id"));
            oPostBean.setTitulo(oResultSet.getString("titulo"));
            oPostBean.setCuerpo(oResultSet.getString("cuerpo"));
            oPostBean.setEtiquetas(oResultSet.getString("etiquetas"));
            oPostBean.setFecha(oResultSet.getDate("fecha"));
        } else {
            oPostBean = null;
        }
        return oPostBean;
    }

    public Integer update(PostBean oPostBean) throws SQLException {

        PreparedStatement oPreparedStament = null;
        String strSQL = "";
        int iResult;

        strSQL = "UPDATE post ";
        strSQL += " SET ";
        strSQL += " titulo = " + oPostBean.getTitulo();
        strSQL += " cuerpo = " + oPostBean.getCuerpo();
        strSQL += " etiquetas = " + oPostBean.getEtiquetas();
        strSQL += " fecha = " + oPostBean.getFecha();
        strSQL += " WHERE id=?";

        PreparedStatement oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);

        oPreparedStament.setInt(1, oPostBean.getId());

        iResult = oPreparedStament.executeUpdate();

        return iResult;

    }
    
    public List<PostBean> getall() throws SQLException {
        
        List<PostBean> lPostBean = new  ArrayList<PostBean>();
        String strSQL = "SELECT * FROM post";
        PreparedStatement oPreparedStatement;
        ResultSet oResultSet;
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oResultSet = oPreparedStatement.executeQuery(strSQL);
        PostBean oPostBean;
        if (oResultSet.next()) {
            oPostBean = new PostBean();
            oPostBean.setId(oResultSet.getInt("id"));
            oPostBean.setTitulo(oResultSet.getString("titulo"));
            oPostBean.setCuerpo(oResultSet.getString("cuerpo"));
            oPostBean.setEtiquetas(oResultSet.getString("etiquetas"));
            oPostBean.setFecha(oResultSet.getDate("fecha"));
            
            lPostBean.add(oPostBean);
        } else {
            oPostBean = null;
        }
        
        return lPostBean;
          
    }
    
    public Integer remove(int id) throws SQLException {
        PreparedStatement oPreparedStatement;
        int iResult;
        String strSQL = "DELETE FROM post WHERE id=?";
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        oPreparedStatement.setInt(1, id);
        iResult = oPreparedStatement.executeUpdate();
        
        return iResult;
    }

    public Integer insert(PostBean oPostBean) throws SQLException {

        PreparedStatement oPreparedStament = null;
        String strSQL = "";
        int iResult;

        strSQL = "INSERT INTO post ";
        strSQL += " (id, titulo, cuerpo, etiquetas, fecha) ";
        strSQL += " VALUES (" + oPostBean.getId() + ", " 
                + oPostBean.getTitulo() + ", "
                + oPostBean.getCuerpo() + ", " 
                + oPostBean.getEtiquetas() + ", " 
                + oPostBean.getFecha() + " )";
 

        PreparedStatement oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);

        iResult = oPreparedStament.executeUpdate(strSQL);

        return iResult;

    }
    
    public Integer getcount() throws SQLException {
        
        ResultSet iResult;
        String strSQL = " SELECT * FROM post ";
        PreparedStatement oPreparedStatement;
        
        oPreparedStatement = oConnection.prepareStatement(strSQL);
        //oPreparedStatement.addBatch();
        iResult = oPreparedStatement.executeQuery();
        //se mueve por las filas
        iResult.last();
        //devuelve la posición actual del cursor
        int count = iResult.getRow();
        System.out.print(count);
        
        return count;
          
    }
}
