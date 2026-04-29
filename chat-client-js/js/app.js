let socket;
const chatDisplay = document.getElementById('chat-display');
const usernameInput = document.getElementById('username');
const messageInput = document.getElementById('message-input');
const sendBtn = document.getElementById('send-btn');
const connectBtn = document.getElementById('connect-btn');
const statusLabel = document.getElementById('status');

function connect() {
    const name = usernameInput.value.trim();
    if (!name) {
        alert("Por favor ingresa un nombre");
        return;
    }

    // Cambiar a la URL de tu servidor Quarkus
    socket = new WebSocket(`ws://localhost:8080/chat/${name}`);

    socket.onopen = () => {
        statusLabel.textContent = "En línea";
        statusLabel.className = "online";
        usernameInput.disabled = true;
        connectBtn.style.display = "none";
        messageInput.disabled = false;
        sendBtn.disabled = false;
    };

    socket.onmessage = (event) => {
        const msgPara = document.createElement('div');
        msgPara.className = 'message';
        msgPara.textContent = event.data;
        chatDisplay.appendChild(msgPara);
        chatDisplay.scrollTop = chatDisplay.scrollHeight;
    };

    socket.onclose = () => {
        statusLabel.textContent = "Desconectado";
        statusLabel.className = "offline";
        alert("Conexión perdida con el servidor.");
    };

    socket.onerror = (error) => {
        console.error("Error en WebSocket: ", error);
    };
}

function sendMessage() {
    const text = messageInput.value.trim();
    if (text && socket.readyState === WebSocket.OPEN) {
        socket.send(text);
        messageInput.value = "";
    }
}

// Eventos de botones
connectBtn.addEventListener('click', connect);
sendBtn.addEventListener('click', sendMessage);

// Enviar con la tecla Enter
messageInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') sendMessage();
});