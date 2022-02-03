var draggedEventIsAllDay;
var activeInactiveWeekends = true;
var clv =null;
function convertDate(date) {
    var date = new Date(date);
    console.log(date.yyyymmdd());
}

function searchingHoliday(callback){

	clv=callback;
	var nowMonth = null;
	
	if( calendar!= null ){
		nowMonth = moment(calendar.fullCalendar('getDate').toDate()).format('YYYYMM');//calendar.fullCalendar('getDate').toDate().getFullYear().toString() + calendar.fullCalendar('getDate').toDate().getMonth()+1;
	} else {
		nowMonth = moment($("#calendar").fullCalendar('getDate').toDate()).format("YYYYMM");//$("#calendar").fullCalendar('getDate').toDate().getFullYear().toString() + $("#calendar").fullCalendar('getDate').toDate().getMonth()+1;
	}
	
	$.ajax({
			 type : "POST"
		    , url : "/diligenceAndLazinessManagement/getWorkEventList.do"
		   , data : { "nowMonth" : nowMonth }
	   , dataType : "JSON"
        , success : function (data) {
		   	  	       var fixedDate = data.workEventList.map(function (array) {
		   	  	           if( array.allDay && array.start !== array.end) {
		   	  	               // 이틀 이상 AllDay 일정인 경우 달력에 표기시 하루를 더해야 정상출력
						       array.end = moment(array.end).add(1, 'days');
					           }
		   	  	           return array;
		                });
		                
		   	  	       callback(fixedDate);
		            }
		});//ajax end

}

function getDisplayEventDate(event) {

  var displayEventDate;

  if( event.allDay == false ){
    var startTimeEventInfo = moment(event.start).format('HH:mm');
    var endTimeEventInfo = moment(event.end).format('HH:mm');
    displayEventDate = startTimeEventInfo + " - " + endTimeEventInfo;
  } else {
    displayEventDate = "하루종일";
  }

  return displayEventDate;
}

function filtering(event) {
  var show_username = true;
  var show_type = true;

  var username = $('input:checkbox.filter:checked').map(function () {
    return $(this).val();
  }).get();
  var types = $('#type_filter').val();

  show_username = username.indexOf(event.frestcd) >= 0;

  if (types && types.length > 0) {
    if (types[0] == "all") {
      show_type = true;
    } else {
      show_type = types.indexOf(event.type) >= 0;
    }
  }

  return show_username && show_type;
}

function calDateWhenResize(event) {

  var newDates = {
    startDate: '',
    endDate: ''
  };

  if (event.allDay) {
    newDates.startDate = moment(event.start._d).format('YYYY-MM-DD');
    newDates.endDate = moment(event.end._d).subtract(1, 'days').format('YYYY-MM-DD');
  } else {
    newDates.startDate = moment(event.start._d).format('YYYY-MM-DD HH:mm');
    newDates.endDate = moment(event.end._d).format('YYYY-MM-DD HH:mm');
  }

  return newDates;
}

function calDateWhenDragnDrop(event) {
  // 드랍시 수정된 날짜반영
  var newDates = {
    startDate: '',
    endDate: ''
  }

  // 날짜 & 시간이 모두 같은 경우
  if(!event.end) {
    event.end = event.start;
  }

  //하루짜리 all day
  if (event.allDay && event.end === event.start) {
    console.log('1111')
    newDates.startDate = moment(event.start._d).format('YYYY-MM-DD');
    newDates.endDate = newDates.startDate;
  }

  //2일이상 all day
  else if (event.allDay && event.end !== null) {
    newDates.startDate = moment(event.start._d).format('YYYY-MM-DD');
    newDates.endDate = moment(event.end._d).subtract(1, 'days').format('YYYY-MM-DD');
  }

  //all day가 아님
  else if (!event.allDay) {
    newDates.startDate = moment(event.start._d).format('YYYY-MM-DD HH:mm');
    newDates.endDate = moment(event.end._d).format('YYYY-MM-DD HH:mm');
  }

  return newDates;
}

//fn_set_calendar
function fn_set_calendar(events){

	$('#calendar').fullCalendar({
		events: events,
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		editable: true,
		droppable: true, // this allows things to be dropped onto the calendar
		drop: function() {
			// is the "remove after drop" checkbox checked?
			if ($('#drop-remove').is(':checked')) {
			  // if so, remove the element from the "Draggable Events" list
			  $(this).remove();
			}
		}
	});
}


var calendar = $("#calendar").fullCalendar({
		height: "auto",//height를 설정하지 않으면 마지막주의 select event가 동작되지 않는다. 참조 : https://fullcalendar.io/docs/v3/height
		  //주말 숨기기 & 보이기 버튼
		customButtons : {
					       viewWeekends : {
					    	  				text : '주말'
					    	  			  , click : function(){
		    	  				  						activeInactiveWeekends ? activeInactiveWeekends = false : activeInactiveWeekends = true;
												        $('#calendar').fullCalendar('option', {
												        	weekends: activeInactiveWeekends
												        });
					    	  			    }
					       }
  			            }//End customButtons
             , header : {
			              left : 'today, prevYear, nextYear'
			          , center : 'prev, title, next'
			           , right : 'month,listWeek'
			            }
              , views : {
			    	 		month: {
			    	 				columnFormat : 'dddd'
			    	 	    } 
			             , agendaWeek : {
			            	  		columnFormat : 'M/D ddd'
			            	  	   , titleFormat : 'YYYY년 M월 D일'
			            	  	    , eventLimit : false
			                }
			              , agendaDay : {
			            	  	    columnFormat : 'dddd'
			            	  	    , eventLimit : false
			                }
			              , listWeek : {
			            	  		columnFormat : ''
			              			  }
			             } // End views
                //일정 받아옴
               , events : function (start, end, timezone, callback) {
            	  		    searchingHoliday(callback);
            	  		    
                          }
              
           , viewRender : function(view, element) {
            	
            	if(calendar!= null){
            		console.log( calendar.fullCalendar('getDate').toDate() );
            		//var b = $('#calendar').fullCalendar('getDate');
            		//alert(b.format('L'));
            	}
            }
            	
  , eventAfterAllRender : function (view, callback) {
	 
                          	if( view.name == "month" ){
                          		$(".fc-content").css('height', 'auto');
                          	}
                          	
                          	//날짜에 a tag > span tag 로 변경
                          	var $da = $(".fc-day-top a");
	                  		$da.replaceWith( function() {
	                  		    				return $('<span/>', {
	                  		    									 class : 'fc-day-number'
	                  		    									, html : this.innerHTML
	                  		    				       });
	                  		});
                         }
           //일정 리사이즈
          , eventResize : function( event, delta, revertFunc, jsEvent, ui, view ){
        	   			        $('.popover.fade.top').remove();

        	   			       /** 리사이즈시 수정된 날짜반영
        	   			        * 하루를 빼야 정상적으로 반영됨. */
        	   			       var newDates = calDateWhenResize(event);
						
						    //리사이즈한 일정 업데이트
						    /*
						    $.ajax({
						             type : "get"
						            , url : ""
						           , data : {
						        	   			//id: event._id,
						            	 		//....
						             	    }
						        , success : function (response) {
						        alert('수정: ' + newDates.startDate + ' ~ ' + newDates.endDate);
						      }
						    });
						    */
						
						  }
	   , eventDragStart : function (event, jsEvent, ui, view) {
						    draggedEventIsAllDay = event.allDay;
						  }
		    //일정 드래그앤드롭
		    , eventDrop : function (event, delta, revertFunc, jsEvent, ui, view) {
			   			      $('.popover.fade.top').remove();
						
						      //주,일 view일때 종일 <-> 시간 변경불가
						      if( view.type === 'agendaWeek' || view.type === 'agendaDay' ){
						          if( draggedEventIsAllDay !== event.allDay ){
						    	      alert('드래그앤드롭으로 종일<->시간 변경은 불가합니다.');
						    		  location.reload();
						    		  return false;
						    	  }
						      }
						
						      // 드랍시 수정된 날짜반영
						      var newDates = calDateWhenDragnDrop(event);
						
						      //드롭한 일정 업데이트
						      /*
						          $.ajax({
						             type : "get"
						            , url : ""
						           , data : {
						        	   			//id: event._id,
						            	 		//....
						             	    }
						        , success : function (response) {
						        alert('수정: ' + newDates.startDate + ' ~ ' + newDates.endDate);
						      }
						    });
						     */
						
		                 }//End eventDrop
			  , select : function (startDate, endDate, jsEvent, view) {

				  			$("#standardDate").val(moment(startDate).format('YYYY-MM-DD'));
						    
				  			$(".fc-body").unbind('click');
						    $(".fc-body").on('click', 'td', function(e){
																$("#contextMenu")
																.addClass("contextOpened")
																.css({
																	display : "block"
																		/* contextMenu 위치 조정 by dgkim */
																		, left : e.pageX-100//, left : e.pageX-210
																		, top : e.pageY-50//, top : e.pageY-110
																		, zIndex : 9999
																	});
																return false;
															});
						    var today = moment();
						
						    if( view.name == "month" ){
						    	
						      startDate.set({
						           hours : today.hours()
						        , minute : today.minutes()
						      });
						      
						      startDate = moment(startDate).format('YYYY-MM-DD HH:mm');
						        endDate = moment(endDate).subtract(1, 'days');
						
						        endDate.set({
						             hours : today.hours() + 1
						          , minute : today.minutes()
						        });
						        endDate = moment(endDate).format('YYYY-MM-DD HH:mm');
						
						    } else {
						      startDate = moment(startDate).format('YYYY-MM-DD HH:mm');
						        endDate = moment(endDate).format('YYYY-MM-DD HH:mm');
						    }
						    
						    
						    //날짜 클릭시 카테고리 선택메뉴
						    var $contextMenu = $("#contextMenu");
						
						    $contextMenu.on("click", "a", function (e){
						    	e.preventDefault();
						
						        //닫기 버튼이 아닐때
						        if( $(this).data().role !== 'close' ){
						            newEvent(startDate, endDate, $(this).html());
						        }
						
						        $contextMenu.removeClass("contextOpened");
						        $contextMenu.hide();
						    });
						
						    $('body').on('click', function () {
						        $contextMenu.removeClass("contextOpened");
						        $contextMenu.hide();
						    });
						
						 }// End Select
  
	  		//이벤트 클릭시 수정이벤트
	      , eventClick : function (event, jsEvent, view) {
	                       editEvent(event);
	                     }
              , locale : 'ko'
            , timezone : "local"
    , nextDayThreshold : "09:00:00"
          , allDaySlot : true
    , displayEventTime : true
     , displayEventEnd : true
            , firstDay : 0 //월요일이 먼저 오게 하려면 1
         , weekNumbers : false
          , selectable : true
, weekNumberCalculation: "ISO"
          , eventLimit : true
               , views : { 
            			  month: { eventLimit: 12  }
                         }
     , eventLimitClick : 'week' //popover
            , navLinks : true
         //defaultDate : moment('2019-05'), //실제 사용시 삭제
          , timeFormat : 'HH:mm'
            , editable : false
             , minTime : '00:00:00'
             , maxTime : '24:00:00'
     , slotLabelFormat : 'HH:mm'
            , weekends : true
        , nowIndicator : true
    , dayPopoverFormat : 'MM/DD dddd'
      , longPressDelay : 0
  , eventLongPressDelay: 0
 , selectLongPressDelay: 0
 , defaultTimedEventDuration: '01:00:00'
});