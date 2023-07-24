$(document).ready(function() {
  $("#btnSearch").on("click", function() {
    var searchTerm = $("#ibxMovieNmSearch").val();

    // AJAX 요청
    $.ajax({
      url: "/movies/search", // 서버 URL
      method: "POST",
      data: { searchWord: searchTerm }, // 검색어를 데이터로 전송
      success: function(response) {
        // 서버에서 응답받은 HTML을 검색 결과를 표시할 공간에 추가하여 렌더링
        $("#movieList").html(response);
      },
      error: function(error) {
        console.log("Error occurred:", error);
      }
    });
  });
});