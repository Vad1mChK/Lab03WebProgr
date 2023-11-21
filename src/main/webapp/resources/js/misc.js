function getZoneOffsetSeconds() {
    const currentDate = new Date();
    return -currentDate.getTimezoneOffset() * 60;
}

function miscMain() {
    const serverZoneInput = document.getElementById("server-zone");
    const zoneInput = document.getElementById("zone");
    if (serverZoneInput && serverZoneInput.value === "") {
        const zoneOffset = getZoneOffsetSeconds();
        zoneInput.value = zoneOffset;
        console.log(`Zone set to ${zoneOffset}.`);

        jsf.ajax.request(zoneInput, null, {
            execute: 'zone',
            render: 'clock meme zone-select'
        });
    }
}