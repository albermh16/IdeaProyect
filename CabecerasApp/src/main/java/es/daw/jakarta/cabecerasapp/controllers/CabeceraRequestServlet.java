package es.daw.jakarta.cabecerasapp.controllers;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/cabeceraReq")
public class CabeceraRequestServlet extends HttpServlet {
    private String message;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        // Información de la solicitud
        String metodoHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ipCliente = req.getRemoteAddr();
        String ipLocal = req.getLocalAddr();
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url = scheme + "://" + host + contextPath + servletPath;
        String url2 = scheme + "://" + ipLocal + ":" + port + contextPath + servletPath;


        req.setAttribute("metodoHttp", metodoHttp);
        req.setAttribute("requestUri", requestUri);
        req.setAttribute("requestUrl", requestUrl);
        req.setAttribute("contextPath", contextPath);
        req.setAttribute("servletPath", servletPath);
        req.setAttribute("ipCliente", ipCliente);
        req.setAttribute("ipLocal", ipLocal);
        req.setAttribute("port", port);
        req.setAttribute("scheme", scheme);
        req.setAttribute("host", host);
        req.setAttribute("url", url);
        req.setAttribute("url2", url2);

        req.getRequestDispatcher("cabeceraReq.jsp").forward(req, resp);

        }
    }

