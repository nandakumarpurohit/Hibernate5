package com.demo.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.HashMap;

/**
 * RequestLoggingFilter to log requests to MovieHut.com
 */

public class RequestLoggingFilter implements Filter {

    private ServletContext context;
    Connection con;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
        
        try{
        		Class.forName("com.mysql.cj.jdbc.Driver");
        		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feb2019", "root", "password");
        		
        } catch(Exception e) {
        		e.printStackTrace();
        }
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.context.log("TimeStamp :" + timestamp + " - IP Address" + req.getRemoteAddr());
        
        try{
        		PreparedStatement ps = con.prepareStatement("select * from currency_pairs");
        		ResultSet rs = ps.executeQuery();
        		HashMap map = new HashMap();
        		while(rs.next()) {
        			map.put(rs.getInt(1), rs.getString(2));
        		}
        		HttpSession session = req.getSession(true);
        		if(session.getAttribute("currencyPairs") == null) {
        			session.setAttribute("currencyPairs", map);
        			this.context.log("Currency pairs CACHED!");
        		}
        } catch(Exception e) {
        		e.printStackTrace();
        }
        
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    public void destroy() {
        //we can close resources here
    }

}