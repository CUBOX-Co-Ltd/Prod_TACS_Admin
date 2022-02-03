/* ****************
 *  일정 편집
 * ************** */
var editEvent = function (event, element, view) {

	$("#contextMenu").hide(); 					  //메뉴 숨김
	  
    $('#btn_del_event').data('standardDate', event.start._i);    //기준일자
    $('#txtStandardDate').val(event.start._i); 	  //기준일자
    $('#selWorkRestdayCd').val(event.fworkholcd); //근무휴일구분
    $('#selRestdayCd').val(event.frestcd);        //휴일구분
    $('#txtRestdayNm').val(event.frestnm);        //휴일명
    
    $('.popover.fade.top').remove();
    $(element).popover("hide");

    if( event.allDay === true ){
        editAllDay.prop('checked', true);
    } else {
        editAllDay.prop('checked', false);
    }

    if( event.end === null ){
        event.end = event.start;
    }

    if( event.allDay === true && event.end !== event.start ){
        editEnd.val(moment(event.end).subtract(1, 'days').format('YYYY-MM-DD HH:mm'))
    } else {
        editEnd.val(event.end.format('YYYY-MM-DD HH:mm'));
    }

    modalTitle.html('휴일 수정');
    editTitle.val(event.title);
    editStart.val(event.start.format('YYYY-MM-DD HH:mm'));
    editType.val(event.type);
    editDesc.val(event.description);
    editColor.val(event.backgroundColor).css('color', event.backgroundColor);

    addBtnContainer.hide();
    modifyBtnContainer.show();
    $("#eventModal").modal();
    $("#hidCrudCd").val("U");
    $("#btn_del_event").show();
    //eventModal.modal('show');

    //업데이트 버튼 클릭시
    $('#updateEvent').unbind();
    $('#updateEvent').on('click', function() {

        if( editStart.val() > editEnd.val() ){
            alert('끝나는 날짜가 앞설 수 없습니다.');
            return false;
        }

        if( editTitle.val() === '' ){
            alert('일정명은 필수입니다.')
            return false;
        }

        var statusAllDay;
        var startDate;
        var endDate;
        var displayDate;

        if( editAllDay.is(':checked') ){
            statusAllDay = true;
            startDate = moment(editStart.val()).format('YYYY-MM-DD');
            endDate = moment(editEnd.val()).format('YYYY-MM-DD');
            displayDate = moment(editEnd.val()).add(1, 'days').format('YYYY-MM-DD');
        } else {
            statusAllDay = false;
            startDate = editStart.val();
            endDate = editEnd.val();
            displayDate = endDate;
        }

        eventModal.modal('hide');

        event.allDay = statusAllDay;
        event.title = editTitle.val();
        event.start = startDate;
        event.end = displayDate;
        event.type = editType.val();
        event.backgroundColor = editColor.val();
        event.description = editDesc.val();

        $("#calendar").fullCalendar('updateEvent', event);

        //일정 업데이트 addEvent.js newevent 사용
        /*
        $.ajax({
            type: "get",
            url: "",
            data: {
                //...
            },
            success: function (response) {
                alert('수정되었습니다.')
            }
        });
        */

    });
};

// 삭제버튼
$('#btn_del_event').on('click', function () {

	eventModal.modal('hide');

	var eventDay = moment( $(this).data('standardDate') ).format('YYYYMMDD');
	var today = moment().format("YYYYMMDD");

	if( !moment(today).isBefore(eventDay, 'day') ){
		alert("지난 휴일 이벤트는 삭제할 수 없습니다.");
		return;
	}
	
	if( !confirm("휴일정보를 삭제하시겠습니까?") ){
		alert("취소되었습니다.");
		return;
	}
	
    //삭제시 처리부분
    $.ajax({
        type: "post",
        url: "/diligenceAndLazinessManagement/deleteWorkEvent.do",
        data: {
            "standardDate" : eventDay
        },
        success: function (data) {
            if( data.result==0 ){
            	$('#eventModal').modal('hide');
		    	calendar.fullCalendar('rerenderEvents');
		    	calendar.fullCalendar('refetchEvents');
            	alert('삭제되었습니다.');
            } else if( data.result==1 ){
            	alert('삭제처리가 되지않았습니다.\n다시 진행해주세요.');
            } else if( data.result==2 ){
            	alert('지난 휴일 이벤트는 삭제할 수 없습니다.');
            } else if( data.result==2 ){
            	alert('삭제대상 휴일이벤트를 조회되지 않았습니다.\n다시 진행해주세요.');
            	
            }
        }
    });

});