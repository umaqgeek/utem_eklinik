
<%@page import="controller.Main"%>

<jsp:include page="menu.jsp"></jsp:include>

<%

    Main load_data = new Main();
    boolean status = load_data.view();
    
    if (status) {
        out.print("<br />Load data success ...");
    } else {
        out.print("<br />Load data fail!");
    }
    
%>