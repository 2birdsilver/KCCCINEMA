
/*동적 영화 리스트*/
const movieData = [
	{
		title: "lee is the best",
		genre: "액션"
	},
	{
		title: "developer",
		genre: "코미디"
	}
	// 추가적인 영화 데이터를 원하는 만큼 배열에 추가할 수 있습니다.
];

function createMovieElement(movie) {
	const li = document.createElement("li");
	li.className = "movie";
	div.innerHTML = `
        <img src="${movie.image}" alt="${movie.title}">
        <div>
          <strong class="title">${movie.title}</strong>
          <em class="genre">${movie.genre}</em>
        </div>
      `;
	return li;
}

const movieContainer = document.getElementById("movieContainer");
movieData.forEach((movie) => {
	const movieElement = createMovieElement(movie);
	movieContainer.appendChild(movieElement);
});