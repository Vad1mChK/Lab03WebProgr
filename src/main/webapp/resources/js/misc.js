function getZoneOffsetSeconds() {
    const currentDate = new Date();
    return -currentDate.getTimezoneOffset() * 60;
}

document.addEventListener("DOMContentLoaded", () => {
    const zoneInput = document.getElementById("zone")
    zoneInput.value = getZoneOffsetSeconds()
})