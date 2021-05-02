<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Report</title>
    <link rel="stylesheet" type="text/css" href="css/default.css">
    <link rel="stylesheet" type="text/css" href="css/crownix-viewer.min.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/crownix-viewer.min.js"></script>
</head>
<body style ="margin:0;height:100%">
	<div  id = "crownix-viewer" style ="width:100%;height:100%;"></div>
 	<script>
 		function createMRDParamInfo() {
 			var mrdCode;
 			var mrdParams =[];
 			var queryStr = decodeURIComponent(location.href).split("?")[1];
 			var paramArr = queryStr.split('&');
 			for (var i =0; i <paramArr.length ; i++) {
 				var query = paramArr[i];
 				var paramName = query.split('=')[0];
 				var paramValue = query.split('=')[1];
 				if (paramName == "requestCode") {
 					mrdCode = paramValue;
 				}else {
 					mrdParams.push(paramName + "[" + (!paramValue ? "" : paramValue ) + "]");
 				}
 			}
 			return {mrdCode:mrdCode, mrdParams:mrdParams ,mrdParamString:mrdParams.join(" ")}
 		}
 		
 		window.onload = function(){
 			var mrdParamInfo = createMRDParamInfo();
 			console.log("############  MRD 파라미터 정보#############")
 			console.log(mrdParamInfo);
 			//document.getElementById("mrdCode").innerText = mrdParamInfo.mrdCode;
 			//document.getElementById("params").innerText = '/rv ' + mrdParamInfo.mrdParamString;
 			var viewer = new m2soft.crownix.Viewer("http://52.1.111.65:8080/ReportingServer/service", "crownix-viewer");
 			viewer.openFile(mrdParamInfo.mrdCode + ".mrd", '/rv ' + mrdParamInfo.mrdParamString);
 			
 		}
 	</script>
</body>
</html>
