<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
<head>
  <title><fmt:message key="title"/></title>
  <style>
    .error { color: red; }
  </style>  
</head>
<body>
<h1><fmt:message key="createloan.heading"/></h1>
<form:form method="POST" commandName="createloan">
  <table>
    <tr>
    	<td>Amount of Money: </td>
    	<td><form:input path="initialCapital"></form:input></td>
    </tr>
    <tr>
    	<td>Interest: </td>
    	<td><form:input path="interest"></form:input></td>
    </tr>
    <tr>
    	<td>Payment period: </td>
    	<td>
    		<form:select path="paymentPeriod">
    			<form:option value="NONE" label = "--select--"></form:option>
    			<form:options items="${paymentPeriod}"/>
    		</form:select>
    	</td>
    </tr>
    <tr>
    	<td>Calculation of fees:</td>
    	<td>
    		<form:select path="loanType">
    			<form:option value="French" label = "French"></form:option>
    			<form:options items="${loanType}"/>
    		</form:select>
    	</td>
    </tr>
    <tr>
    	<td>Number of fees: </td>
    	<td><form:input path="amortizationTime"></form:input></td>
    </tr>
  </table>
  <br>
  <input type="submit" value="Create">
  <br>
</form:form>
<a href="<c:url value="client.htm"/>">Home</a>
</body>
</html>