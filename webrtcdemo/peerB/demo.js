const servers = {
    iceServers: [
        {
            urls: ['stun:stun1.l.google.com:19302'],
        },
    ],
    iceCandidatePoolSize: 10,
};

// Global State
const pc = new RTCPeerConnection(servers);
let localStream = null;
let remoteStream = null;

// HTML elements
const webcamButton = document.getElementById('webcamButton');
const webcamVideo = document.getElementById('webcamVideo');
const remoteVideo = document.getElementById('remoteVideo');
const hangupButton = document.getElementById('hangupButton');

// 1. Setup media sources
webcamButton.onclick = async () => {
    localStream = await navigator.mediaDevices.getUserMedia({video: true})
    remoteStream = new MediaStream()

    localStream.getTracks().forEach(track => {
        console.log("local stream get track")
        pc.addTrack(track, localStream)
    })

    pc.ontrack = (e) => {
        e.streams[0].getTracks().forEach(track => {
            console.log("pc on track: ", track)
            remoteStream.addTrack(track)
        });
    }

    webcamVideo.srcObject = localStream
    remoteVideo.srcObject = remoteStream
}

// export let getAnswerSuccessfully;

const answerBtn = document.getElementById('answerBtn');
console.log("answerBtn = ", answerBtn);
answerBtn.onclick = async () => {
    const offer = JSON.parse(localStorage.getItem("offer"))
    console.log(offer)
    const answerCandidates = [];
    pc.onicecandidate = (event) => {
        if (event.candidate) {
            answerCandidates.push(event.candidate);
            localStorage.setItem("answerCandidates", JSON.stringify(answerCandidates))
            console.log("answer candidates: ", localStorage.getItem("answerCandidates"))
        }

    }

    await pc.setRemoteDescription(new RTCSessionDescription(offer));

    const answerDescription = await pc.createAnswer();
    await pc.setLocalDescription(answerDescription);
    console.log("set answer local description successfully! ", answerDescription);
    // getAnswerSuccessfully(answerDescription)
    localStorage.setItem("answer", JSON.stringify(answerDescription))

    const offerCandidates = JSON.parse(localStorage.getItem("offerCandidates"))
    console.log(offerCandidates)
    offerCandidates.forEach(offer => pc.addIceCandidate(new RTCIceCandidate(offer)))

}