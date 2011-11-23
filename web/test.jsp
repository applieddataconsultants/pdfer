<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <s:form beanclass="com.adc.pdfer.FitAction">
        URL: <s:text name="url" size="60"></s:text><br/>
        html_head:<br/><s:textarea name="html_head" rows="10" cols="70"/><br/>
        html_inner:<br/><s:textarea name="html_inner" rows="20" cols="70"/><br/>
        html_foot:<br/><s:textarea name="html_foot" rows="10" cols="70"/><br/>
        options: <s:text name="options" size="60" value=""></s:text><br/>
        <s:submit name="submit">Submit</s:submit>
    </s:form>
    </body>
</html>
