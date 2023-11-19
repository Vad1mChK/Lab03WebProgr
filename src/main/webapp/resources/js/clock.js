const CLOCK_UPDATE_PERIOD_MS = 6_000;

function updateClock() {
    jsf.ajax.request('clock', null, {
        render: 'clock'
    });
}

function clockMain() {
    updateClock();
    window.setInterval(updateClock, CLOCK_UPDATE_PERIOD_MS);
}
