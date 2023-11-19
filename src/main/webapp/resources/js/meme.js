function memeMain() {
    const memeImg = document.getElementById('meme');
    const blockingWall = document.getElementById('blocking-wall');

    if (memeImg && blockingWall) {
        memeImg.addEventListener('click', () => {
            // Toggle the 'centered' class on click
            memeImg.classList.toggle('centered');
            blockingWall.classList.toggle('disabled');
        });
    } else {
        console.error('Elements not found: #meme or #blocking-wall');
    }
}
