$(function() {
	$(".custom-file input").change(function(e) {
        for (var o = [], t = 0; t < $(this)[0].files.length; t++)
            o.push($(this)[0].files[t].name);
        $(".custom-file-label .custom-file-fileName").html(o.join(", "))
    });

	// img tag에서 미리보기
	$("#customFile").change(function(e) {
		$("#imageType").val("file");
		document.querySelector("#myCanvas").style.display = "none";
		document.querySelector("#imgView").style.display = "block";

		if(e.target.files && e.target.files[0]) {
			var fileName = e.target.files[0].name;
			var fileExt = fileName.slice(fileName.lastIndexOf(".")+1).toLowerCase();
			if($.inArray(fileExt, ['jpg','jpeg','bmp','wbmp','png','gif']) == -1) {
				alert("jpg, jpeg, bmp, wbmp, png, gif 포맷만 사용 가능합니다.");
				//location.reload();
				$("#customFileNm").val("");
				$("#customFile").val("");
			    $("#imageType").val("");
			    $("#imgView").attr("src", "/images/no-img-user.jpg");
			} else {
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#imgView").attr("src", e.target.result);
				}
				reader.readAsDataURL(e.target.files[0]);
			}
		}
	});
});

function fnDetectPop () {
	openPopup('/visitInfo/objDetectPopup.do','detecting', 640, 870); // 방문신청
}


