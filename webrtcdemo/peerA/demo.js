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
const callButton = document.getElementById('callButton');
const remoteVideo = document.getElementById('remoteVideo');
const respBtn = document.getElementById("responseButton")
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
        })
    }

    webcamVideo.srcObject = localStream
    remoteVideo.srcObject = remoteStream
}

// 2. Create an offer
callButton.onclick = async () => {
    console.log("call button click")
    let offerCandidates = []
    pc.onicecandidate = (event) => {
        if (event.candidate) {
            offerCandidates.push(event.candidate)
            localStorage.setItem("offerCandidates", JSON.stringify(offerCandidates));
            console.log(localStorage.getItem("offerCandidates"))
        }
    }
    const offer = await pc.createOffer();
    await pc.setLocalDescription(offer);
    localStorage.setItem("offer", JSON.stringify(offer))
    console.log("offer desc set successfully! ", offer)
}

respBtn.onclick = async () => {
    const answer = JSON.parse(localStorage.getItem("answer"))
    console.log(answer)
    await pc.setRemoteDescription(answer)
    console.log("respond to answer")

    const answerCandidates = JSON.parse(localStorage.getItem("answerCandidates"))
    answerCandidates.forEach(answer => pc.addIceCandidate(answer))
}