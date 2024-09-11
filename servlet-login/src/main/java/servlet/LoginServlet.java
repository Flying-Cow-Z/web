package servlet;

import bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String psw = req.getParameter("password");
        System.out.println(name+""+psw);
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

        User user = null;
        try {
            user = qr.query("SELECT * FROM `user` WHERE username=? AND `password`=?", new BeanHandler<>(User.class), name, psw);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (user == null){
            resp.getWriter().write("fail");
        }else {
            resp.getWriter().write("success");
        }
    }

        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);
        }
}

























