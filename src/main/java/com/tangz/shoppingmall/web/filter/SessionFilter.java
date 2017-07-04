package com.tangz.shoppingmall.web.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 请求的uri
        String uri = request.getRequestURI();

        // uri中包含background时才进行过滤
        if (uri.indexOf(".do") != -1) {
            // 从session中获取登录者实体
            Object obj = request.getSession().getAttribute("user");
            if (null == obj) {
                // 如果session中不存在登录者实体，则弹出框提示重新登录
                /*
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                String loginPage = "....";
                StringBuilder builder = new StringBuilder();
                builder.append("<script type=\"text/javascript\">");
                builder.append("alert('网页过期，请重新登录！');");
                builder.append("window.top.location.href='");
                builder.append(loginPage);
                builder.append("';");
                builder.append("</script>");
                out.print(builder.toString());
                */

                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                try {
                    response.getWriter().write("{\"code\":-1}");   //返回json的code为-1表示没有登录
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果uri中不包含.do，则继续
            filterChain.doFilter(request, response);
        }
    }

}
