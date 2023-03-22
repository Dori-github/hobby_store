<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
<script>

window.addEventListener("load", function() {
	Swal.fire({
        imageUrl:'/image_bundle/icon.png',
        imageWidth:200,
        imageHeight:130,
        title:'${message}',
        showCancelButton: false,
        confirmButtonText: "확인",
        confirmButtonColor: "#FF4E02"
		}).then(function(){
			location.href='${url}';
        });
});
</script>