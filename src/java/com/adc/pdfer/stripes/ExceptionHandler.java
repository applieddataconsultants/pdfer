
package com.adc.pdfer.stripes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.config.Configuration;



public class ExceptionHandler implements net.sourceforge.stripes.exception.ExceptionHandler {


    public void init(Configuration configuration) throws Exception { }

    public void handle(Throwable throwable,
                       HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

            throwable.printStackTrace();
            request.getRequestDispatcher("/error.jsp").forward(request, response);

    }
}