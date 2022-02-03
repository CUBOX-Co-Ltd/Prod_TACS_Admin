
var eventModal = $('#eventModal');

var modalTitle = $('.modal-title');
var editAllDay = $('#edit-allDay');
var  editTitle = $('#edit-title');
var  editStart = $('#edit-start');
var    editEnd = $('#edit-end');
var   editType = $('#edit-type');
var  editColor = $('#edit-color');
var   editDesc = $('#edit-desc');

var    addBtnContainer = $('.modalBtnContainer-addEvent');
var modifyBtnContainer = $('.modalBtnContainer-modifyEvent');


/* ****************
 *  새로운 일정 생성
 * ************** */
var newEvent = function (start, end, eventType) {

	//$('#eventModal').find('input, textarea').val('');
	$('#txtStandardDate').val("");
	$('#selWorkRestdayCd').val("");
	$('#selRestdayCd').val("");
	$('#txtRestdayNm').val("");
	
	console.log("addEvent.js 일정 등록");
	
    $("#contextMenu").hide(); //메뉴 숨김
    
    start = moment(start).format('YYYY-MM-DD');
    $("#txtStandardDate").val(start); //기준일자
    $("#hidCrudCd").val("C");	
    $("#btn_del_event").hide();
    
    modalTitle.html('휴일정보 등록');
    
    editStart.val(start);
    editEnd.val(end);
    editType.val(eventType).prop("selected", true);
    addBtnContainer.show();
    modifyBtnContainer.hide();
    $('#eventModal').modal('show');
    
    $("#account-editor-modal").modal();

    /******** 임시 RAMDON ID - 실제 DB 연동시 삭제 **********/
    var eventId = 1 + Math.floor(Math.random() * 1000);
    /******** 임시 RAMDON ID - 실제 DB 연동시 삭제 **********/

    //새로운 일정 저장버튼 클릭
    $('#btn_save_event').unbind();

};

/* modal이 아닌 popup으로 등록함으로 주석처리하고 해당 파일에서 등록하도록 수정 by dgkim */
/*$('#btn_save_event').on('click', function () {
	
	console.log("btn_save_event");
		
    var eventData = {
    				   standardDate : $('#txtStandardDate').val()
    				, workRestdayCd : $('#selWorkRestdayCd').val()
    				    , restdayCd : $('#selRestdayCd').val()
    				    , restdayNm : $('#txtRestdayNm').val()
    				       , crudCd : $('#hidCrudCd').val()
    };

    if (eventData.workRestdayCd === '') {
        alert('근무휴일구분을 선택해주세요.');
        return false;
    }
    if (eventData.restdayCd === '') {
    	alert('휴일구분을 선택해주세요.');
    	return false;
    }
    if (eventData.restdayNm === '') {
    	alert('휴일명을 입력해주세요.');
    	return false;
    }

    //DB에 넣을때(선택)
    eventData.standardDate = moment(eventData.standardDate).format('YYYYMMDD');

    //$("#calendar").fullCalendar('renderEvent', {}, true);
    $('#eventModal').find('input, textarea').val('');
    editAllDay.prop('checked', false);
    //eventModal.modal('hide');
    
    //새로운 일정 저장
    $.ajax({
        type : "post",
        url  : "/diligenceAndLazinessManagement/saveWorkEvent.do",
        data : eventData,
        dataType :'json', 
        success  : function( response ){
        	
        		       if( response.result=="Y" ){
        		    	   $('#eventModal').modal('hide');
        		    	   calendar.fullCalendar('rerenderEvents');
        		    	   calendar.fullCalendar('refetchEvents');
        		    	   alert("저장되었습니다.");
        		       }
        }
    });
});*/