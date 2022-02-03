$.datetimepicker.setLocale('ko');



$('#datetimepicker_mask').datetimepicker({
	mask:'9999/19/39 29:59',
});
$('#datetimepicker').datetimepicker();

$('#datetimepicker').datetimepicker({value:'2015/04/15 05:06'});

$('#datetimepicker1').datetimepicker({
	datepicker:false,
	format:'H:i',
	step:5
});

$('#startDatetimepicker').datetimepicker({
	format:'Y-m-d H:i'
});
$('#endDatetimepicker').datetimepicker({
	format:'Y-m-d H:i'
});

$('#startDate').datetimepicker({
	timepicker:false,
	format:'Y-m-d'
});
$('#endDate').datetimepicker({
	timepicker:false,
	format:'Y-m-d'
});
$('#expireDate').datetimepicker({
	timepicker:false,
	format:'Y-m-d'
});

$('#workDate').datetimepicker({
	timepicker:false,
	format:'Y-m'
});

$('#datetimepicker2_2').datetimepicker({
	timepicker:false,
	format:'Y/m/d'
});
$('#datetimepicker3_2').datetimepicker({
	timepicker:false,
	format:'Y/m/d'
});

$('#datetimepicker4').datetimepicker();
$('#open').click(function(){
	$('#datetimepicker4').datetimepicker('show');
});
$('#close').click(function(){
	$('#datetimepicker4').datetimepicker('hide');
});
$('#datetimepicker5').datetimepicker({
	datepicker:false,
	allowTimes:['12:00','13:00','15:00','17:00','17:05','17:20','19:00','20:00']
});
$('#datetimepicker6').datetimepicker();
$('#destroy').click(function(){
	if( $('#datetimepicker6').data('xdsoft_datetimepicker') ){
		$('#datetimepicker6').datetimepicker('destroy');
		this.value = 'create';
	}else{
		$('#datetimepicker6').datetimepicker();
		this.value = 'destroy';
	}
});