const servers = {
    iceServers: [
        {
            urls: ['stun:stun1.l.google.com:19302'],
        }
    ]
}


// TURN ... NAT
const rtcPeerConnection = new RTCPeerConnection(servers);
let localStream = null;
let remoteStream = null;

const webcamButton = document.getElementById('webcamButton');
const webcamVideo = document.getElementById('webcamVideo');
const remoteVideo = document.getElementById('remoteVideo');
const respBtn = document.getElementById("responseButton")
const answerBtn = document.getElementById('answerBtn');

// 打开摄像头，获取视频流
webcamButton.onclick = async () => {
    localStream = await navigator.mediaDevices.getUserMedia({video: true});
    remoteStream = new MediaStream();

    localStream.getTracks().forEach((track) => {
        rtcPeerConnection.addTrack(track, localStream);
    });

    rtcPeerConnection.ontrack = (e) => {
        e.streams[0].getTracks().forEach(track => {
            console.log("track: ", track)
            remoteStream.addTrack(track)
        })
    }

    webcamVideo.srcObject = localStream
    remoteVideo.srcObject = remoteStream
}

// 呼叫对方
// offer RTC 中表示呼叫对方
answerBtn.onclick = async () => {
    const answerCandidates = []
    rtcPeerConnection.onicecandidate = (event) => {
        if (event.candidate) {
            answerCandidates.push(event.candidate)
            localStorage.setItem("answerCandidates", JSON.stringify(answerCandidates));
        }
    }

    // 获取到对方发出呼叫的 Description，然后进行响应
    const offer = JSON.parse(localStorage.getItem("offer"));
    await rtcPeerConnection.setRemoteDescription(new RTCSessionDescription(offer));

    const answer = await rtcPeerConnection.createAnswer();
    await rtcPeerConnection.setLocalDescription(answer);

    localStorage.setItem("answer", JSON.stringify(answer));

    const offerCandidates = JSON.parse(localStorage.getItem("offerCandidates"));
    console.log(offerCandidates);
    offerCandidates.forEach(offer => rtcPeerConnection.addIceCandidate(offer));
}