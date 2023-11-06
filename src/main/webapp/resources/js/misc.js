function getZoneOffsetSeconds() {
    const currentDate = new Date();
    return -currentDate.getTimezoneOffset() * 60;
}

function miscMain() {
    const zoneInput = document.getElementById("zone")
    const zoneOffset = getZoneOffsetSeconds()
    zoneInput.value = zoneOffset
    console.log(`Zone set to ${zoneOffset}.`)
}