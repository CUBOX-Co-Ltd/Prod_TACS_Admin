$(function(){
	
	$('#srchRegdt_s').datetimepicker({
		timepicker:false,
		format:'Y-m-d'
	});
	
	$('#srchRegdt_e').datetimepicker({
		timepicker:false,
		format:'Y-m-d'
	});
	
	$('#srchVisitsdt').datetimepicker({
		timepicker:false,
		format:'Y-m-d'
	});
	
	$('#srchVisitedt').datetimepicker({
		timepicker:false,
		format:'Y-m-d'
	});
	
	$("#srchRegdt_s").change(function(){ 
		var srchCfsdt = $("#srchRegdt_s").val().replace(/\-/g,'');
		var srchCfedt = $("#srchRegdt_e").val().replace(/\-/g,'');
		if(srchCfsdt > srchCfedt && srchCfsdt != "" && srchCfedt != ""){
			alert("신청종료일보다 뒷 날짜를 선택할 수 없습니다.");
			$("#srchRegdt_s").val('');
			$("#srchRegdt_s").focus();
		}
	});
	
	$("#srchRegdt_e").change(function(){
		var srchCfsdt = $("#srchRegdt_s").val().replace(/\-/g,'');
		var srchCfedt = $("#srchRegdt_e").val().replace(/\-/g,'');
		if(srchCfsdt > srchCfedt && srchCfsdt != "" && srchCfedt != ""){
			alert("신청시작일보다 앞 날짜를 선택할 수 없습니다.");
			$("#srchRegdt_e").val('');
			$("#srchRegdt_e").focus();
		}
	}); 
	
	$("#srchVisitsdt").change(function(){ 
		var srchCfsdt = $("#srchVisitsdt").val().replace(/\-/g,'');
		var srchCfedt = $("#srchVisitedt").val().replace(/\-/g,'');
		if(srchCfsdt > srchCfedt && srchCfsdt != "" && srchCfedt != ""){
			alert("방문종료일보다 뒷 날짜를 선택할 수 없습니다.");
			$("#srchVisitsdt").val('');
			$("#srchVisitsdt").focus();
		}
	});
	
	$("#srchVisitedt").change(function(){
		var srchCfsdt = $("#srchVisitsdt").val().replace(/\-/g,'');
		var srchCfedt = $("#srchVisitedt").val().replace(/\-/g,'');
		if(srchCfsdt > srchCfedt && srchCfsdt != "" && srchCfedt != ""){
			alert("방문시작일보다 앞 날짜를 선택할 수 없습니다.");
			$("#srchVisitedt").val('');
			$("#srchVisitedt").focus();
		}
	});
	
});

function editVisitor(gb, str, bio, sta) {
	var $form = $("<form></form>");
	$form.attr("method", "post");
	$form.attr("target", "winVisitEditPopup");
	$form.attr("action", "/visitInfo/visitEditPopup.do");
	$form.appendTo("body");
	
	var vgb = $("<input type='hidden' id='pagegb' name='pagegb' value='"+gb+"'>");
	$form.append(vgb);	
	
	var vno = $("<input type='hidden' id='vno' name='vno' value='"+str+"'>");
	$form.append(vno);

	//console.log($form);
	
	var numWidth = 1170;
	var numHieght = 820;
	var numHieghtMin = 700;
		
	if(bio == 'N') numWidth = 900;
	
	if(!fnIsEmpty(sta)) {
		if(gb == "H") numHieght = numHieghtMin;
		else if(gb == "C" && sta == "C") numHieght = numHieghtMin;
		else if(sta == "T" || sta == "E" || sta == "R" || sta == "X" || sta == "Z") numHieght = numHieghtMin;
	}	

	openPopup('/visitInfo/visitEditPopup.do', 'winVisitEditPopup', numWidth, numHieght);
	$form.submit();
}
