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
        Filename: <s:text name="file" size="30"></s:text>
        Format: 
        <s:select name="format">
            <s:option value="pdf" label="pdf"/>
            <s:option value="png" label="png"/>
        </s:select><br/>
        html_head:<br/><s:textarea name="html_head" rows="10" cols="70"/><br/>
        html_inner:<br/><s:textarea name="html_inner" rows="20" cols="70"/><br/>
        html_foot:<br/><s:textarea name="html_foot" rows="10" cols="70"/><br/>
        options: <s:text name="options" size="60" value=""></s:text><br/>
        --encoding UTF-8  (try this for PDF) <br/>
        --javascript-delay 5000 --no-stop-slow-scripts --crop-h 400 --crop-w 460 (for images) <br/>
        <s:submit name="submit">Submit</s:submit>
    </s:form>
    </body>
</html>
