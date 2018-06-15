<!DOCTYPE html>
<html>
	<meta charset="UTF-8">
	<head>
		<script>
			function answer(index,xx,answer){
				if(xx==answer){
						document.getElementById('jichu'+index).color="green";
				}else{
					document.getElementById('jichu'+index).color="red";
				}
		 }
		 function show(index){
		 		if(document.getElementById("analy"+index).style.display=='block'){
		 			document.getElementById("analy"+index).style.display='none';
		 		}else{
		 			document.getElementById("analy"+index).style.display='block';
		 		}
		 	}	
		</script>	
		<style>
			h2{
				}
		</style>
	</head>
	<body>
		<div style="margin-left:50px">
			<#list grid as s>
					<h3 style="word-wrap: break-word;">
					<font id="jichu${s_index}">${s_index}、${s.topic}</font>
					</h3>
				<input type="radio" name="${s_index}" onclick="answer(${s_index},'A','${s.answer}')"></input>A、${s.A}<br/>
				<input type="radio" name="${s_index}" onclick="answer(${s_index},'B','${s.answer}')"></input>B、${s.B}<br/>
				<input type="radio" name="${s_index}" onclick="answer(${s_index},'C','${s.answer}')"></input>C、${s.C}<br/>
				<input type="radio" name="${s_index}" onclick="answer(${s_index},'D','${s.answer}')"></input>D、${s.D}<br/>
				<button onclick="show(${s_index})">显示分析</button>
				<div id="analy${s_index}" style="display:none">
					<p>答案为：${s.answer}</p>
					<p style="word-wrap: break-word;">分析：${s.analy}</p>	
			 	</div>
			</#list>
		</div>
	</body>	
</html>