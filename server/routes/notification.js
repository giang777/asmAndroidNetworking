const express = require('express');
const router = express.Router();

const NotifiCTL = require("../controller/notification.controller");

router.get("/",NotifiCTL.getNotification);

module.exports = router;