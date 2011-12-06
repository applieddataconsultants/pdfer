<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Internal Error</title>
    </head>
    <body>
        <b>Ah snap! Your request caused a unhandled server error. </b>
        <br/><br/>
        <pre>
        ${actionBean.errormessage}
        </pre>
    </body>
</html>
