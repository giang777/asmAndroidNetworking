const socket = require('../socket/socket_server');

const getNotification= (notification)=>{
    //socket.io.emit("new msg",{notification});
    socket.io.emit("new msg",JSON.stringify(notification));
}

module.exports = {getNotification}