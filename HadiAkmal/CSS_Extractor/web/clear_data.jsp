
<%@page import="controller.Main"%>

<jsp:include page="menu.jsp"></jsp:include>

<%
    Main clear_data = new Main();
    boolean status = clear_data.deleteAll();
    
    if (status) {
        out.print("<br />Clear data success ...");
    } else {
        out.print("<br />Clear data fail!");
    }
    
%>