const video = document.getElementById('video')

Promise.all([
  faceapi.nets.tinyFaceDetector.loadFromUri('/js/face-api/models'),
  faceapi.nets.faceLandmark68Net.loadFromUri('/js/face-api/models'),
  faceapi.nets.faceRecognitionNet.loadFromUri('/js/face-api/models'),
  faceapi.nets.faceExpressionNet.loadFromUri('/js/face-api/models')
]).then(startVideo)

function startVideo() {
  navigator.getUserMedia(
    { video: {} },
    stream => video.srcObject = stream,
    err => console.error(err)
  )
}

video.addEventListener('play', () => {
  const canvas = faceapi.createCanvasFromMedia(video)
  document.body.append(canvas)
  const displaySize = { width: video.width, height: video.height }
  faceapi.matchDimensions(canvas, displaySize)
  setInterval(async () => {
    const detections =  faceapi.detectAllFaces(video, new faceapi.TinyFaceDetectorOptions({
        inputSize: 256, // this line solves 'Box.constructor - expected box to be IBoundingBox | IRect, instead ...'
        scoreThreshold: 0.5,
      })).withFaceLandmarks().withFaceExpressions()
    const resizedDetections = faceapi.resizeResults(detections, displaySize)
    canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height)
    faceapi.draw.drawDetections(canvas, resizedDetections)
    faceapi.draw.drawFaceLandmarks(canvas, resizedDetections)
    faceapi.draw.drawFaceExpressions(canvas, resizedDetections)
  }, 100)
})