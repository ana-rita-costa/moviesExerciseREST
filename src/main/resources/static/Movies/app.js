const movieList = document.getElementById('movie-list');
const dummyImage = 'https://via.placeholder.com/450x300'; // Replace with your dummy image URL

// Global array to store movies retrieved from the database
let moviesArray = [];
let selectedMovies = [];

// Load movies from the backend
function loadMovies() {
    const url = 'http://localhost:8080/api/movies'; // Replace with your backend URL

    sendRequest('GET', 'movies', {}).then((res) => {
        console.log(res);

        // Store the movies in the global array
        moviesArray = res.result;

        // Clear existing movies from the DOM
        movieList.innerHTML = '';

        // Render movies in the DOM
        moviesArray.forEach(movie => {
            const col = document.createElement("div");
            col.className = "col-md-4";

            col.innerHTML = `
                <div class="card movie-card" data-movie-id="${movie.id}">
                    <img src="${movie.base64Poster ? movie.base64Poster : dummyImage}"
                         class="card-img-top"
                         style="height: 450px; object-fit: cover;"
                         alt="${movie.title}">
                    <div class="card-body">
                        <h5 class="card-title">${movie.title}</h5>
                        <p class="card-text">Rating: ${movie.rank}</p>
                    </div>
                </div>
            `;

            movieList.appendChild(col);
        });

        addMovieSelectionListeners();
    });
}

function removeMovies() {

    selectedMovies.forEach(movie => {
        sendRequest('DELETE', 'movies/'+movie.id, {}).then((res) => {
                        console.log(res);

                    });
    });
}


// Add event listeners for selecting movies
function addMovieSelectionListeners() {
    const movieCards = document.querySelectorAll('.movie-card');
    movieCards.forEach(card => {
        card.addEventListener('click', () => {
            const movieId = card.getAttribute('data-movie-id');

            // Toggle selection
            card.classList.toggle('selected');
            if (card.classList.contains('selected')) {
                card.style.border = "2px solid green";

                // Add to selectedMovies
                const selectedMovie = moviesArray.find(movie => movie.id == movieId);
                selectedMovies.push(selectedMovie);
                console.log('Selected Movies:', selectedMovies);
            } else {
                card.style.border = "none";

                // Remove from selectedMovies
                selectedMovies = selectedMovies.filter(movie => movie.id != movieId);
                console.log('Selected Movies:', selectedMovies);
            }
        });
    });
}

// Add event listener for the "Create Movie" button
document.getElementById('create-movie').addEventListener('click', () => {
    alert('Redirecting to Create Movie form...');
    // Example: window.location.href = '/create-movie.html';
});

document.getElementById('remove-movie').addEventListener('click', () => {
    removeMovies();
});



// Call the function to load movies
loadMovies();