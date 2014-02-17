function handleNewOperationRequest(xhr, status, args) {
    if (args.correct) {
        location.href = '/AhorcadoWebOSApp/faces/co/edu/udea/os/ahorcado/web/recordedsuccessful.xhtml';
    }
}