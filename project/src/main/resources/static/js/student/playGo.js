document.addEventListener('DOMContentLoaded', () => {
    const boardSize = 19; 
    const board = document.getElementById('goBoard');
    let currentPlayer = 'black';

    for (let i = 0; i < boardSize; i++) {
        const row = board.insertRow();
        for (let j = 0; j < boardSize; j++) {
            const cell = row.insertCell();
            cell.addEventListener('click', () => placeStone(cell));
        }
    }

    function placeStone(cell) {
        if (!cell.classList.contains('stone-black') && !cell.classList.contains('stone-white')) {
            cell.classList.add(`stone-${currentPlayer}`);
            currentPlayer = currentPlayer === 'black' ? 'white' : 'black';
        }
    }
});