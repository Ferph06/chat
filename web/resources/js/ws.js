M.AutoInit();

const msjobj = {
    userId: '',
    mensaje: ''
};
msjobj.userId = user;
let url = window.location.href.split('salas/')[0].replace('http', 'ws');
const ws = new WebSocket(url + 'chat/' + user);

ws.onmessage = (event) => {
    console.log(event);
    let data = JSON.parse(event.data);
    if (data.mensaje.includes('Conectado') || data.mensaje.includes('Desconectado')) {
        document.querySelector('#txt').innerHTML += '<br /><div class=\"col m5 chip\"> <div class=\"col m12\">EL usuario ' + data.userId + ' se ha ' + data.mensaje + '</div></div><br />';
    } else {
        let div = document.createElement('div');
        let contenedor = document.createElement('div');
        div.className = "col m12 ";
        contenedor.className = data.userId.includes(user) ? " chip col m5" : "chip col m5 offset-m3";
        contenedor.style.height = "auto";
        //div.style.height = "auto";
        contenedor.innerHTML = data.userId + " dice : " + data.mensaje;
        div.appendChild(contenedor);
        console.log(div);
        document.querySelector('#txt').appendChild(div);
    }
};

console.log(ws);
const enviarMensaje = function () {
    console.log(document.querySelector('#cajamensaje').value);
    if (document.querySelector('#cajamensaje').value !== "" && document.querySelector('#cajamensaje').value !== undefined) {
        msjobj.mensaje = document.querySelector('#cajamensaje').value;
        ws.send(JSON.stringify(msjobj));
        document.querySelector('#cajamensaje').value = "";
    } else {
        M.toast({html: 'No se pueden enviar mensajes vacios', outDuration: 200});
    }

};
document.onkeypress = function (e) {
    if (parseInt(e.keyCode) === 13) {
        enviarMensaje();
    }
};
