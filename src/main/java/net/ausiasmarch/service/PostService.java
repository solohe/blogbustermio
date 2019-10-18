package net.ausiasmarch.service;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import net.ausiasmarch.bean.PostBean;
import net.ausiasmarch.bean.ResponseBean;
import net.ausiasmarch.connection.ConnectionInterface;
import net.ausiasmarch.dao.PostDao;
import net.ausiasmarch.factory.ConnectionFactory;
import net.ausiasmarch.setting.ConnectionSettings;

public class PostService {

    HttpServletRequest oRequest = null;

    public PostService(HttpServletRequest oRequest) {
        this.oRequest = oRequest;
    }

    public String get() throws SQLException {
        ConnectionInterface oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
        Connection oConection = oConnectionImplementation.newConnection();
        int id = Integer.parseInt(oRequest.getParameter("id"));
        PostDao oPostDao = new PostDao(oConection);
        PostBean oPostBean = oPostDao.get(id);
        Gson oGson = new Gson();
        String strJson = oGson.toJson(oPostBean);
        oConnectionImplementation.disposeConnection();
        return "{\"status\":200,\"response\":" + strJson + "}";
    }

    public String update() throws SQLException {
        ConnectionInterface oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
        Connection oConection = oConnectionImplementation.newConnection();
        PostBean oPostBean = new PostBean();
        Gson oGson = new Gson();
        oPostBean = oGson.fromJson(oRequest.getParameter("data"), PostBean.class);
        PostDao oPostDao = new PostDao(oConection);
        ResponseBean oResponseBean;
        if (oPostDao.update(oPostBean) == 0) {
            oResponseBean = new ResponseBean(500, "KO");
        } else {
            oResponseBean = new ResponseBean(200, "OK");
        };
        oConnectionImplementation.disposeConnection();
        return oGson.toJson(oResponseBean);
    }
    
    public String getall() throws SQLException {
        ConnectionInterface oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
        Connection oConection = oConnectionImplementation.newConnection();
        //int id = Integer.parseInt(oRequest.getParameter("id"));
        PostDao oPostDao = new PostDao(oConection);
        List<PostBean> lPostBean = oPostDao.getall();
        Gson oGson = new Gson();
        String strJson = oGson.toJson(lPostBean);
        oConnectionImplementation.disposeConnection();
        return "{\"status\":200,\"response\":" + strJson + "}";
    }

    public String remove() throws SQLException {
        ConnectionInterface oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
        Connection oConection = oConnectionImplementation.newConnection();
        int id = Integer.parseInt(oRequest.getParameter("id"));
       // PostBean oPostBean = new PostBean();
        PostDao oPostDao = new PostDao(oConection);
        Gson oGson = new Gson();
        int iDelete = oPostDao.remove(id);
        // oPostBean = oGson.fromJson(oRequest.getParameter("data"), PostBean.class);
        //PostDao oPostDao = new PostDao(oConection);
        ResponseBean oResponseBean;
        if (iDelete == 0) {
            oResponseBean = new ResponseBean(500, "KO");
        } else {
            oResponseBean = new ResponseBean(200, "OK");
        };
        oConnectionImplementation.disposeConnection();
        return oGson.toJson(oResponseBean);
    }
    
    public String insert() throws SQLException {
        ConnectionInterface oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
        Connection oConection = oConnectionImplementation.newConnection();
        PostBean oPostBean = new PostBean();
        Gson oGson = new Gson();
        oPostBean = oGson.fromJson(oRequest.getParameter("data"), PostBean.class);
        PostDao oPostDao = new PostDao(oConection);
        ResponseBean oResponseBean;
        if (oPostDao.insert(oPostBean) == 0) {
            oResponseBean = new ResponseBean(500, "KO");
        } else {
            oResponseBean = new ResponseBean(200, "OK");
        };
        oConnectionImplementation.disposeConnection();
        return oGson.toJson(oResponseBean);
    }
    
    public String getcount() throws SQLException {
        ConnectionInterface oConnectionImplementation = ConnectionFactory.getConnection(ConnectionSettings.connectionPool);
        Connection oConection = oConnectionImplementation.newConnection();
        
        Gson oGson = new Gson();
        int iresult;
        PostDao oPostDao = new PostDao(oConection);
        ResponseBean oResponseBean;
        iresult = oPostDao.getcount();
        String strJson = oGson.toJson(iresult);
        
        if (iresult == 0) {
            oResponseBean = new ResponseBean(500, "KO por aqui");
        } else {
           // oResponseBean = new ResponseBean(200, strJson);
           return "{\"status\":200,\"response\":" + strJson + "}";
        };
        oConnectionImplementation.disposeConnection();
        return oGson.toJson(oResponseBean);
    }
}
