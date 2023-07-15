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

const webcamVideo = document.getElementById('webcamVideo');
const callButton = document.getElementById('callButton');
const remoteVideo = document.getElementById('remoteVideo');
const respBtn = document.getElementById("responseButton")

const selectVideo = document.getElementById("selectVideo")
const selectAudio = document.getElementById("selectAudio")

const mediaDevices = await navigator.mediaDevices.enumerateDevices()
const videoInputs = [];
const audioInputs = [];
const audioOutputs = [];
let lastVideoInput = null;
let lastAudioInput = null;

navigator.mediaDevices.ondevicechange = (e) => {
    console.log("ondevicechange: ", e);
}

selectVideo.onchange = async ()  => {
    await updateLocalInput(videoInputs[selectVideo.selectedIndex], null);
}

selectAudio.onchange = async () => {
    await updateLocalInput(null, audioInputs[selectAudio.selectedIndex]);
}

mediaDevices.forEach(async d => {
    if (d.kind === 'videoinput') {
        const option = document.createElement("option");
        option.text = d.label;
        option.value = d.deviceId;
        selectVideo.appendChild(option);
        videoInputs.push(d);
    } else if (d.kind === 'audioinput') {
        const option = document.createElement("option");
        option.text = d.label;
        option.value = d.deviceId;
        selectAudio.appendChild(option);
        audioInputs.push(d);
    } else if (d.kind === 'audiooutput') {
        const option = document.createElement("option");
        option.text = d.label;
        option.value = d.deviceId;
        audioOutputs.push(d);
    }
})

await updateLocalInput(videoInputs[0], audioInputs[0])

// 打开摄像头，获取视频流
// selectVideo.selected
async function updateLocalInput(videoDeviceP, audioDeviceP) {
    const videoDevice = videoDeviceP ?? lastVideoInput;
    const audioDevice = audioDeviceP ?? lastAudioInput;
    localStream = await navigator.mediaDevices.getUserMedia({video: videoDevice, audio: audioDevice});
    localStream.getTracks().forEach((track) => {
        console.log("track: ", track);
        rtcPeerConnection.addTrack(track, localStream);
    });
    webcamVideo.srcObject = localStream
    lastVideoInput = videoDevice;
    lastAudioInput = audioDevice;
}

remoteStream = new MediaStream();
remoteVideo.srcObject = remoteStream
rtcPeerConnection.ontrack = (e) => {
    e.streams[0].getTracks().forEach(track => {
        remoteStream.addTrack(track);
    })
}

// 呼叫对方
// offer RTC 中表示呼叫对方
callButton.onclick = async () => {
    let offerCandidates = []
    rtcPeerConnection.onicecandidate = (event) => {
        if (event.candidate) {
            offerCandidates.push(event.candidate);
            localStorage.setItem("offerCandidates", JSON.stringify(offerCandidates));
            console.log(localStorage.getItem("offerCandidates"));
        }
    }

    const offer = await rtcPeerConnection.createOffer();
    await rtcPeerConnection.setLocalDescription(offer);
    console.log("offer = ", offer);
    localStorage.setItem("offer", JSON.stringify(offer));
}

// 建立连接
respBtn.onclick = async () => {
    const answer = JSON.parse(localStorage.getItem("answer"));
    await rtcPeerConnection.setRemoteDescription(answer);
    console.log("answer = ", answer);

    const answerCandidates = JSON.parse(localStorage.getItem("answerCandidates"));
    answerCandidates.forEach(offer => rtcPeerConnection.addIceCandidate(offer));
}