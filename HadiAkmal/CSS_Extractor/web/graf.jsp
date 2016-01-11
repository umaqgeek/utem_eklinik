<%@page import="java.util.Properties"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.Graf"%>
<script src="Chart.js"></script>

<jsp:include page="menu.jsp"></jsp:include>
<br />
<ul>
    <li><a href="graf.jsp?type=1">Graf Chief Complaint</a></li>
    <li><a href="graf.jsp?type=2">Graf Diagnosis</a></li>
    <li><a href="graf.jsp?type=3">Graf Drug Treatment Order</a></li>
</ul>
<br />
	
<div style="width: 90%; height: 90%">
    <canvas id="canvas"></canvas>
</div>	

<%
int type = 1;
try {
    type = Integer.parseInt(request.getParameter("type"));
} catch (Exception e) {
    type = 1;
}
Graf graf = new Graf();
ArrayList<Properties> data = graf.getGrafAll(type);
String x_axis = "";
String y_axis = "";
for (int i = 0; i < data.size(); i++) {
    x_axis += "\"" + data.get(i).getProperty("x_axis") + "\",";
    y_axis += data.get(i).getProperty("y_axis") + ",";
}
%>

	<script>
	var barChartData = {
		labels : [<%=x_axis %>],
		datasets : [
			{
				fillColor : "rgba(220,220,220,0.7)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill: "rgba(220,220,220,0.75)",
				highlightStroke: "rgba(220,220,220,1)",
				data : [<%=y_axis %>]
			}
		]

	}
	window.onload = function(){
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myBar = new Chart(ctx).Bar(barChartData, {
			responsive : true
		});
	}

	</script>